/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author edward
 */
public final class Closure extends ItemSet {
    private final HashMap<Symbol, HashSet<Rule>> rules;

    Closure(ParserBase parser, Rule rule) {
        this.rules = parser.getRules();
        Item item = rule.getItem(0);        
        closure(item);
    }
    
    Closure(ParserBase parser, Item item) {
        this.rules = parser.getRules();
        closure(item);
    }    
    
    Closure(ParserBase parser, List<Item> list) {
        this.rules = parser.getRules();
        for (Item item : list) this.closure(item);
    }        

    void closure(Item item) {
        this.add(item);
        if (item.dereference().isTerminal()) return;

        for (Rule rule : this.rules.get(item.dereference())){
            if (this.add(rule.getItem(0))) closure(rule.getItem(0));
        }
    }
}
