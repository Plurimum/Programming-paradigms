package expression.exceptions;

public class MissingOperationException extends FormatParserException {

    public MissingOperationException(int pos, String expression) {

        super("Can't find operation at index " + (pos + 1) + "\n" + expression + mark(pos));
    }
}
