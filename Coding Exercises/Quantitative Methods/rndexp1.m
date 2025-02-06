function out = rndexp1(mu, m, n)
% RANDEXP(a,n)
%   Esta função cria com 'n' valores distribuidos aleatóriamente  
%   segundo distribuição exponencial com parâmetro média mu.  

    a = 1/mu;
    out = -log(rand(m,n)) ./ a;
end