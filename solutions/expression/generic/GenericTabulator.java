package expression.generic;

import expression.TripleExpression;
import expression.exceptions.ArithmeticParserException;
import expression.exceptions.FormatParserException;
import expression.exceptions.UnsupportedTypeException;
import expression.numbers.*;
import expression.numbers.Number;
import expression.parser.ExpressionParser;

import java.util.Map;
import java.util.function.Function;

public class GenericTabulator implements Tabulator {

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2,
                                 int y1, int y2, int z1, int z2) throws Exception, UnsupportedTypeException {
        Number<?> type = MODES.get(mode);
        if (type != null) {
            return tabulate(expression, x1, x2, y1, y2, z1, z2, type);
        } else {
            throw new UnsupportedTypeException(mode);
        }
    }

    private <T> Object[][][] tabulate(String expression, int x1, int x2,
                                      int y1, int y2, int z1, int z2, Number<T> operation)
            throws ArithmeticParserException, FormatParserException {
        final TripleExpression<T> expr = new ExpressionParser<>(operation).parse(expression);
        final int dX = x2 - x1 + 1;
        final int dY = y2 - y1 + 1;
        final int dZ = z2 - z1 + 1;
        Object[][][] result = new Object[dX][dY][dZ];
        final Function<Integer, T> makeSuitableType = object -> operation.parseNumber(Integer.toString(object));
        for (int x = 0; x < dX; x++) {
            for (int y = 0; y < dY; y++) {
                for (int z = 0; z < dZ; z++) {
                    try {
                        result[x][y][z] = expr.evaluate(makeSuitableType.apply(x + x1), makeSuitableType.apply(y + y1),
                                makeSuitableType.apply(z + z1), operation);
                    } catch (ArithmeticException exception) {
                    }
                }
            }
        }
        return result;
    }

    private Map<String, Number<?>> MODES = Map.of(
            "i", new IntegerNumber(true),
            "d", new DoubleNumber(),
            "bi", new BigIntegerNumber(),
            "f", new FloatNumber(),
            "s", new ShortNumber(),
            "l", new LongNumber(),
            "u", new IntegerNumber(false),
            "b", new ByteNumber()
    );
}
