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
public class UnhandledInputException extends Exception {
    private final State state;
    private final Symbol symbol;

    public UnhandledInputException(State state, Symbol symbol) {
        this.state = state;
        this.symbol = symbol;
    }

    /**
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * @return the symbol
     */
    public Symbol getSymbol() {
        return symbol;
    }
}