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
public class GotoAction extends Action{
    private final int destinationState;
    
    GotoAction(int destinationState){
        this.destinationState = destinationState;
        this.type = Action.TYPE.GOTO;        
    }
    
    public int getDestinationState(){
        return destinationState;
    }
    
    public String toString(){
        return "g" + destinationState;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj instanceof GotoAction == false) return false;
        GotoAction that = (GotoAction) obj;
        return that.destinationState == this.destinationState;
    }    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.destinationState;
        return hash;
    }
}
