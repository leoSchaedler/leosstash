public class TabHash {

    private final int TAMANHO;
    private int ncolisoes;
    private ListaSE<Integer>[] tabela;

    public  TabHash(int tamanho){
        this.TAMANHO = tamanho;
        ncolisoes = 0;
        tabela = new ListaSE[tamanho];
    }

    private int funcaoh(int elemento){
        return ( Math.abs(elemento) % TAMANHO );
    }

    public int busca(int elemento) {
        int pos = funcaoh(elemento);
        try{
            int dado = tabela[pos].procurar(elemento);
            return dado;
            } catch (NullPointerException e) {
             // System.out.println("Elemento não encontrado" + e);
         }

        return -1;
    }


    public void insere(int elemento) {
        int pos = funcaoh(elemento);

        if (tabela[pos] == null) {
            tabela[pos] = new ListaSE();
            tabela[pos].inserirPrimeiro(elemento);
        } else {
            tabela[pos].inserirUltimo(elemento);
            ++ncolisoes;
        }
    }

    public void remove(int elemento){
        int pos = funcaoh(elemento);
        if(elemento == (int) tabela[pos].getPrimeiro().getDado())
            tabela[pos].removerPrimeiro();
        else{
            tabela[pos].removerElemento(elemento);
        }


    }


     public void imprimir(){
           for (ListaSE x : tabela){
               if(x!=null)
               x.imprimir();
           }
        }


    public int getTAMANHO() {
        return TAMANHO;
    }

    public int getNcolisoes() {
        return ncolisoes;
    }


    public ListaSE[] getTabela() {
        return tabela;
    }

}





