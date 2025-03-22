//package TestBanca;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PrelievoTest {
    private Banca banca;
    private Utente utente;

    @BeforeEach
    void setUp() {
        banca = new Banca("Banca Test");
        utente = new Utente("Mario", "Rossi", 30, "password123", 100.0, 500.0);
        banca.addUtente(utente);
    }

    @Test
    void testPrelievoSuccesso() {
        int persona = banca.findPersona("password123");
        assertTrue(banca.Prelievo(50.0, "password123", persona), "Il prelievo dovrebbe riuscire");
        assertEquals(450.0, utente.getContoBancario(), "Il saldo del conto bancario dovrebbe essere 450€");
        assertEquals(150.0, utente.getPortafoglio(), "Il saldo del portafoglio dovrebbe essere 150€");
    }

    @Test
    void testPrelievoFallitoSaldoInsufficiente() {
        int persona = banca.findPersona("password123");
        assertFalse(banca.Prelievo(600.0, "password123", persona), "Il prelievo dovrebbe fallire per saldo insufficiente");
        assertEquals(500.0, utente.getContoBancario(), "Il saldo del conto bancario non dovrebbe cambiare");
        assertEquals(100.0, utente.getPortafoglio(), "Il saldo del portafoglio non dovrebbe cambiare");
    }

    @Test
    void testPrelievoConPasswordErrata() {
        int persona = banca.findPersona("wrongPassword");
        assertEquals(-1, persona, "L'utente non dovrebbe essere trovato con una password errata");
    }

    @Test
    void testPrelievoConImportoNegativo() {
        int persona = banca.findPersona("password123");

        //questo controllo fallisce, dovrebbe restituire false ma restituisce true
        assertFalse(banca.Prelievo(-50.0, "password123", persona), "Il prelievo con importo negativo dovrebbe fallire");
        assertEquals(500.0, utente.getContoBancario(), "Il saldo del conto bancario non dovrebbe cambiare");
        assertEquals(100.0, utente.getPortafoglio(), "Il saldo del portafoglio non dovrebbe cambiare");
    }
}
