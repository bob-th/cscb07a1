public class Polynomial {
	double coefficients[];

	public Polynomial() {
		double[] z = {0.};
		this.coefficients = z;
	}

	public Polynomial(double[] coeff) {
		this.coefficients = coeff;
	}

	public Polynomial add(Polynomial p){
		double[] coeff = new double[Math.max(this.coefficients.length, p.coefficients.length)];

		int i = 0;

		for(; i < this.coefficients.length && i < p.coefficients.length; i++) {
			coeff[i] = this.coefficients[i] + p.coefficients[i];
		}

		for (; i < this.coefficients.length; i++) {
			coeff[i] = this.coefficients[i];
		}

		for (; i < p.coefficients.length; i++) {
			coeff[i] = p.coefficients[i];
		}

		return new Polynomial(coeff);
	}

	public double evaluate(double x) {
		double s = 0;
		for(int i=0;i<this.coefficients.length;i++) {
			s += this.coefficients[i] * Math.pow(x, i);
		}
		return s;
	}

	public boolean hasRoot(double x) {
		return this.evaluate(x) == 0;
	}
}