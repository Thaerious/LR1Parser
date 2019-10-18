/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1parser;

/**
 *
 * @author Ed Armstrong
 */
public class ShiftAction extends Action{
    private final int destinationState;
    
    ShiftAction(int destinationState){
        this.destinationState = destinationState;
        this.type = TYPE.SHIFT;        
    }
    
    public int getDestinationState(){
        return destinationState;
    }
    
    public String toString(){
        return "s" + destinationState;
    }   
}
