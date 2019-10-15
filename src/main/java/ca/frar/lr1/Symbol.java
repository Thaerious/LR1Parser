/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.frar.lr1;

/**
 *
 * @author edward
 */
class Symbol {
    private final String originString;
    
    public static Symbol NULL_PART = new Symbol(){
        @Override
        boolean isTerminal(){
            return true;
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
        if (object.getClass() != Symbol.class) return false;
        Symbol that = (Symbol) object;
        return this.originString.equals(that.originString);
    }
    
    @Override
    public String toString(){
        return originString;
    }
}
