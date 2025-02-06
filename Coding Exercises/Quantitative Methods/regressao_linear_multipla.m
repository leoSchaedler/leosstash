function [betaHat, F, R2] = regressao_linear_multipla(X,Y)
% Regressao linear multipla
n = size(X,1);
k = size(X,2);
if size(Y,1)==n
    % calculo de betahat
    X1 = [X ones(n,1)];
    % betaHat = inv(X'*X)*X'*Y;
    betaHat = (X1'*X1)\X1'*Y;  % modo mais eficiente de calcular expressao anterior
    
    % calculo de F
    SYY = ((n*(Y'*Y))-(sum(Y)^2))/n;
    SYX = ((n*(Y'*X))-(sum(Y)*sum(X)))/n;
    BHxSYX = betaHat(1:k)'*SYX'; %calculo auxiliar
    F = ((1/k*BHxSYX))/((SYY-BHxSYX)/(n-k-1));
    
    %calculo de R2
    R2 = BHxSYX/SYY;
end