/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Ed Armstrong
 */
public class First extends ArrayList<Symbol> {

    private final HashMap<Symbol, ArrayList<Rule>> rules;
    private final Parser parser;

    public First(Parser parser, Symbol symbol) {
        this.parser = parser;
        this.rules = parser.rules;
        this.first(symbol);
    }

    private void first(Symbol symbol) {
        if (symbol.isTerminal()) {
            this.add(symbol);
        } else {
            for (Rule rule : rules.get(symbol)) {
                if (rule.rhs.isEmpty()) this.add(Symbol.EMPTY);
                else addRHS(rule, symbol);
            }
        }
    }

    private void addRHS(Rule rule, Symbol symbol) {
        for (Symbol s : rule.rhs) {
            if (s.equals(symbol)){
                break;
            }
            else if (s.isTerminal()) {
                this.add(s);
                break;
            } else {
                First firstS = new First(parser, s);                
                this.addAll(firstS);
                if (!firstS.contains(Symbol.EMPTY)){
                    break;
                }
            }
        }
    }
}
