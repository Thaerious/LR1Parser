/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1;

import java.util.Objects;

/**
 *
 * @author edward
 */
public class Symbol {
    private final String originString;
    
    public static Symbol END = new Symbol(){
        @Override
        boolean isTerminal(){
            return true;
        }    
        
        public String toString(){
            return "$";
        }
    };
    
    public static Symbol EMPTY = new Symbol(){
        @Override
        boolean isTerminal(){
            return true;
        }
        
        public String toString(){
            return "~";
        }
    };    
    
    public static Symbol NULL_PART = new Symbol(){
        @Override
        boolean isTerminal(){
            return true;
        }    
        
        public String toString(){
            return "";
        }        
    };
    
    private Symbol(){
        this.originString = "";
    }
    
    Symbol(String originString){
        if (Character.isUpperCase(originString.charAt(0))){
            this.originString = originString.toUpperCase();
        } else {
            this.originString = originString.toLowerCase();
        }        
    }

    String getOrigin(){
        return originString;
    }
    
    boolean isTerminal(){
        return !Character.isUpperCase(originString.charAt(0));
    }    
    
    @Override
    public boolean equals(Object object){
        if (object == Symbol.EMPTY && this == Symbol.EMPTY) return true;
        if (object == Symbol.END && this == Symbol.END) return true;
        if (object == Symbol.NULL_PART && this == Symbol.NULL_PART) return true;
        
        if (object.getClass() != Symbol.class) return false;
        Symbol that = (Symbol) object;
        return this.originString.equals(that.originString);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.originString);
        return hash;
    }
    
    @Override
    public String toString(){
        return originString;
    }
}
