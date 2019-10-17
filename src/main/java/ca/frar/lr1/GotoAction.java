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
public class GotoAction extends Action{
    private final int state;
    
    GotoAction(int state){
        this.state = state;
        this.type = Action.TYPE.GOTO;        
    }
    
    public String toString(){
        return "g" + state;
    }
    
}
