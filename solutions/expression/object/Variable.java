package expression.object;

import expression.TripleExpression;
import expression.exceptions.FormatParserException;
import expression.numbers.Number;

public class Variable<T> implements TripleExpression<T> {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public T evaluate(T x, T y, T z, Number<T> operation) throws FormatParserException {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                throw new FormatParserException("Incorrect variable name");
        }
    }
}