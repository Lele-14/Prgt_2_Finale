import java.util.Random;
import java.util.Scanner;

public class App {
    /**
     * Calcola un importo casuale compreso tra un minimo e un massimo.
     *
     * @param max Valore massimo.
     * @param min Valore minimo.
     * @return Un valore casuale tra min e max.
     */

    public static double soldiRisultanti(double max, double min) {
        return min + (Math.random() * max);
    }

    /**
     * Determina casualmente se un investimento è vincente o perdente (dunque se
     * l'utente guadagnerà o perderà i soldi generati casualmente).
     *
     * @return true se l'investimento è vincente, false altrimenti.
     */

    public static boolean winOrLose() {
        Random random = new Random();
        int r = random.nextInt(2);
        return (r == 1); //al posto di fare if else come in precedenza ritorno solo la condizione
    }

    /**
     * Somma due valori double.
     *
     * @param a Primo valore.
     * @param b Secondo valore.
     * @return La somma di a e b.
     */

    public static double sommaDouble(double a, double b) {
        return a + b;
    }

    /**
     * Calcola una percentuale di un valore.
     *
     * @param n            Valore base.
     * @param numeratore   Numeratore della percentuale.
     * @param denominatore Denominatore della percentuale.
     * @return La percentuale calcolata.
     */

    public static double calcPercentuale(double n, double numeratore, double denominatore) {
        return (n * numeratore) / denominatore;
    }

    /**
     * Inizializza i nomi dei mesi in un array.
     *
     * @param mesi Array dei mesi da inizializzare.
     */

    public static void assegnaMesi(String[] mesi) {
        mesi[0] = "Gennaio";
        mesi[1] = "Febbraio";
        mesi[2] = "Marzo";
        mesi[3] = "Aprile";
        mesi[4] = "Maggio";
        mesi[5] = "Giugno";
        mesi[6] = "Luglio";
        mesi[7] = "Agosto";
        mesi[8] = "Settembre";
        mesi[9] = "Ottobre";
        mesi[10] = "Novembre";
        mesi[11] = "Dicembre";
    }

    /**
     * Ritorna il nome del mese corrispondente all'indice fornito.
     *
     * @param mesi   Array con i nomi dei mesi.
     * @param i Indice del mese.
     * @return Nome del mese.
     */

    public static String mostraMese(String[] mesi, int i) {
        return mesi[i];
    }

