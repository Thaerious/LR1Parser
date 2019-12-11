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
import java.util.Objects;
import static ca.frar.lr1parser.Symbol.NULL;

/**
 * An item is a production with an extra annotation. A single symbol • indicating 
 * an “index” is added to the symbols on the right hand side, and can appear in 
 * any position.
 * @author edward
 */
class Item {
    final Rule rule;
    final int index;

    public Item(Rule rule, int index) {
        this.rule = rule;
        this.index = index;
    }
    
    /**
     * Retrieve the symbol immediately to the right of the index.
     * @return Symbol to immediate right of index, null if index is at the end.
     */
    public Symbol dereference(){
        if (this.index >= this.rule.rhs.size()) return NULL;
        return this.rule.rhs.get(this.index);
    }

    /**
     * If the index is not at the end, increase index by one.
     * @return 
     */
    public Item increment(){
        if (this.index < this.rule.rhs.size()){
            return new Item(rule, index + 1);
        }
        return null;       
    }
    
    /**
     * Try if the index is at the end of the RHS.  This indicates it is a terminal
     * item, and can produce a reduce action.
     * @return 
     */    
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
