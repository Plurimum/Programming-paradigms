package expression.operation;

import expression.exceptions.ArithmeticParserException;
import expression.TripleExpression;
import expression.numbers.Number;

public class Subtract<T> extends AbstractBinaryOperation<T> {
    public Subtract(TripleExpression<T> left, TripleExpression<T> right) {
        super(left, right);
    }

    protected T doOperation(T left, T right, Number<T> operation) throws ArithmeticParserException {
        return operation.subtract(left, right);
    }
}