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

import java.util.Objects;

/**
 *
 * @author edward
 */
public class Symbol {

    private final String originString;

    public static Symbol END = new Symbol() {
        @Override
        boolean isTerminal() {
            return true;
        }

        public String toString() {
            return "$";
        }
    };

    public static Symbol EMPTY = new Symbol() {
        @Override
        boolean isTerminal() {
            return true;
        }

        public String toString() {
            return "~";
        }
    };

    public static Symbol NULL = new Symbol() {
        @Override
        boolean isTerminal() {
            return true;
        }

        public String toString() {
            return "";
        }
    };

    protected Symbol() {
        this.originString = "";
    }

    public Symbol(String originString) {
        if (Character.isUpperCase(originString.charAt(0))) {
            this.originString = originString.toUpperCase();
        } else {
            this.originString = originString.toLowerCase();
        }
    }

    String getOrigin() {
        return originString;
    }

    boolean isTerminal() {
        return !Character.isUpperCase(originString.charAt(0));
    }

    @Override
    public boolean equals(Object object) {
        if (object == Symbol.EMPTY && this == Symbol.EMPTY) {
            return true;
        }
        if (object == Symbol.END && this == Symbol.END) {
            return true;
        }
        if (object == Symbol.NULL && this == Symbol.NULL) {
            return true;
        }

        if (object.getClass() != Symbol.class) {
            return false;
        }
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
    public String toString() {
        return originString;
    }
}
