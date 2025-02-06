package Matematica;

public class Retangulo {
	private static float ladoH;
	private static float ladoV;
	public static void definir_lados(float A, float B) {
		if (A>0 && B>0) {
		ladoH=A;
		ladoV=B;
		}
		else {
			System.out.println("Retangulo não pode conter lados de valores negativos");
		}
	}
	public static float area() {
		return (ladoH*ladoV);
	}
	public static float perimetro() {
		return ((ladoH*2)+(ladoV*2));
	}
}
