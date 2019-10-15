/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class Rule {
    final String lhs;
    final ArrayList<Symbol> rhs = new ArrayList<>();

    /**
     * Create a single rule from a comma delimitated RHS. The LHS must be a
     * non-terminal.
     *
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

    Rule(String rule) {
        int splitIndex = rule.indexOf(":");
        this.lhs = rule.substring(0, splitIndex).trim();
        String rhs = rule.substring(splitIndex + 1).trim();
        
        String[] split = rhs.split("[ \t]+");
        
        for (String part : split) {
            part = part.trim();
            if (!part.isEmpty()) this.rhs.add(new Symbol(part));
        }
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
        this.lhs = lhs;
        String[] split = rhs.split("[ \t]+");
        for (String part : split) {
            part = part.trim();
            if (!part.isEmpty()) this.rhs.add(new Symbol(part));
        }
    }

    ArrayList<Item> getAllItems() {
        ArrayList<Item> list = new ArrayList<Item>();
        for (int i = 0; i <= rhs.size(); i++) {
            Item item = new Item(this, i);
            list.add(item);
        }
        return list;
    }

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
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(lhs).append(" : ");
        for (Symbol r : rhs) {
            builder.append(r.getOrigin()).append(" ");
        }
        builder.append("\n");
        return builder.toString();
    }
}
