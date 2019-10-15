/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * An LR1 programmatic parser.
 *
 * @author edward
 * @param <TOKEN> Token type.
 */
public class Parser<TOKEN> {
    HashMap<String, ArrayList<Rule>> rules = new HashMap<>();
    ArrayList<Symbol> terminals = new ArrayList<>();
    ArrayList<Symbol> nonterminals = new ArrayList<>();
    ArrayList<Symbol> symbols = new ArrayList<>();
    
    /**
     * Add a rule to this parser. The rule takes the form of 'LHS : RHS |
     * [RHS]*'.
     *
     * @param rule
     */
    public Rule addRule(Rule rule) {
        String lhs = rule.lhs;

        if (!this.rules.containsKey(lhs)) {
            this.rules.put(lhs, new ArrayList<>());
        }
        ArrayList<Rule> ruleList = this.rules.get(lhs);
        ruleList.add(rule);
        
        for (Symbol symbol : rule.rhs){
            if (symbol.isTerminal() && !terminals.contains(symbol)) terminals.add(symbol);
            else if (!symbol.isTerminal() && !nonterminals.contains(symbol)) nonterminals.add(symbol);
            if (!symbols.contains(symbol)) symbols.add(symbol);
        }
        
        return rule;
    }

    ArrayList<Item> closure(Rule rule) {
        return new Closure(this, rule);
    }
    
    ArrayList<State> buildStates(Rule start){
        ArrayList<State> list = new ArrayList<>();
        State startState = new State(0, closure(start));
        list.add(startState);
        
        int n = 0;
        int m = 0;
                
        while(m <= n){
            State state = list.get(m++);
            
            for (Symbol s : symbols){
                List<Item> all = new ArrayList<>();
                for (Item i : state.getAllItems(s)) all.add(i.increment());
                if (all.isEmpty()) continue;
                Closure closure = new Closure(this, all);
                if (list.contains(closure)) continue;
                State newState = new State(++n, closure);
                list.add(newState);
            }
        }
        
        return list;
    }
    
    public void printRules(PrintStream stream) {
        StringBuilder builder = new StringBuilder();
        for (String lhs : this.rules.keySet()) {
            ArrayList<Rule> ruleList = this.rules.get(lhs);
            for (Rule rule : ruleList) {
                builder.append(rule.toString());
            }
        }
        stream.print(builder.toString());
    }
}
