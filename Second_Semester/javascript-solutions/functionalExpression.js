"use strict";
const Binaryoperation = f => (a, b) => (...arg) => f(a(...arg), b(...arg))
const Unaryoperation = f => (a) => (...arg) => f(a(...arg))
const add = Binaryoperation((x, y) => x + y)
const subtract = Binaryoperation((x, y) => x - y)
const multiply = Binaryoperation((x, y) => x * y)
const divide = Binaryoperation((x, y) => x / y)
const negate = Unaryoperation((x) => -x)
const square = Unaryoperation((x) => x * x)
const sqrt = Unaryoperation((x) => Math.sqrt(Math.abs(x)))
const cnst = num => () => num
const variables = ["x", "y", "z"]
const variable = symbol => (...arg) => arg[variables.indexOf(symbol)]
const pi = cnst(Math.PI)
const e = cnst(Math.E)