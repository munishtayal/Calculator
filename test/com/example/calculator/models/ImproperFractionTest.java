package com.example.calculator.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ImproperFractionTest {

    @Test
    void testImproperFraction_withMixedNumber() {
        MixedNumber mixedNumber = new MixedNumber(3, 3, 4);

        ImproperFraction improperFraction = new ImproperFraction(mixedNumber);

        assertEquals(15, improperFraction.getNumerator());
        assertEquals(4, improperFraction.getDenominator());
    }
    @Test
    void testSimplifyFraction_whenNumeratorAndDenominatorHaveGCD() {
        ImproperFraction improperFraction = new ImproperFraction(6, 4);

        improperFraction.simplifyFraction();

        assertEquals(3, improperFraction.getNumerator());
        assertEquals(2, improperFraction.getDenominator());
    }
}