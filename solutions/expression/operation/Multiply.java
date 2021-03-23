package expression.operation;

import expression.TripleExpression;
import expression.exceptions.OverflowException;
import expression.numbers.Number;

public class Multiply<T> extends AbstractBinaryOperation<T> {
    public Multiply(TripleExpression left, TripleExpression right) {
        super(left, right);
    }

    @Override
    protected T doOperation(T left, T right, Number<T> operation) throws OverflowException {
        return operation.multiply(left, right);
    }
}