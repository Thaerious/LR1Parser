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
import java.util.List;
import java.util.Objects;

/**
 */
public class Rule {
    private static int nextIndex = 1; /* 0 is for the added start rule S' */
    final Symbol lhs;
    final ArrayList<Symbol> rhs = new ArrayList<>();
    final int index;
    
    /**
     * Create a single rule from a comma delimitated RHS. The LHS must be a
     * non-terminal.
     * @param rhs
     * @param lhs
     */
    static List<Rule> parseRule(String rule) {
        ArrayList<Rule> list = new ArrayList<>();

        int splitIndex = rule.indexOf(":");
        String lhs = rule.substring(0, splitIndex).trim();
        String rhs = rule.substring(splitIndex + 1).trim();

        String[] split = rhs.split("[|]");
        for (String part : split) {
            list.add(new Rule(lhs, part.trim()));
        }

        return list;
    }

    public Rule(String rule, int index) {
        this.index = index;
        int splitIndex = rule.indexOf(":");
        this.lhs = new Symbol(rule.substring(0, splitIndex).trim());
        String rhs = rule.substring(splitIndex + 1).trim();
        
        String[] split = rhs.split("[ \t]+");
        
        for (String part : split) {
            part = part.trim();
            if (!part.isEmpty()) this.rhs.add(new Symbol(part));
        }
    }    
    
    public Rule(String rule) {
        this(rule, nextIndex++);
    }

    /**
     * Create a space delimitated RHS condition. No 'or' statements are
     * permitted at this point. Each word is either an intermediate non-terminal
     * or a terminal. non-terminals start with a capital, terminals start with a
     * lower case character. All rule items are otherwise considered case,
     * independent.
     *
     * @param rhs
     */
    Rule(String lhs, String rhs) {
        this.index = nextIndex++;
        this.lhs = new Symbol(lhs);
        String[] split = rhs.split("[ \t]+");
        for (String part : split) {
            part = part.trim();
            if (!part.isEmpty()) this.rhs.add(new Symbol(part));
        }
    }

    public Symbol getLHS(){
        return this.lhs;
    }
    
    public List<Symbol> getRHS(){
        return this.rhs;
    }    
    
    ArrayList<Item> getAllItems() {
        ArrayList<Item> list = new ArrayList<Item>();
        for (int i = 0; i <= rhs.size(); i++) {
            Item item = new Item(this, i);
            list.add(item);
        }
        return list;
    }

    /**
     * Return a new item (rule with cursor index) based on this rule with 
     * the given 'index'.
     * @param index
     * @return 
     */
    Item getItem(int index) {
        return new Item(this, index);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Rule.class) return false;
        Rule that = (Rule) obj;
        if (!that.lhs.equals(this.lhs)) return false;
        if (that.rhs.size() != this.rhs.size()) return false;
        for (int i = 0; i < this.rhs.size(); i++) {
            if (!that.rhs.get(i).getOrigin().equals(this.rhs.get(i).getOrigin())) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.lhs);
        hash = 17 * hash + Objects.hashCode(this.rhs);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(lhs).append(" : ");
        for (Symbol r : rhs) {
            builder.append(r.getOrigin()).append(" ");
        }
        return builder.toString();
    }
}
