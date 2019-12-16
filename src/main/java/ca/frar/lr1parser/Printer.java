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

import java.util.ArrayList;

/**
 *
 * @author Ed Armstrong
 */
public class Printer {

    public static void printStep(Parser<?> parser) throws UnhandledInputException {
        System.out.print(parser.currentState());
        System.out.print(parser.stack);
        System.out.print(" <- ");
        System.out.println(parser.input);

        Action nextAction = parser.nextAction();
        switch (nextAction.getType()) {
            case SHIFT:
            case ACCEPT:
                System.out.println("next action: " + nextAction);
                break;
            case REDUCE:
                ReduceAction reduceAction = (ReduceAction) nextAction;
                System.out.println("next action: " + nextAction + " '" + reduceAction.getRule() + "'");
                break;
        }

        System.out.println("<press q to quit>");
    }

    public static void printTree(NonTerminalASTNode<?> root, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("<" + root.production.getOrigin() + ">");
        for (ASTNode<?> child : root.getChildren()) {
            if (child.isTerminal()) {
                for (int i = 0; i <= depth; i++) {
                    System.out.print("  ");
                }
                TerminalASTNode<?> terminal = (TerminalASTNode<?>) child;
                System.out.println("<terminal value=\"" + terminal.token + "\"/>");
            } else {
                printTree((NonTerminalASTNode<?>) child, depth + 1);
            }
        }
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("</" + root.production.getOrigin() + ">");
    }

    public static void printTable(ParseTable table, ParseTableBuilder builder) {
        ArrayList<Symbol> symbols = new ArrayList<>();
        symbols.addAll(builder.terminals);
        symbols.addAll(builder.nonterminals);

        for (Rule rule : builder.getRules().allRules()) {
            System.out.println(rule.index + ": " + rule);
        }

        System.out.print("   ");
        for (Symbol s : symbols) {
            String format = String.format(" |%3s", s);
            System.out.print(format);
        }
        System.out.println(" |");

        for (State row : table) {
            System.out.print(String.format("%3d", row.index));

            for (Symbol s : symbols) {
                Action x = row.getAction(s);
                String format = String.format(" |%3s", (x == null ? " " : x));
                System.out.print(format);
            }
            System.out.println(" |");
        }
    }
}
