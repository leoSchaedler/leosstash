%% C�lculo de probabilidades por simula��o
% 
%  %  Instru��es
%  ------------
% 
%  Script para apoio de trabalho em sala de aula
%  Chama as seguintes fun��es que precisam ser programadas 
%
%     aniverA.m
%     aniverS.m
%
%  Para esse exerc�cio ser� necess�rio aalterar algumas 
%  partes do c�digo a seguir, conforme indicado.

%

%% In�cio
%% Limpar vari�veis
clear ; close all; clc


%% Obt�m quantidade quantas pessoas para a qual deve ser calculada a probabilidade
tamanho = input('Digite a quantidade de pessoas para a qual deve ser calculada a probabilidade: ');


%% Chama a fun��o para c�lculo anal�tico e imprime resultado

probA = aniverA(tamanho);
fprintf('Quantidade de pessoas: %d \n', tamanho);
fprintf('Probabilidade calculada pelo formula analitica: %f \n', probA);
fprintf('\n');

fprintf('Script suspenso \n');
fprintf('Digite qualquer tecla para continuar ou ctrl-c para abortar \n');
fprintf('\n');
pause;

%% Obt�m quantidade de vezes que deve ser realizada a simula��o

n = input('Digite a quantidade de vezes que deve ser realizada a simula��o: ');
fprintf('\n');

%% Chama a fun��o para c�lculo por simula��o e imprime resultado

probS = aniverS(tamanho, n);
fprintf('Quantidade de pessoas: %d \n', tamanho);
fprintf('Quantidade de simulacoes: %d \n', n);
fprintf('Probabilidade calculada pela simula��o: %f \n', probS);
fprintf('\n');


