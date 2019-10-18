/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1parser;

import java.util.ArrayList;

/**
 *
 * @author edward
 */
public class NonTerminalASTNode<TOKEN> extends ASTNode<TOKEN> {
    final Symbol production;
    final ArrayList<ASTNode<TOKEN>> children = new ArrayList<>();
    
    NonTerminalASTNode(Symbol production) {
        this.production = production;
    }

    void AddChild(ASTNode<TOKEN> child) {
        this.children.add(child);
        child.parent = this;        
    }    
    
    @Override
    public boolean isTerminal() {
        return false;
    }

    public Iterable<ASTNode<TOKEN>> getChildren() {
        ArrayList<ASTNode<TOKEN>> arrayList = new ArrayList<>();
        arrayList.addAll(this.children);
        return arrayList;
    }
    
}
