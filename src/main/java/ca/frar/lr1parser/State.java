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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Ed Armstrong
 */
public class State extends ItemSet {

    final int index;
    private final HashMap<Symbol, Action> actions = new HashMap<>();

    State(int index) {
        this.index = index;
    }

    State(int index, Collection<Item> source) {
        this.index = index;
        this.addAll(source);
    }

    /**
     * Create a new symbol action association. Return true if a value was
     * replaced.
     *
     * @param symbol
     * @param action
     * @return
     */
    boolean addAction(Symbol symbol, Action action) {
        Action previous = this.actions.put(symbol, action);
        return previous != null;
    }

    public Action getAction(Symbol symbol) {
        return this.actions.get(symbol);
    }

    /**
     * Return all unique items with a cursor in front of symbol.
     *
     * @param symbol
     */
    List<Item> getAllItems(Symbol symbol) {
        ArrayList<Item> list = new ArrayList<>();
        for (Item item : this) {
            if (item.dereference().equals(symbol) && !list.contains(item)) {
                list.add(item);
            }
        }
        return list;
    }

    boolean hasAction(Symbol symbol) {
        return this.actions.containsKey(symbol);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("state #").append(index).append("\n");

        for (Item item : this){
            builder.append(item.toString());
            builder.append("\n");
        }
        
        return builder.toString();
    }
}
