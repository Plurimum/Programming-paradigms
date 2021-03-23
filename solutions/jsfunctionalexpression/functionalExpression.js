"use strict"

var VARIABLES = {
    "x": 0,
    "y": 1,
    "z": 2
}
var binaryFunction = ((h) => (f, g) => (...args) => h(f(...args), g(...args)))

var unaryFunction = ((h) => (f) => (...args) => h(f(...args)))

var cnst = ((arg) => () => arg)

var variable = ((arg) => () => arguments[VARIABLES[arg]])

var add = binaryFunction((x, y) => x + y)

var subtract = binaryFunction((x, y) => x - y)

var multiply = binaryFunction((x, y) => x * y)

var divide = binaryFunction((x, y) => x / y)

var mod = binaryFunction((x, y) => x % y)

var negate = unaryFunction((x) => -x)

var isNumber = ((n) => !isNaN(parseInt(n)))

var parse = function (expression) {
    var tokens = expression.split(' ');
    var stack = [];
    var tokensLen = tokens.length;
    for (var i = 0; i < tokensLen; i++) {
        switch (tokens[i]) {
            case "+":
                var summand = stack.pop();
                stack.push(add(stack.pop(), summand));
                break;
            case "-":
                var minuend = stack.pop();
                stack.push(subtract(stack.pop(), minuend));
                break;
            case "*":
                var multiplier = stack.pop();
                stack.push(multiply(stack.pop(), multiplier));
                break;
            case "/":
                var denominator = stack.pop();
                stack.push(divide(stack.pop(), denominator));
                break;
            case "%":
                var a = stack.pop();
                stack.push(mod(stack.pop(), a));
                break;
            case "negate":
                stack.push(negate(stack.pop()));
                break;
            case "":
                break;
            default:
                if (isNumber(tokens[i])) {
                    stack.push(cnst(parseInt(tokens[i])))
                } else {
                    stack.push(variable(tokens[i]))
                }
        }
    }
    return stack.pop()
};