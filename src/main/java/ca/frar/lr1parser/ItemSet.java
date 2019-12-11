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
import java.util.HashSet;

/**
 *
 * @author edward
 */
public abstract class ItemSet extends HashSet<Item>{
    
    @Override
    public boolean equals(Object obj){
        if (obj instanceof ItemSet == false) return false;
        ItemSet that = (ItemSet)obj;
        if (this.size() != that.size()) return false;
        for (Item item : this){
            if (! that.contains(item)) return false;
        }
        for (Item item : that){
            if (! that.contains(item)) return false;
        }        
        return true;
    }
    
}
