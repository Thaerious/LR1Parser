/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1parser;

import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author edward
 */
public class Parser<TOKEN>{
    ParseTable table = null;
    ParseTableBuilder builder = new ParseTableBuilder();
    Rule start = null;
    Stack<StackItem> stack = new Stack<>();
    LinkedList<Symbol<TOKEN>> input = new LinkedList<>();
    NonTerminalASTNode root;
    
    /**
     * Add a rule to the parser.  The start rule must be the first rule added.
     * @param rule 
     */
    public void addRule(String rule){
        if (start == null) start = builder.addRule(new Rule(rule));
        else builder.addRule(new Rule(rule));
    }
    
    void makeReady(){
        if (this.table == null) this.table = builder.build(start);
        stack.push(new StackItem(this.table.get(0), Symbol.NULL, null));
    }
    
    public Symbol<TOKEN> toSymbol(TOKEN token){
        String toLowerCase = token.toString().toLowerCase();
        return new Symbol(toLowerCase, token);
    }
    
    public void setInput(TOKEN[] tokens){
        input.clear();
        for (TOKEN token : tokens) this.input.add(toSymbol(token));
        this.input.add(Symbol.END);
    }
    
    boolean step(){
        Symbol<TOKEN> symbol = this.input.get(0);        
        State state = stack.peek().state;
        Action action = state.getAction(symbol);        
        
        switch (action.getType()){
            case SHIFT:
                shiftAction((ShiftAction) action);
                break;
            case REDUCE:
                reduceAction((ReduceAction)action);
                break;
            case ACCEPT:
                root = (NonTerminalASTNode) stack.peek().astNode;
                return false;
        }
        return true;
    }    
    
    private void shiftAction(ShiftAction action){
        Symbol<TOKEN> symbol = input.remove(0);
        State state = table.get(action.getDestinationState());        
        TerminalASTNode<TOKEN> astNode = new TerminalASTNode<>(symbol.token);
        StackItem stackItem = new StackItem(state, symbol, astNode);        
        stack.push(stackItem);
    }
    
    private void reduceAction(ReduceAction action){
        Rule rule = action.getRule();
        Symbol<TOKEN> symbol = action.getRule().getLHS();
        NonTerminalASTNode astNode = new NonTerminalASTNode<>(symbol);
        
        for (int i = 0; i < rule.getRHS().size(); i++){
            StackItem pop = stack.pop();  
            astNode.AddChild(pop.astNode);
        }
        State state = stack.peek().state;
        
        GotoAction gotoAction = (GotoAction) state.getAction(symbol);
        int destinationState = gotoAction.getDestinationState();
        
        StackItem stackItem = new StackItem(table.get(destinationState), symbol, astNode);
        stack.push(stackItem);        
    }    
}
