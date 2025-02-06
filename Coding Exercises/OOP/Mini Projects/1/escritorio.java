public class escritorio {
    public static void main(String[] args) {
        Arquiteto oscar = new Arquiteto("Oscar Niemeyer", 104);
        Arquiteto kengo = new Arquiteto("Kengo Kuma", 64);
        desenhar(oscar, 2.0F, 5.2F, 3.7F);
        desenhar(kengo, 7.5F, 4.0F, 9.6F);
    }
    private static void desenhar(Arquiteto arq,
                          float a, float b, float c) {
        arq.exiba_dados_pessoais();
        arq.trabalhe(a,b,c);
    }
}
