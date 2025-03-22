import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class gestione {
    private static Vector<String> raccoltapassword = new Vector<String>(10, 20);

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

    public static String logIn() {
        System.out.println("Inserisci la tua password --> ");
        String password = Input.leggiString();
        return password;
    }

    public static Utente LoadUtente() {
        String nome, cognome, password;
        double portafoglio, contoBancario;
        int eta;
        System.out.println("Inserisci il nome --> ");
        nome = Input.leggiString();
        System.out.println("Inserisci il cognome --> ");
        cognome = Input.leggiString();
        System.out.println("Inserisci la password --> ");
        password = Input.leggiString();
        while (password.length() < 8) {
            System.out.println("La password deve avere almeno 8 caratteri. Reinserisci la password --> ");
            password = Input.leggiString();
        }

        for (int i = 0; i < raccoltapassword.size(); i++) {
            while (password.equals(raccoltapassword.elementAt(i))) {
                System.out.println("Password non valida! Reinserisci la password --> ");
                password = Input.leggiString();
            }
        }

        raccoltapassword.add(password);
        System.out.println("Inserisci l'età --> ");
        eta = (int) Input.leggiIntero(Input.leggiString());
        while (eta < 18 || eta > 100) {
            System.out.println("Attenzione. Eta' compresa tra 18 e 100 anni. Reinserisci l'età --> ");
            eta = (int) Input.leggiIntero(Input.leggiString());
        }
        System.out.println("Inserisci il tuo conto in portafoglio --> ");
        portafoglio = Input.leggiDouble(Input.leggiString());

        while (portafoglio < 0 || portafoglio > 1000000) {
            System.out.println(
                    "Attenzione. Il tuo conto in portafoglio deve essere compreso tra 0 e 1000000 euro. Reinserisci il tuo conto -->");
            portafoglio = Input.leggiDouble(Input.leggiString());
        }

        System.out.println("Inserisci il tuo conto bancario --> ");
        contoBancario = Input.leggiDouble(Input.leggiString());

        while (contoBancario < 0 || portafoglio > 1000000) {
            System.out.println(
                    "Attenzione. Il tuo conto in banca deve essere compreso tra 0 e 1000000 euro. Reinserisci il tuo conto -->");
            portafoglio = Input.leggiDouble(Input.leggiString());
        }

        Utente utente = new Utente(nome, cognome, eta, password, portafoglio, contoBancario);
        return utente;
    }

    // ************************************************************************************************************************************************
    // */

    public static void main(String[] args) throws Exception {
        fileUtenti fileUser = new fileUtenti();

        Banca banca = new Banca("Lele & Artur BANK, altro che intesa San Paolo");

        Utente utente;

        String mesi[] = new String[12];
        assegnaMesi(mesi);
        Menu menu = new Menu();

        String password;
        int contatoriMesi = 0, anno = 2025;
        int ch = 1;
        String azione = "";

        do {
            System.out.println(mesi[contatoriMesi] + " " + anno);
            String transazione = "TRANSAZIONE:\n" + mesi[contatoriMesi] + " " + anno + "\n";
            menu.menu1();
            System.out.print("Fai la tua scelta --> ");
            ch = (int) Input.leggiIntero(Input.leggiString());
            while (ch < 0 || ch > 7) {
                System.out.print("Attenzione. Reinserire valori tra 0 e 7 --> ");
                ch = (int) Input.leggiIntero(Input.leggiString());
            }
            switch (ch) {
                case 1: {
                    System.out.println("1. Iscriviti o ritrova le tue credenziali");
                    menu.menu4();
                    System.out.print("Fai la tua scelta --> ");
                    ch = (int) Input.leggiIntero(Input.leggiString());
                    while (ch < 1 || ch > 2) {
                        System.out.print("Attenzione. Reinserire valori tra 1 e 2 --> ");
                        ch = (int) Input.leggiIntero(Input.leggiString());
                    }
                    switch (ch) {
                        case 1: {
                            System.out.println("1. Iscriviti");
                            utente = LoadUtente();
                            if (banca.addUtente(utente)) {
                                System.out.println("Utente iscritto con successo");
                                try {
                                    File gestioneUtente = fileUser.creaFileUtente(utente);
                                    fileUser.aggiungiFile(gestioneUtente);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            } else {
                                System.out.println("Utente gia' presente");
                            }
                            break;

                        }
                        case 2: {
                            System.out.println("2) Accedi attraverso file");
                            System.out.print(
                                    "Inserisci il nome del file. Ricorda che devi mettere ''congnome'' ''_'' ''nome'' --> ");
                            String nomeFile = Input.leggiString();
                            //nomeFile = nomeFile + ".txt";
                            utente = banca.prelevaDaFile(nomeFile, nomeFile+ ".txt");

                            if (utente == null) {
                                System.out.println("ATTENZIONE!!! Utente insesistente");
                            } else {
                                System.out.println("Devi cambiare password");
                                password = logIn();
                                utente.setPassword(password);
                                if (banca.addUtente(utente)) {
                                    System.out.println("Utente accesso con successo");
                                }
                            }
                            break;

                        }
                    }
                    break;
                }

                case 2: {
                    System.out.println("2. Accedi e visualizza");

                    password = logIn();
                    int persona = banca.findPersona(password);
                    if (persona != -1) {
                        System.out.println("\nBENVENUTO");
                        System.out.println(banca.getUtente(persona).toString());
                    } else {
                        System.out.println("Password errata");
                    }
                    break;

                }
                case 3: {

                    System.out.println("3)Deposita il denaro");

                    password = logIn();
                    if (banca.findPersona(password) == -1) {
                        System.out.println("Persona non trovata");
                        break;
                    }
                    System.out.print("Inserire la quota che vuoi depositare --> ");
                    double deposito = Input.leggiDouble(Input.leggiString());
                    while (deposito < 0 || deposito > banca.getUtente(banca.findPersona(password)).getPortafoglio()) {
                        System.out.print("ATTENZIONE!!! Reinserire la quota che vuoi depositare --> ");
                        deposito = Input.leggiDouble(Input.leggiString());
                    }
                    if (banca.Deposito(deposito, password)) {
                        System.out.println("Deposito avvenuto con successo");
                        azione = azione + transazione + "Deposito: " + deposito;
                        try {
                            File gestioneUtente = fileUser.creaFileUtente(banca.getUtente(banca.findPersona(password)));
                            fileUser.aggiungiFile(gestioneUtente);
                            // fileUser.creaFileUtente(utente);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        fileUser.appendToFile(banca.getUtente(banca.findPersona(password)), azione);

                    } else {
                        System.out.println("Deposito NON avvenuto con successo");
                    }
                    break;

                }
                case 4: {
                    System.out.println("4)Preleva il denaro");
                    password = logIn();
                    while (banca.findPersona(password) == -1) {
                        System.out.println("Persona non trovata");
                        password = logIn();
                        break;
                    }
                    System.out.print("Inserire la quota che vuoi prelevare --> ");
                    double prelievo = Input.leggiDouble(Input.leggiString());

                    while (prelievo < 0 || prelievo > banca.getUtente(banca.findPersona(password)).getContoBancario()) {
                        System.out.print("ATTENZIONE!!! Reinserire la quota che vuoi prelevare --> ");
                        prelievo = Input.leggiDouble(Input.leggiString());
                    }

                    if (banca.Prelievo(prelievo, password, banca.findPersona(password))) {
                        System.out.println("Prelievo avvenuto con successo");
                        azione = azione + transazione + "Prelievo: " + prelievo+"\n";
                        try {
                            File gestioneUtente = fileUser.creaFileUtente(banca.getUtente(banca.findPersona(password)));
                            fileUser.aggiungiFile(gestioneUtente);
                            // fileUser.creaFileUtente(utente);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        fileUser.appendToFile(banca.getUtente(banca.findPersona(password)), azione);

                    } else {
                        System.out.println("Prelievo NON avvenuto con successo");
                    }
                    break;
                }
                case 5: {
                    double guadagno = -1;

                    System.out.println("5)Investi del denaro\n");
                    password = logIn();
                    if (banca.findPersona(password) == -1) {
                        System.out.println("Impossibile investire. Password errata");
                        break;
                    }
                    menu.menu2();
                    ch = (int) Input.leggiIntero(Input.leggiString());
                    while (ch < 0 || ch > 3) {
                        System.out.print("Attenzione. Reinserire valori tra 0 e 3 --> ");
                        ch = (int) Input.leggiIntero(Input.leggiString());
                    }

                    switch (ch) {
                        case 1: {
                            System.out.println("1)Breve (Mensile)");
                            contatoriMesi = contatoriMesi + 1;
                            banca.avanzamentoMese(100);
                            break;
                        }

                        case 2: {
                            System.out.println("2)Media (Trimestrale)");
                            contatoriMesi = contatoriMesi + 3;
                            banca.avanzamentoMese(100 * 3);
                            break;
                        }

                        case 3: {
                            System.out.println("3)Lunga (Semestrale)\n");
                            contatoriMesi = contatoriMesi + 6;
                            banca.avanzamentoMese(100 * 6);
                            break;
                        }

                        case 0: {
                            System.out.println("0)ANNULLA");
                            break;
                        }

                    }

                    menu.menu3();
                    ch = (int) Input.leggiIntero(Input.leggiString());
                    while (ch < 0 || ch > 3) {
                        System.out.print("Attenzione. Reinserire valori tra 0 e 3 --> ");
                        ch = (int) Input.leggiIntero(Input.leggiString());
                    }
                    System.out.print("Inserire la quota da investire--> ");
                    double quotaInvestimento = Input.leggiDouble(Input.leggiString());

                    while (quotaInvestimento < 1
                            || quotaInvestimento > banca.getUtente(banca.findPersona(password)).getContoBancario()) {
                        System.out.print("ATTENZIONE! Valori non corretti. Reinserire la quota da investire--> ");
                        quotaInvestimento = Input.leggiDouble(Input.leggiString());
                    }

                    banca.getUtente(banca.findPersona(password)).setContoBancario(
                            banca.getUtente(banca.findPersona(password)).getContoBancario() - quotaInvestimento);
                    switch (ch) {
                        case 1: {
                            System.out.println("1)Basso rischio e basso guadagno");
                            guadagno = banca.Investimento(quotaInvestimento, password, 100);
                            break;
                        }

                        case 2: {
                            System.out.println("2)Medio rischio e medio guadagno");
                            guadagno = banca.Investimento(quotaInvestimento, password, 250);
                            break;
                        }

                        case 3: {
                            System.out.println("3)Alto rischio e alto guadagno");
                            guadagno = banca.Investimento(quotaInvestimento, password, 500);
                            break;
                        }

                    }
                    if (guadagno > quotaInvestimento) {
                        System.out.println("Investimento andato a buon fine!!!");
                        System.out.println(
                                "Con l'investimento hai guadagnato " + (guadagno - quotaInvestimento) + " !!!");
                    } else {
                        System.out.println("Investimento NON andato a buon fine!!!");
                        System.out.println(
                                "Con l'investimento hai perso " + ((guadagno - quotaInvestimento) / (-1)) + " !!!");
                    }

                    azione = transazione + "Investimento: " + quotaInvestimento;
                    azione = azione + "\tGuadagnato: " + guadagno;

                    fileUser.appendToFile(banca.getUtente(banca.findPersona(password)), azione);

                    banca.getUtente(banca.findPersona(password)).setContoBancario(
                            banca.getUtente(banca.findPersona(password)).getContoBancario() + guadagno);
                    break;
                }
                case 6: {
                    System.out.println("6)Vai al mese successivo");
                    contatoriMesi = contatoriMesi + 1;
                    banca.avanzamentoMese(100);
                }

                case 7: {
                    System.out.println("7)Disdetta dalla banca");
                    password = logIn();
                    int persona = banca.findPersona(password);
                    if (persona == -1) {
                        System.out.println("Utente non trovato");
                    } else {
                        if (banca.removeUtente(persona)) {
                            System.out.println("Disdetta effettuata");
                            raccoltapassword.remove(persona);
                        } else {
                            System.out.println("Disdetta NON effettuata");
                        }
                    }
                }

                case 0: {

                    System.out.println("0)ESCI *** ");
                    System.out.println("Arrivederci");
                }
            }

            if (contatoriMesi > 11) {
                contatoriMesi = 0;
                anno = anno + 1;
            }

        } while (ch != 0);

    }
}
