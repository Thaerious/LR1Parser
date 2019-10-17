/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * An LR1 programmatic parser.
 * https://pages.github-dev.cs.illinois.edu/cs421-haskell/web-su19/files/handouts/lr-parsing-tables.pdf
 * @author edward
 * @param <TOKEN> Token type.
 */
public class ParserBase {
    private RuleSet rules = new RuleSet();
    ArrayList<Symbol> terminals = new ArrayList<>();
    ArrayList<Symbol> nonterminals = new ArrayList<>();
    ArrayList<State> table;
    private FollowSet followSet;
    
    /**
     * Add a rule to this parser. The rule takes the form of 'LHS : RHS |
     * [RHS]*'.
     *
     * @param rule
     */
    public Rule addRule(Rule rule) {
        Symbol lhs = rule.lhs;

        if (!this.rules.containsKey(lhs)) {
            this.rules.put(lhs, new HashSet<>());
        }
        HashSet<Rule> ruleList = this.rules.get(lhs);
        ruleList.add(rule);
        
        for (Symbol symbol : rule.rhs){
            if (symbol.isTerminal() && !terminals.contains(symbol)) terminals.add(symbol);
            else if (!symbol.isTerminal() && !nonterminals.contains(symbol)) nonterminals.add(symbol);
        }
        
        return rule;
    }

    /**
     * Retrieve a non-reflective list of rules.  The contained Lists are
     * cloned and non-reflective.
     * @return 
     */
    public RuleSet getRules(){
        return this.rules;
    }
    
    HashSet<Item> closure(Rule rule) {
        return new Closure(this, rule);
    }
    
    public void checkProductions(){
        for (Symbol symbol : this.nonterminals){
            HashSet<Rule> ruleset = this.rules.get(symbol);
            if (ruleset == null || ruleset.isEmpty()) throw new UnknownProductionException(symbol);
        }
    }
    
    ArrayList<State> buildStates(Rule start){
        terminals.add(Symbol.END);
        
        this.table = new ArrayList<>();
        this.followSet = new FollowSet(this, start.lhs);
        State startState = new State(0, closure(start));
        table.add(startState);
        
        int n = 0;
        int m = 0;
                
        while(n < table.size()){
            State state = table.get(n++);

            /* for each Item i in State n */
            for (Item i : state){
                step2(i, state);
                step1(i, state);                
                step3(i, state);
            }
        }
        
        return table;
    }

    /* for each terminal symbol 's', take all items in 'state' #n with index
       in front of 's' and create a new item set where the cursor is
       incremented.  Take the closure this set creates a new state #m, if an
       identical state already exists, it instead.
    
        Enter a shift action in 'state' for symbol 's', and set goto to m for 
        symbol 's'.
    */            
    void step1(Item i, State state){
        for (Symbol s : terminals){
            List<Item> all = new ArrayList<>();
            
            for (Item item : state.getAllItems(s)){
                all.add(item.increment());
            }            
            if (all.isEmpty()) continue;
            
            Closure closure = new Closure(this, all);
            int m = table.indexOf(closure);
            if (m == -1){
                m = this.table.size();
                State newState = new State(m, closure);
                table.add(newState);
            }
            state.addAction(s, new ShiftAction(m));
        }
    }
    
    /* for each nonterminal symbol 's', take all items in 'state' #n with index
       in front of 's' and create a new item set where the cursor is
       incremented.  Take the closure this set creates a new state #m, if an
       identical state already exists, it instead.
    
        Enter a shift action in 'state' for symbol 's', and set goto to m for 
        symbol 's'.
    */               
    void step2(Item i, State state){
        for (Symbol s : nonterminals){
            List<Item> all = new ArrayList<>();
            
            for (Item item : state.getAllItems(s)){
                all.add(item.increment());
            }            
            if (all.isEmpty()) continue;
            
            Closure closure = new Closure(this, all);
            int m = table.indexOf(closure);
            if (m == -1){
                m = this.table.size();
                State newState = new State(m, closure);
                table.add(newState);
            }
            state.addAction(s, new GotoAction(m));
        }
    }    
    
    void step3(Item i, State state){
        for (Item item : state){
            if (item.isFinal()){
                HashSet<Symbol> follows = this.followSet.get(item.rule.lhs);
                for (Symbol s : follows){
                    state.addAction(s, new ReduceAction(item.rule));
                }
            }
        }
    }
    
    public void printRules(PrintStream stream) {
        StringBuilder builder = new StringBuilder();
        for (Symbol lhs : this.rules.keySet()) {
            HashSet<Rule> ruleList = this.rules.get(lhs);
            for (Rule rule : ruleList) {
                builder.append(rule.toString());
            }
        }
        stream.print(builder.toString());
    }
}
