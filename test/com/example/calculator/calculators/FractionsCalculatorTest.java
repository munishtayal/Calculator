package com.example.calculator.calculators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.calculator.models.MixedNumber;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FractionsCalculatorTest {
    private static FractionsCalculator fractionsCalculator;

    @BeforeAll
    static void setUp() {
        fractionsCalculator = new FractionsCalculator();
    }

    @Test
    void testAdd_whenBothOperandsProperMixedNumber() {
        MixedNumber operand1 = new MixedNumber(1, 2, 3);
        MixedNumber operand2 = new MixedNumber(3, 3, 4);

        MixedNumber result = fractionsCalculator.add(operand1, operand2);

        assertEquals(5, result.getWholeNumber());
        assertEquals(5, result.getNumerator());
        assertEquals(12, result.getDenominator());
    }

    @Test
    void testSubtract_whenBothOperandsProperMixedNumber() {
        MixedNumber operand1 = new MixedNumber(1, 2, 3);
        MixedNumber operand2 = new MixedNumber(3, 3, 4);

        MixedNumber result = fractionsCalculator.subtract(operand1, operand2);

        assertEquals(-2, result.getWholeNumber());
        assertEquals(-1, result.getNumerator());
        assertEquals(12, result.getDenominator());
    }

    @Test
    void testMultiply_whenBothOperandsProperMixedNumber() {
        MixedNumber operand1 = new MixedNumber(1, 2, 3);
        MixedNumber operand2 = new MixedNumber(3, 3, 4);

        MixedNumber result = fractionsCalculator.multiply(operand1, operand2);

        assertEquals(6, result.getWholeNumber());
        assertEquals(1, result.getNumerator());
        assertEquals(4, result.getDenominator());
    }

    @Test
    void testDivide_whenBothOperandsProperMixedNumber() {
        MixedNumber operand1 = new MixedNumber(1, 2, 3);
        MixedNumber operand2 = new MixedNumber(3, 3, 4);

        MixedNumber result = fractionsCalculator.divide(operand1, operand2);

        assertEquals(0, result.getWholeNumber());
        assertEquals(4, result.getNumerator());
        assertEquals(9, result.getDenominator());
    }

    @Test
    void testParseNumber_returnsMixedNumber() {
        MixedNumber mixedNumber = fractionsCalculator.parseOperand("1_2/3");

        assertEquals(1, mixedNumber.getWholeNumber());
        assertEquals(2, mixedNumber.getNumerator());
        assertEquals(3, mixedNumber.getDenominator());
    }

    @Test
    void testCalculate_addTwoOperands() {
        // Both operands mixed number and both positive
        assertEquals("6_1/2", fractionsCalculator.calculate("1_2/3 + 4_5/6"));

        // Both operands mixed number and both negative
        assertEquals("-6_1/2", fractionsCalculator.calculate("-1_2/3 + -4_5/6"));

        // Both operands mixed number with first operand positive and second negative
        assertEquals("-3_1/6", fractionsCalculator.calculate("1_2/3 + -4_5/6"));

        // Both operands mixed number with first operand negative and second positive
        assertEquals("3_1/6", fractionsCalculator.calculate("-1_2/3 + 4_5/6"));

        // One operand mixed number and another operand improper fraction
        assertEquals("2_13/15", fractionsCalculator.calculate("1_2/3 + 6/5"));

        // One operands mixed number and another operand whole number
        assertEquals("5_2/3", fractionsCalculator.calculate("1_2/3 + 4"));

        // One operands improper fraction and another operand whole number
        assertEquals("5_1/2", fractionsCalculator.calculate("3/2 + 4"));
    }

    @Test
    void testCalculate_subtractTwoOperands() {
        // Both operands mixed number and both positive
        assertEquals("-3_1/6", fractionsCalculator.calculate("1_2/3 - 4_5/6"));

        // Both operands mixed number and both negative
        assertEquals("3_1/6", fractionsCalculator.calculate("-1_2/3 - -4_5/6"));

        // Both operands mixed number with first operand positive and second negative
        assertEquals("6_1/2", fractionsCalculator.calculate("1_2/3 - -4_5/6"));

        // Both operands mixed number with first operand negative and second positive
        assertEquals("-6_1/2", fractionsCalculator.calculate("-1_2/3 - 4_5/6"));

        // One operand mixed number and another operand improper fraction
        assertEquals("7/15", fractionsCalculator.calculate("1_2/3 - 6/5"));

        // One operands mixed number and another operand whole number
        assertEquals("-2_1/3", fractionsCalculator.calculate("1_2/3 - 4"));

        // One operands improper fraction and another operand whole number
        assertEquals("-2_1/2", fractionsCalculator.calculate("3/2 - 4"));
    }

    @Test
    void testCalculate_multiplyTwoOperands() {
        // Both operands mixed number and both positive
        assertEquals("8_1/18", fractionsCalculator.calculate("1_2/3 * 4_5/6"));

        // Both operands mixed number and both negative
        assertEquals("8_1/18", fractionsCalculator.calculate("-1_2/3 * -4_5/6"));

        // Both operands mixed number with first operand positive and second negative
        assertEquals("-8_1/18", fractionsCalculator.calculate("1_2/3 * -4_5/6"));

        // Both operands mixed number with first operand negative and second positive
        assertEquals("-8_1/18", fractionsCalculator.calculate("-1_2/3 * 4_5/6"));

        // One operand mixed number and another operand improper fraction
        assertEquals("2", fractionsCalculator.calculate("1_2/3 * 6/5"));

        // One operands mixed number and another operand whole number
        assertEquals("6_2/3", fractionsCalculator.calculate("1_2/3 * 4"));

        // One operands improper fraction and another operand whole number
        assertEquals("6", fractionsCalculator.calculate("3/2 * 4"));
    }

    @Test
    void testCalculate_divideTwoOperands() {
        // Both operands mixed number and both positive
        assertEquals("10/29", fractionsCalculator.calculate("1_2/3 / 4_5/6"));

        // Both operands mixed number and both negative
        assertEquals("10/29", fractionsCalculator.calculate("-1_2/3 / -4_5/6"));

        // Both operands mixed number with first operand positive and second negative
        assertEquals("-10/29", fractionsCalculator.calculate("1_2/3 / -4_5/6"));

        // Both operands mixed number with first operand negative and second positive
        assertEquals("-10/29", fractionsCalculator.calculate("-1_2/3 / 4_5/6"));

        // One operand mixed number and another operand improper fraction
        assertEquals("1_7/18", fractionsCalculator.calculate("1_2/3 / 6/5"));

        // One operands mixed number and another operand whole number
        assertEquals("5/12", fractionsCalculator.calculate("1_2/3 / 4"));

        // One operands improper fraction and another operand whole number
        assertEquals("3/8", fractionsCalculator.calculate("3/2 / 4"));
    }

    @Test
    void testCalculate_withMultipleOperands_throwsIllegalArgumentException() {
        String inputExpression = "2_3/8 + 9/8 * 4/7";
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                fractionsCalculator.calculate(inputExpression));

        assertEquals(String.format("%s does not represent a supported maths expression.", inputExpression), exception.getMessage());
    }

    @Test
    void testCalculate_withInvalidExpression_throwsIllegalArgumentException() {
        String inputExpression = "2_3/8 +- 4/7";
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                fractionsCalculator.calculate(inputExpression));

        assertEquals(String.format("%s does not represent a supported maths expression.", inputExpression), exception.getMessage());
    }

    @Test
    void testCalculate_withUnsupportedOperation_throwsUnsupportedOperationException() {
        Exception exception = assertThrows(UnsupportedOperationException.class, () ->
                fractionsCalculator.calculate("2_3/8 ^ 4/7"));

        assertEquals("Operation ^ is not supported by calculator.", exception.getMessage());
    }
}