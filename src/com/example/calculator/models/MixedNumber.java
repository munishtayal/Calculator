package com.example.calculator.models;

import lombok.Getter;
import lombok.NonNull;

/**
 * This class represents a mixed number.
 * A mixed number have following properties -
 *      1. It is partly a whole number.
 *      2. It is partly a proper fraction.
 */
@Getter
public class MixedNumber {
    private long wholeNumber;
    private long numerator;
    private long denominator;

    /**
     * Initializes the number to 0 by setting whole number and numerator to 0 and denominator to 1.
     */
    public MixedNumber() {
        wholeNumber = numerator = 0;
        denominator = 1;
    }

    /**
     * Initializes the number using provided parameters
     */
    public MixedNumber(long wholeNumber, long numerator, long denominator) {
        this.wholeNumber = wholeNumber;
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Initializes the number by parsing the input string.
     * @param numberString string which represents mixed number.
     * @throws IllegalArgumentException If the input does not represent a mixed number.
     */
    public MixedNumber(@NonNull String numberString) {
        this(); // Initialize the number to 0 first.

        try {
            String[] temp = numberString.trim().split("[_/]");
            if (temp.length == 1) {  // represents whole number like "2"
                wholeNumber = Long.parseLong(temp[0]);
            } else if (temp.length == 2) { // represents fraction like "2/3"
                numerator = Long.parseLong(temp[0]);
                denominator = Long.parseLong(temp[1]);
            } else if (temp.length == 3) { // represents mixed number like "1_2/3"
                wholeNumber = Long.parseLong(temp[0]);
                numerator = Long.parseLong(temp[1]);
                denominator = Long.parseLong(temp[2]);
            } else {
                throw new IllegalArgumentException(String.format("%s does not represent a valid mixed number", numberString));
            }

            // If the number string represents a negative mix number, the numerator should also be set to negative.
            // Example - for mixed number represented as "-1_2/3", the numerator whole number is -1, numerator is -2 and
            // denominator is 3.
            if (wholeNumber < 0) {
                numerator *= -1;
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("%s does not represent a valid mixed number", numberString));
        }
    }

    /**
     * Creates a instance of MixedNumber by converting the improper fraction.
     * @param improperFraction improper fraction which will be converted into mixed number
     */
    public MixedNumber(@NonNull final ImproperFraction improperFraction) {
        this.wholeNumber = improperFraction.getNumerator() / improperFraction.getDenominator();
        this.numerator = improperFraction.getNumerator() % improperFraction.getDenominator();
        this.denominator = (numerator == 0) ? 1 :  improperFraction.getDenominator();
    }

    @Override
    public String toString() {
        if (numerator == 0) {
            // If the numerator is 0, that means there's no fractional component.
            return String.format("%d", wholeNumber);
        } else if (wholeNumber == 0) {
            // If whole number is 0 and fractional component is present, then print fractional component only.
            return String.format("%d/%d", numerator, denominator);
        } else {
            // If whole number as well as fractional component is present, then print both.
            // If the mixed number is a negative number, both whole number and numerator will have negative value, so
            // don't show the negative sign with numerator.
            return String.format("%d_%d/%d", wholeNumber, Math.abs(numerator), denominator);
        }
    }
}
