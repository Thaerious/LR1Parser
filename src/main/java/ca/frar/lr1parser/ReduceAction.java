/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1parser;

import java.util.Objects;

/**
 *
 * @author Ed Armstrong
 */
public class ReduceAction extends Action{

    private final Rule rule;
    
    public ReduceAction(Rule rule){
        this.type = TYPE.REDUCE;
        this.rule = rule;
    }
    
    public Rule getRule(){
        return this.rule;
    }
    
    @Override
    public String toString(){
        return "r" + rule.index;
    }    
    
    @Override
    public boolean equals(Object obj){
        if (obj instanceof ReduceAction == false) return false;
        ReduceAction that = (ReduceAction) obj;
        return that.equals(this.rule);
    }    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.rule);
        return hash;
    }
}
