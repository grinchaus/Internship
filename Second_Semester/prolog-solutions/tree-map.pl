pre_process([], _).
pre_process([H | Tail], N) :- asserta(index_tree((N, H))),
    N1 is N + 1, pre_process(Tail, N1).

lengths([], Len, Len - 1) :- !.
lengths([H | Tail], N, Len) :- N1 is N + 1, lengths(Tail, N1, Len).

map_build(ListMap, TreeMap) :- pre_process(ListMap, 0), lengths(ListMap, 0, Len),
    build_tree(TreeMap, 0, Len).

build_tree(nil, L, R) :- L > R, !.
build_tree(tree(Key, Value, Left, Right), L, R) :-
    M is (L + R + 1) // 2,
    index_tree((M, (Key, Value))),
    build_tree(Right, M + 1, R),
    build_tree(Left, L, M - 1), !.

map_get(tree(Key, Value, _, _), Key, Value) :-  !.
map_get(tree(Key, _, _, Right), K, V) :-
    K >= Key,
    map_get(Right, K, V).
map_get(tree(Key, _, Left, _), K, V) :-
    K < Key,
    map_get(Left, K, V).

map_lastEntry(tree(Key, Value, _, nil), (Key, Value)).
map_lastEntry(tree(_, _, _, Right),(Key, Value)) :-
    map_lastEntry(Right, (Key, Value)).
map_lastKey(Tree, Key) :- map_lastEntry(Tree, (Key, Value)).
map_lastValue(Tree, Value) :- map_lastEntry(Tree, (Key, Value)).
