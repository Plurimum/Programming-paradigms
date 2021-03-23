"use strict";

const vars = {
    'x': 0,
    'y': 1,
    'z': 2
};

function OperationFactory(op, opName) {
    const Operation = function (...args) {
        this.args = args;
    };
    Operation.prototype.evaluate = function (...values) {
        return op(...this.args.map((arg) => arg.evaluate(...values)));
    };
    Operation.prototype.toString = function () {
        return this.args.join(" ") + " " + opName;
    };
    Operation.prototype.prefix = function () {
        return "(" + opName + " " + this.args.map((curArg) => curArg.prefix()).join(" ") + ")";
    };
    Operation.prototype.postfix = function () {
        return "(" + this.args.map((curArg) => curArg.postfix()).join(" ") + " " + opName + ")";
    }
    return Operation;
}

function OperandFactory(evalFunc) {
    const Operand = function (value) {
        this.value = value;
    };
    Operand.prototype.evaluate = evalFunc;

    Operand.prototype.toString = function () {
        return this.value.toString();
    };
    Operand.prototype.prefix = Operand.prototype.toString;
    Operand.prototype.postfix = Operand.prototype.toString;
    return Operand;
}

const Const = new OperandFactory(function () {
    return this.value;
});

const Variable = new OperandFactory(function (...values) {
    const ind = vars[this.value];
    return values[ind];
});

const Add = OperationFactory((x, y) => x + y, "+");

const Subtract = OperationFactory((x, y) => x - y, "-");

const Multiply = OperationFactory((x, y) => x * y, "*");

const Divide = OperationFactory((x, y) => x / y, "/");

const Negate = OperationFactory((x) => -x, "negate");

const ArcTan = OperationFactory((x) => Math.atan(x), "atan");

const Exp = OperationFactory((x) => Math.exp(x), "exp");

const Sum = OperationFactory((...values) => values.reduce((a, b) => a + b, 0), 'sum');

const Avg = OperationFactory((...values) => values.reduce((a, b) => a + b, 0) / values.length, 'avg');

const Sinh = OperationFactory((x) => Math.sinh(x), "sinh");

const Cosh = OperationFactory((x) => Math.cosh(x), "cosh");

function opsFill(name, op, argsCnt) {
    ops[name] = {Op: op, numOfArgs: argsCnt};
}

let ops = {};

opsFill("+", Add, 2);
opsFill("*", Multiply, 2);
opsFill("-", Subtract, 2);
opsFill("/", Divide, 2);
opsFill("negate", Negate, 1);
opsFill("atan", ArcTan, 1);
opsFill("exp", Exp, 1);
opsFill("sum", Sum, undefined);
opsFill("avg", Avg, undefined);
opsFill("sinh", Sinh, 1);
opsFill("cosh", Cosh, 1);

const ParseError = function (message, expression, index) {
    this.name = "ParsingException";
    if (arguments.length > 2) {
        this.message = "Expected " + message + ', found ' + expression[index - 1]
            + " at index " + index + '\n"' + expression + '"\n';
    } else {
        this.message = message + '\n"' + expression + '"\n';
    }
};

ParseError.prototype = Error.prototype;

const Tokenizer = function (string) {
    this.index = 0;
    this.prevToken = '';
    this.curToken = '';

    const isWhitespace = function (c) {
        return /\s/.test(c);
    };

    this.checkPos = () => this.index < string.length;

    this.symbol = () => string[this.index];

    this.isBracket = () => this.symbol() === ')' || this.symbol() === '(';

    this.next = () => {
        this.index++;
    };

    this.nextToken = function () {
        this.prevToken = this.curToken;
        while (this.index < string.length && isWhitespace(string[this.index])) {
            this.index++;
        }
        this.curToken = '';
        if (this.isBracket()) {
            this.curToken = this.symbol();
            this.next();
        } else {
            while (this.checkPos() && !(this.isBracket() || isWhitespace(this.symbol()))) {
                this.curToken += this.symbol();
                this.next();
            }
        }
    };
};

const parseOperand = function (tokenizer, parseExpression, expression) {
    if (tokenizer.curToken === '(') {
        return parseExpression();
    } else if (tokenizer.curToken in vars) {
        tokenizer.nextToken();
        return new Variable(tokenizer.prevToken)
    } else if (tokenizer.curToken !== '' && !isNaN(tokenizer.curToken)) {
        tokenizer.nextToken();
        return new Const(parseInt(tokenizer.prevToken, 10));
    } else {
        throw new ParseError('operand', expression, tokenizer.index,);
    }
};

const parsePrefix = function (expression) {
    const tokenizer = new Tokenizer(expression);

    if (expression === '') {
        throw new ParseError('Expression is not defined', '');
    }

    let parseExpression = function () {
        if (tokenizer.curToken === '(') {
            tokenizer.nextToken();
            if (!(tokenizer.curToken in ops)) {
                throw new ParseError('operation', expression, tokenizer.index);
            }
            const opParams = ops[tokenizer.curToken];
            tokenizer.nextToken();
            let curArgs = [];
            while (tokenizer.curToken !== ')') {
                curArgs.push(parseOperand(tokenizer, parseExpression, expression));
                if (tokenizer.curToken === '') {
                    throw new ParseError('Missing closing parenthesis', expression);
                }
            }
            const argsCnt = opParams.numOfArgs;
            const curArgsCnt = curArgs.length;
            if (!isNaN(argsCnt) && argsCnt !== curArgsCnt) {
                throw new ParseError('Expected ' + argsCnt + ' operand(s), found ' + curArgsCnt, expression);
            }
            tokenizer.nextToken();
            return new opParams.Op(...curArgs);
        } else {
            return parseOperand(tokenizer, parseExpression, expression);
        }
    };

    tokenizer.nextToken();
    const result = parseExpression();
    if (tokenizer.curToken !== '') {
        throw new ParseError('end of expression', expression, tokenizer.index);
    }
    return result;
};

const parsePostfix = function (expression) {
    const tokenizer = new Tokenizer(expression);

    if (expression === '') {
        throw new ParseError('Expression is not defined', '');
    }

    let parseExpression = function () {
        if (tokenizer.curToken === '(') {
            tokenizer.nextToken();
            let curArgs = [];
            while (!(tokenizer.curToken in ops)) {
                curArgs.push(parseOperand(tokenizer, parseExpression, expression));
                if (tokenizer.curToken === ')') {
                    throw new ParseError('operation', expression, tokenizer.index);
                }
            }
            const opParams = ops[tokenizer.curToken];
            const argsCnt = opParams.numOfArgs;
            const curArgsCnt = curArgs.length;
            if (!isNaN(argsCnt) && argsCnt !== curArgsCnt) {
                throw new ParseError('Expected ' + argsCnt + ' operand(s), found ' + curArgsCnt, expression);
            }
            tokenizer.nextToken();
            if (tokenizer.curToken !== ')') {
                throw new ParseError('Missing closing parenthesis', expression);
            }
            tokenizer.nextToken();
            return new opParams.Op(...curArgs);
        } else {
            return parseOperand(tokenizer, parseExpression, expression);
        }
    };

    tokenizer.nextToken();
    const result = parseExpression();
    if (tokenizer.curToken !== '') {
        throw new ParseError('end of expression', expression, tokenizer.index);
    }
    return result;
};
