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

    void PrePend(ASTNode<TOKEN> child) {
        this.children.add(0, child);
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
