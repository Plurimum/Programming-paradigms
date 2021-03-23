package expression.operation;

import expression.TripleExpression;
import expression.numbers.Number;

public class Add<T> extends AbstractBinaryOperation<T> {
    public Add(TripleExpression<T> left, TripleExpression<T> right) {
        super(left, right);
    }

    @Override
    protected T doOperation(T left, T right, Number<T> operation) {
        return operation.add(left, right);
    }
}