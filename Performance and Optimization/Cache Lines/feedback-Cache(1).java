//
// Feedback atividade formativa Cache Simplificada
// Possível implementação da Cache Simplificada
// (considerando que a próxima memória é a RAM)
// PSCF - Prof. Luiz Lima Jr.
//

public class Cache {
    private RAM ram = null;         // próxima memória
    private int capacidade = 0;     // capacidade da cache
    private int end_ram = -1;       // ender. RAM do início do bloco armazenado
    private boolean modif = false;  // modificada?
    private int [] bloco = null;    // bloco de dados da cache

    public Cache(int tam, RAM prox) {
        ram = prox;
        capacidade = tam;
        bloco = new int [capacidade];
    }

    public int Read(int ender) throws InvalidAddress {
        Valida(ender);          // verifica se é um endereço válido da ram
        VerificaCache(ender);   // cache hit ou cache miss?
        return bloco[ender - end_ram];
    }

    public void Write(int ender, int w) throws InvalidAddress {
        Valida(ender);
        VerificaCache(ender);
        bloco[ender - end_ram] = w;
        modif = true;
    }

    private void VerificaCache(int ender) throws InvalidAddress {
        if (Hit(ender))
            System.out.println("Cache HIT: " + ender);
        else {
            System.err.println("Cache MISS: " + ender);
            TrazParaCache(ender);
        }
    }

    private boolean Hit(int ender) {
        if (end_ram > 0)    // cache já tem algum bloco carregado?
            return ((ender >= end_ram) && (ender < end_ram + capacidade));
        else
            return false;
    }

    private void TrazParaCache(int ender) throws InvalidAddress {
        if (modif) {
            // copia da cache para a RAM, pois foi modificado na cache
            for (int i=0; i<capacidade; ++i)
                ram.Write(end_ram+i, bloco[i]);
            modif = false;
        }
        // traz bloco iniciando no endereço solicitado da ram para a cache
        end_ram = ender;
        for (int a=ender; a<ender+capacidade && a<ram.Size(); ++a)
            bloco[a-ender] = ram.Read(a);
    }
}
