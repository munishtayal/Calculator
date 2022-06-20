package com.example.calculator.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MathsUtilTest {

    @Test
    void testGetGCD_whenGCDisOne() {
        long gcd = MathsUtil.getGCD(11, 7);
        assertEquals(1, gcd);
    }

    @Test
    void testGetGCD_whenGCDisNotOne() {
        long gcd = MathsUtil.getGCD(6, 4);
        assertEquals(2, gcd);
    }

    @Test
    void testGetGCD_whenBothNumbersNegative() {
        long gcd = MathsUtil.getGCD(-6, -4);
        assertEquals(2, gcd);
    }

    @Test
    void testGetGCD_whenOneNumbersNegative() {
        long gcd = MathsUtil.getGCD(6, -4);
        assertEquals(2, gcd);
    }
}