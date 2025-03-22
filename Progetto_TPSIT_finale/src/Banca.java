import java.io.*;

import java.util.*;

public class Banca {
    private String nome;
    private ArrayList<Utente> utenti = new ArrayList<Utente>(25);

    public Banca(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean addUtente(Utente x) {

        for (int i = 0; i < utenti.size(); i = i + 1) {
            if (utenti.get(i).getPassword().equals(x.getPassword())) {
                return false;
            }
        }

        return utenti.add(x);

    }

    public boolean removeUtente(int persona) {

        if (utenti.isEmpty() || persona<0 || persona >= utenti.size()) {
            return false;
        }

        return utenti.remove(utenti.get(persona));

    }

    public int findPersona(String passwordInserita) {
        int persona = -1;
        for (int i = 0; i < utenti.size(); i++) {
            if (utenti.get(i).getPassword().equals(passwordInserita)) {
                persona = i;
                return persona;
            }
        }
        return persona;

    }

    public Utente getUtente(int index) {
        return utenti.get(index);
    }

    public boolean disponibilitaAzioni(double parametro, double risorsa) {
        if (risorsa <= 0 || parametro > risorsa) {
            return false;
        }
        return true;
    }

    
    //passowrd inserita si può rimuovere...
    public boolean Prelievo(double prelievo, String passwordInserita, int persona) {

        if (utenti.isEmpty() || persona == -1) {
            return false;
        }
        if (!disponibilitaAzioni(prelievo, utenti.get(persona).getContoBancario())) {
            return false;
        } else {
            utenti.get(persona).setPortafoglio(utenti.get(persona).getPortafoglio() + prelievo);
            utenti.get(persona).setContoBancario(utenti.get(persona).getContoBancario() - prelievo);
            return true;
        }

    }

    public boolean Deposito(double deposito, String passwordInserita) {
        int persona = findPersona(passwordInserita);
        if (utenti.isEmpty() || persona == -1) {
            return false;
        }

        if (!disponibilitaAzioni(deposito, utenti.get(persona).getPortafoglio())) {
            return false;
        }

        utenti.get(persona).setContoBancario(utenti.get(persona).getContoBancario() + deposito);
        utenti.get(persona).setPortafoglio(utenti.get(persona).getPortafoglio() - deposito);
        return true;

    }

    public double Investimento(double quotaInvestimento, String passwordInserita, int grandezzaRischio) {
        double guadagno = -1;
        int persona = findPersona(passwordInserita);
        if (utenti.isEmpty() || persona == -1) {
            return guadagno;
        } else {
            if (disponibilitaAzioni(quotaInvestimento, utenti.get(persona).getContoBancario())) {
                int rischio = (int) (Math.random() * grandezzaRischio);
                if (rischio <= 50) {
                    guadagno = (quotaInvestimento * (grandezzaRischio / 20.0));
                } else {
                    guadagno = (double) ((20.0 / grandezzaRischio) * quotaInvestimento);
                }

            }
        }
        return guadagno;
    }

  public void avanzamentoMese (int aumento) {
    for (int i=0; i<utenti.size(); i++) {
        utenti.get(i).setPortafoglio(utenti.get(i).getPortafoglio()+aumento);
    }
  }

  

  public Utente prelevaDaFile(String nomeFile, String nomeFileCompleto) throws Exception {
    File file= new File(nomeFileCompleto);
    if (!file.exists()) {
        return null;
    } else {
    FileReader fr = new FileReader(nomeFileCompleto);
    
        try (BufferedReader br = new BufferedReader(fr)) {
            String line;
            String nome = "";
            String cognome = "";
            int eta = 0;
            double saldoPortafoglio = 0;
            double saldoBancomat = 0;

            while ((line = br.readLine()) != null) {
                
                    String[] parts = new String[2];
                    parts = nomeFile.split("_");
                    nome = parts[1];
                    cognome = parts[0];
                    parts = line.trim().split("\\s+");
                if (line.startsWith("Eta'")) {
                    eta = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                } else if (line.startsWith("Saldo portafoglio")) {
                    parts = line.split("Saldo portafoglio:")[1].split("Saldo bancomat:");
                    saldoPortafoglio = Double.parseDouble(parts[0].trim());
                    saldoBancomat = Double.parseDouble(parts[1].trim());
                }
            }

            Utente utente = new Utente(nome, cognome, eta, "",saldoPortafoglio, saldoBancomat);
           return utente ;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        return null;
    }

    //elenco utenti della banca
    public void toStringBanca() {
        System.out.println("Banca: " + nome);
        System.out.println("Utenti registrati:");
    
        if (utenti.isEmpty()) {
            System.out.println("Nessun utente registrato.");
        } else {
            for (Utente u : utenti) {
                System.out.println(u.toString()); // Presupponendo che Utente abbia un toString() valido
            }
        }
    }
}


