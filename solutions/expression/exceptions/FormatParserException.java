package expression.exceptions;


public class FormatParserException extends Exception {
    public FormatParserException(String s) {
        super(s);
    }

    static String mark(int pos) {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < pos; i++) {
            sb.append(" ");
        }
        sb.append("^");
        return sb.toString();
    }
}