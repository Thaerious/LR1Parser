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
import ca.frar.lr1parser.Parser;
import ca.frar.lr1parser.Printer;
import ca.frar.lr1parser.UnhandledInputException;
import java.io.IOException;

/**
 *
 * @author Ed Armstrong
 */
public class ParserTerminalController <TOKEN extends IsToken>{
    private final Parser<TOKEN> parser;
    
    public ParserTerminalController(Parser<TOKEN> parser){
        this.parser = parser;
    }
    
    public void run() throws IOException{
        boolean running = true;

        try {
            Printer.printStep(parser);
            while (running && System.in.read() != 'q') {
                running = parser.step();
                Printer.printStep(parser);
            }
        } catch (UnhandledInputException ex) {
            System.out.println("Unhandled input '" + ex.getSymbol().getOrigin() + "'");
            System.out.println(ex.getState());
            System.exit(1);
        }

        Printer.printTree(parser.getRoot(), 0);        
    }
    
}
