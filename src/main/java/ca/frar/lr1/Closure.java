/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author edward
 */
public final class Closure extends ItemList {
    private final HashMap<Symbol, ArrayList<Rule>> rules;

    Closure(Parser parser, Rule rule) {
        this.rules = parser.rules;
        Item item = rule.getItem(0);        
        closure(item);
    }
    
    Closure(Parser parser, Item item) {
        this.rules = parser.rules;
        closure(item);
    }    
    
    Closure(Parser parser, List<Item> list) {
        this.rules = parser.rules;
        for (Item item : list) this.closure(item);
    }        

    void closure(Item item) {
        if (!this.contains(item)) this.add(item);
        if (item.dereference().isTerminal()) return;

        ArrayList<Rule> dRules = this.rules.get(item.dereference());
        for (Rule rule : dRules){
            Item item1 = rule.getItem(0);
            if (!this.contains(item1)){
                this.add(item1);
                closure(item1);
            }
        }
    }
}
