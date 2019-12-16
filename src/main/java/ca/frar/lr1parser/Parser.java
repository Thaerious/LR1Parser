/*
 * Copyright (C) 2019 Ed Armstrong
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ca.frar.lr1parser;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author edward
 * @param <TOKEN> The raw input type for symbols, typically a string.
 */
public class Parser<TOKEN>{
    ParseTable table = null;
    ParseTableBuilder builder = new ParseTableBuilder();
    Rule start = null;
    Stack<StackItem> stack = new Stack<>();
    LinkedList<TOKEN> input = new LinkedList<>();
    NonTerminalASTNode root;
    
    /**
     * Add a rule to the parser.  The start rule must be the first rule added.
     * @param rule 
     */
    public void addRule(String rule){
        if (start == null) start = builder.addRule(new Rule(rule));
        else builder.addRule(new Rule(rule));
    }
    
    public NonTerminalASTNode parse() throws UnhandledInputException{
        while(this.step());
        return this.root;
    }
    
    /**
     * Retrieve the AST root node.  This is only valid when the parsing is
     * complete.
     * @return 
     */
    public NonTerminalASTNode getRoot(){
        return root;
    }
    
    /**
     * Build the parse table if it's not already built and push a null symbol
     * onto the stack;
     */
    public void makeReady(){
        if (this.table == null) this.table = builder.build(start);
        stack.push(new StackItem(this.table.get(0), Symbol.NULL, null));
    }
    
    public Parser setInput(TOKEN[] tokens){
        input.clear();
        for (TOKEN token : tokens){
            this.input.add(token);
        }
        return this;
    }
    
    /**
     * Retrieve the current state.
     * @return 
     */
    State currentState(){
        return stack.peek().state;
    }
    
    /**
     * Consume the next token and advance the parser.
     * @return If parsing should continue, true.
     * @throws UnhandledInputException 
     */
    public boolean step() throws UnhandledInputException{
        Action action = nextAction();    
        
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
    
 /**
     * Retrieve the next action based on current state and look-ahead symbol.
     * This makes no change to the internal state of the parser.
     * @return
     * @throws UnhandledInputException 
     */
    Action nextAction() throws UnhandledInputException{
        Symbol symbol;
        
        if (this.input.isEmpty()){
            symbol = Symbol.END;
        } else {
            TOKEN token = this.input.get(0);
            symbol = new Symbol(token.toString().toLowerCase()); // <-- here is why tokens must have a unique lowercase toString result;            
        }

        Action action = currentState().getAction(symbol);
        
        if (!currentState().hasAction(symbol)){
            throw new UnhandledInputException(currentState(), symbol);
        }

        return action;
    }    
    
    private void shiftAction(ShiftAction action){
        TOKEN token = input.remove(0);
        Symbol symbol  = new Symbol(token.toString().toLowerCase()); // <-- here is why tokens must have a unique lowercase toString result;
        
        State state = table.get(action.getDestinationState());        
        TerminalASTNode<TOKEN> astNode = new TerminalASTNode<>(symbol, token);
        StackItem stackItem = new StackItem(state, symbol, astNode);        
        stack.push(stackItem);
    }
    
    private void reduceAction(ReduceAction action){
        Rule rule = action.getRule();
        Symbol symbol = action.getRule().getLHS();
        NonTerminalASTNode astNode = new NonTerminalASTNode<>(symbol);
        
        for (int i = 0; i < rule.getRHS().size(); i++){
            StackItem pop = stack.pop();  
            astNode.PrePend(pop.astNode);
        }
        State state = stack.peek().state;
        
        GotoAction gotoAction = (GotoAction) state.getAction(symbol);
        int destinationState = gotoAction.getDestinationState();
        
        StackItem stackItem = new StackItem(table.get(destinationState), symbol, astNode);
        stack.push(stackItem);        
    }    
}