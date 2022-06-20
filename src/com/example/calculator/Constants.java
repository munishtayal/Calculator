package com.example.calculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
    public static final String WELCOME_MESSAGE = "Thank you for using fractions calculator. Type \"help\" to see usage. Type \"exit\" to exit.";
    public static final String EXIT_COMMAND = "exit";
    public static final String HELP_COMMAND = "help";
    public static final char MATHS_EXPRESSION_INPUT_PREFIX = '?';
    public static final String APP_USAGE = "This calculator supports following operators: *, /, +, - (multiply, divide, add, subtract).\n" +
            "-> Operand and operators should be separated by one or more spaces.\n" +
            "-> Mixed numbers should be represented by whole_numerator/denominator. e.g. \"3_1/4\"\n" +
            "-> Negative numbers should be represented by prepending hyphen (-) to operand without any space. e.g. \"-1_2/3\"\n" +
            "-> Improper fractions and whole numbers are also allowed as operands.\n" +
            "-> The input mathematical expression should start with question mark (?) without any leading spaces.\n" +
            "Sample inputs:\n" +
            "? 1/2 * -3_3/4\n" +
            "?2_3/8 + 9/8\n" +
            "Type \"exit\" to exit.";

    public static final String OPERATORS_AND_OPERANDS_SEP = " ";

    public static final List<Character> SUPPORTED_OPERATIONS = List.of(
            OPERATION.ADD.symbol,
            OPERATION.SUBTRACT.symbol,
            OPERATION.MULTIPLY.symbol,
            OPERATION.DIVIDE.symbol);

    public enum OPERATION {
        ADD('+'),
        SUBTRACT('-'),
        MULTIPLY('*'),
        DIVIDE('/');

        char symbol;
        OPERATION(char operation) {
            this.symbol = operation;
        }

        // Reverse-lookup map for getting a operation from symbol
        private static final Map<Character, OPERATION> lookup = new HashMap<Character, OPERATION>();

        static {
            for (OPERATION op : OPERATION.values()) {
                lookup.put(op.symbol, op);
            }
        }

        public static OPERATION get(char symbol) {
            return lookup.get(symbol);
        }
    }
}
