function [fat] = Fatorial (n)
  fat = 1;
  if (n==0)
    fat = 1;
  else  
    for k = n:-1:1
      fat = fat*k;
    end
  end
end
