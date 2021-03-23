package expression.parser;

import expression.TripleExpression;
import expression.exceptions.ArithmeticParserException;
import expression.exceptions.FormatParserException;
import expression.exceptions.UnsupportedTypeException;

public interface GenericParser<T>{
    TripleExpression<T> parse(String expression) throws ArithmeticParserException, FormatParserException, UnsupportedTypeException;
}
