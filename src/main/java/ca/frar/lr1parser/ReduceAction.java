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
public class ReduceAction extends Action{

    private final Rule rule;
    
    public ReduceAction(Rule rule){
        this.type = TYPE.REDUCE;
        this.rule = rule;
    }
    
    public Rule getRule(){
        return this.rule;
    }
    
    public String toString(){
        return "r" + rule.index;
    }    
    
}
