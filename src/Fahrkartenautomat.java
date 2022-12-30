import java.util.Scanner;

class Fahrkartenautomat
{
	public static double fahrkartenBestellungErfassen(Scanner in) {
		int[] options = {1,2,3,4,5,6,7,8,9,10};
		String[] names = {"Einzelfahrschein Berlin AB", "Einzelfahrschein Berlin BC", "Einzelfahrschein Berlin ABC", "Kurzstrecke", "Tageskarte Berlin AB", "Tageskarte Berlin BC", "Tageskarte Berlin ABC", "Kleingruppen-Tageskarte Berlin AB" , "Kleingruppen-Tageskarte Berlin BC", "Kleingruppen-Tageskarte Berlin ABC"}; 
		double[] prices = {2.9, 3.3, 3.6, 1.9, 8.6, 9.0, 9.6, 23.5, 24.3, 24.9};
		double zuZahlenderBetrag = 0.0; 
		int anzahlTickets = 0;
		int auswahl = 0;
	    do {
	    	System.out.println("Wählen Sie Ihre Wunschfahrkarte aus:");
	    	System.out.println();
	    	System.out.println("Option	Bezeichnung	Preis in EUR");
	    	for (int i = 0; i < options.length; i++) {
	    		System.out.println(options[i] + "\t" + names[i] + "\t" + prices[i]);
	    	}
	    	System.out.println((options.length+1) + "	Auswahl abschließen und Bezahlen");
	    	System.out.println();
	    	boolean wahlVorgang = true;
	    	System.out.print("Ihre Wahl: ");
	    	auswahl = in.nextInt();
    		System.out.println();
    		double adder = 0.0;
	    	do {
	    		if ((auswahl > 0) && (auswahl <= options.length)) {
	    		wahlVorgang = false;
	    		adder = prices[auswahl-1];
	    		}
	    		else if (auswahl == (options.length + 1)) {
	    			wahlVorgang = false;
	    		}
	    		else {
	    			System.out.println(auswahl + " ist keine gültige Option!");
	    		}
	    	} while (wahlVorgang);
	    	if (auswahl < (options.length + 1)) {
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
	    	
	    }while (auswahl != (options.length + 1));
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
		System.out.println("\nFahrschein wird ausgegeben");
	       for (int i = 0; i < 8; i++)
	       {
	    	  warte(250); 
	          System.out.print("=");
	          
	       }
	       System.out.println("\n\n");
		
	}
	
	public static void rueckgeldAusgeben(double zuZahlen, double eingeZahlt) {
		double rückgabebetrag = eingeZahlt - zuZahlen;
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
	       }
	}
	
	public static void muenzeAusgeben(int betrag, String Einheit) {
		System.out.println(Integer.toString(betrag) + Einheit);
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