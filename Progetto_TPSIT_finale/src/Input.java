import java.util.Scanner;

public class Input {
    
    public static Scanner input = new Scanner(System.in);

    public static double leggiDouble(String x) {
        
        double d = 0;
   
            try {
                d = Double.parseDouble(x);
            } catch (NumberFormatException e) {
                System.out.println("ERRORE! Formato non valido. Reinserire");
            }

        return d;
    }




    public static double leggiIntero(String x) {
        
        int i = -1;
            try {
                i = (int)Integer.parseInt(x);
            } catch (NumberFormatException e) {
                
                System.out.println("ERRORE! Formato non valido. Reinserire");
            }

        return i;
    }




    public static String leggiString () {
        String s="";
        s=input.nextLine();
        return s;

    }

}
