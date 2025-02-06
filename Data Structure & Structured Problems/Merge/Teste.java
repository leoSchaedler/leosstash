
public class Teste {
	
		public static void main(String[] args) {
			
			Merge m = new Merge();
			
			Fila<Integer> x = new Fila<>();
			Fila<Integer> y = new Fila<>();
			
			
		// X = {12,35,52,64)
			x.adicionar(12);
			x.adicionar(35);
			x.adicionar(52);
			x.adicionar(64);
			
			
		// Y = {05,15,23,55,75}
			y.adicionar(05);
			y.adicionar(15);
			y.adicionar(23);
			y.adicionar(55);
			y.adicionar(75);

			
		


	Fila g = m.opMerge(x, y);	
	System.out.println(g.toString());

			
			
			
			
			
			
			
	
 }
}
