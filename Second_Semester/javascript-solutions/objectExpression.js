"use strict";

function MultiOperation(solve, operation, ...args) {
    this.arn = solve.length
    this.evaluate = (...arg) => solve(...args.map(item => item.evaluate.apply(item, arg)))
    this.toString = () => args.join(" ") + " " + operation
    this.prefix = () => "(" + operation + " " + args.map(str => str.prefix()).join(" ") + ")"
}

const MultiUnary = {};

function magic(solve, operation) {
    function superMagic(...args) {
        return new MultiOperation(solve, operation, ...args);
    }

    MultiUnary[operation] = superMagic
    return superMagic
}

const Add = magic((a, b) => a + b, "+")
const Subtract = magic((a, b) => a - b, "-")
const Multiply = magic((a, b) => a * b, "*")
const Divide = magic((a, b) => a / b, "/")
const Negate = magic((a) => -1 * a, "negate")
const Sin = magic((a) => Math.sin(a), "sin")
const Cos = magic((a) => Math.cos(a), "cos")
const Mean = magic((...args) => args.reduce((acc, val) => acc + val, 0) / args.length, "mean")
const Var = magic((...args) => args.reduce((acc, val) => acc + Math.pow(val - args.reduce((acc, val) => acc + val, 0) / args.length, 2), 0) / args.length, "var")

function Const(val) {
    this.evaluate = () => val
    this.toString = () => val.toString()
    this.prefix = () => val.toString()
}

const variables = ["x", "y", "z"]

function Variable(variable) {
    this.evaluate = (...arg) => arg[variables.indexOf(variable)]
    this.toString = () => variable
    this.prefix = () => variable
}

const parse = expression => {
    let expr = expression.split(" ").filter(Boolean)
    let stackOperand = []
    for (let i = 0; i < expr.length; i++) {
        if (variables.includes(expr[i])) {
            stackOperand.push(new Variable(expr[i]))
        } else if (expr[i] in parsBinary) {
            let buff = stackOperand.pop()
            stackOperand.push(new MultiUnary[expr[i]](stackOperand.pop(), buff))
        } else if (expr[i] in MultiUnary) {
            stackOperand.push(new MultiUnary[expr[i]](stackOperand.pop()))
        } else {
            stackOperand.push(new Const(+expr[i]))
        }
    }
    return stackOperand.pop()
}
const parsBinary = {
    "+": Add,
    "-": Subtract,
    "*": Multiply,
    "/": Divide
}
function ParseException(message) {
    this.message = message;
}

ParseException.prototype = Object.create(Error.prototype);

function ParenthesisException(message) {
    this.message = message
}

ParenthesisException.prototype = Object.create(ParseException.prototype);
ParenthesisException.prototype.constructor = ParenthesisException;

function OperatorException(message) {
    this.message = message
}

OperatorException.prototype = Object.create(ParseException.prototype);
OperatorException.prototype.constructor = OperatorException;

function ElementException(message) {
    this.message = message
}

ElementException.prototype = Object.create(ParseException.prototype);
ElementException.prototype.constructor = ElementException;

const parsePrefix = expression => {
    expression = expression.replace(/\(/g, ' ( ').replace(/\)/g, ' ) ')
    let expr = expression.split(" ").filter(Boolean)
    let bracketLevel = 0
    let stackOperand = [[]]
    let stackOperation = []
    for (let i = 0; i < expr.length; i++) {
        if (variables.includes(expr[i])) {
            stackOperand[bracketLevel].push(new Variable(expr[i]))
        } else if (!isNaN(+expr[i])) {
            stackOperand[bracketLevel].push(new Const(+expr[i]))
        } else if (expr[i] === "(") {
            bracketLevel += 1
            stackOperand[bracketLevel] = []
        } else if (expr[i] === ")") {
            if (stackOperation.length !== bracketLevel || bracketLevel === 0) {
                throw new ParenthesisException("Missing parenthesis, pos: " + i)
            }
            let op = stackOperation.pop()
            let copy = stackOperand[bracketLevel]
            if (copy.length !== MultiUnary[op](...copy).arn && MultiUnary[op](...copy).arn !== 0) {
                throw new ElementException("Not the right amount for the operation " + op + " pos: " + i)
            }
            stackOperand[bracketLevel] = []
            stackOperand[--bracketLevel].push(new MultiUnary[op](...copy))
        } else if (expr[i] in MultiUnary) {
            stackOperation.push(expr[i])
        } else {
            throw new ElementException("Not the elements of the expression " + expr[i] + " pos: " + i)
        }
    }
    if (bracketLevel !== 0) {
        throw new OperatorException("There are too many unnecessary operators: " + expression)
    }
    if (stackOperand[bracketLevel].length > 1 || stackOperation.length !== 0 || expression.trim().length === 0) {
        throw new OperatorException("No operation for Variables: \"" + expression+"\"")
    }
    return stackOperand[bracketLevel].pop()
}
