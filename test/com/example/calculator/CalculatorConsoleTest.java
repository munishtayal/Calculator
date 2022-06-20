package com.example.calculator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import com.example.calculator.calculators.FractionsCalculator;
import com.example.calculator.models.MixedNumber;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorConsoleTest {
    private FractionsCalculator calculatorMock;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private CalculatorConsole calculatorConsole;

    @BeforeEach
    void setUp() {
        calculatorMock = mock(FractionsCalculator.class);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testCalculatorConsole_whenInputIsExit_doNothing() {
        String input = "exit";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        calculatorConsole = new CalculatorConsole(calculatorMock, new Scanner(System.in));
        calculatorConsole.initialize();

        verifyZeroInteractions(calculatorMock);
        assertEquals("Thank you for using fractions calculator. Type \"help\" to see usage. Type \"exit\" to exit.\n", outContent.toString());
    }

    @Test
    void testCalculatorConsole_whenInputIsHelp_showHelpMessage() {
        String input = String.join("\n", new String[]{"help", "exit"});
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        calculatorConsole = new CalculatorConsole(calculatorMock, new Scanner(System.in));
        calculatorConsole.initialize();

        verifyZeroInteractions(calculatorMock);
        String expectedMsg = "Thank you for using fractions calculator. Type \"help\" to see usage. Type \"exit\" to exit.\n" +
                "This calculator supports following operators: *, /, +, - (multiply, divide, add, subtract).\n" +
                "-> Operand and operators should be separated by one or more spaces.\n" +
                "-> Mixed numbers should be represented by whole_numerator/denominator. e.g. \"3_1/4\"\n" +
                "-> Negative numbers should be represented by prepending hyphen (-) to operand without any space. e.g. \"-1_2/3\"\n" +
                "-> Improper fractions and whole numbers are also allowed as operands.\n" +
                "-> The input mathematical expression should start with question mark (?) without any leading spaces.\n" +
                "Sample inputs:\n" +
                "? 1/2 * -3_3/4\n" +
                "?2_3/8 + 9/8\n" +
                "Type \"exit\" to exit.\n";
        assertEquals(expectedMsg, outContent.toString());
    }

    @Test
    void testCalculatorConsole_whenInputIsValidMathExpression_showResult() {
        String input = String.join("\n", new String[]{"? 1/2 * 3_3/4", "exit"});
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        MixedNumber expectedMixedNumber = new MixedNumber(1, 7, 8);
        when(calculatorMock.calculate("1/2 * 3_3/4")).thenReturn(expectedMixedNumber.toString());

        calculatorConsole = new CalculatorConsole(calculatorMock, new Scanner(System.in));
        calculatorConsole.initialize();

        verify(calculatorMock, times(1)).calculate("1/2 * 3_3/4");
        String expectedMsg = String.format("Thank you for using fractions calculator. Type \"help\" to see usage. Type \"exit\" to exit.\n" +
                "%s\n", expectedMixedNumber.toString());
        assertEquals(expectedMsg, outContent.toString());
    }

    @Test
    void testCalculatorConsole_whenInputMathExpressionNotValid_showErrorMessage() {
        String inputExpression = "1/2 * 3_3/4";
        String input = String.join("\n", new String[]{inputExpression, "exit"});
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        calculatorConsole = new CalculatorConsole(calculatorMock, new Scanner(System.in));
        calculatorConsole.initialize();

        verifyZeroInteractions(calculatorMock);
        String expectedMsg = String.format("Thank you for using fractions calculator. Type \"help\" to see usage. Type \"exit\" to exit.\n" +
                "Input \"%s\" does not represent a valid/supported maths expression\n", inputExpression);
        assertEquals(expectedMsg, outContent.toString());
    }

    @Test
    void testCalculatorConsole_whenCalculatorThrowsUnsupportedOperationException_showErrorMessage() {
        String inputExpression = "?2 ^ 3";
        String input = String.join("\n", new String[]{inputExpression, "exit"});
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        when(calculatorMock.calculate("2 ^ 3")).thenThrow(new UnsupportedOperationException());

        calculatorConsole = new CalculatorConsole(calculatorMock, new Scanner(System.in));
        calculatorConsole.initialize();

        verify(calculatorMock, times(1)).calculate("2 ^ 3");
        String expectedMsg = String.format("Thank you for using fractions calculator. Type \"help\" to see usage. Type \"exit\" to exit.\n" +
                "Input \"%s\" contains unsupported operation\n", inputExpression);
        assertEquals(expectedMsg, outContent.toString());
    }

    @Test
    void testCalculatorConsole_whenCalculatorThrowsArithmeticException_showErrorMessage() {
        String inputExpression = "?1 / 0";
        String input = String.join("\n", new String[]{inputExpression, "exit"});
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Exception exception = new ArithmeticException("/ by zero");
        when(calculatorMock.calculate("1 / 0")).thenThrow(exception);

        calculatorConsole = new CalculatorConsole(calculatorMock, new Scanner(System.in));
        calculatorConsole.initialize();

        verify(calculatorMock, times(1)).calculate("1 / 0");
        String expectedMsg = String.format("Thank you for using fractions calculator. Type \"help\" to see usage. Type \"exit\" to exit.\n" +
                "Input \"%s\" is illegal: %s\n", inputExpression, exception.getMessage());
        assertEquals(expectedMsg, outContent.toString());
    }

    @Test
    void testCalculatorConsole_whenInputMathExpressionHasMultipleSpaces_callsCalculatorWithSingleSpace() {
        String input = String.join("\n", new String[]{"?   2    +  3  ", "exit"});
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        calculatorConsole = new CalculatorConsole(calculatorMock, new Scanner(System.in));
        calculatorConsole.initialize();

        verify(calculatorMock, times(1)).calculate("2 + 3");
    }
}