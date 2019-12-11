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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author edward
 */
public final class Closure extends ItemSet {
    private final HashMap<Symbol, HashSet<Rule>> rules;

    Closure(ParseTableBuilder parser, Rule rule) {
        this.rules = parser.getRules();
        Item item = rule.getItem(0);        
        closure(item);
    }
    
    Closure(ParseTableBuilder parser, Item item) {
        this.rules = parser.getRules();
        closure(item);
    }    
    
    Closure(ParseTableBuilder parser, List<Item> list) {
        this.rules = parser.getRules();
        for (Item item : list) this.closure(item);
    }        

    void closure(Item item) {
        this.add(item);
        if (item.dereference().isTerminal()) return;

        HashSet<Rule> possibleRules = this.rules.get(item.dereference());
        if (possibleRules == null){
            throw new UnknownProductionException(item.dereference());
        }
        
        for (Rule rule : possibleRules){
            try{
                if (this.add(rule.getItem(0))) closure(rule.getItem(0));
            } catch (UnknownProductionException ex){
                throw new UnknownProductionException(ex.symbol, rule);
            }
        }
    }
}
