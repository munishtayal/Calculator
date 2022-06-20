package com.example.calculator.calculators;

import com.example.calculator.models.ImproperFraction;
import com.example.calculator.models.MixedNumber;

/**
 * This class extends the abstract class {@link Calculator} to implement mathematical operations for {@link MixedNumber}
 */
public class FractionsCalculator extends Calculator<MixedNumber> {

    /**
     * Returns the result of addition of two mixed numbers represented by {@link MixedNumber}
     */
    @Override
    public MixedNumber add(MixedNumber operand1, MixedNumber operand2) {
        ImproperFraction fraction1 = new ImproperFraction(operand1);
        ImproperFraction fraction2 = new ImproperFraction(operand2);

        long numerator = (fraction1.getNumerator() * fraction2.getDenominator()) + (fraction2.getNumerator() * fraction1.getDenominator());
        long denominator = fraction1.getDenominator() * fraction2.getDenominator();

        ImproperFraction resultantFraction = new ImproperFraction(numerator, denominator).simplifyFraction();
        return new MixedNumber(resultantFraction);
    }

    /**
     * Returns the result of subtraction of two mixed numbers represented by {@link MixedNumber}
     */
    @Override
    public MixedNumber subtract(MixedNumber operand1, MixedNumber operand2) {
        ImproperFraction fraction1 = new ImproperFraction(operand1);
        ImproperFraction fraction2 = new ImproperFraction(operand2);

        long numerator = (fraction1.getNumerator() * fraction2.getDenominator()) - (fraction2.getNumerator() * fraction1.getDenominator());
        long denominator = fraction1.getDenominator() * fraction2.getDenominator();

        ImproperFraction resultantFraction = new ImproperFraction(numerator, denominator).simplifyFraction();
        return new MixedNumber(resultantFraction);
    }

    /**
     * Returns the result of multiplication of two mixed numbers represented by {@link MixedNumber}
     */
    @Override
    public MixedNumber multiply(MixedNumber operand1, MixedNumber operand2) {
        ImproperFraction fraction1 = new ImproperFraction(operand1);
        ImproperFraction fraction2 = new ImproperFraction(operand2);

        long numerator = fraction1.getNumerator() * fraction2.getNumerator();
        long denominator = fraction1.getDenominator() * fraction2.getDenominator();

        ImproperFraction resultantFraction = new ImproperFraction(numerator, denominator).simplifyFraction();
        return new MixedNumber(resultantFraction);
    }

    /**
     * Returns the result of division of two mixed numbers represented by {@link MixedNumber}
     */
    @Override
    public MixedNumber divide(MixedNumber operand1, MixedNumber operand2) {
        ImproperFraction fraction1 = new ImproperFraction(operand1);
        ImproperFraction fraction2 = new ImproperFraction(operand2);

        long numerator = fraction1.getNumerator() * fraction2.getDenominator();
        long denominator = fraction1.getDenominator() * fraction2.getNumerator();

        if (denominator < 0) {
            if (numerator < 0) {
                // If numerator and denominator both are negative, take the absolute value of both
                numerator = Math.abs(numerator);
            } else {
                // If numerator is positive and denominator is negative, make numerator negative and denominator positive
                numerator = numerator * -1;
            }
            denominator = Math.abs(denominator);
        }

        ImproperFraction resultantFraction = new ImproperFraction(numerator, denominator).simplifyFraction();
        return new MixedNumber(resultantFraction);
    }

    /**
     * Returns a instance of {@link MixedNumber} from it's string representation
     */
    @Override
    MixedNumber parseOperand(String numberString) {
        return new MixedNumber(numberString);
    }
}
