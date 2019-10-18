package ca.frar.lr1parser;

public class StackItem {
    final State state;
    final Symbol symbol;
    final ASTNode astNode;
    
    StackItem(State state, Symbol symbol, ASTNode astNode){
        this.state = state;
        this.symbol = symbol;
        this.astNode = astNode;
    }
    
    @Override
    public String toString(){
        return state.index + ":" + symbol.toString();
    }
}
