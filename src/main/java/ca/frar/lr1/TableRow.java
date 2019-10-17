/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Ed Armstrong
 */
class TableRow extends State{
    private final HashMap<Symbol, Action> actions = new HashMap<>();
    private final HashMap<Symbol, Integer> gotos = new HashMap<>();
    
    TableRow(int index){
        super(index);
    }
    
    TableRow(int index, Collection<Item> source){
        super(index, source);
    }
    
    void addAction(Symbol symbol, Action action){
        this.actions.put(symbol, action);
    }
    
    void addGoto(Symbol symbol, Integer state){
        this.gotos.put(symbol, state);
    }
    
    Action getAction(Symbol symbol){
        return this.actions.get(symbol);
    }
    
    Integer getGoto(Symbol symbol){
        return this.gotos.get(symbol);
    }
}
