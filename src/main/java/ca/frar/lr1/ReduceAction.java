/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1;

/**
 *
 * @author Ed Armstrong
 */
public class ReduceAction extends Action{

    private final int targetState;
    
    public ReduceAction(int targetState){
        this.type = TYPE.REDUCE;
        this.targetState = targetState;
    }
    
    public int getTargetState(){
        return this.targetState;
    }
    
    public String toString(){
        return "r" + targetState;
    }    
    
}
