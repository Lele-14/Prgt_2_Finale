//package TestBanca;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InvestimentoTest {
    private Banca banca;
    private Utente utente;

    @BeforeEach
    void setUp() {
        banca = new Banca("Banca Test");
        utente = new Utente("Mario", "Rossi", 30, "password123", 100.0, 500.0);
        banca.addUtente(utente);
    }

    @Test
    void testInvestimentoSuccesso() {
        double quotaInvestimento = 100.0;
        int grandezzaRischio = 10;

        // Calcola il guadagno atteso
        double guadagnoAtteso = quotaInvestimento * (grandezzaRischio / 20.0);

        double guadagno = banca.Investimento(quotaInvestimento, "password123", grandezzaRischio);

        // Verifica che il guadagno non sia uguale a -1 (investimento riuscito)
        assertNotEquals(-1, guadagno, "L'investimento avrebbe dovuto generare un guadagno.");

        // Verifica che il guadagno sia nel range atteso
        assertTrue(guadagno > 0, "Il guadagno dovrebbe essere positivo.");
        assertEquals(guadagnoAtteso, guadagno, 0.01, "Il guadagno calcolato non corrisponde a quello atteso.");
    }

    @Test
    void testInvestimentoFallitoSaldoInsufficiente() {
        double quotaInvestimento = 600.0;
        int grandezzaRischio = 10;

        double guadagno = banca.Investimento(quotaInvestimento, "password123", grandezzaRischio);

        // Verifica che l'investimento fallisca quando il saldo è insufficiente
        assertEquals(-1, guadagno, "L'investimento avrebbe dovuto fallire per saldo insufficiente.");
    }
}
