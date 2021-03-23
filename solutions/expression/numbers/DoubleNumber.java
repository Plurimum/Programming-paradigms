package expression.numbers;

import expression.exceptions.ArithmeticParserException;
import expression.exceptions.UnsupportedTypeException;

public class DoubleNumber implements Number<Double> {

    @Override
    public Double add(Double left, Double right) {
        return left + right;
    }

    @Override
    public Double subtract(Double left, Double right) {
        return left - right;
    }

    @Override
    public Double divide(Double left, Double right) {
        return left / right;
    }

    @Override
    public Double multiply(Double left, Double right) {
        return left * right;
    }

    @Override
    public Double min(Double left, Double right) {
        return Double.min(left, right);
    }

    @Override
    public Double max(Double left, Double right) {
        return Double.max(left, right);
    }

    @Override
    public Double mod(Double left, Double right) {
        return left % right;
    }

    @Override
    public Double abs(Double argument) {
        return Math.abs(argument);
    }

    @Override
    public Double negate(Double argument) {
        return -argument;
    }

    @Override
    public Double square(Double argument) {
        return argument * argument;
    }

    @Override
    public Double count(Double argument) {
        return (double) Long.bitCount(Double.doubleToLongBits(argument));
    }

    @Override
    public Double parseNumber(String argument) throws ArithmeticParserException {
        return Double.parseDouble(argument);
    }
}