    public static void main(String[] args) {

        Scanner tastiera = new Scanner(System.in);

        //Nuovo Oggetto Menu (creato da Tonolo-Ceaglei)
        Menu menu = new Menu();

        //NUovo Oggetto Input (creato da Tonolo-Ceaglei)
        Input input = new Input();


        // variabili necessarie in vari sotto-ambienti del programma
        String[] mesi = new String[12];
        assegnaMesi(mesi);
        int choice1, contatoreMese = 0, durata = 0, anno = 2024;
        double wallet = 0, conto = 1000, max = 0, min = 0, sInvestiti = 0;
        //boolean infinity = true; basta mettere while True
        boolean investimento = false;

        // ciclo infinito, utilizzato per tenere traccia del trascorrere dei mesi
        while(true) {
            System.out.println("****Benvenuto nella gestione bancaria del tuo conto****" + "\n");

            wallet = wallet + 100;

            // se un investimento è in corso, si attua l'operazione di guadagno/perdita di
            // denaro casuale
            if (investimento) {
                double soldi = soldiRisultanti(max, min);
                if (winOrLose()) {
                    conto = conto + soldi;
                } else {
                    conto = conto - soldi;
                }
                durata--;
            }

            // se i mesi di durata degli investimenti terminano, si dichiara concluso
            // l'investimento

            if (durata == 0) {
                if (investimento)
                    System.out.println("(Investimento concluso)" + "\n");
                investimento = false;
            }

            do {
                // dati vari da mostrare all'utente
                System.out.println("Data --> " + mostraMese(mesi, contatoreMese) + " " + anno);
                System.out.println("Portafoglio --> " + wallet);
                System.out.println("Conto corrente --> " + conto + "\n\n");
                //choice1 = scelta(1);
                choice1 = menu.scelta(1); //il parametro mi indica in tipo di menu che uscirà

                switch (choice1) {

                    // selezione prelievo
                    case 1: {
                        if (conto > 0) {
                            String x = "Inserire la somma di denaro che si desidera prelevare --> ";
                            double prelievo = input.leggiDouble(x);
                            while (prelievo> conto) {
                                System.out.println(
                                        "ATTENZIONE! Non ci sono abbastanza soldi in banca. Scegliere una cifra piu' piccola.");
                                prelievo = input.leggiDouble(x);
                            }
                            // conto = sommaDouble(conto, (prelievo * -1));
                            conto = conto - prelievo;
                            // wallet = sommaDouble(wallet, prelievo);
                            wallet = wallet + prelievo;
                        } else {
                            System.out.println(
                                    "ATTENZIONE! Il tuo conto corrente e' uguale o minore a 0 euro. Non ti e' permesso prelevare.");
                        }
                        break;
                    }

                    // selezione deposito
                    case 2: {
                        if (wallet > 0) {
                            String x = "Inserire la somma di denaro che si desidera depositare --> ";
                            double deposito = input.leggiDouble(x);
                            while (deposito > wallet) {
                                System.out.println(
                                        "ATTENZIONE! Non ci sono abbastanza soldi nel portafoglio. Scegliere una cifra piu' piccola.");
                                deposito = input.leggiDouble(x);
                            }
                            conto = sommaDouble(conto, deposito);
                            wallet = sommaDouble(conto, (deposito * -1));
                        } else {
                            System.out.println("ATTENZIONE! Il tuo portafoglio e' vuoto. Non puoi depositare.");
                        }
                        break;
                    }

                    // selezione investimento
                    case 3: {
                        if (conto > 0) {
                            if (investimento) {
                                System.out.println("ATTENZIONE! Stai gia' eseguendo un investimento.");
                            } else {
                                int choice2 = menu.scelta(2);
                                if (choice2 == 0)
                                    break;
                                int choice3 = menu.scelta(3);;
                                if (choice3 == 0)
                                    break;

                                investimento = true;

                                String x = "Inserire la somma di denaro che si vuole investire --> ";
                                sInvestiti = input.leggiDouble(x);
                                while (sInvestiti > conto || sInvestiti < 1) {
                                    System.out.println(
                                            "ATTENZIONE! Non e' possibile investire una somma di denaro maggiore del conto corrente o minore di 1 euro");
                                    sInvestiti = input.leggiDouble(x);
                                }

                                switch (choice2) {

                                    // assegnazione della durata in base alla scelta 2
                                    case 1: {
                                        durata = 1;
                                        break;
                                    }

                                    case 2: {
                                        durata = 3;
                                        break;
                                    }

                                    case 3: {
                                        durata = 6;
                                        break;
                                    }

                                }

                                switch (choice3) {

                                    // assegnazione dei soldi massimi e minimi che si possono perdere/guadagnare
                                    // ogni mese in base alla scelta 3
                                    case 1: {
                                        min = calcPercentuale(sInvestiti, 5.0, 100.0);
                                        max = calcPercentuale(sInvestiti, 10.0, 100.0);
                                        break;
                                    }

                                    case 2: {
                                        min = calcPercentuale(sInvestiti, 15.0, 100.0);
                                        max = calcPercentuale(sInvestiti, 20.0, 100.0);
                                        break;
                                    }

                                    case 3: {
                                        min = calcPercentuale(sInvestiti, 25.0, 100.0);
                                        max = calcPercentuale(sInvestiti, 30.0, 100.0);
                                        break;
                                    }
                                }
                            }

                        } else {
                            System.out.println("ATTENZIONE! Non hai soldi in banca da investire.");
                        }
                        break;
                    }
                }
            } while (choice1 != 0);

            // avanzamento del mese e dell'anno
            if (contatoreMese < 11) {
                contatoreMese++;
            } else {
                contatoreMese = 0;
                anno++;
            }
        }

    }

}
