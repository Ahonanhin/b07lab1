
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Polynomial {
	double[] array;
	int [] exponents;
	
	
	/*Helper Functions*/
    public static boolean search (int [] arg, int val){
        //Checks if an integer is in an array
        for (int i = 0 ; i < arg.length; i++){
            if(arg[i] == val ) return true;
        }
        return false;
    }

    public static boolean search (double [] arg, double val){
        //checks if a double is in an array
        for (int i = 0 ; i < arg.length; i++){
            if(arg[i] == val ) return true;
        }
        return false;
    }

    public static int sims (double [] a, double [] b){
        //counts the similarities in two arrays
        int count = 0;
        for (int i = 0 ; i < a.length; i++){
            for (int j = 0; j < b.length; j++){
                if (a[i] == b[j]) count++;
            }
        }
        return count;
    }

    public static int sims (int [] a, int [] b){
        //counts the similarities in two arrays
        int count = 0;
        for (int i = 0 ; i < a.length; i++){
            for (int j = 0; j < b.length; j++){
                if (a[i] == b[j]) count++;
            }
        }
        return count;
    }
	public static int count_zeros (double [] array) { 
		//Counts the number of ZEROS in the double argument
		int count = 0;
		for(int i = 0; i < array.length; i++) {
			if (array[i] == 0.0) count++;
		}
		return count;
	}
	
	public static int count_zeros (int [] array) {
		//Counts the number of ZEROS in the int argument
		int count = 0;
		for(int i = 0; i < array.length; i++) {
			if (array[i] == 0) count++;
		}
		return count;
	}
	
	public static void remove_zeros (double [] array, double [] new_a) {
		//creates a new array without the zeros
		int index = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != 0.0) {
				new_a[index] = array[i];
				index++;
			}
		}
	}
	
	public static void exp_maker(double [] array, int [] exp) {
		//creates a new array of exponents without the zeros 
		int index = 0;
		for(int i = 0; i < array.length; i++) {
			if (array[i] != 0) {
				exp[index] = i;
				index++;
			}
		}
	}
    public static void add_exp( int [] a, int [] b, int [] f){
        int i = 0;
        int [] c = new int [a.length + b.length];
        for (; i < a.length; i++){
            c[i] = a[i];
        }
        int d = i;
        i = 0;
        for (; i < b.length; i++){
            c[d] = b[i];
            d++;
        }
        Arrays.sort(c);
        i = 0;
        for (int q = 0; q < c.length; q++){
            if (!search(f, c[q])){
                f[i] = c[q];
                i++;
            }
        }
        Arrays.sort(f);
    }	
    public static void coef_maker( double [] a, double [] b, int [] c){
        int index = 0;
        for (int i = 0; i < b.length; i++){
            if (index  >= a.length) index = a.length - 1;

            if(!search(c,i)) b[i] = 0;
            else b[i] = a[Arrays.binarySearch(c,i)];

            index++;
        }
    }
	/* Constructors*/
	Polynomial() {
		array = new double[1];
		exponents = new int[1];
		for(int i = 0; i < array.length; i++) {
			array[i] = 0;
			exponents[i] = 0;
		}
	}
	

	Polynomial( double [] arg) {
		array = new double[arg.length - count_zeros(arg)];
		exponents = new int[arg.length - count_zeros(arg)];
		
		remove_zeros(arg, array);
		exp_maker(arg, exponents);
		
	}
	Polynomial (double [] arg, int [] coef){
		array = new double[arg.length];
		exponents = new int[coef.length];
		for(int i = 0; i < array.length; i++) {
			array[i] = arg[i];
			exponents[i] = coef[i];
		}
	}
	
	Polynomial( String args){
		try{
            BufferedReader file = new BufferedReader(new FileReader(args));
            String name = file.readLine();
            int c = 1;
            for (int i = 0; i < name.length(); i++){
                if (Character.compare(name.charAt(i), '+') == 0) c++;
                if (Character.compare(name.charAt(i), '-') == 0) c++;
            }
            int [] sign = new int[c];
            int d = 1;
            if (Character.compare(name.charAt(0), '-') == 0) sign[0] = -1;
            else { 
                sign[0] = 1;
            }
            for (int i = 1; i < name.length(); i++){
                if (Character.compare(name.charAt(i), '+') == 0){
                sign[d] = 1;
                d++;
                }
                else if (Character.compare(name.charAt(i), '-') == 0) {
                sign[d] = -1;
                d++;
                }
            }
            /* end get signs*/
            String [] alt  = name.split("[\\+, -]");
            double [] all =  new double [alt.length*2];

            
            int index = 0;
            int m = 0;
            for (int i = 0; i < alt.length; i++){
                String [] temp = alt[i].split("x");
                all[index] = Double.parseDouble(temp[0]) * sign[m];
                m++;
                index++;
                if (temp.length == 1){
                    all[index] = 0;
                    index++;
                }else{       
                    all[index] = Double.parseDouble(temp[1]);
                    index++;
                }
            }
            double [] coef = new double[c];
            int [] exp = new int[c];
            int t = 0;
            for (int i = 0; i < all.length; i = i +2){
                coef[t] = all[i];
                t++;
            }
            t = 0;
            for (int i = 1; i < all.length; i = i +2){
                exp[t] = (int)all[i];
                t++;
            }
            array = new double [coef.length];
            exponents = new int [coef.length];
            for (int i = 0; i < coef.length; i++){
                array[i] = coef[i];
            }
            // System.out.println();
            for (int i = 0; i < exp.length; i++){
                exponents[i] = exp[i];
            }
           file.close();
          } catch (IOException e) {
           e.printStackTrace();
          } 
          

        //   Polynomial mid = new Polynomial(array, exponents);
	}
	
	/*End COnstructors*/
	public Polynomial add(Polynomial poly2){
        
        int sim = sims(exponents, poly2.exponents);
        int [] fin =  new int [ sim + (exponents.length - sim) + (poly2.exponents.length -sim)];
        add_exp(exponents, poly2.exponents, fin);
        double [] coef =  new double [ sim + (exponents.length - sim) + (poly2.exponents.length -sim)];

        for (int i = 0; i < coef.length; i++){
            if (search(exponents, fin[i]) & search(poly2.exponents, fin[i])){
                coef[i] = array[Arrays.binarySearch(exponents, fin[i])] 
                    + poly2.array[Arrays.binarySearch(poly2.exponents, fin[i])];
            }else if (!search(exponents, fin[i])){
                coef[i] = poly2.array[Arrays.binarySearch(poly2.exponents, fin[i])];
            } else{
                coef[i] = array[Arrays.binarySearch(exponents, fin[i])];
            }
        }
        Polynomial mid = new Polynomial(coef, fin);

        return mid;
	}
	
    public Polynomial multiply (Polynomial args){
        double [] end = new double [exponents[exponents.length-1]+1];
        double [] end2 = new double [args.exponents[args.exponents.length-1]+1];

        coef_maker(array, end, exponents);
        coef_maker(args.array, end2, args.exponents);

        double [] mid = new double[end.length + end2.length -1];
        
        for (int i = 0; i < end.length; i++){
            for (int j = 0; j < end2.length; j++){
                mid[ i+j ] += (end[i] * end2[j]);
            }
        }

        // for (int i = 0; i <mid.length ; i++) System.out.print(mid[i] +"x");
        Polynomial last = new Polynomial(mid);
        return last;
    }
    


	public double evaluate (double number){
		double to_return = 0.0;
		for (int i = 0; i < array.length; i++) {
			to_return  += array[i] * (Math.pow(number, exponents[i]));
		}
		return to_return;
	}
	public boolean hasRoot( double number) {
		if (evaluate(number) == 0) return true;
		return false;
	}
	
	public void SaveToFile(String args) {
		try{
	        File file = new File(args);
	        FileWriter writer = new FileWriter(file);
	        writer.write(Double.toString(array[0]));
	        if (exponents[0] >0) writer.write("x" + exponents[0]);
	        for(int i = 1; i < array.length; i++){
	            if (array[i] >=0) writer.write("+" + Double.toString(array[i]));
	            else writer.write( Double.toString(array[i]));
	            if (exponents[i] >0) writer.write("x" + exponents[i]);
	        }
	        writer.close();
	      }
	      catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	}
}



