package expression.operation;

import expression.TripleExpression;
import expression.numbers.Number;

public class Max<T> extends AbstractBinaryOperation<T>{
    public Max(TripleExpression<T> left, TripleExpression<T> right) {
        super(left, right);
    }

    @Override
    protected T doOperation(T left, T right, Number<T> operation) {
        return operation.max(left, right);
    }
}
