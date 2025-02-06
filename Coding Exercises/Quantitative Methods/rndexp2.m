function out = rndexp2(mu, m, n)
% RANDEXP(a,n)
%   Esta fun��o cria com 'n' valores distribuidos aleat�riamente  
%   segundo distribui��o exponencial com par�metro m�dia mu.  

    a = 1/mu;
    out = -log(1-rand(m,n)) ./ a;
end