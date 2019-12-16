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

import ca.frar.lr1parser.*;
import ca.frar.lr1parser.demo.ParserTerminalController;
import ca.frar.lr1parser.demo.Token;
import ca.frar.lr1parser.demo.TokenFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author edward
 */
public class Main {

    public static void main(String... args) throws IOException {
        Parser<Token<String>> parser = new Parser();

        parser.addRule("S : E");
        parser.addRule("E : E a");
        parser.addRule("E : a");

        parser.makeReady();
        
        TokenFactory<String, Token<String>> tokenFactory = new TokenFactory<>();
        LinkedList<Token<String>> input = tokenFactory.build("a a a".split(" "));
        parser.setInput(input);
        
        Printer.printTable(parser.table, parser.builder);
        new ParserTerminalController(parser).run();
    }
}
