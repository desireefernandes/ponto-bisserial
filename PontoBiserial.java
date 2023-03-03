import java.util.ArrayList;
import java.util.List;

public class PontoBiserial {

	public static void main(String[] args) {
		aplicacao();

	}

	private static void aplicacao() {

		// ------ Conjuntos X e Y
		// X é um conjunto de dados contínuo;
		double[] arrX = { 8.333, 58.333, 32.263, 6.664, 83.785, 3.686, 17.895, 25.867, 4.856 };
		// Y um conjunto de dados binários (contendo apenas zeros e uns);
		int[] arrY = { 0, 1, 1, 0, 1, 0, 1, 1, 0 };

		// --> M1 --- M0 --- elementos p media --- soma
		List<Double> elementosM0 = new ArrayList<Double>();
		List<Double> elementosM1 = new ArrayList<Double>();
		double somaM0 = 0;
		double somaM1 = 0;

		// -------------------------------- MO e M1
		for (int i = 0; i < arrY.length; i++) {

			if (arrY[i] == 0) {

				elementosM0.add(arrX[i]);

				somaM0 += arrX[i];

			} else {
				elementosM1.add(arrX[i]);

				somaM1 += arrX[i];
			}

		}

		double mediaM0 = somaM0 / elementosM0.size();

		double mediaM1 = somaM1 / elementosM1.size();

		// *varivel chamada metodo*
		double m1M0 = m1M0(mediaM0, mediaM1);

		// -------------------------------- n1 e n2 --- n
		// --> Elementos do lado --- n1 e n2 --- n
		int n0 = elementosM0.size();
		int n1 = elementosM1.size();
		int n = arrX.length;

		// *varivel chamada metodo*
		double lado = lado(n0, n1, n);

		// -------------------------------- X = media dos elementos ArrX
		// *varivel chamada metodo*
		double mediaArrX = mediaArrX(arrX);

		// -------------------------------- PARTE DE BAIXO
		// *varivel chamada metodo*
		double parteBaixoFinal = baixo(arrX, mediaArrX);

		// *varivel chamada metodo*
		double constanteFinal = constante(m1M0, parteBaixoFinal, lado);

		// Impressão
		// n1 e n2
		System.out.println("\n");
		System.out.print("n0 = " + n0 + "\n");
		System.out.print("n1 = " + n1 + "\n");

		// M0 e M1
		System.out.println("\n");
		System.out.println("elementos list M0 -> " + elementosM0);
		System.out.println("elementos list M1 -> " + elementosM1);
		System.out.println("media m0 -> " + mediaM0);
		System.out.println("media m1 -> " + mediaM1);
		System.out.println("cima - (m1 - m0) -> " + m1M0);

		// X
		System.out.println("\n");
		System.out.println("X -> media dos elementos arrX = " + mediaArrX);

		// Lado
		System.out.println("\n");
		System.out.println("lado -> " + lado);

		// Baixo
		System.out.println("\n");
		System.out.print("baixo -> " + parteBaixoFinal);

		// Ponto Biserial - final constante
		System.out.println("\n");
		System.out.println("FINAL -> " + constanteFinal);

	}

	// *outros metodos*

	// -------------------------------- Ponto Biserial - final constante
	private static double constante(double m1M0, double parteBaixoFinal, double lado) {

		return m1M0 / parteBaixoFinal * lado;

	}

	// -------------------------------- Parte de baixo
	private static double baixo(double[] arrX, double mediaArrX) {

		double somatorio = 0;

		for (int j = 0; j < arrX.length; j++) {

			double a = arrX[j] - mediaArrX;

			somatorio += Math.pow(a, 2);

		}

		double parteBaixo = Math.sqrt(somatorio / arrX.length);

		System.out.println("parte de baixo - somatorio " + somatorio);
		System.out.println("parte de baixo final " + parteBaixo);

		return parteBaixo;

	}

	// -------------------------------- X = media dos elementos ArrX
	private static double mediaArrX(double[] arrX) {

		double somaMediaArrX = 0;

		for (int j = 0; j < arrX.length; j++) {

			somaMediaArrX += arrX[j];
		}

		return somaMediaArrX / arrX.length;

	}

	// -------------------------------- LADO
	private static double lado(double n0, double n1, double n) {

		return Math.sqrt(n1 * n0 / Math.pow(n, 2));

	}

	// -------------------------------- MO e M1
	private static double m1M0(double mediaM0, double mediaM1) {

		return mediaM1 - mediaM0;

	}

}
