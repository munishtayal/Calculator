package com.example.calculator.calculators;

import static com.example.calculator.Constants.OPERATION;
import static com.example.calculator.Constants.OPERATORS_AND_OPERANDS_SEP;
import static com.example.calculator.Constants.SUPPORTED_OPERATIONS;

/**
 * Abstract class that accepts the mathematical expression in string format and calculates that expression.
 * The methods which will perform the operations like add, subtract are left to child classes to implement.
 */
public abstract class Calculator<T> {
    // The below methods will be implemented by child classes based on type of object T
    /**
     * Returns the result of addition of two operands.
     */
    abstract T add(T operand1, T operand2);

    /**
     * Returns the result of subtracting operand 2 from operand 1.
     */
    abstract T subtract(T operand1, T operand2);

    /**
     * Returns the result of multiplication of two operands.
     */
    abstract T multiply(T operand1, T operand2);

    /**
     * Returns the result of dividing operand 1 by operand 2.
     */
    abstract T divide(T operand1, T operand2);

    /**
     * Creates a instance of {@link T} from it's string representation.
     */
    abstract T parseOperand(String numberString);

    /**
     * Calculates the given mathematical expression.
     * @param expression mathematical expression where operator and operands are separated by
     * {@value com.example.calculator.Constants#OPERATORS_AND_OPERANDS_SEP}. Example - "1_2/3 * 4_5/6"
     * @return result of the mathematical expression.
     */
    public String calculate(String expression) {
        String[] tokens = expression.trim().split(OPERATORS_AND_OPERANDS_SEP); // Split the operator and operands

        // A valid expression should have 2 tokens and 1 operand and the operand should be a single character.
        if (tokens.length != 3 || tokens[1].length() != 1) {
            throw new IllegalArgumentException(String.format("%s does not represent a supported maths expression.", expression));
        }

        if (!SUPPORTED_OPERATIONS.contains(tokens[1].charAt(0))) {
            throw new UnsupportedOperationException(String.format("Operation %s is not supported by calculator.", tokens[1].charAt(0)));
        }

        T operand1 = this.parseOperand(tokens[0]);
        T operand2 = this.parseOperand(tokens[2]);
        OPERATION operation = OPERATION.get(tokens[1].charAt(0));

        return applyOperation(operand1, operand2, operation).toString();
    }

    /**
     * Applies the operation on the operands.
     * @param operand1 operand 1 on which operation is to be applied.
     * @param operand2 operand 2 on which operation is to be applied
     * @param operation operation that is to be applied.
     * @return result of the operation after being applied on operands
     * @throws UnsupportedOperationException If the operation is not supported by the calculator.
     */
    private T applyOperation(T operand1, T operand2, OPERATION operation) {
        switch (operation) {
            case ADD:
                return add(operand1, operand2);
            case SUBTRACT:
                return subtract(operand1, operand2);
            case MULTIPLY:
                return multiply(operand1, operand2);
            case DIVIDE:
                return divide(operand1, operand2);
            default:
                throw new UnsupportedOperationException(String.format("Operation %s is not supported by calculator.", operation));
        }
    }
}
