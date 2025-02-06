function data = importarDados(arquivo, dataLines)
%  Argumentos
%    Nome do arquivo
%    Vetor com os intervalos de linhas (dataLines)
%  Retorno
%    Objeto que contem os dados
%
%  Tratar os argumentos
%  Construir op��es de importa��o
%  Importar os dados (usa a fun��o readtable)
%



%% Tratar os argumentos

% Se dataLines nao for definido, define com o valor default
if nargin < 2
    dataLines = [2, Inf];
end

%% Construir op��es de importa��o

% A fun��o delimitedTextImportOptions cria um objeto para especificar 
% como o MatLab importa em formato de tabela a partir de arquivos de texto.
% O objeto cont�m propriedades que controlam o processo de importa��o.
opts = delimitedTextImportOptions("NumVariables", 23);

% Define o range e o delimitador
opts.DataLines = dataLines;
opts.Delimiter = ",";

% Define os nomes e os tipos das colunas
opts.VariableNames = ["Var1", "Var2", "Estado", "Var4", "Mes", "Tipo_Evento", "Inicio_Data_Hora", "Timezone", "Fim_Data_Hora", "Ferimentos_Diretos", "Ferimentos_Indiretos", "Mortes_Diretas", "Mortes_Indiretas", "Var14", "Custo_Propriedade", "Var16", "Custo_Colheita", "Inicio_Lat", "Inicio_Lon", "Fim_Lat", "Fim_Lon", "Episodio_Narrativa", "Evento_Narrativa"];
opts.SelectedVariableNames = ["Mes", "Tipo_Evento", "Inicio_Data_Hora", "Timezone", "Ferimentos_Diretos", "Ferimentos_Indiretos", "Mortes_Diretas", "Mortes_Indiretas", "Custo_Propriedade", "Custo_Colheita", "Inicio_Lat", "Inicio_Lon", "Fim_Lat", "Fim_Lon", "Episodio_Narrativa", "Evento_Narrativa"];
opts.VariableTypes = ["string", "string", "categorical", "string", "categorical", "categorical", "datetime", "categorical", "datetime", "double", "double", "double", "double", "string", "double", "string", "double", "double", "double", "double", "double", "categorical", "string"];
opts = setvaropts(opts, 7, "InputFormat", "yyyy-MM-dd HH:mm:ss");
opts = setvaropts(opts, 9, "InputFormat", "yyyy-MM-dd HH:mm:ss");
opts = setvaropts(opts, [1, 2, 4, 14, 16, 23], "WhitespaceRule", "preserve");
opts = setvaropts(opts, [1, 2, 3, 4, 5, 6, 8, 14, 16, 22, 23], "EmptyFieldRule", "auto");
opts.ExtraColumnsRule = "ignore";
opts.EmptyLineRule = "read";

% Importar os dados
data = readtable(arquivo, opts);

end