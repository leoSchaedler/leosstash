% aniverS.m
%
function probS = aniverS( K, N )

% K � o tamanho do grupo
% N � a quantidade de simula��es                                  
evento = zeros(N, 1);                      % iniciado com N eventos nulos (1 evento para cada simula��o)
for sorteio = 1:N
    % sortear as K datas de anivers�rio
    % verificar se n�o existem datas repetidas 
    % se n�o existirem datas repetidas acumular um evento
        % evento(sorteio)=1;               % use esse comando para acuimular eventos 

end

probS = sum(evento)/N;                      % fim da simula��o e impress�o do resultado

end