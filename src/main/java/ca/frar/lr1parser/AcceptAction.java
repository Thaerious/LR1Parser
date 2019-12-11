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

/**
 *
 * @author Ed Armstrong
 */
public class AcceptAction extends Action{
    
    public AcceptAction(){
        this.type = TYPE.ACCEPT;
    }
    
    public String toString(){
        return "ac";
    }
  
    @Override
    public boolean equals(Object obj){
        return (obj instanceof AcceptAction);
    }      

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }
    
}
