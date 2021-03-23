package expression.numbers;

import expression.exceptions.ArithmeticParserException;
import expression.exceptions.UnsupportedTypeException;

public class FloatNumber implements Number<Float> {

    @Override
    public Float add(Float left, Float right) {
        return left + right;
    }

    @Override
    public Float subtract(Float left, Float right) {
        return left - right;
    }

    @Override
    public Float divide(Float left, Float right) {
        return left / right;
    }

    @Override
    public Float multiply(Float left, Float right) {
        return left * right;
    }

    @Override
    public Float min(Float left, Float right) {
        return Float.min(left, right);
    }

    @Override
    public Float max(Float left, Float right) {
        return Float.max(left, right);
    }

    @Override
    public Float mod(Float left, Float right) {
        return left % right;
    }

    @Override
    public Float abs(Float argument) {
        return Math.abs(argument);
    }

    @Override
    public Float negate(Float argument) {
        return -argument;
    }

    @Override
    public Float square(Float argument) {
        return argument * argument;
    }

    @Override
    public Float count(Float argument) {
        return (float) Integer.bitCount(Float.floatToIntBits(argument));
    }

    @Override
    public Float parseNumber(String argument) throws ArithmeticParserException {
        return Float.parseFloat(argument);
    }
}