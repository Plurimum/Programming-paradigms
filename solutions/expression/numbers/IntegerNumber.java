package expression.numbers;

import expression.exceptions.ArithmeticParserException;
import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;

public class IntegerNumber implements Number<Integer> {

    public boolean checked = true;

    public IntegerNumber(boolean bool) {
        checked = bool;
    }

    private void checkAdd(final Integer x, final Integer y) throws OverflowException {
        if (x > 0 && Integer.MAX_VALUE - x < y) {
            throw new OverflowException();
        }
        if (x < 0 && Integer.MIN_VALUE - x > y) {
            throw new OverflowException();
        }
    }

    public Integer add(final Integer x, final Integer y) throws OverflowException {
        if (checked) {
            checkAdd(x, y);
        }
        return x + y;
    }

    private void checkSub(final Integer x, final Integer y) throws OverflowException {
        if (x >= 0 && y < 0 && x - Integer.MAX_VALUE > y) {
            throw new OverflowException();
        }
        if (x <= 0 && y > 0 && Integer.MIN_VALUE - x > -y) {
            throw new OverflowException();
        }
    }

    public Integer subtract(final Integer x, final Integer y) throws OverflowException {
        if (checked) {
            checkSub(x, y);
        }
        return x - y;
    }

    private void checkMul(final Integer x, final Integer y) throws OverflowException {
        if (x > 0 && y > 0 && Integer.MAX_VALUE / x < y) {
            throw new OverflowException();
        }
        if (x > 0 && y < 0 && Integer.MIN_VALUE / x > y) {
            throw new OverflowException();
        }
        if (x < 0 && y > 0 && Integer.MIN_VALUE / y > x) {
            throw new OverflowException();
        }
        if (x < 0 && y < 0 && Integer.MAX_VALUE / x > y) {
            throw new OverflowException();
        }
    }

    public Integer multiply(final Integer x, final Integer y) throws OverflowException {
        if (checked) {
            checkMul(x, y);
        }
        return x * y;
    }

    @Override
    public Integer min(Integer x, Integer y) {
        return Integer.min(x, y);
    }

    @Override
    public Integer max(Integer x, Integer y) {
        return Integer.max(x, y);
    }

    private void checkDiv(final Integer x, final Integer y) throws OverflowException {
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException();
        }
    }

    private void checkZero(final int y, final String reason) throws ArithmeticParserException {
        if (y == 0) {
            throw new DivisionByZeroException(reason);
        }
    }

    public Integer divide(final Integer x, final Integer y) throws ArithmeticParserException, OverflowException {
        if (checked) {
            checkZero(y, "Division by zero");
            checkDiv(x, y);
        }
        return x / y;
    }

    public Integer mod(final Integer x, final Integer y) throws ArithmeticParserException {
        if (checked) {
            checkZero(y, "Taking module by zero");
        }
        return x % y;
    }

    private void checkNegate(final Integer x) throws OverflowException {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
    }

    public Integer negate(final Integer x) throws OverflowException {
        if (checked) {
            checkNegate(x);
        }
        return -x;
    }

    public Integer square(Integer x) {
        if (checked) {
            checkMul(x, x);
        }
        return x * x;
    }

    @Override
    public Integer count(Integer argument) {
        return Integer.bitCount(argument);
    }

    private void checkAbs(final Integer x) throws OverflowException {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
    }

    public Integer abs(final Integer x) throws OverflowException {
        checkAbs(x);
        return Math.abs(x);
    }

    @Override
    public Integer parseNumber(String argument) throws ArithmeticParserException {
        return Integer.parseInt(argument);
    }
}
