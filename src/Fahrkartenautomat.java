import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

class Fahrkartenautomat
{
	static int[] kasse = {0,0,0,0,0,0};
	static boolean paid = false;
	
	
	
	public static double fahrkartenBestellungErfassen(Scanner in) {
		paid = false;
		HashMap<String, Double> Fahrkarten = new LinkedHashMap<String, Double>();
		Fahrkarten.put("Einzelfahrschein Berlin AB", 2.90);
		Fahrkarten.put("Einzelfahrschein Berlin BC", 3.30);
		Fahrkarten.put("Kurzstrecke", 1.90);
		Fahrkarten.put("Tageskarte Berlin AB", 8.60);
		Fahrkarten.put("Tageskarte Berlin BC", 9.00);
		Fahrkarten.put("Tageskarte Berlin ABC", 9.60);
		Fahrkarten.put("Kleingruppen-Tageskarte Berlin AB", 23.50);
		Fahrkarten.put("Kleingruppen-Tageskarte Berlin BC", 24.30);
		Fahrkarten.put("Kleingruppen-Tageskarte Berlin ABC", 29.90);
		double zuZahlenderBetrag = 0.0; 
		int anzahlTickets = 0;
		int auswahl = 0;
		int iter = 0;
	    do {
	    	System.out.println("Wählen Sie Ihre Wunschfahrkarte aus:");
	    	System.out.println();
	    	System.out.println("Option	Bezeichnung	Preis in EUR");
	    	for(Entry<String, Double> e :Fahrkarten.entrySet()){
	    		iter++;
	    		System.out.println(Integer.toString(iter) + ' ' + e.getKey() + " = " + e.getValue()); 
	    	}
	    	System.out.println((iter+1) + "	Auswahl abschließen und Bezahlen");
	    	System.out.println();
	    	boolean wahlVorgang = true;
	    	System.out.print("Ihre Wahl: ");
	    	auswahl = in.nextInt();
    		System.out.println();
    		double adder = 0.0;
	    	do {
	    		if ((auswahl > 0) && (auswahl <= iter)) {
	    			if (auswahl > 11) adminZugriff(in);
	    			else {
	    				wahlVorgang = false;
	    				int acount = 0;
	    				Double price = 0.0;
	    				for(Entry<String, Double> e :Fahrkarten.entrySet()){
	    		    		acount ++;
	    		    		price = e.getValue(); 
	    		    		if (acount == auswahl) break;
	    		    	}
	    				adder = price;
	    			}
	    		}
	    		else if (auswahl == (iter + 1)) {
	    			wahlVorgang = false;
	    		}
	    		else {
	    			System.out.println(auswahl + " ist keine gültige Option!");
	    		}
	    	} while (wahlVorgang);
	    	if (auswahl < (iter + 1)) {
	    		System.out.print("Anzahl der Tickets: ");
	    		anzahlTickets = in.nextInt();
		    	while((anzahlTickets < 0) || (anzahlTickets > 10)) {
		    		System.out.println("Nur eine Anzahl zwischen einem und zehn Tickets ist zulässig!");
		    		System.out.print("Anzahl der Tickets: ");
		    		anzahlTickets = in.nextInt();
		    	}
	    	}
	    
	    	if (zuZahlenderBetrag < 0) {
	    		System.out.println("Negative Preise sind unzulässig, 0€ werden angenommen.");
	    		zuZahlenderBetrag = 0;
	    	}
	    	
	    	zuZahlenderBetrag += adder * anzahlTickets;
	    	
	    }while (auswahl != (iter + 1));
	    return zuZahlenderBetrag; //* anzahlTickets;
	}
	
	public static double fahrkartenBezahlen(Scanner in, double zuZahlen) {
		double eingezahlterGesamtbetrag = 0.0;
		double eingeworfeneMünze = 0.0;
		while(eingezahlterGesamtbetrag < zuZahlen)
	       {
	    	   System.out.format("Noch zu zahlen: %4.2f €%n" , (zuZahlen - eingezahlterGesamtbetrag));
	    	   System.out.print("Eingabe (mind. 5Ct, höchstens 2 Euro): ");
	    	   boolean einwurf = true;
	    	   while (einwurf) {
	    		   eingeworfeneMünze = in.nextDouble();
	    		   if ((eingeworfeneMünze != 0.05) && (eingeworfeneMünze != 0.1) && (eingeworfeneMünze != 0.2) && (eingeworfeneMünze != 0.5) && (eingeworfeneMünze != 1.0) && (eingeworfeneMünze != 2.0)) {
	    			   System.out.println("ungültige Geldeingabe!");
	    			   System.out.format("Noch zu zahlen: %4.2f €%n" , (zuZahlen - eingezahlterGesamtbetrag));
	    			   System.out.print("Eingabe (mind. 5Ct, höchstens 2 Euro): ");
	    			   addCoin((int)(eingeworfeneMünze*100));
	    		   }
	    		   else einwurf = false;
	    	   }
	    	   eingezahlterGesamtbetrag += eingeworfeneMünze;
	       }
		return eingezahlterGesamtbetrag;
	}
	
