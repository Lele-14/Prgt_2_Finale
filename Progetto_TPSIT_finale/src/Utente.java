import java.util.Vector;

public class Utente {
    private String nome, cognome, password;


    private int eta;
    private double contoBancario, portafoglio;
    private Vector <String> transazioni= new Vector<>(10, 5);


    public Utente(String nome, String cognome, int eta, String password, double portafoglio, double contoBancario) {
        this.cognome = cognome;
        this.contoBancario = contoBancario;
        this.eta = eta;
        this.nome = nome;
        this.password = password;
        this.portafoglio = portafoglio;
    }




    public double getContoBancario() {
        return contoBancario;
    }

    public void setContoBancario(double contoBancario) {
        this.contoBancario = contoBancario;
    }

    public double getPortafoglio() {
        return portafoglio;
    }

    public void setPortafoglio(double portafoglio) {
        this.portafoglio = portafoglio;
    }

    public Vector<String> getTransazioni() {
        return transazioni;
    }

    public void setTransazioni(Vector<String> transazioni) {
        this.transazioni = transazioni;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public int getEta() {
        return eta;
    }
    public void setEta(int eta) {
        this.eta = eta;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String toString () {
        String s="UTENTE:\n";
        s=s+nome+ "\t "+cognome+"\n";
        s=s+"Eta': "+eta+"\n";
        s=s+"Saldo portafoglio: "+portafoglio+"\t Saldo bancomat: "+contoBancario;
        return s;
    }


}