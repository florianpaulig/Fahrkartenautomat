
public class Widerstandsnetzwerk {

	public static void main(String[] args) {
		//Widerstände wX in Ohm
		double w1=1.2, w2=2.7, w3=8.2;
		
		double wreihe = w1+w2+w3;
		double wparallel = 1.0/((1.0/w1)+(1.0/w2)+(1.0/w3));
		
		System.out.print("Reihenschaltung:\t");
		System.out.printf("%.2f" , wreihe);
		System.out.print(" Ohm\n");
		System.out.print("Parallelschaltung:\t");
		System.out.printf("%.2f", wparallel);
		System.out.print(" Ohm\n");

	}

}
