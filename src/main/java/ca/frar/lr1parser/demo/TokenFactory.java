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
package ca.frar.lr1parser.demo;
import ca.frar.lr1parser.IsToken;
import java.util.LinkedList;

/**
 *
 * @author Ed Armstrong
 */
public class TokenFactory<T, U extends IsToken<T>> {
    
    public LinkedList<IsToken<T>> build(T[] source){
        LinkedList<IsToken<T>> linkedList = new LinkedList<>();        
        for (T t : source) linkedList.add(this.parse(t));            
        return linkedList;
    }

    /**
     * Override this method.
     * @param t
     * @return 
     */
    public IsToken<T> parse(T t) {
        return new Token<T>(t.toString(), t);
    }
    
}
