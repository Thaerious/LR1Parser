/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1;

import java.util.HashSet;


/**
 *
 * @author edward
 */
public class Main1 {
    public static void main(String ... args){
        ParserBase parser = new ParserBase();
        Rule start = parser.addRule(new Rule("S : A"));
        parser.addRule(new Rule("A : a B C"));
        parser.addRule(new Rule("A : A a"));
        parser.addRule(new Rule("B : b"));
        parser.addRule(new Rule("C : c"));
        parser.addRule(new Rule("C : "));

        Item i1 = new Item(start, 0);
        Item i2 = new Item(start, 0);
        System.out.println(i1.equals(i2));
        System.out.println(i1.hashCode());
        System.out.println(i2.hashCode());
    }
}
