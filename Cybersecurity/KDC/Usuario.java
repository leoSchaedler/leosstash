public class Usuario {

    private String nome;
    private String chave;
    private String chaveSessao;

    public Usuario(String nome, String chave) {
        this.nome = nome;
        this.chave = chave;
    }

    public String getNome() {
        return nome;
    }

    public String getChave() {
        return chave;
    }

    public void pedirChave(Usuario contato) {
        try {
            byte[] euMesmo = AES.cifra(this.nome, this.chave);
            byte[] nomeContato = AES.cifra(contato.getNome(), this.chave);
            KDC.receber(this.nome, euMesmo, nomeContato);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
