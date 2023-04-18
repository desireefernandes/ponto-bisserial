import java.util.ArrayList;
import java.util.List;

public class PointBiserial {

	public static void main(String[] args) {
		application();

	}

	private static void application() {

		// ------ Set X and Y
		// X is a continuous data set;
		double[] arrX = { 8.333, 58.333, 32.263, 6.664, 83.785, 3.686, 17.895, 25.867, 4.856 };
		// Y is a binary data set (containing only zeros and ones)
		int[] arrY = { 0, 1, 1, 0, 1, 0, 1, 1, 0 };
		
		// Method final
		pointBisserialCalculate(arrX, arrY);

	}

	private static double pointBisserialCalculate(double[] arrX, int[] arrY) {
		// --> M1 --- M0 --- elements for average --- sum
		List<Double> elementsM0 = new ArrayList<Double>();
		List<Double> elementsM1 = new ArrayList<Double>();
		double additionM0 = 0;
		double additionM1 = 0;

		// -------------------------------- MO and M1
		for (int i = 0; i < arrY.length; i++) {

			if (arrY[i] == 0) {

				elementsM0.add(arrX[i]);

				additionM0 += arrX[i];

			} else if (arrY[i] == 1) {
				elementsM1.add(arrX[i]);

				additionM1 += arrX[i];
			} else {
				throw new RuntimeException("The array must have onle 0's and 1's");
			}

		}

		double averageM0 = additionM0 / elementsM0.size();

		double averageM1 = additionM1 / elementsM1.size();

		// *call method*
		double m1M0 = m1M0(averageM0, averageM1);

		// -------------------------------- n1 and n2 --- n
		// --> Elements left side --- n1 and n2 --- n
		int n0 = elementsM0.size();
		int n1 = elementsM1.size();
		int n = arrX.length;

		// *variable called method*
		double side = side(n0, n1, n);

		// -------------------------------- X = average of elements ArrX
		// *variable called method*
		double averageArrX = average(arrX);

		// -------------------------------- BOTTOM SIDE
		// *variable called method*
		double bottomPartFinal = bottom(arrX, averageArrX); // divider

		// *variable called method*
		double correlation = constant(m1M0, bottomPartFinal, side);

		debugInfo(elementsM0, elementsM1, averageM0, averageM1, m1M0, n0, n1, side, averageArrX, bottomPartFinal, correlation);
		
		return correlation;
	}

	private static void debugInfo(List<Double> elementsM0, List<Double> elementsM1, double averageM0, double averageM1,
			double m1M0, int n0, int n1, double side, double averageArrX, double bottomPartFinal, double correlation) {
		// Printing
		// n1 and n2
		System.out.println("\n");
		System.out.print("n0 = " + n0 + "\n");
		System.out.print("n1 = " + n1 + "\n");

		// M0 and M1
		System.out.println("\n");
		System.out.println("M0 list elements -> " + elementsM0);
		System.out.println("M1 list elements -> " + elementsM1);
		System.out.println("M0 average -> " + averageM0);
		System.out.println("M1 average -> " + averageM1);
		System.out.println("Equation top part {M1 - M0} -> " + m1M0);

		// X
		System.out.println("\n");
		System.out.println("X -> average of arrX elements = " + averageArrX);

		// Side
		System.out.println("\n");
		System.out.println("Equation side part -> " + side);

		// Bottom
		System.out.println("\n");
		System.out.print("Equation bottom part -> " + bottomPartFinal);

		// Point Biserial - final constant
		System.out.println("\n");
		System.out.println("FINAL -> " + correlation);
	}

	// *Other methods*

	// -------------------------------- Ponto Biserial - final constante
	private static double constant(double m1M0, double bottomPartFinal, double side) {

		return (m1M0 / bottomPartFinal) * side;

	}

	// -------------------------------- Bottom part
	private static double bottom(double[] arrX, double averageArrX) {

		double sum = 0;

		for (int j = 0; j < arrX.length; j++) {

			double a = arrX[j] - averageArrX;

			sum += Math.pow(a, 2);

		}

		double bottomPart = Math.sqrt(sum / arrX.length);

		System.out.println("Bottom part - sum " + sum);
		System.out.println("Final bottom part " + bottomPart);

		return bottomPart;

	}

	// -------------------------------- X = average of ArrX elements
	private static double average(double[] arr) {

		double sumAverageArrX = 0;

		for (int j = 0; j < arr.length; j++) {

			sumAverageArrX += arr[j];
		}

		return sumAverageArrX / arr.length;

	}

	// -------------------------------- SIDE
	private static double side(double n0, double n1, double n) {

		return Math.sqrt((n1 * n0) / Math.pow(n, 2));

	}

	// -------------------------------- MO and M1
	private static double m1M0(double averageM0, double averageM1) {

		return averageM1 - averageM0;

	}

}
