/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1parser;

/**
 *
 * @author edward
 */
public class TerminalASTNode<TOKEN> extends ASTNode<TOKEN> {
    
    final TOKEN token;

    public TerminalASTNode(TOKEN token) {
        this.token = token;
    }

    @Override
    public boolean isTerminal() {
        return true;
    }
    
}
