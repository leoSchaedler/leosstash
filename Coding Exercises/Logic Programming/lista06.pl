%QUESTAO ---- 1: OK
maior([],_,[]).
maior([Y|Z],V,[Y|L]):-
    Y > V, !,
    maior(Z,V,L).
maior([_|Z],V,L) :-
    maior(Z,V,L).


% QUESTAO ---- 2: OK
media(L,X):-
    cont2(L,0,0,X).
cont2([],S,C,M):-
    M is S/C.
cont2([X|Y],S,C,M):-
    Soma is S + X,
    Contador is C + 1,
    cont2(Y,Soma,Contador,M).

% QUESTAO --- 3: OK

maiores_media(L,X):-
    media(L,M),
    maior(L,M,X).

% QUESTAO --- 4: OK
anteriores(_,1,[]):-!.
anteriores([X|Y],P,[X|L]):-
    Posicao is P - 1,
    anteriores(Y,Posicao,L).

% QUESTAO --- 5: OK
posteriores([N|X], N, X):- !.
posteriores([_|Z], N, X):-
    posteriores(Z, N, X).

% QUESTAO --- 6: OK
inteiros(N,L):-
    cont6(1,N,L),!.
cont6(N,N,[N]):-!.
cont6(C,N,[C|L]):-
    Contador is C + 1,
    cont6(Contador,N,L).

% QUESTAO ---- 7: OK
vizinhos(E, [A,E,B|_], [A,B]):-!.
vizinhos(E, [E,B|_], [B]):-!.
vizinhos(E, [A,E], [A]):-!.
vizinhos(E, [_|Y], L):-
    vizinhos(E, Y, L).

% QUESTAO ---- 8: OR
dividir1(_,[],[],[]).
dividir1(M,[H|T],[H|U1],U2):-
    H =< M,
    dividir1(M,T,U1,U2).
dividir1(M,[H|T],U1,[H|U2]):-
    H > M,
    dividir1(M,T,U1,U2).

divide(L,1,[],L):-!.
divide([X|Y],P, [X|L1],L2):-
    P1 is P - 1,
    divide(Y,P1,L1,L2).

% QUESTAO ---- 9: OK
intervalo(N1, N2, [H|T]):-
    H is N1,
    N2 >= N1,
    N3 is N1 + 1,
    intervalo(N3, N2, T).
intervalo(_, _, []).


% QUESTAO ---- 10: OK
n_elementos([],0).
n_elementos([_|Y],N):-
    n_elementos(Y,M),
    N is M + 1.


% QUESTAO ---- 11: OK
merg([],L,L):- !.
merg(L,[],L):- !. %dois criterios de parada
merg([X1|Y1], [X2|Y2], [X1|Z]):-
    X1 < X2, !,
    merg(Y1,[X2|Y2],Z).
merg([X1|Y1], [X2|Y2], [X2|Z]):-
    merg([X1|Y1],Y2,Z).


% QUESTAO ---- 12: OK
concatena([], L, L).
concatena([X|Y], L, [X|Z]) :-
    concatena(Y,L,Z).
inverte([],[]).
inverte([X|Y],L):-
    inverte(Y,L1),
    concatena(L1, [X], L).

% QUESTAO ---- 13: OK
pertence(X,[X|_]).
pertence(X, [_|Y]):-
    pertence(X,Y).
sublista(X,Y):-
    pertence(E,X),
    pertence(E,Y).


% QUESTAO ---- 14:
comp([],_):-!.
comp([X|Y],[X|Z]):-
    comp(Y,Z).
sublista1([],_):-!.
sublista1(L1,L2):-
    comp(L1,L2),!.
sublista1(L1,[_|L2]):-
    sublista1(L1,L2).

% QUESTAO ---- 15: OK
remove(E, [E|Y], Y) :- !.
remove(E, [X|Y], [X|L]):-
    remove(E,Y,L).


% QUESTAO ---- 16: OK
removeAll(_, [], []):-!.
removeAll(E, [E|Y], L) :-
    removeAll(E,Y,L),!.
removeAll(E, [X|Y], [X|L]):-
    removeAll(E, Y, L).

% QUESTAO ---- 17: OR
remoce_posicao([_|XS], 1, XS).
remove_posicao([X|Y], N, [X|R]) :-
    P is N - 1,
    del(Y, P, R).


% QUESTAO --- 18: OK
lista(X) :-
        var(X), !,
        fail.
lista([]).
lista([_|T]) :-
        lista(T).


% QUESTAO ---- 19: OK
nivela([],[]).
nivela([X|Y], L):-
    lista(X),
    nivela(X,A),
    nivela(Y,B),
    concatena(A,B,L).
nivela([X|Y],[X|B]):-
    not(lista(X)),
    nivela(Y,B).

% QUESTAO ---- 20: OK
intersecao([],[],_):-!.
intersecao([X|Y],[X|Z],L):-
    pertence(X,L), !,
    intersecao(Y,Z,L).
intersecao([_|Y],Z,L):-
    intersecao(Y,Z,L).

% QUESTAO ---- 21: NOT
encontra_elemento1(1,X,[X|_]).
encontra_elemento1(N,X,[_|Y]):-
    encontra_elemento1(M,X,Y),
    N is M + 1.

n_esimo(1, E, [E|_]).
n_esimo(N,E,[_|C]):-
    n_esimo(M,E,C),
    N is M + 1.

encontra_elementos([],_,[]).
encontra_elementos([X|Y],P,[X1|Z]):-
    n_esimo(P,X,X1),
    encontra_elementos(Y,P,Z).

% QUESTAO ---- 22: NOT
empacota_copias([],[]).
empacota_copias([X|Y],[L1|L]):-
    empacota(X,[X|Y],L1,R),
    empacota_copias(R,L).

empacota(E,[E,Y],[E|Y1],W):-
    empacota(E,Y,Y1,W),!.
empacota(_,L,[],L).


% QUESTAO ---- 23: NOT
codifica([],[]).
codifica([X|Y],[[X,N|L]]):-
    empacota(X,[X|Y],L1,R),
    n_elementos(L1,N),
    codifica(R,L).


% QUESTAO ---- 24: NOT
decodifica([],[]):-!.
decodifica([[E,N]|Y],R):-
    cria_duplicata(E,N,L),
    decodifica(Y,L1),
    concatena(L,L1,R).

cria_duplicata(_,0,[]):-!.
cria_duplicata(E,N,[E|L]):-
    N1 is N - 1,
    cria_duplicata(E,N1,L).

% QUESTAO ---- 25: OK

replica([],_,[]).
replica([X|Y],N,R):-
    cria_duplicata(X,N,L1),
    replica(Y,N,L2),
    concatena(L1,L2,R).
