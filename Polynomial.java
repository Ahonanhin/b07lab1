
public class Polynomial {
	double[] array;
	Polynomial() {
		array = new double[1];
		for(int i = 0; i < array.length; i++) {
			array[i] = 0;
		}
	}
	Polynomial( double [] arg) {
		array = new double[arg.length];
		for(int i = 0; i < arg.length ;i++) {
			array[i] = arg[i];
		}
	}
	public Polynomial add(Polynomial poly2){
		Polynomial mid;
		if (array.length >= poly2.array.length){
			 mid = new Polynomial(array);
			for(int i = 0; i < poly2.array.length ;i++) {
				mid.array[i] = poly2.array[i] + mid.array[i];
			}
			return mid;
		}
		else{
			 mid = new Polynomial(poly2.array);
			for(int i = 0; i < array.length ;i++) {
				mid.array[i] = mid.array[i] + array[i];
			}
			return mid;
		}
	}

	public double evaluate (double number){
		double to_return = 0.0;
		for (int i = 1; i < array.length; i++) {
			to_return  += (array[i] * number);
		}
		return (to_return + array[0]);
	}
	public boolean hasRoot( double number) {
		if (evaluate(number) == 0) return true;
		return false;
	}
}




