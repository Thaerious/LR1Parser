/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author edward
 */
public class Main {

    public static void main(String... args) throws IOException {
        Parser<String> parser = new Parser();
                
        parser.addRule("S : SPART");
        parser.addRule("S : S SPART");
        parser.addRule("SPART : A B");
        parser.addRule("SPART : B A");
        parser.addRule("A : a");
        parser.addRule("A : A A");
        parser.addRule("B : b");
        parser.addRule("B : B B");
                
        parser.makeReady();
        
        printTable(parser.table, parser.builder);
        
        parser.setInput("a a b b".split(" "));
        boolean running = true;
        
        System.out.println(parser.input);
        System.out.println(parser.stack);                
        while(running && System.in.read() != 'q'){            
            running = parser.step();                        
            System.out.println(parser.input);
            System.out.println(parser.stack);
        }
        
        printTree(parser.root, 0);
    }

    public static void printTree(NonTerminalASTNode<String> root, int depth){
        for (int i = 0; i < depth; i++) System.out.print("  ");
        System.out.println("<" + root.production.getOrigin() + ">");
        for (ASTNode<String> child : root.getChildren()){
            if (child.isTerminal()){
                for (int i = 0; i <= depth; i++) System.out.print("  ");
                TerminalASTNode<String> terminal = (TerminalASTNode<String>) child;
                System.out.println("<terminal value=\"" + terminal.token + "\"/>");
            } else {
                printTree((NonTerminalASTNode<String>) child, depth + 1);
            }
        }        
        for (int i = 0; i < depth; i++) System.out.print("  ");
        System.out.println("</" + root.production.getOrigin() + ">");
    }
    
    public static void printTable(ParseTable table, ParseTableBuilder builder) {
        ArrayList<Symbol> symbols = new ArrayList<>();
        symbols.addAll(builder.terminals);
        symbols.addAll(builder.nonterminals);
        
        for (Rule rule : builder.getRules().allRules()) {
            System.out.println(rule.index + ": " + rule);
        }

        System.out.print("   ");
        for (Symbol s : symbols) {
            String format = String.format(" |%3s", s);
            System.out.print(format);
        }
        System.out.println(" |");

        for (State row : table) {
            System.out.print(String.format("%3d", row.index));

            for (Symbol s : symbols) {
                Action x = row.getAction(s);
                String format = String.format(" |%3s", (x == null ? " " : x));
                System.out.print(format);
            }
            System.out.println(" |");
        }

//        for (State row : table) {
//            System.out.println("State " + row.index);
//            for (Item item : row) {
//                System.out.println(item);
//            }
//        }
    }
}
