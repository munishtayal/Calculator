package com.example.calculator;

import java.util.Scanner;

import com.example.calculator.calculators.Calculator;
import com.example.calculator.calculators.FractionsCalculator;

import static com.example.calculator.Constants.APP_USAGE;
import static com.example.calculator.Constants.EXIT_COMMAND;
import static com.example.calculator.Constants.HELP_COMMAND;
import static com.example.calculator.Constants.MATHS_EXPRESSION_INPUT_PREFIX;
import static com.example.calculator.Constants.OPERATORS_AND_OPERANDS_SEP;
import static com.example.calculator.Constants.WELCOME_MESSAGE;

/**
 * This class is used to interact with user and by accepting input and displaying output to console.
 */
public class CalculatorConsole {
    private final Calculator calculator;
    private final Scanner inputScanner;

    public CalculatorConsole() {
        this(new FractionsCalculator(), new Scanner(System.in));
    }

    /**
     * This constructor is used by unit tests.
     * @param calculator Object of class which implements abstract class {@link Calculator}.
     * @param inputScanner object to read input from console.
     */
    public CalculatorConsole(Calculator calculator, Scanner inputScanner) {
        this.calculator = calculator;
        this.inputScanner = inputScanner;
    }

    /**
     * This method will read the user input and do following -
     *      1. If user inputs exit command, do nothing and returns the execution to caller.
     *      2. Else df user inputs help commands, show help message and waits for next input.
     *      3. Else calls the calculator to calculate the maths expression and displays result and waits for next input.
     */
    public void initialize() {
        System.out.println(WELCOME_MESSAGE);

        String input = inputScanner.nextLine().trim();
        while (!input.equalsIgnoreCase(EXIT_COMMAND)) {
            if (input.equalsIgnoreCase(HELP_COMMAND) || input.length() < 2) {
                System.out.println(APP_USAGE);
            } else {
                try {
                    if (input.charAt(0) != MATHS_EXPRESSION_INPUT_PREFIX) {
                        throw new IllegalArgumentException();
                    }

                    // Get the mathematical expression by removing the question mark and replacing the one or more
                    // consecutive white space characters with OPERATORS_AND_OPERANDS_SEP
                    String mathsExpression = input.substring(1).replaceAll("\\s+", OPERATORS_AND_OPERANDS_SEP).trim();

                    String result = calculator.calculate(mathsExpression);
                    System.out.println(result);
                } catch (IllegalArgumentException iae) {
                    System.out.printf("Input \"%s\" does not represent a valid/supported maths expression%n", input);
                } catch (UnsupportedOperationException ex) {
                    System.out.printf("Input \"%s\" contains unsupported operation%n", input);
                } catch (ArithmeticException ex) {
                    System.out.printf("Input \"%s\" is illegal: %s%n", input, ex.getMessage());
                }
            }
            input = inputScanner.nextLine();
        }
    }
}
