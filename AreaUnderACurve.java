import java.util.Scanner;

public class AreaUnderACurve {

	/**
	 * The function f(x) = x^2 + x + 1.
	 */
	private static double f(double x) {
		return x * x + x + 1;
	}

	/**
	 * Returns an approximation for the area under the curve f(x) between x = a and
	 * x = b.
	 */
	private static double computeArea(double a, double b) {
		double error = 1e-8; // This is the comparison error. See document for description.
		double c = (b - a) * f(b);
		Interval start = new Interval(a, b);
		PriorityQueue q = new PriorityQueue(100);
		q.insert(start);
		double d = 0;
		while (true) {

			Interval next = q.remove_max();
			double p = (next.getStart() + next.getEnd()) / 2.0;
			double n = next.getEnd();
			double m = next.getStart();
			Interval one = new Interval(m, p);
			Interval two = new Interval(p, n);
			d = c - (n - m) * f(n) + (p - m) * f(p) + (n - p) * f(n);
			if (Math.abs(d - c) <= error)
				break;
			q.insert(one);
			q.insert(two);
			c = d;

		}

		// TODO: Please compute an approximation for the area under the curve here.

		return c; // Remove this statement and return the computed area.
	}

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("We have the function f(x) = x^2 + x + 1.");
		System.out.print("Please enter lower value a: ");
		double a = kb.nextDouble();
		System.out.print("Please enter upper value b: ");
		double b = kb.nextDouble();

		double area = computeArea(a, b);
		System.out.println("\nAn approximation for the area under the curve f(x) \nbetween a = " + a + " and b = " + b
				+ " is " + area);
	}
}
