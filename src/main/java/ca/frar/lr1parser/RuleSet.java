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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author edward
 */
public class RuleSet extends HashMap<Symbol, HashSet<Rule>>{
    
    public Collection<Rule> allRules(){        
        ArrayList<Rule> arrayList = new ArrayList<Rule>();
        
        for (Symbol key : this.keySet()){
            for (Rule rule : this.get(key)){
                arrayList.add(rule);
            }
        }
        
        Comparator<Rule> comparator = new Comparator<Rule>(){
            @Override
            public int compare(Rule o1, Rule o2) {
                return o1.index - o2.index;
            }
        };
        
        Collections.sort(arrayList, comparator);
        return arrayList;
    }
    
}
