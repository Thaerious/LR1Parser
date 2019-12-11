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

/**
 *
 * @author Ed Armstrong
 */
public class ReduceAction extends Action{
    private final Rule rule;
    
    public ReduceAction(Rule rule){
        this.type = TYPE.REDUCE;
        this.rule = rule;
    }
    
    public Rule getRule(){
        return this.rule;
    }
    
    @Override
    public String toString(){
        return "r" + rule.index;
    }    
    
    @Override
    public boolean equals(Object obj){
        if (obj instanceof ReduceAction == false) return false;
        ReduceAction that = (ReduceAction) obj;
        return that.equals(this.rule);
    }    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.rule);
        return hash;
    }
}
