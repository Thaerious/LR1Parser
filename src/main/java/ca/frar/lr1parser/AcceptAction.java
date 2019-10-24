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
public class AcceptAction extends Action{
    
    public AcceptAction(){
        this.type = TYPE.ACCEPT;
    }
    
    public String toString(){
        return "ac";
    }
  
    @Override
    public boolean equals(Object obj){
        return (obj instanceof AcceptAction);
    }      

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }
    
}
