package expression.parser;

import expression.*;
import expression.exceptions.*;
import expression.numbers.Number;
import expression.object.Const;
import expression.object.Variable;
import expression.operation.*;

public class ExpressionParser<T> implements GenericParser<T> {
    private static final char END = '\0';
    private String expression;
    private int curPos;
    private int len;

    private Number<T> parser;

    public ExpressionParser(Number<T> parser) {
        this.parser = parser;
    }

    private boolean checkPos() {
        return curPos < len;
    }

    private String getExpression() {
        return expression.substring(0, len - 1);
    }

    private char curChar() {
        return expression.charAt(curPos);
    }

    private void next() {
        curPos++;
        skipSpace();
    }

    private void skipSpace() {
        while (checkPos() && Character.isWhitespace(curChar())) {
            curPos++;
        }
    }

    private void expect(char symbol) throws FormatParserException {
        if (checkPos() && symbol != curChar()) {

            if (Character.isDigit(curChar())) {
                throw new MissingOperationException(curPos, getExpression());
            }

            if (curChar() == END) {
                throw new MissingParenthesis("closing", curPos, getExpression());
            } else {
                throw new UnexpectedCharacter(curChar(), curPos, getExpression());
            }
        } else {
            next();
        }
    }

    private void nextDigit() {
        curPos++;
    }

    private String getNumber() {
        StringBuilder number = new StringBuilder();
        while (checkPos() && Character.isDigit(curChar())) {
            number.append(curChar());
            nextDigit();
        }
        skipSpace();
        return number.toString();
    }

    private boolean test(char c) {
        boolean result = curChar() == c;
        if (result) {
            next();
        }
        skipSpace();
        return result;
    }

    private String getIdentifier() {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (index < len && Character.isLetter(expression.charAt(index + curPos))) {
            sb.append(expression.charAt(curPos + index));
            index++;
        }
        return sb.toString();
    }

    private TripleExpression<T> getConst(String number) throws FormatParserException {
        try {
            return new Const<>(parser.parseNumber(number));
        } catch (NumberFormatException NFE) {
            throw new FormatParserException("Illegal constant at index " +
                    (curPos - number.length() + 1) + "\n" + number + "\n");
        }
    }

    private TripleExpression<T> resultInBrackets() throws FormatParserException {
        next();
        TripleExpression<T> currentResult = addAndSub();
        if (!checkPos()) {
            throw new MissingParenthesis("closing", curPos, getExpression());
        }
        expect(')');
        return currentResult;
    }

    private TripleExpression<T> addAndSub() throws FormatParserException {
        TripleExpression<T> currentResult = mulAndDiv();
        while (true) {
            if (test('+')) {
                currentResult = new Add<>(currentResult, mulAndDiv());
            } else if (test('-')) {
                currentResult = new Subtract<>(currentResult, mulAndDiv());
            } else {
                return currentResult;
            }
        }
    }

    private TripleExpression<T> minAndMax() throws FormatParserException{
        TripleExpression<T> currentResult = addAndSub();
        while (true){
            switch (getIdentifier()){
                case "min":
                    curPos += 3;
                    currentResult = new Min<>(currentResult, addAndSub());
                    break;
                case "max":
                    curPos += 3;
                    currentResult = new Max<>(currentResult, addAndSub());
                    break;
                default:
                    return currentResult;
            }
        }
    }

    private TripleExpression<T> mulAndDiv() throws FormatParserException {
        TripleExpression<T> currentResult = unary();
        while (true) {
            if (test('*')) {
                currentResult = new Multiply<>(currentResult, unary());
            } else if (test('/')) {
                currentResult = new Divide<>(currentResult, unary());
            } else {
                return currentResult;
            }
        }
    }

    private TripleExpression<T> unary() throws FormatParserException {
        skipSpace();
        char c = curChar();
        switch (c) {
            case '(':
                return resultInBrackets();
            case '-':
                next();
                if (Character.isDigit(curChar())) {
                    String number = getNumber();
                    return getConst("-" + number);
                } else {
                    return new Negate<>(unary());
                }
            case 'x':
            case 'y':
            case 'z':
                next();
                return new Variable<>(String.valueOf(c));
            case 'c':
                curPos += 5;
                return new Count<>(unary());
            default:
                if (Character.isDigit(curChar())) {
                    return getConst(getNumber());
                } else {
                    throw new MissingArgumentException(curPos, getExpression());
                }
        }
    }

    public TripleExpression<T> parse(String s) throws FormatParserException, ArithmeticParserException {
        curPos = 0;
        expression = s + END;
        len = expression.length();
        TripleExpression<T> result = minAndMax();
        expect(END);
        return result;
    }
}