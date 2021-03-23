package expression.numbers;

import expression.exceptions.ArithmeticParserException;
import expression.exceptions.UnsupportedTypeException;

public class ShortNumber implements Number<Short> {

    @Override
    public Short add(Short left, Short right) {
        return (short) (left + right);
    }

    @Override
    public Short subtract(Short left, Short right) {
        return (short) (left - right);
    }

    @Override
    public Short divide(Short left, Short right) {
        return (short) (left / right);
    }

    @Override
    public Short multiply(Short left, Short right) {
        return (short) (left * right);
    }

    @Override
    public Short min(Short left, Short right) {
        return (short) Math.min(left, right);
    }

    @Override
    public Short max(Short left, Short right) {
        return (short) Math.max(left, right);
    }

    @Override
    public Short mod(Short left, Short right) {
        return (short) (left % right);
    }

    @Override
    public Short abs(Short argument) {
        return (short) Math.abs(argument);
    }

    @Override
    public Short negate(Short argument) {
        return (short) -argument;
    }

    @Override
    public Short square(Short argument) {
        return (short) (argument * argument);
    }

    @Override
    public Short count(Short argument) {
        return (short) Integer.bitCount(argument & 0xffff);
    }

    @Override
    public Short parseNumber(String argument) throws ArithmeticParserException {
        return (short) Integer.parseInt(argument);
    }
}