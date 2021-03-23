package expression.numbers;

import expression.exceptions.ArithmeticParserException;
import expression.exceptions.FormatParserException;
import expression.exceptions.UnsupportedTypeException;

public interface Number<T> {
    T add(final T left, final T right);

    T subtract(final T left, final T right);

    T divide(final T left, final T right);

    T multiply(final T left, final T right);

    T mod(final T left, final T right);

    T abs(final T argument);

    T negate(final T argument);

    T square(final T argument);

    T count (final T argument);

    T min(final T left, final T right);

    T max(final T left, final T right);

    T parseNumber(final String argument) throws ArithmeticParserException;
}
