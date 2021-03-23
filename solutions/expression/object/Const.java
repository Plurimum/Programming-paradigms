package expression.object;

import expression.TripleExpression;
import expression.numbers.Number;

public class Const<T> implements TripleExpression<T> {
    private final T value;

    public Const(T value) {
        this.value = value;
    }

    public T evaluate(T x, T y, T z, Number<T> operation) {
        return value;
    }
}