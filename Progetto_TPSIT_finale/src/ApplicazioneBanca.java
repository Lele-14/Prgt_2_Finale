import java.util.Random;
import java.util.Scanner;

//LAVORO INIZIALE FORNITO DA UN'ALTRA COPPIA SU CUI NOI ABBIAMO LAVORATO

public class ApplicazioneBanca {

	/**
	 * Mostra il menu principale con le opzioni disponibili per la gestione del
	 * conto.
	 */
	public static void menu1() {
		System.out.println("***Scegli l'opzione***\n");
		System.out.println("1)Preleva il denaro");
		System.out.println("2)Deposita il denaro");
		System.out.println("3)Investi del denaro\n");
		System.out.println("0)ESCI e vai al mese successivo");
		System.out.println("---> ");
	}

	/**
	 * Mostra il menu per scegliere la durata dell'investimento.
	 */

	public static void menu2() {
		System.out.println("****INVESTIMENTO****");
		System.out.println("***Scegli la durata***\n");
		System.out.println("1)Breve (Mensile)");
		System.out.println("2)Media (Trimestrale)");
		System.out.println("3)Lunga (Semestrale)\n");
		System.out.println("0)ANNULLA");
		System.out.println("---> ");
	}

	/**
	 * Mostra il menu per scegliere il livello di rischio e guadagno
	 * dell'investimento.
	 */

	public static void menu3() {
		System.out.println("****INVESTIMENTO****");
		System.out.println("***Scegli il rischio e il guadagno***\n");
		System.out.println("1)Basso rischio e basso guadagno");
		System.out.println("2)Medio rischio e medio guadagno");
		System.out.println("3)Alto rischio e alto guadagno\n");
		System.out.println("0)ANNULLA");
		System.out.println("---> ");
	}


	public static double leggiDouble(String x) {
		Scanner input = new Scanner(System.in);
		double d = 0;
		String stringa;
		boolean ok;
		do {
			ok = true;
			System.out.print(x);
			stringa = input.nextLine();
			System.out.print("\n");
			try {
				d = Double.parseDouble(stringa);
			} catch (NumberFormatException e) {
				ok = false;
				System.out.println("ERRORE! Formato non valido. Reinserire");
			}

			if (ok) {
				if (d < 0) {
					ok = false;
					System.out.println("ERRORE! Valore non valido. Reinserire");
				}
			}
		} while (!ok);
		return d;
	}


	public static int scelta(int x) {
		Scanner input = new Scanner(System.in);
		int scelta = 10;
		String stringa;
		boolean ok;
		do {
			switch (x) {

			case 1: {
				menu1();
				break;
			}

			case 2: {
				menu2();
				break;
			}

			case 3: {
				menu3();
				break;
			}

			}
			stringa = input.nextLine();
			ok = true;
			try {
				scelta = Integer.parseInt(stringa);
			} catch (NumberFormatException e) {
				ok = false;
				System.out.println("ERRORE! Formato non valido. Reinserire");
			}
			if (ok) {
				if (scelta < 0 || scelta > 3) {
					ok = false;
					System.out.println("ERRORE! Valore non valido. Reinserire");
				}
			}
		} while (!ok);
		return scelta;
	}

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
		if (r == 1)
			return true;
		else
			return false;
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

	public static void assegnaMesi(String mesi[]) {
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


	public static String mostraMese(String mesi[], int i) {
		return mesi[i];
	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		// variabili necessarie in vari sotto-ambienti del programma
		String mesi[] = new String[12];
		assegnaMesi(mesi);
		int choice1, contatoreMese = 0, durata = 0, anno = 2024;
		double wallet = 0, conto = 1000, max = 0, min = 0, sInvestiti = 0;
		boolean infinity = true, investimento = false;

		// ciclo infinito, utilizzato per tenere traccia del trascorrere dei mesi
		while (infinity) {
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
					System.out.print("\n" + "(Investimento concluso)" + "\n");
				investimento = false;
			}

			do {
				// dati vari da mostrare all'utente
				System.out.println("Data --> " + mostraMese(mesi, contatoreMese) + " " + anno);
				System.out.println("Portafoglio --> " + wallet);
				System.out.println("Conto corrente --> " + conto + "\n\n");
				choice1 = scelta(1);
				switch (choice1) {

				// selezione prelievo
				case 1: {
					if (conto > 0) {
						String x = "Inserire la somma di denaro che si desidera prelevare --> ";
						double prelievo = leggiDouble(x);
						while (prelievo > conto) {
							System.out.println(
									"ATTENZIONE! Non ci sono abbastanza soldi in banca. Scegliere una cifra piu' piccola.");
							prelievo = leggiDouble(x);
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
						double deposito = leggiDouble(x);
						while (deposito > wallet) {
							System.out.println(
									"ATTENZIONE! Non ci sono abbastanza soldi nel portafoglio. Scegliere una cifra piu' piccola.");
							deposito = leggiDouble(x);
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
							int choice2 = scelta(2);
							if (choice2 == 0)
								break;
							int choice3 = scelta(3);
							if (choice3 == 0)
								break;

							investimento = true;

							String x = "Inserire la somma di denaro che si vuole investire --> ";
							sInvestiti = leggiDouble(x);
							while (sInvestiti > conto || sInvestiti < 1) {
								System.out.println(
										"ATTENZIONE! Non e' possibile investire una somma di denaro maggiore del conto corrente o minore di 1 euro");
								sInvestiti = leggiDouble(x);
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
