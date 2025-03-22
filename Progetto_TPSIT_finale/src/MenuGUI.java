
import javax.swing.*;
import java.awt.*;

import java.io.*;

public class MenuGUI extends JFrame {
    private Banca banca;
    private fileUtenti fileUser;
    private String[] mesi = new String[12];
    private int contatoriMesi = 0;
    private int anno = 2025;

    public MenuGUI() {
        banca = new Banca("Lele & Artur BANK");
        fileUser = new fileUtenti();
        gestione.assegnaMesi(mesi);

        setTitle("Lele & Artur BANK - Menu");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 1, 10, 10));

        JButton btnIscriviti = new JButton("1. Iscriviti");
        JButton btnAccedi = new JButton("2. Accedi e visualizza");
        JButton btnDeposita = new JButton("3. Deposita denaro");
        JButton btnPreleva = new JButton("4. Preleva denaro");
        JButton btnInvesti = new JButton("5. Investi denaro");
        JButton btnMese = new JButton("6. Vai al mese successivo");
        JButton btnDisdetta = new JButton("7. Disdetta");
        JButton btnEsci = new JButton("0. Esci");

        add(btnIscriviti);
        add(btnAccedi);
        add(btnDeposita);
        add(btnPreleva);
        add(btnInvesti);
        add(btnMese);
        add(btnDisdetta);
        add(btnEsci);

        btnIscriviti.addActionListener(e -> iscriviti());
        btnAccedi.addActionListener(e -> accediVisualizza());
        btnDeposita.addActionListener(e -> depositaDenaro());
        btnPreleva.addActionListener(e -> prelevaDenaro());
        btnInvesti.addActionListener(e -> investiDenaro());
        btnMese.addActionListener(e -> avanzaMese());
        btnDisdetta.addActionListener(e -> disdetta());
        btnEsci.addActionListener(e -> System.exit(0));
    }

    private void iscriviti() {
        try {
            String nome = JOptionPane.showInputDialog(this, "Inserisci Nome:");
            String cognome = JOptionPane.showInputDialog(this, "Inserisci Cognome:");
            String password;
            do {
                password = JOptionPane.showInputDialog(this, "Inserisci Password (min 8 caratteri):");
            } while (password.length() < 8);

            int eta = Integer.parseInt(JOptionPane.showInputDialog(this, "Inserisci Età (18-100):"));
            while (eta < 18 || eta > 100) {
                eta = Integer.parseInt(JOptionPane.showInputDialog(this, "Età non valida! Reinserisci Età (18-100):"));
            }

            double portafoglio = Double
                    .parseDouble(JOptionPane.showInputDialog(this, "Inserisci Conto Portafoglio (0-1.000.000):"));
            while (portafoglio < 0 || portafoglio > 1000000) {
                portafoglio = Double
                        .parseDouble(JOptionPane.showInputDialog(this, "Valore non valido! Reinserisci Portafoglio:"));
            }

            double contoBancario = Double
                    .parseDouble(JOptionPane.showInputDialog(this, "Inserisci Conto Bancario (0-1.000.000):"));
            while (contoBancario < 0 || contoBancario > 1000000) {
                contoBancario = Double.parseDouble(
                        JOptionPane.showInputDialog(this, "Valore non valido! Reinserisci Conto Bancario:"));
            }

            Utente nuovoUtente = new Utente(nome, cognome, eta, password, portafoglio, contoBancario);
            if (banca.addUtente(nuovoUtente)) {
                JOptionPane.showMessageDialog(this, "Utente iscritto con successo!");
                File gestioneUtente = fileUser.creaFileUtente(nuovoUtente);
                fileUser.aggiungiFile(gestioneUtente);
            } else {
                JOptionPane.showMessageDialog(this, "Utente già esistente!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Errore nei dati inseriti!");
        }
    }

    private String chiediPassword() {
        LoginDialog login = new LoginDialog(this);
        login.setVisible(true);
        return login.isSucceeded() ? login.getPassword() : null;
    }

    private void accediVisualizza() {
        String password = chiediPassword();
        if (password == null)
            return;
        int index = banca.findPersona(password);
        if (index != -1) {
            JOptionPane.showMessageDialog(this, banca.getUtente(index).toString());
        } else {
            JOptionPane.showMessageDialog(this, "Password errata!");
        }
    }

    private void depositaDenaro() {
        String password = chiediPassword();
        if (password == null)
            return;
        int index = banca.findPersona(password);
        if (index != -1) {
            double deposito = Double.parseDouble(JOptionPane.showInputDialog(this, "Quota da depositare:"));
            while (deposito < 0 || deposito > banca.getUtente(index).getPortafoglio()) {
                deposito = Double.parseDouble(JOptionPane.showInputDialog(this, "Valore non valido! Reinserire:"));
            }
            if (banca.Deposito(deposito, password)) {
                JOptionPane.showMessageDialog(this, "Deposito effettuato!");
                aggiornaFileUtente(index, "Deposito: " + deposito);
            } else {
                JOptionPane.showMessageDialog(this, "Errore nel deposito!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Utente non trovato!");
        }
    }

    private void prelevaDenaro() {
        String password = chiediPassword();
        if (password == null)
            return;
        int index = banca.findPersona(password);
        if (index != -1) {
            double prelievo = Double.parseDouble(JOptionPane.showInputDialog(this, "Quota da prelevare:"));
            while (prelievo < 0 || prelievo > banca.getUtente(index).getContoBancario()) {
                prelievo = Double.parseDouble(JOptionPane.showInputDialog(this, "Valore non valido! Reinserire:"));
            }
            if (banca.Prelievo(prelievo, password, index)) {
                JOptionPane.showMessageDialog(this, "Prelievo effettuato!");
                aggiornaFileUtente(index, "Prelievo: " + prelievo);
            } else {
                JOptionPane.showMessageDialog(this, "Errore nel prelievo!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Utente non trovato!");
        }
    }

    private void investiDenaro() {
        String password = chiediPassword();
        if (password == null)
            return;
        int index = banca.findPersona(password);
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Password errata!");
            return;
        }

        Object[] durata = { "1. Breve (Mensile)", "2. Media (Trimestrale)", "3. Lunga (Semestrale)" };
        int sceltaDurata = JOptionPane.showOptionDialog(this, "Scegli durata investimento:", "Durata",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, durata, durata[0]);
        if (sceltaDurata == -1)
            return;
        contatoriMesi += (sceltaDurata == 0) ? 1 : (sceltaDurata == 1) ? 3 : 6;
        banca.avanzamentoMese(100 * ((sceltaDurata == 0) ? 1 : (sceltaDurata == 1) ? 3 : 6));

        Object[] rischio = { "1. Basso rischio", "2. Medio rischio", "3. Alto rischio" };
        int sceltaRischio = JOptionPane.showOptionDialog(this, "Scegli rischio investimento:", "Rischio",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, rischio, rischio[0]);
        if (sceltaRischio == -1)
            return;

        double quota = Double.parseDouble(JOptionPane.showInputDialog(this, "Quota da investire:"));
        while (quota < 1 || quota > banca.getUtente(index).getContoBancario()) {
            quota = Double.parseDouble(JOptionPane.showInputDialog(this, "Valore non valido! Reinserire:"));
        }
        banca.getUtente(index).setContoBancario(banca.getUtente(index).getContoBancario() - quota);

        int guadagnoPerc = (sceltaRischio == 0) ? 100 : (sceltaRischio == 1) ? 250 : 500;
        double guadagno = banca.Investimento(quota, password, guadagnoPerc);

        String risultato = (guadagno > quota) ? "Investimento positivo! Guadagnato: " + (guadagno - quota)
                : "Investimento negativo! Perso: " + ((guadagno - quota) / -1);
        banca.getUtente(index).setContoBancario(banca.getUtente(index).getContoBancario() + guadagno);

        JOptionPane.showMessageDialog(this, risultato);
        aggiornaFileUtente(index, "Investimento: " + quota + ", Guadagnato: " + guadagno);
    }

    private void avanzaMese() {
        contatoriMesi++;
        banca.avanzamentoMese(100);
        if (contatoriMesi > 11) {
            contatoriMesi = 0;
            anno++;
        }
        JOptionPane.showMessageDialog(this, "Mese avanzato! " + mesi[contatoriMesi] + " " + anno);
    }

    private void disdetta() {
        String password = chiediPassword();
        if (password == null)
            return;
        int index = banca.findPersona(password);
        if (index != -1) {
            banca.removeUtente(index);
            JOptionPane.showMessageDialog(this, "Disdetta effettuata!");
        } else {
            JOptionPane.showMessageDialog(this, "Utente non trovato!");
        }
    }

    private void aggiornaFileUtente(int index, String azione) {
        try {
            File gestioneUtente = fileUser.creaFileUtente(banca.getUtente(index));
            fileUser.aggiungiFile(gestioneUtente);
            String transazione = "TRANSAZIONE: " + mesi[contatoriMesi] + " " + anno + " | " + azione;
            fileUser.appendToFile(banca.getUtente(index), transazione);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuGUI().setVisible(true));
    }
}
