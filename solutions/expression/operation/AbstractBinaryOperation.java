package expression.operation;

import expression.TripleExpression;
import expression.exceptions.ArithmeticParserException;
import expression.exceptions.FormatParserException;
import expression.numbers.Number;

public abstract class AbstractBinaryOperation<T> implements TripleExpression<T> {
    private final TripleExpression<T> left;
    private final TripleExpression<T> right;

    public AbstractBinaryOperation(TripleExpression<T> left, TripleExpression<T> right) {
        this.left = left;
        this.right = right;
    }

    protected abstract T doOperation(T left, T right, Number<T> operation);


    public T evaluate(T x, T y, T z, Number<T> operation) throws ArithmeticParserException, FormatParserException {
        return doOperation(left.evaluate(x, y, z, operation), right.evaluate(x, y, z, operation), operation);
    }
}