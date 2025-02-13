% This buffer is for notes you don't want to save.
% If you want to create a file, visit that file with C-x C-f,
% then enter the text in that file's own buffer.
%
% n_elementos([a,b,c], X).
% 10) da lista
n_elementos([],0).
n_elementos([_|Y],N):-
    n_elementos(Y,M),
    N is M + 1.

% n_esimo(2, [a,b,c], X).

n_esimo(1,[X|_],X):-!.
n_esimo(N,[_|Y],X):-
    N > 1,
    N1 is N - 1,
    n_esimo(N1,Y,X).

% 11) da lista 6
% merg([1, 4, 12, 28], [6,7,10], X).  % faz o merge
%	X = [1,4,6,7,10,12,28]

merg([],L,L):-!.
merg(L,[],L):-!.
merg([X1|Y1],[X2|Y2],[X1|Z]):-
    X1 < X2,!,
    merg(Y1,[X2|Y2],Z).
merg([X1|Y1],[X2|Y2],[X2|Z]):-
       merg([X1|Y1],Y2,Z).

%insere(a, 2, [1,2,3], L).
insere(E,1,L,[E|L]):-!.
insere(E,N,[X|Y],[X|L]):-
    N1 is N - 1,
    insere(E,N1,Y,L).

%concatena([a,b,c],[1,2,3], L).

concatena([],L,L).
concatena([X|Y],L,[X|Z]):-
    concatena(Y,L,Z).


% 12) da lista 6
%inverte([a,b,c], X).
inverte([],[]).
inverte([X|Y],L):-
    inverte(Y,L1),
    concatena(L1,[X],L).

%ultimo([a,b,c],X).
ultimo([X],X):-!.
ultimo([_|Y],X):-
    ultimo(Y,X).




%7) da lista 6
%Ex.: ?- vizinhos(c,[a,b,c,d],L).  -> L = [b,d]
%Ex.: ?- vizinhos(d,[a,b,c,d],L).  -> L = [c]
% 3 criterios de parada - falta a chamada recursiva
vizinhos(E,[A,E,B|_],[A,B]).
vizinhos(E,[E,B|_],[B]).
vizinhos(E,[A,E],[A]).


%16) Remove todas as ocorrências de um elemento dentro de uma lista.
% Ex.: remove(3,[1,2,3,4,3],X).  è X = [1,2,4]

removeAll(_,[],[]):-!.
removeAll(E,[E|Y],L):-
    removeAll(E,Y,L),!.
removeAll(E,[X|Y],[X|L]):-
    removeAll(E,Y,L).


%15) da lista 6
%remove(3,[1,2,3,4,3],X).   X = [1,2,4,3]
remove(E,[E|Y],Y):-!.
remove(E,[X|Y],[X|L]):-
    remove(E,Y,L).


% ----- 22/05
%1)  maiores([1,2,3,4],2,X).   X = [3,4]
maiores([],_,[]):-!.
maiores([H|T],N,[H|L]):-
    H > N,!,
    maiores(T, N, L).
maiores([_|T], N, L):-
    maiores(T, N, L).


%2) media([1,2,3],X).

media(L,M):-
    mediaAux(L,0,0,M).

mediaAux([],S,N,M):-
    M is S / N.
mediaAux([X|Y],S,N,M):-
    S1 is S + X,
    N1 is N + 1,
    mediaAux(Y,S1,N1,M).

%3) maiores_media([1,2,3],X).

maiores_media(L,X):-
    media(L,M),
    maiores(L,M,X).

%4) anteriores([a,b,c,2,3,5], 4,X).

anteriores(_,1,[]):-!.
anteriores([X|Y],P,[X|L]):-
    P1 is P - 1,
    anteriores(Y,P1,L).

%5)posteriores([a,b,c,2,3,5], 4,X).

posteriores([_|Y],1,Y):-!.
posteriores([_|Y],P,L):-
    P1 is P - 1,
    posteriores(Y,P1,L).

%6)inteiros(6,X).
%
inteiros(N,L):-
    inteirosAux(1,N,L).

inteirosAux(N,N,[N]):-!.
inteirosAux(M,N,[M|L]):-
    M1 is M + 1,
    inteirosAux(M1,N,L).

%8) divide([a,b,c,d],3,X,Y).

divide(L,1,[],L):-!.
divide([X|Y],P,[X|L1],L2):-
    P1 is P -1,
    divide(Y, P1,L1,L2).

%13)sublista([a,1,2,b,c],[3,a,2,u,c,1,j,b]).
%
sublista([],_):-!.
sublista([X|Y],Z):-
    member(X,Z),!,
    sublista(Y,Z).

% ?- set_prolog_flag(answer_write_options,[max_depth(0)]). EXIBE LISTA
% LONGA
% 14)sublista1([a,1,2,b,c], [2,1,a,1,2,b,c,g,4]).
sublista1([],_).
sublista1(L1,L2):-
    sublista1Aux(L1,L2),!.
sublista1(L1,[_|L2]):-
    sublista1(L1,L2).

sublista1Aux([],_).
sublista1Aux([X|Y],[X|Z]):-
    sublista1Aux(Y,Z).


