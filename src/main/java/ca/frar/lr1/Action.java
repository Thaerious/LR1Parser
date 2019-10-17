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
abstract class Action {
    enum TYPE {SHIFT, REDUCE, ACCEPT}    
    TYPE type;
    
    TYPE getType(){
        return type;
    }
    
}
