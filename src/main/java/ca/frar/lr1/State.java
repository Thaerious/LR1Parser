/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author edward
 */
public class State extends ItemList{
    final int index;
   
    public State(int index){
        super();
        this.index = index;
    }   

    public State(int index, Collection<Item> source){
        super();
        this.index = index;
        this.addAll(source);
    }   
    
    /**
     * Return all unique items with a cursor in from of symbol.
     * @param symbol 
     */
    List<Item> getAllItems(Symbol symbol){
        ArrayList<Item> list = new ArrayList<>();
        for (Item item : this){
            if (item.dereference().equals(symbol) && !list.contains(item)){
                list.add(item);
            }
        }
        return list;
    }
}
