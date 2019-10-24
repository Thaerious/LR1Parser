/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1parser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Ed Armstrong
 */
public class State extends ItemSet{
    final int index;
    private final HashMap<Symbol, Action> actions = new HashMap<>();
    
    State(int index){
        this.index = index;
    }
    
    State(int index, Collection<Item> source){
        this.index = index;
        this.addAll(source);
    }
    
    /**
     * Create a new symbol action association.  Return true if a value was 
     * replaced.
     * @param symbol
     * @param action
     * @return 
     */
    boolean addAction(Symbol symbol, Action action){
        Action previous = this.actions.put(symbol, action);
        return previous != null;
    }
    
    public Action getAction(Symbol symbol){
        return this.actions.get(symbol);
    }
    
    /**
     * Return all unique items with a cursor in front of symbol.
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

    boolean hasAction(Symbol symbol) {
        return this.actions.containsKey(symbol);
    }
}
