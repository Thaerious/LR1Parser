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
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Ed Armstrong
 */
public class First extends ArrayList<Symbol> {
    private final HashMap<Symbol, HashSet<Rule>> rules;
    private final ParseTableBuilder parser;
    private final ArrayList<Symbol> done; /* first sets already looked at */
    
    public First(ParseTableBuilder parser, Symbol symbol) {
        this.parser = parser;
        this.rules = parser.getRules();
        this.done = new ArrayList<>();
        this.done.add(symbol);
        this.first(symbol);
    }
    
    private First(ParseTableBuilder parser, Symbol symbol, ArrayList<Symbol> done) {
        this.parser = parser;
        this.rules = parser.getRules();
        this.done = done;
        this.done.add(symbol);
        this.first(symbol);
    }    

    private void first(Symbol symbol) {
        System.out.println("first (" + symbol + ")");
        
        if (symbol.isTerminal()) {
            this.add(symbol);
        } else {
            HashSet<Rule> possibleRules = this.rules.get(symbol);
            
            if (possibleRules == null) {
                throw new UnknownProductionException(symbol);
            }

            for (Rule rule : rules.get(symbol)) {
                if (rule.rhs.isEmpty()) {
                    this.add(Symbol.EMPTY);
                } else {
                    addRHS(rule, symbol);
                }
            }
        }
    }

    private void addRHS(Rule rule, Symbol symbol) {
        for (Symbol s : rule.rhs) {
            if (this.done.contains(s)){
                break;
            }
            else if (s.isTerminal()) {
                this.add(s);
                break;
            } else {
                this.done.add(s);
                First firstS = new First(parser, s, this.done);
                this.addAll(firstS);
                if (!firstS.contains(Symbol.EMPTY)) {
                    break;
                }
            }
        }
    }
}
