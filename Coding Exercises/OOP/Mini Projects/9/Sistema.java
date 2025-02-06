
public class Sistema {

	public static void main(String[] args) throws CloneNotSupportedException {
		
		Disco d = new Disco('C');
		
		Pasta escola = d.criarPasta("Escola");
		Pasta pessoal = d.criarPasta("Pessoal");
		Pasta fotos = d.criarPasta("fotos");


		
		d.listarPastas();
		
		Texto t = new Texto("Trabalho 10");
		escola.inserirArquivo(t);
		Apresentacao a = new Apresentacao("Defesa do Projeto 2");
		escola.inserirArquivo(a);
		Planilha p = new Planilha("Calculo 2");
		escola.inserirArquivo(p);
		
		escola.listarArquivos();
		
		Video v = new Video("Aniversario");
		pessoal.inserirArquivo(v);
		Imagem i = new Imagem("Foto RJ");
		pessoal.inserirArquivo(i);
		Som s = new Som("Musica Chico");
		pessoal.inserirArquivo(s);
		
		pessoal.listarArquivos();
		
		t.abrir();
		a.abrir();
		p.abrir();
		v.abrir();
		i.abrir();
		s.abrir();
		
		escola.removerArquivo(t);
		escola.duplicarArquivo(a);
		escola.duplicarArquivo(a);
		escola.duplicarArquivo(a);
		escola.listarArquivos();
		
		d.removerPasta(pessoal);
		d.listarPastas();
		
		
		

	}

}
