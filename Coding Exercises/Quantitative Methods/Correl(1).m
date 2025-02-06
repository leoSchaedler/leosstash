function R = Correl(arquivo)
CA = readtable(arquivo);
n = size(CA, 1);       % quantidade de amostras
CA.X2 = CA.X .^ 2;     % coluna com valores de X^2
CA.Y2 = CA.Y .^ 2;     % coluna com valores de X^2
CA.XY = CA.X .* CA.Y;  % coluna com valores X.Y
num = n*sum(CA.XY)-(sum(CA.X)*sum(CA.Y));
den = sqrt((n*sum(CA.X2)-sum(CA.X)^2))*sqrt((n*sum(CA.Y2)-sum(CA.Y)^2));
R = num/den;
end

