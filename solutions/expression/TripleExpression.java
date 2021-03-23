package expression;

import expression.exceptions.FormatParserException;
import expression.numbers.Number;

public interface TripleExpression<T> {

    T evaluate(T x, T y, T z, Number<T> operation) throws ArithmeticException, FormatParserException;
}