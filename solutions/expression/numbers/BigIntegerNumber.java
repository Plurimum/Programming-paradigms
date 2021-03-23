package expression.numbers;

import expression.exceptions.ArithmeticParserException;
import expression.exceptions.UnsupportedTypeException;

import java.math.BigInteger;

public class BigIntegerNumber implements Number<BigInteger> {
    @Override
    public BigInteger add(BigInteger left, BigInteger right) {
        return left.add(right);
    }

    @Override
    public BigInteger subtract(BigInteger left, BigInteger right) {
        return left.subtract(right);
    }

    @Override
    public BigInteger divide(BigInteger left, BigInteger right) {
        return left.divide(right);
    }

    @Override
    public BigInteger multiply(BigInteger left, BigInteger right) {
        return left.multiply(right);
    }

    @Override
    public BigInteger min(BigInteger left, BigInteger right) {
        return left.min(right);
    }

    @Override
    public BigInteger max(BigInteger left, BigInteger right) {
        return left.max(right);
    }

    @Override
    public BigInteger mod(BigInteger left, BigInteger right) {
        return left.mod(right);
    }

    @Override
    public BigInteger abs(BigInteger argument) {
        return argument.abs();
    }

    @Override
    public BigInteger negate(BigInteger argument) {
        return argument.negate();
    }

    @Override
    public BigInteger square(BigInteger argument) {
        return argument.multiply(argument);
    }

    @Override
    public BigInteger count(BigInteger argument) {
        return BigInteger.valueOf(argument.bitCount());
    }

    @Override
    public BigInteger parseNumber(String argument) throws ArithmeticParserException {
        return new BigInteger(argument);
    }
}
