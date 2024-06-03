import java.io.IOException;
import java.io.File;

public class Driver {

	public static void roots() throws IOException {


		Polynomial p = new Polynomial(new double[]{1}, new int[]{0});

		for(int i=1; i<5; i++) {
			double[] c = {1, -i};
			int[] e = {1, 0};
			p = p.multiply(new Polynomial(c, e));

			if(!p.hasRoot(i))
				System.out.println("FUC");
		}

		double[] x = new double[]{1, 2, 3,4,5,6,7};
		for(double d:x)
			System.out.println(p.evaluate(d));

		System.out.println(p.toString());

		Polynomial p3 = p.multiply(new Polynomial(new double[]{-3.1, 3.9, 5.1}, new int[]{5,2,0}));

		p3.saveToFile("BIGBALLS.txt");

		
	}

	public static void eval_zero(Polynomial p) {
		double[] x = new double[]{0, 1, -1, 0.1, 100, 200, -100, -1000};
		for(double d:x)
			if (p.evaluate(d) != 0) {
				System.out.println("WTF");
			}
	}

	public static void zeros() {
		Polynomial p = new Polynomial();
		eval_zero(p);
	}

	public static void main(String [] args) throws IOException{
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,5};
		int[] e1 = {0, 3};
		Polynomial p1 = new Polynomial(c1, e1);
		double [] c2 = {-2,-9};
		int[] e2 = {1, 4};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");

		zeros();
		roots();
	}
}