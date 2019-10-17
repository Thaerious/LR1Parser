/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Ed Armstrong
 */
public class Follow extends ArrayList<Symbol> {
    private final HashMap<Symbol, ArrayList<Rule>> rules;
    private final Parser parser;

    public Follow(Parser parser, Symbol symbol) {
        this.parser = parser;
        this.rules = parser.rules;
        this.follow(symbol);
    }

    private void follow(Symbol symbol) {
        System.out.println(symbol);
        for (Symbol key : rules.keySet()) {
            for (Rule rule : rules.get(key)) {                
                if (rule.rhs.contains(symbol)){
                    this.follow(rule, symbol);
                }
            }
        }
    }

    private void follow(Rule rule, Symbol symbol) {                
        System.out.println(rule);
        int i = rule.rhs.indexOf(symbol);
            if (i == rule.rhs.size() - 1 && !rule.lhs.equals(symbol)){                
                this.addAll(new Follow(parser, rule.lhs));
            } 
            else {
                Symbol s = rule.rhs.get(i + 1);
                First first = new First(parser, s);
                if (first.contains(Symbol.EMPTY)){
                    first.remove(Symbol.EMPTY);
                    this.addAll(new Follow(parser, rule.lhs));
                }
                this.addAll(first);
            }
    }
}
