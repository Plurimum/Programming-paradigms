package expression.operation;

import expression.TripleExpression;
import expression.exceptions.ArithmeticParserException;
import expression.exceptions.UnderflowException;
import expression.numbers.Number;

public class Negate<T> extends AbstractUnaryOperation<T> {
    public Negate(TripleExpression<T> object) {
        super(object);
    }

    @Override
    protected T doOperation(T argument, Number<T> operation) throws ArithmeticParserException {
        return operation.negate(argument);
    }

}