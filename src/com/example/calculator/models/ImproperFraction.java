package com.example.calculator.models;

import com.example.calculator.utils.MathsUtil;

import lombok.Getter;
import lombok.NonNull;

/**
 * This class represents a improper fraction.
 * A improper fraction is a fraction in which the numerator is greater than the denominator, such as 5/4
 */
@Getter
public class ImproperFraction {
    private long numerator;
    private long denominator;

    public ImproperFraction(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Initializes the fraction using the mixed number.
     */
    public ImproperFraction(@NonNull final MixedNumber mixedNumber) {
        this.numerator = (mixedNumber.getWholeNumber() * mixedNumber.getDenominator()) + mixedNumber.getNumerator();
        this.denominator = mixedNumber.getDenominator();
    }

    /**
     * Simplifies the fraction by dividing the numerator and denominator by their greatest common divisor.
     * @return same ImproperFraction object after simplifying the fraction.
     */
    public ImproperFraction simplifyFraction() {
        long gcd = MathsUtil.getGCD(numerator, denominator);
        numerator = numerator/gcd;
        denominator = denominator/gcd;

        return this;
    }
}
