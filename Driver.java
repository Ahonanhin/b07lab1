public class Driver {  
public static void main( String[] args) {
		
        double [] a1 = {14, 0, 0, 4, 6, 0, 6, 7};
        Polynomial poly1 = new Polynomial(a1);

        double [] a2 = {0};
        Polynomial poly2 = new Polynomial(a2);

        Polynomial poly3 = new Polynomial("test.txt");
        
        

        double [] a4 = {-1 , 0, 2, 0, -2};
        Polynomial poly4 = new Polynomial(a4);

        System.out.println("Testing add function");
        Polynomial test1 = poly1.add(poly2);
        for (int i = 0; i < test1.array.length; i++){
            if (poly1.array[i] != test1.array[i]) {
                System.out.println("error with add function");
                return;
            }
        }
        System.out.println("Testing add function: done -correct");

        System.out.println("Testing multiply function");
        Polynomial test2 = poly3.multiply(poly1);
        double [] a3 = {14, 28 , -56, 718, 14, -4, 186, 325, -332, 278, 357, -92, -138, -138, -161 };
        Polynomial test2_ = new Polynomial(a3);
        for (int i = 0; i < test2.array.length; i++){
            if (test2.array[i] != test2_.array[i]) {
                System.out.println("error with multiply function");
                return;
            }
        }
        System.out.println("Testing multiply function: done -correct");

        System.out.println("Testing evaluate function");
        if(poly4.evaluate(0) != -1 && poly4.evaluate(0.707) != 0.5) {
            System.out.println("error with evaluate function");
            return;
        }
        System.out.println("Testing evaluate function: done -correct");
        System.out.println("Testing saveToFile function");
        Polynomial poly5 = poly1.add(poly4);
        poly5.SaveToFile("random.txt");
        Polynomial poly6 = new Polynomial("random.txt");
        for (int i = 0; i < poly6.array.length; i++){
            if (poly6.array[i] != poly5.array[i]) {
                System.out.println("error with saveToFile function");
                return;
            }
        }
        System.out.println("Testing saveToFile function: done -correct");

        System.out.println("Testing hasRoot function:");
        double [] a7 = {0, 1, 0, 0, 0, 5};
        double [] a8 = {0,0,0,1,1};
        Polynomial poly7 = new Polynomial (a7);
        Polynomial poly8 = new Polynomial (a8);
        Polynomial test3 = poly7.multiply(poly8);
        if (!test3.hasRoot(0) && !test3.hasRoot(-1)) {
            System.out.println("error with hasRoot function");
            return;
        }
        System.out.println("Testing hasRoot function: done -correct");
        

        System.out.println("Passed all basic test cases");
	}
} 