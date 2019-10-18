/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1parser;

/**
 *
 * @author edward
 */
public class UnknownProductionException extends RuntimeException{
    public UnknownProductionException(Symbol symbol){
        super("No production found for symbol '" + symbol + "'");
    }
}
