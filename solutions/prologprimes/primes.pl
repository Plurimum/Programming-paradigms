range(L, L, []).
range(N, L, [N | T]) :- N < L, N1 is N + 1, range(N1, L, T).

contains(V, [V | _]).
contains(V, [H | T]) :- V \= H, contains(V, T).

check(D, N) :-
	D = N, !.
check(D, N) :-
	D < N,
	\+ dividable(N, D),
	DN is D + 1,
	check(DN, N).

dividable(A, B) :-
	0 is mod(A, B).

prime(N) :-
	N > 1,
	check(2, N).

composite(N) :-
	composite(N, R).

composite(N, R) :-
	N > -1,
	\+ check(2, N, R).

all_members([], _).
all_members([H | T], L) :- member(H, L), all_members(T, L).

concat([], B, B).
concat([H | T], B, [H | R]) :- concat(T, B, R).

get_list(N, R) :-
	prime(N),
	R = [1, N], !.
get_list(N, [1 | R]) :-
	get_list(N, N, 2, R).
get_list(NS, _, NS, []) :- !.
get_list(_, 1, _, []) :- !.
get_list(NS, N, D, [D | T]) :-
	N > 1,
	D \= NS,
	0 is mod(N, D),
	NN is div(N, D),
	get_list(NS, NN, D, T), !.
get_list(NS, N, D, List) :-
	\+ 0 is mod(N, D),
	N > 1,
	D \= NS,
	DN is D + 1,
	get_list(NS, N, DN, List), !.

get_divisors(0, []).
get_divisors(1, [1]).
get_divisors(N, R) :-
	prime(N),
	R = [1, N].
get_divisors(N, R) :-
	fill_list(N, N, R).

sub_start([], _List):-!.
sub_start([Head|TailSub], [Head|TailList]):-
   sub_start(TailSub, TailList).
sublist(Sub, List):-
   sub_start(Sub, List), !.
sublist(Sub, [_Head|Tail]):-
   sublist(Sub, Tail).

prime_divisors(N, Divisors) :-
	get_list(N, R),
	sublist(R, Divisors),
	sublist(Divisors, R), !.


