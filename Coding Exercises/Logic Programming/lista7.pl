% PARTE 01 --- GRAFO
% SEM DISTANCIA
mapa(porto_alegre,florianopolis).
mapa(florianopolis,curitiba).
mapa(curitiba,campo_grande).
mapa(são_paulo,rio_de_janeiro).
mapa(rio_de_janeiro,belo_horizonte).
mapa(rio_branco,porto_velho).
mapa(vitoria,salvador).
mapa(belo_horizonte,vitoria).
mapa(brasilia, belo_horizonte).
mapa(campo_grande,goiania).
mapa(goiania,brasilia).
mapa(cuiaba,palmas).
mapa(porto_velho,manaus).
mapa(rio_branco,manaus).
mapa(manaus,belem).
mapa(boa_vista,belem).
mapa(macapa,belem).
mapa(belem,são_luis).
mapa(palmas,são_luis).
mapa(são_luis,teresina).
mapa(salvador,teresina).
mapa(teresina,recife).
mapa(fortaleza,recife).
mapa(natal,joao_pessoa).
mapa(joao_pessoa,recife).
mapa(recife,maceio).
mapa(aracaju,maceio).
mapa(curitiba,são_paulo).
mapa(são_paulo,belo_horizonte).
mapa(rio_de_janeiro,vitoria).
mapa(belo_horizonte,salvador).
mapa(campo_grande,cuiaba).
mapa(goiania,cuiaba).
mapa(cuiaba,porto_velho).
mapa(manaus,boa_vista).
mapa(belem,palmas).
mapa(palmas,teresina).
mapa(salvador,recife).
mapa(teresina,fortaleza).
mapa(fortaleza,joao_pessoa).
mapa(são_paulo,campo_grande).
mapa(belo_horizonte,goiania).
mapa(goiania,palmas).
mapa(cuiaba,manaus).
mapa(palmas,salvador).
mapa(salvador,aracaju).
mapa(fortaleza,natal).
mapa(goiania,salvador).
mapa(cuiaba,belem).

% COM DISTANCIA

mapaD(porto_alegre,florianopolis,476).
mapaD(florianopolis,curitiba,251).
mapaD(curitiba,campo_grande,991).
mapaD(são_paulo,rio_de_janeiro,429).
mapaD(rio_de_janeiro,belo_horizonte,434).
mapaD(vitoria,salvador,1202).
mapaD(belo_horizonte,vitoria,524).
mapaD(campo_grande,goiania,705).
mapaD(goiania,brasilia,203).
mapaD(cuiaba,palmas,1029).
mapaD(porto_velho,manaus,901).
mapaD(rio_branco,manaus,1445).
mapaD(rio_branco,porto_velho,513).
mapaD(brasilia,belo_horizonte, 754).
mapaD(manaus,belem,3050).
mapaD(boa_vista,belem,3801).
mapaD(macapa,belem,329).
mapaD(belem,são_luis,481).
mapaD(palmas,são_luis,964).
mapaD(são_luis,teresina,329).
mapaD(salvador,teresina,1163).
mapaD(teresina,recife,1137).
mapaD(fortaleza,recife,629).
mapaD(natal,joao_pessoa,185).
mapaD(joao_pessoa,recife,104).
mapaD(recife,maceio,120).
mapaD(aracaju,maceio,201).
mapaD(curitiba,são_paulo,408).
mapaD(são_paulo,belo_horizonte,586).
mapaD(rio_de_janeiro,vitoria,412).
mapaD(belo_horizonte,salvador,964).
mapaD(campo_grande,cuiaba,559).
mapaD(goiania,cuiaba,934).
mapaD(cuiaba,porto_velho,1137).
mapaD(manaus,boa_vista,785).
mapaD(belem,palmas,973).
mapaD(palmas,teresina,835).
mapaD(salvador,recife,839).
mapaD(teresina,fortaleza,634).
mapaD(fortaleza,joao_pessoa,555).
mapaD(são_paulo,campo_grande,1014).
mapaD(belo_horizonte,goiania,666).
mapaD(goiania,palmas,724).
mapaD(cuiaba,manaus,1453).
mapaD(palmas,salvador,1114).
mapaD(salvador,aracaju,356).
mapaD(fortaleza,natal,435).
mapaD(goiania,salvador,1225).
mapaD(cuiaba,belem,2941).


% PARTE 02 --- CAMINHO UNICO OK
conectados(X,Y) :- mapa(X,Y).
conectados(X,Y) :- mapa(Y,X).

prox(Atual,Prox,Visitados):-
    conectados(Atual,Prox),
    not(member(Prox,Visitados)).

caminho(Meta,Meta,_,[Meta]).
caminho(Inicio,Meta,Visitados,[Inicio|Caminho]) :-
    prox(Inicio,Prox,Visitados),
    caminho(Prox,Meta,[Prox|Visitados],Caminho),!.


% PARTE 03 --- CAMINHO E DISTANCIA UNICO OK
conectadosD(X,Y,D) :- mapaD(X,Y,D).
conectadosD(X,Y,D) :- mapaD(Y,X,D).

proxD(Atual,Prox,Visitados,D):-
    conectadosD(Atual,Prox,D),
    not(member(Prox,Visitados)).

cont(Meta,Meta,_,[Meta],_,Df,Df):-!.
cont(Inicio,Meta,Visitados,[Inicio|Caminho],D,Dp,Df):-
    proxD(Inicio,Prox,Visitados,X),
    Dp1 is Dp + X,
    cont(Prox,Meta,[Prox|Visitados],Caminho,D,Dp1,Df).

buscaDU(Meta,Meta,_,[Meta],0,_):-!.
buscaDU(Inicio,Meta,Visitados,[Inicio|Caminho], D, Df) :-
    proxD(Inicio,Prox,Visitados,X),
    cont(Prox,Meta,[Prox|Visitados],Caminho,D,X,Df),!.

% PARTE 04 --- CAMINHO E DISTANCIAS MULTIPLOS [falta teste]
buscaDM(Meta,Meta,_,[Meta],0,_):-!.
buscaDM(Inicio,Meta,Visitados,[Inicio|Caminho], D, Df) :-
    proxD(Inicio,Prox,Visitados,X),
    cont(Prox,Meta,[Prox|Visitados],Caminho,D,X,Df),  print(('Caminho: ' ,[Inicio|Caminho])),nl, print(('Km: ', Df)), nl, fail.


% PARTE 05 --- LARGURA

consed(A,B,[B|A]).
conectado(X,Y,A):- mapaD(X,Y,A).
conectado(X,Y,A):- mapaD(Y,X,A).

busca_largura(Meta,Inicio,Caminho):-
    busca_larg(Meta,[[Inicio]],Caminho).

busca_larg(Meta, [[Meta|Cami]|_], [Meta|Cami]):-!.
busca_larg(Meta,[Visi|ReVisi],Cami):-
    Visi = [Inicio|_],
    Inicio \== Meta,
    findall(X,(conectado(X,Inicio,_),not(member(X,Visi))),L),
    maplist(consed(Visi),L,VisiExt),
    append(ReVisi,VisiExt,VisiAt),
    busca_larg(Meta,VisiAt, Cami).
