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
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author edward
 */
public class Main {

    public static void main(String... args) throws IOException {
        Parser<String> parser = new Parser();

        parser.addRule("S : E");
        parser.addRule("E : E a");
        parser.addRule("E : a");

        parser.makeReady();
        parser.setInput("a a a".split(" "));
        
        Printer.printTable(parser.table, parser.builder);
        new ParserTerminalController(parser).run();
    }
}
