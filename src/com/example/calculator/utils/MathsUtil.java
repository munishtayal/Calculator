package com.example.calculator.utils;

import java.math.BigInteger;

/**
 * Utility class containing maths functions
 */
public class MathsUtil {

    /**
     * Returns the GCD (greatest common divisor) of the two numbers
     */
    public static long getGCD(long num1, long num2){
        BigInteger bigInteger1 = BigInteger.valueOf(num1);
        BigInteger bigInteger2 = BigInteger.valueOf(num2);

        return bigInteger1.gcd(bigInteger2).longValue();
    }
}
