//package TestBanca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DepositoTest {
    private Banca banca;
    private Utente utente;

    @BeforeEach
    void setUp() {
        banca = new Banca("Banca Test");
        utente = new Utente("Mario", "Rossi", 30, "password123", 100.0, 500.0);
        banca.addUtente(utente);
    }

    @Test
    void testDepositoSuccesso() {
        assertTrue(banca.Deposito(50.0, "password123"));
        assertEquals(550.0, utente.getContoBancario(), "Il saldo del conto bancario dovrebbe essere 550€");
        assertEquals(50.0, utente.getPortafoglio(), "Il saldo del portafoglio dovrebbe essere 50€");
    }

    @Test
    void testDepositoFallitoSaldoPortafoglioInsufficiente() {
        assertFalse(banca.Deposito(200.0, "password123"));
        assertEquals(500.0, utente.getContoBancario(), "Il saldo del conto bancario non dovrebbe cambiare");
        assertEquals(100.0, utente.getPortafoglio(), "Il saldo del portafoglio non dovrebbe cambiare");
    }

    @Test
    void testDepositoConImportoNegativo() {
        double saldoInizialeConto = utente.getContoBancario();
        double saldoInizialePortafoglio = utente.getPortafoglio();

        //questo controllo fallisce, dovrebbe restituire false ma restituisce true
        assertFalse(banca.Deposito(-50.0, "password123"));
        assertEquals(saldoInizialeConto, utente.getContoBancario(), "Il saldo del conto bancario non dovrebbe cambiare");
        assertEquals(saldoInizialePortafoglio, utente.getPortafoglio(), "Il saldo del portafoglio non dovrebbe cambiare");
    }
}
