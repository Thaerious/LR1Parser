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
public class Main {
    public static void main(String ... args){
        Parser parser = new Parser();
        Rule start = parser.addRule(new Rule("S' : S"));
        parser.addRule(new Rule("S : E"));
        Rule r = parser.addRule(new Rule("E : E x E"));
        parser.addRule(new Rule("E : z"));
        
        System.out.println(Arrays.toString(parser.nonterminals.toArray()));
        System.out.println(Arrays.toString(parser.terminals.toArray()));
        
//        Item item = new Item(r, 1);
//        System.out.println("-- " + item);
//        Closure closure = new Closure(parser, item);
//        for (Item i : closure) System.out.println("- " + i);
        
        
        ArrayList<State> buildStates = parser.buildStates(start);
        for (State state : buildStates){
            System.out.println("State " + state.index);
            for (Item item : state){
                System.out.println(item);
            }
            System.out.println();
        }

    }
}
