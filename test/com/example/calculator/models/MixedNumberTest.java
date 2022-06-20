package com.example.calculator.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MixedNumberTest {

    @Test
    void testMixedNumber_withNumberStringAsWholeNumber() {
        MixedNumber mixedNumber = new MixedNumber("4");

        assertEquals(4, mixedNumber.getWholeNumber());
        assertEquals(0, mixedNumber.getNumerator());
        assertEquals(1, mixedNumber.getDenominator());
    }

    @Test
    void testMixedNumber_withNumberStringAsFraction() {
        MixedNumber mixedNumber = new MixedNumber("4/5");

        assertEquals(0, mixedNumber.getWholeNumber());
        assertEquals(4, mixedNumber.getNumerator());
        assertEquals(5, mixedNumber.getDenominator());
    }

    @Test
    void testMixedNumber_withNumberStringAsMixedNumber() {
        MixedNumber mixedNumber = new MixedNumber("2_4/5");

        assertEquals(2, mixedNumber.getWholeNumber());
        assertEquals(4, mixedNumber.getNumerator());
        assertEquals(5, mixedNumber.getDenominator());
    }

    @Test
    void testMixedNumber_withNumberStringAsNegativeMixedNumber() {
        MixedNumber mixedNumber = new MixedNumber("-2_4/5");

        assertEquals(-2, mixedNumber.getWholeNumber());
        assertEquals(-4, mixedNumber.getNumerator());
        assertEquals(5, mixedNumber.getDenominator());
    }

    @Test
    void testMixedNumber_withNumberStringContainsNonNumericDigit_throwsIllegalArgumentException() {
        String numberString = "1a_2/3";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new MixedNumber(numberString));

        assertEquals(String.format("%s does not represent a valid mixed number", numberString), exception.getMessage());
    }

    @Test
    void testMixedNumber_withInvalidNumberString_throwsIllegalArgumentException() {
        String numberString = "1_1_2/3";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new MixedNumber(numberString));

        assertEquals(String.format("%s does not represent a valid mixed number", numberString), exception.getMessage());
    }

    @Test
    void testMixedNumber_withImproperFraction() {
        ImproperFraction improperFraction = new ImproperFraction(8, 3);

        MixedNumber mixedNumber = new MixedNumber(improperFraction);

        assertEquals(2, mixedNumber.getWholeNumber());
        assertEquals(2, mixedNumber.getNumerator());
        assertEquals(3, mixedNumber.getDenominator());
    }

    @Test
    void testToString_whenNumeratorEqualsZero() {
        MixedNumber mixedNumber = new MixedNumber(3,0,1);

        assertEquals("3", mixedNumber.toString());
    }

    @Test
    void testToString_whenWholeNumberEqualsZero() {
        MixedNumber mixedNumber = new MixedNumber(0,2,3);

        assertEquals("2/3", mixedNumber.toString());
    }

    @Test
    void testToString_whenWholeNumberNumeratorDenominatorGreaterThanOne() {
        MixedNumber mixedNumber = new MixedNumber(1,2,3);

        assertEquals("1_2/3", mixedNumber.toString());
    }

    @Test
    void testToString_whenNumberIsNegative() {
        MixedNumber mixedNumber = new MixedNumber(-2, -3, 4);

        assertEquals("-2_3/4", mixedNumber.toString());
    }
}