package expression.operation;

import expression.TripleExpression;
import expression.exceptions.FormatParserException;
import expression.numbers.Number;

public abstract class AbstractUnaryOperation<T> implements TripleExpression<T> {
    private final TripleExpression<T> operand;

    AbstractUnaryOperation(final TripleExpression<T> value) {
        this.operand = value;
    }

    abstract T doOperation(T operand, Number<T> operation);

    public T evaluate(T x, T y, T z, Number<T> operation) throws FormatParserException {
        return doOperation(operand.evaluate(x, y, z, operation), operation);
    }
}