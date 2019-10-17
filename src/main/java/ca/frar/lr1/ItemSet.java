/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author edward
 */
public abstract class ItemSet extends HashSet<Item>{
    
    @Override
    public boolean equals(Object obj){
        if (obj instanceof ItemSet == false) return false;
        ItemSet that = (ItemSet)obj;
        if (this.size() != that.size()) return false;
        for (Item item : this){
            if (! that.contains(item)) return false;
        }
        for (Item item : that){
            if (! that.contains(item)) return false;
        }        
        return true;
    }
    
}