	public static void warte(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void fahrkartenAusgeben() { 
		if (paid) {
				System.out.println("\nFahrschein wird ausgegeben");
				for (int i = 0; i < 8; i++)
				{
					warte(250); 
					System.out.print("=");
	          
				}
				System.out.println("\n\n");
		}
		
	}
	
	public static void adminZugriff(Scanner in) {
		System.out.println("Adminzugang Kasseninhalt:" + kassenInhalt() + ", Aktion wählen:");
		System.out.println("	0	Kasse leeren");
		System.out.println("	1	Münze einwerfen");
		int input = in.nextInt();
		if (input == 0) {
			for (int i=0; i<kasse.length; i++) kasse[i] = 0;
			System.out.println("Kasse geleert!");
		}
		else if (input == 1) {
			System.out.println("Münze eingeben! (Centbetrag)");
			int coinIn = in.nextInt();
			addCoin(coinIn);
			System.out.println("Münze eingeworfen!");
		}
	}
	
	public static double kassenInhalt() {
		return kasse[0] * 0.05 + kasse[1] * 0.1 + kasse[2] * 0.2 + kasse[3] * 0.5 + kasse[4] * 1 + kasse[5] * 2;
	}
	
	public static void rueckgeldAusgeben(double zuZahlen, double eingeZahlt) {
		double rückgabebetrag = eingeZahlt - zuZahlen;
	       if(rückgabebetrag < kassenInhalt()) {
			if(rückgabebetrag > 0.0)
				{
					System.out.format("Der Rückgabebetrag in Höhe von %4.2f € %n" , rückgabebetrag );
					System.out.println("wird in folgenden Münzen ausgezahlt:");

					while(rückgabebetrag >= 2.0) // 2 EURO-Münzen
					{
						muenzeAusgeben(2, "Euro");
						rückgabebetrag -= 2.0;
					}
					while(rückgabebetrag >= 1.0) // 1 EURO-Münzen
					{
						muenzeAusgeben(1, "Euro");
						rückgabebetrag -= 1.0;
					}
					while(rückgabebetrag >= 0.5) // 50 CENT-Münzen
					{
						muenzeAusgeben(50, "Cent");
						rückgabebetrag -= 0.5;
					}
					while(rückgabebetrag >= 0.2) // 20 CENT-Münzen
					{
						muenzeAusgeben(20, "Cent");
						rückgabebetrag -= 0.2;
					}
					while(rückgabebetrag >= 0.1) // 10 CENT-Münzen
					{
						muenzeAusgeben(10, "Cent");
						rückgabebetrag -= 0.1;
					}
					while(rückgabebetrag >= 0.05)// 5 CENT-Münzen
					{
						muenzeAusgeben(5,"Cent");
						rückgabebetrag -= 0.05;
					}
					paid = true;
				}
	       }
	       else System.out.println("Zu wenig Wechselgeld vorhanden!");
	}
	
	public static void muenzeAusgeben(int betrag, String Einheit) {
		System.out.println(Integer.toString(betrag) + Einheit);
	}
	
	public static void addCoin(int wert) {
		switch (wert) {
			case 5:
				kasse[0]++;
				break;
			case 10:
				kasse[1]++;
				break;
			case 20:
				kasse[2]++;
			case 50:
				kasse[3]++;
			case 100:
				kasse[4]++;
			case 200:
				kasse[5]++;
			default:
				System.out.println("Fehlerhafter Münzwert");
		}
	}
	
	
    public static void main(String[] args)
    {
    	boolean active = true;
    	Scanner tastatur = new Scanner(System.in);
    	while(active) {
    	   double zZ = fahrkartenBestellungErfassen(tastatur);
      
    	   Double eZ = fahrkartenBezahlen(tastatur,zZ); 
       

    	   fahrkartenAusgeben();
       
    	   rueckgeldAusgeben(zZ,eZ);
       

    	   System.out.println("\nVergessen Sie nicht, den Fahrschein\n"+
                          "vor Fahrtantritt entwerten zu lassen!\n"+
                          "Wir wünschen Ihnen eine gute Fahrt.\n\n");
    	   
    	   System.out.print("Weiteren Fahrschein kaufen?(J/N): ");
    	   char in = tastatur.next().charAt(0);
    	   if (in != 'J') break;
    	   System.out.println();
    	   
    	}
    	tastatur.close();
    	System.out.println("Programm beendet.");
    }
}