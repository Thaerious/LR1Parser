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
class Token {
    private String name;
    private char source;
    
    public Token(char name, char source){
        this.source = source;
    }
    
    /**
     * The name is used by the parser to determine the classification of the 
     * token.  This is case insensitive.
     * @return the name as used by the parser.
     */
    public String getName() {
        return name;
    }

    /**
     * The source is the original string.
     * @return the source
     */
    public char getSource() {
        return source;
    }    
}
