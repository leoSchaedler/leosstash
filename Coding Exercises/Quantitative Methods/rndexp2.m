function out = rndexp2(mu, m, n)
% RANDEXP(a,n)
%   Esta função cria com 'n' valores distribuidos aleatóriamente  
%   segundo distribuição exponencial com parâmetro média mu.  

    a = 1/mu;
    out = -log(1-rand(m,n)) ./ a;
end