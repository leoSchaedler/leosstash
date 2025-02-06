% aniverS.m
%
function probS = aniverS( K, N )

% K é o tamanho do grupo
% N é a quantidade de simulações                                  
evento = zeros(N, 1);                      % iniciado com N eventos nulos (1 evento para cada simulação)
for sorteio = 1:N
    % sortear as K datas de aniversário
    % verificar se não existem datas repetidas 
    % se não existirem datas repetidas acumular um evento
        % evento(sorteio)=1;               % use esse comando para acuimular eventos 

end

probS = sum(evento)/N;                      % fim da simulação e impressão do resultado

end