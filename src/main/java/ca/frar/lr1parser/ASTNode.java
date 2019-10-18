package ca.frar.lr1parser;
import java.util.ArrayList;

/**
 * @author edward
 */
public abstract class ASTNode<TOKEN> {
    ASTNode parent = null;
    public abstract boolean isTerminal();
}
