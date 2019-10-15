/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1;

import java.util.ArrayList;

/**
 *
 * @author edward
 */
public abstract class ItemList extends ArrayList<Item>{
    
    @Override
    public boolean equals(Object obj){
        if (obj instanceof ItemList == false) return false;
        ItemList that = (ItemList)obj;
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
