/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1parser;

import java.util.Objects;
import static ca.frar.lr1parser.Symbol.NULL;

/**
 *
 * @author edward
 */
class Item {
    final Rule rule;
    final int index;

    public Item(Rule rule, int index) {
        this.rule = rule;
        this.index = index;
    }
    
    public Symbol dereference(){
        if (this.index >= this.rule.rhs.size()) return NULL;
        return this.rule.rhs.get(this.index);
    }

    public Item increment(){
        if (this.index < this.rule.rhs.size()){
            return new Item(rule, index + 1);
        }
        return null;       
    }
    
    public boolean isFinal(){
        return this.index == this.size();
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();        

        if (index < rule.rhs.size()) {
            builder.append(rule.lhs).append(" :");
            for (int i = 0; i < rule.rhs.size(); i++) {
                if (i == index) builder.append(".");
                else builder.append(" ");
                builder.append(rule.rhs.get(i).getOrigin());
            }
        }

        if (index >= rule.rhs.size()) {
            builder.append(rule.lhs).append(" :");
            for (Symbol symbol : rule.rhs) {
                builder.append(" ").append(symbol.getOrigin());
            }
            builder.append(".");
        }

        return builder.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.rule);
        hash = 29 * hash + this.index;
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Item.class) return false;
        Item that = (Item) obj;
        if (!this.rule.equals(that.rule)) return false;
        return this.index == that.index;
    }    

    int size() {
        return rule.rhs.size();
    }
}
