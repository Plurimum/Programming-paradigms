package expression.operation;

import expression.TripleExpression;
import expression.numbers.Number;

public class Min<T> extends AbstractBinaryOperation<T> {
    public Min(TripleExpression<T> left, TripleExpression<T> right) {
        super(left, right);
    }

    @Override
    protected T doOperation(T left, T right, Number<T> operation) {
        return operation.min(left, right);
    }
}
