package expression.operation;

import expression.TripleExpression;
import expression.exceptions.ArithmeticParserException;
import expression.numbers.Number;

public class Divide<T> extends AbstractBinaryOperation<T> {
    public Divide(TripleExpression left, TripleExpression right) {
        super(left, right);
    }

    @Override
    protected T doOperation(T left, T right, Number<T> operation) throws ArithmeticParserException {
        return operation.divide(left, right);
    }
}