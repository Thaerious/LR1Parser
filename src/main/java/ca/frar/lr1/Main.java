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
        
        ArrayList<TableRow> buildStates = parser.buildStates(start);
        for (TableRow row : buildStates){
            
            System.out.println("State " + row.index);
            ArrayList<Symbol> terminals = parser.terminals;
            for (Symbol s : terminals){
                System.out.print(" | " + s.toString());
            }

            System.out.print(" || ");
            
            ArrayList<Symbol> symbols = parser.symbols;
            for (Symbol s : symbols){
                System.out.print(s.toString() + " | ");
            }
            System.out.println();            
            
            for (Symbol s : terminals){
                Action x = row.getAction(s);
                System.out.print(" | " + (x == null ? " " : x));
            }

            System.out.print(" || ");
            
            for (Symbol s : symbols){
                Integer x = row.getGoto(s);
                System.out.print((x == null ? " " : x) + " | ");
            }
            System.out.println();              
            
            for (Item item : row){
                System.out.println(item);
            }
            System.out.println();
        }
    }
}
