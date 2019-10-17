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
 * https://pages.github-dev.cs.illinois.edu/cs421-haskell/web-su19/files/handouts/lr-parsing-tables.pdf
 * @author edward
 * @param <TOKEN> Token type.
 */
public class Parser {
    HashMap<Symbol, ArrayList<Rule>> rules = new HashMap<>();
    ArrayList<Symbol> terminals = new ArrayList<>();
    ArrayList<Symbol> nonterminals = new ArrayList<>();
    ArrayList<Symbol> symbols = new ArrayList<>();
    ArrayList<TableRow> table;
    
    /**
     * Add a rule to this parser. The rule takes the form of 'LHS : RHS |
     * [RHS]*'.
     *
     * @param rule
     */
    public Rule addRule(Rule rule) {
        Symbol lhs = rule.lhs;

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
    
    ArrayList<TableRow> buildStates(Rule start){
        terminals.add(Symbol.END);
        symbols.add(Symbol.END);
        
        this.table = new ArrayList<>();
        TableRow startState = new TableRow(0, closure(start));
        table.add(startState);
        
        int n = 0;
        int m = 0;
                
        while(m <= n){
            TableRow state = table.get(m++);

            for (Symbol s : symbols){
                List<Item> all = new ArrayList<>();
                for (Item i : state.getAllItems(s)) all.add(i.increment());
                if (all.isEmpty()) continue;
                Closure closure = new Closure(this, all);

                int i = table.indexOf(closure);
                if (table.indexOf(closure) == -1){
                    TableRow newState = new TableRow(++n, closure);
                    table.add(newState);
                    i = table.indexOf(closure);
                }
                
                if (s.isTerminal()){
                    state.addAction(s, new ShiftAction());
                }
                else {
                    state.addGoto(s, i);
                }
            }
        }
        
        return table;
    }
    
    public void printRules(PrintStream stream) {
        StringBuilder builder = new StringBuilder();
        for (Symbol lhs : this.rules.keySet()) {
            ArrayList<Rule> ruleList = this.rules.get(lhs);
            for (Rule rule : ruleList) {
                builder.append(rule.toString());
            }
        }
        stream.print(builder.toString());
    }
}
