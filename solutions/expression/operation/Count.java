package expression.operation;

import expression.TripleExpression;
import expression.numbers.Number;

public class Count<T> extends AbstractUnaryOperation<T> {
    public Count(TripleExpression<T> value) {
        super(value);
    }

    @Override
    public T doOperation(T operand, Number<T> operation) {
        return operation.count(operand);
    }
}
