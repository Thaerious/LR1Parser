/*
 * Copyright (C) 2019 Ed Armstrong
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ca.frar.lr1parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Ed Armstrong
 */
public final class FollowSet extends HashMap<Symbol, HashSet<Symbol>> {

    private final HashMap<Symbol, HashSet<Rule>> rules;
    private final ParseTableBuilder parser;

    public FollowSet(ParseTableBuilder parser, Symbol startSymbol) {
        this.parser = parser;
        this.rules = parser.getRules();
        for (Symbol t : parser.nonterminals) {
            this.put(t, new HashSet<>());
        }
        this.add(startSymbol, Symbol.END);
        addFirsts();
        while (addFollows());
    }

    /**
     * Add symbol 'follow' to the follow set of 'symbol'.
     *
     * @param to
     * @param followSymbol
     */
    void add(Symbol symbol, Symbol followSymbol) {
        if (!this.containsKey(symbol)) {
            this.put(symbol, new HashSet<>());
        }
        HashSet<Symbol> follows = this.get(symbol);
        follows.add(followSymbol);
    }

    /**
     * Add symbol 'follow' to the follow set of 'symbol'.
     *
     * @param to
     * @param followSymbol
     */
    void addAll(Symbol symbol, Collection<Symbol> followSymbols) {
        for (Symbol s : followSymbols) {
            this.add(symbol, s);
        }
    }

    void addFirsts() {
        for (Symbol key : rules.keySet()) {
            for (Rule rule : rules.get(key)) {
                this.checkFirsts(rule);
            }
        }
    }

    private void checkFirsts(Rule rule) {
        for (int i = 1; i < rule.rhs.size(); i++) {
            Symbol a = rule.rhs.get(i - 1);
            Symbol b = rule.rhs.get(i);
            if (a.isTerminal()) {
                continue;
            }

            try {
                First first = new First(parser, b);
                first.remove(Symbol.EMPTY);
                this.addAll(a, first);
            } catch (UnknownProductionException ex) {
                throw new UnknownProductionException(ex.symbol, rule);
            }
        }
    }

    private boolean addFollows() {
        boolean found = false;

        for (Symbol key : rules.keySet()) {
            for (Rule rule : rules.get(key)) {
                if (this.checkFollows(rule)) {
                    found = true;
                }
            }
        }
        return found;
    }

    private boolean checkFollows(Rule rule) {
        boolean found = false;

        for (Symbol b : rule.rhs) {
            if (b.isTerminal()) {
                continue;
            }

            int i = rule.rhs.indexOf(b);

            if (i == rule.rhs.size() - 1) {
                HashSet<Symbol> followA = this.get(rule.lhs);
                HashSet<Symbol> followB = this.get(b);
                if (followB.addAll(followA)) {
                    found = true;
                }
            } else if (i < rule.rhs.size() - 1) {
                Symbol c = rule.rhs.get(i + 1);
                First first = new First(parser, c);

                if (first.contains(Symbol.EMPTY)) {
                    HashSet<Symbol> followA = this.get(rule.lhs);
                    HashSet<Symbol> followB = this.get(b);
                    if (followB.addAll(followA)) {
                        found = true;
                    }
                }
            }
        }
        return found;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Symbol key : this.keySet()) {
            HashSet<Symbol> get = this.get(key);
            builder.append(key).append(" = ").append(get.toString());
            builder.append("\n");
        }
        return builder.toString();
    }
}
