 function [probS, time] = passeioAleatorioSV(tamPasseio, k, nSimul)
 
  tic
  if( rem(tamPasseio,2) == rem(k,2) )
    
    passeio = (((unidrnd(2, tamPasseio, nSimul) -1)*2)-1);
    pos = sum(passeio);
    hit = sum(pos == k);
    probS = hit / nSimul;  
    
    else
    probS = 0;
  end 
  time = toc;
end
