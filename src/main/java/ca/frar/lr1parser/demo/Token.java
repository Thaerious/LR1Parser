/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1parser.demo;

import ca.frar.lr1parser.IsToken;

/**
 *
 * @author Ed Armstrong
 */
public class Token <T> implements IsToken<T>{
    private final String name;
    private final T source;
    
    public Token(String name, T source){
        this.name = name;
        this.source = source;
    }
    
    /**
     * The name is used by the parser to determine the classification of the 
     * token.  This is case insensitive.
     * @return the name as used by the parser.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * The source is the original string.
     * @return the source
     */
    @Override
    public T getSource() {
        return source;
    }    
}
