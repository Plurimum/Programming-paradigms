package expression.exceptions;

public class UnsupportedTypeException extends Throwable {
    public UnsupportedTypeException(String argument) {
        super("Unsupported type of number: " + argument);
    }
}
