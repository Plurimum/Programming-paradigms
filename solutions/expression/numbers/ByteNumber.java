package expression.numbers;

import expression.exceptions.ArithmeticParserException;

import java.io.ByteArrayInputStream;

public class ByteNumber implements Number<Byte> {
    @Override
    public Byte add(Byte left, Byte right) {
        return (byte) (left + right);
    }

    @Override
    public Byte subtract(Byte left, Byte right) {
        return (byte) (left - right);
    }

    @Override
    public Byte divide(Byte left, Byte right) {
        return (byte) (left / right);
    }

    @Override
    public Byte multiply(Byte left, Byte right) {
        return (byte) (left * right);
    }

    @Override
    public Byte mod(Byte left, Byte right) {
        return (byte) (left % right);
    }

    @Override
    public Byte abs(Byte argument) {
        return (byte) Math.abs(argument);
    }

    @Override
    public Byte negate(Byte argument) {
        return (byte) -argument;
    }

    @Override
    public Byte square(Byte argument) {
        return null;
    }

    @Override
    public Byte count(Byte argument) {
        return (byte) Integer.bitCount(argument & 0xff);
    }

    @Override
    public Byte min(Byte left, Byte right) {
        return (byte) Math.min(left, right);
    }

    @Override
    public Byte max(Byte left, Byte right) {
        return (byte) Math.max(left, right);
    }

    @Override
    public Byte parseNumber(String argument) throws ArithmeticParserException {
        return (byte) Integer.parseInt(argument);
    }
}
