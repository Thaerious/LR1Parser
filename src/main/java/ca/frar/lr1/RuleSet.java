/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1;

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
