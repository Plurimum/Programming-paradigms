package expression.exceptions;

public class DivisionByZeroException extends ArithmeticParserException{
    public DivisionByZeroException(final String message) {
        super("Division by zero by divide " + message);
    }
}
