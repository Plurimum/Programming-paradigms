package expression.numbers;

import expression.exceptions.ArithmeticParserException;
import expression.exceptions.UnsupportedTypeException;

public class LongNumber implements Number<Long> {

    @Override
    public Long add(Long left, Long right) {
        return left + right;
    }

    @Override
    public Long subtract(Long left, Long right) {
        return left - right;
    }

    @Override
    public Long divide(Long left, Long right) {
        return left / right;
    }

    @Override
    public Long multiply(Long left, Long right) {
        return left * right;
    }

    @Override
    public Long min(Long left, Long right) {
        return Long.min(left, right);
    }

    @Override
    public Long max(Long left, Long right) {
        return Long.max(left, right);
    }

    @Override
    public Long mod(Long left, Long right) {
        return left % right;
    }

    @Override
    public Long abs(Long argument) {
        return Math.abs(argument);
    }

    @Override
    public Long negate(Long argument) {
        return -argument;
    }

    @Override
    public Long square(Long argument) {
        return argument * argument;
    }

    @Override
    public Long count(Long argument) {
        return (long) Long.bitCount(argument);
    }

    @Override
    public Long parseNumber(String argument) throws ArithmeticParserException {
        return Long.parseLong(argument);
    }
}