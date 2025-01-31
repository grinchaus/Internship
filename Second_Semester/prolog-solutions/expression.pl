lookup(K, [(K, V) | _], V).
lookup(K, [_ | T], V) :- lookup(K, T, V).

variable(Name, variable(Name)).
const(Value, const(Value)).

operation(op_add, A, B, R) :- R is A + B.
operation(op_subtract, A, B, R) :- R is A - B.
operation(op_multiply, A, B, R) :- R is A * B.
operation(op_divide, A, B, R) :- R is A / B.
operation(op_negate, A, R) :- R is -1 * A.
operation(op_square , A, R) :- R is A * A.
operation(op_sqrt, A, R) :- R is sqrt(abs(A)).

evaluate(const(Value), _, Value).
evaluate(variable(Name), Vars, R) :- lookup(Name, Vars, R).
evaluate(operation(Op, A), Vars, R) :-
    evaluate(A, Vars, AV),
    operation(Op, AV, R), !.
evaluate(operation(Op, A, B), Vars, R) :-
    evaluate(A, Vars, AV),
    evaluate(B, Vars, BV),
    operation(Op, AV, BV, R).

nonvar(V, _) :- var(V).
nonvar(V, T) :- nonvar(V), call(T).

:- load_library('alice.tuprolog.lib.DCGLibrary').

expr_p(variable(Name)) --> [Name], { member(Name, [x, y, z]) }.

expr_p(const(Value)) -->
  { nonvar(Value, number_chars(Value, Chars)) },
  digits_p(Chars),
  { Chars = [_ | _], number_chars(Value, Chars) }.

digits_p([]) --> [].
digits_p([H | T]) -->
  { member(H, ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'])},
  [H],
  digits_p(T).
digits_p(['-', H | T]) -->
	{ member(H, ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9'])},
  ['-'], [H],
  digits_p(T).

op_p(op_add) --> ['+'].
op_p(op_subtract) --> ['-'].
op_p(op_multiply) --> ['*'].
op_p(op_divide) --> ['/'].
op_p(op_negate) --> ['n', 'e', 'g', 'a', 't', 'e'].
op_p(op_square) --> ['s', 'q', 'u', 'a', 'r', 'e'].
op_p(op_sqrt) --> ['s', 'q', 'r', 't'].

expr_p(operation(Op, A)) --> ['('], op_p(Op), [' '], expr_p(A), [')'].
expr_p(operation(Op, A, B)) --> ['('], op_p(Op), [' '], expr_p(A), [' '], expr_p(B), [')'].

prefix_str(E, A) :- ground(E), phrase(expr_p(E), C), atom_chars(A, C).
prefix_str(E, A) :-   atom(A), atom_chars(A, Ch), remove_spaces(Ch, Ch1), phrase(expr_p(E), Ch1).

remove_spaces(Input, Output) :-
    remove_double_space(Input, In1),
    remove_right(In1, In2),
    remove_left(In2, Output).

remove_right([' '], []) :- !.
remove_right([H], [H]) :- !.
remove_right([H | T], [H | S]) :- remove_right(T, S).

remove_left([' ' | T], S) :- remove_left(T, S).
remove_left(S, S).

remove_double_space([], []) :- !.
remove_double_space([' ', ' '| T], S):- remove_double_space([' ' | T], S), !.
remove_double_space(['(', ' '| T], S):- remove_double_space(['(' | T], S), !.
remove_double_space([' ', ')'| T], S):- remove_double_space([')' | T], S), !.
remove_double_space([H | T], [H | S]):- remove_double_space(T, S).