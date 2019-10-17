/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author edward
 */
public class Main1 {
    public static void main(String ... args){
        Parser parser = new Parser();
        Rule start = parser.addRule(new Rule("S : N"));
        parser.addRule(new Rule("N : V = E"));
        parser.addRule(new Rule("N : E"));
        parser.addRule(new Rule("E : V"));
        parser.addRule(new Rule("E : "));
        parser.addRule(new Rule("V : x"));
        parser.addRule(new Rule("V : * E"));

        Follow f = new Follow(parser, new Symbol("E"));
        System.out.println(f);
        
    }
}
