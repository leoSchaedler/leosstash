N = 20000;
prob = 0;
for i=1:N
    x=  unidrnd(6,1,4)% colocar aqui o código de sorteio dos 4 dados
    y=find(x==6);    % investigar como funciona o comando length
    if (find(x==6)==4) % colocar entre parenteses a condição que determina a ocorrencia do evento
      prob = prob + 1;  % somar 1 na quantidade de ocorrencias do evento
    end
end
prob = prob/N