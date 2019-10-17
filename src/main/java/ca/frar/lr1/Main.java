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

    public static void main(String... args) {
        ParserBase parser = new ParserBase();
        Rule start = parser.addRule(new Rule("S' : S"));
        parser.addRule(new Rule("S : E"));
        parser.addRule(new Rule("E : E x E"));
        parser.addRule(new Rule("E : z"));

        ArrayList<State> buildStates = parser.buildStates(start);
        ArrayList<Symbol> symbols = new ArrayList<>();
        symbols.addAll(parser.terminals);
        symbols.addAll(parser.nonterminals);
        
        for (Rule rule : parser.getRules().allRules()){
            System.out.println(rule.index + ": " + rule);
        }
        
        System.out.print("   ");
        for (Symbol s : symbols) {
            String format = String.format(" |%3s", s);
            System.out.print(format);
        }
        System.out.println(" |");

        for (State row : buildStates) {
            System.out.print(String.format("%3d", row.index));

            for (Symbol s : symbols) {
                Action x = row.getAction(s);
                String format = String.format(" |%3s", (x == null ? " " : x));
                System.out.print(format);
            }
            System.out.println(" |");
        }

        for (State row : buildStates) {
            System.out.println("State " + row.index);
            for (Item item : row) {
                System.out.println(item);
            }
        }
    }
}
