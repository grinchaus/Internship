prime(R) :- R =< 3.
prime(R) :- R > 3, \+ 0 is mod(R,2), prime(R, 3).
prime(R, I) :- I * I > R, !.
prime(R, I) :- \+ 0 is mod(R,I), prime(R, I + 2).

composite(R) :- \+ prime(R).

multiply(N, [], R) :- N = R.
multiply(N, [H | T], R) :- R1 is R * H, multiply(N, T, R1).

good_list(_, []).
good_list(N, [H | T]) :- N =< H, prime(H), good_list(H, T).

unknown_list(N, [N], R) :- R * R > N, !.
unknown_list(N, [R | T], R) :- prime(R), 0 is mod(N, R), N1 is N / R, unknown_list(N1, T, R).
unknown_list(N, T, R) :- R1 is R + 1, unknown_list(N, T, R1).

prime_divisors(1, []) :- !.
prime_divisors(N, [H | T]) :- number(H), !, good_list(0, [H | T]), multiply(N, [H | T], 1), !.
prime_divisors(N, Divisors) :- number(N), !, unknown_list(N, Divisors, 2), !.

nth_prime(N, P) :- nth_prime(N, P, 1, 2).
nth_prime(N, P, R, PR) :- R = N, !, PR = P.
nth_prime(N, P, R, PR) :- PRPL is PR + 1, next_prime(PRPL, PR1), R1 is R + 1, nth_prime(N, P, R1, PR1).

next_prime(P, Next) :- prime(P), !, Next = P.
next_prime(P, Next) :- P1 is P + 1, next_prime(P1, Next).