import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileWriter;

public class Polynomial {
	double coefficients[];
	int exponents[];

	public Polynomial() {
		double[] z = {};
		int[] e = {};
		this.coefficients = z;
		this.exponents = e;
	}

	public Polynomial(double[] coeff, int[] exp) {
		this.coefficients = coeff;
		this.exponents = exp;
	}

	public Polynomial(File f) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(f));

		String[] terms = br.readLine().split("(?=[+-])");
		this.coefficients = new double[terms.length];
		this.exponents = new int[terms.length];

		for(int i=0; i<terms.length; i++) {
			String[] s = terms[i].split("x");
			coefficients[i] = Double.parseDouble(s[0]);
			exponents[i] = s.length == 1 ? 1 : Integer.parseInt(s[1]);
		}

		br.close();
	}

	private int find(int[] a, int o) {
		int i;
		for(i=0; i<a.length; i++) {
			if (a[i] == o) {
				return i;
			}
		}
		return -1;
	}

	public Polynomial add(Polynomial p){
		double[] coeff = new double[coefficients.length + p.coefficients.length];
		int[] exp = new int[exponents.length + p.exponents.length];

		for(int i=0; i < coefficients.length; ++i) {
			coeff[i] = coefficients[i];
			exp[i] = exponents[i];
		}
		int m = coefficients.length;
		for(int i=0; i < p.coefficients.length; i++) {
			int j = find(exp, p.exponents[i]);
			if (j == -1) {
				coeff[m] = p.coefficients[i];
				exp[m] = p.exponents[i];
				++m;
			} else {
				coeff[j] += p.coefficients[i];
			}
		}

		//filter out stuff
		Polynomial r = new Polynomial(coeff, exp);
		Polynomial.filter(r);
		return r;
	}

	protected static void filter(Polynomial p) {
		int nonZCount = 0;
		for(int i=0;i<p.coefficients.length;i++) {
			if (p.coefficients[i] != 0)
				nonZCount++;
		}

		double[] coeff_filter = new double[nonZCount];
		int[] exp_filter = new int[nonZCount];

		int mf = 0;

		for(int i=0; i<p.coefficients.length; i++) {
			if(p.coefficients[i] != 0) {
				coeff_filter[mf] = p.coefficients[i];
				exp_filter[mf] = p.exponents[i];
				++mf;
			}
		}

		p.coefficients = coeff_filter;
		p.exponents = exp_filter;
	}

	public Polynomial multiply(Polynomial p) {
		Polynomial prod = new Polynomial();
		for(int i=0; i<exponents.length; ++i) {
			for(int j=0; j<p.exponents.length; ++j) {
				double[] c = {coefficients[i] * p.coefficients[j]};
				int[] e = {exponents[i] + p.exponents[j]};
				Polynomial t = new Polynomial(c, e);
				prod = prod.add(t);
			}
		} 
		return prod;
	}

	public String toString() {
		String s = "";
		for(int i=0;i<exponents.length;i++) {
			s+=(
				(coefficients[i] < 0 ? "" : i > 0 ? "+" : "")
				+ String.valueOf(coefficients[i])
				+ (exponents[i] > 0 ? "x" + (exponents[i] > 1 ? String.valueOf(exponents[i]) : "") : "")
			);
		}
		return s;
	}

	public void saveToFile(String filename) throws IOException {
		FileWriter fw = new FileWriter(filename, false);
		fw.write(this.toString()+"\n");
		fw.close();
	}

	public double evaluate(double x) {
		double s = 0;
		for(int i=0;i<this.coefficients.length;i++) {
			s += this.coefficients[i] * Math.pow(x, this.exponents[i]);
		}
		return s;
	}

	public boolean hasRoot(double x) {
		return this.evaluate(x) == 0;
	}
}