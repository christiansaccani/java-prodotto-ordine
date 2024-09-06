import com.mycompany.prodotto.Prodotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdottoTest {
    
    private Prodotto prodotto;

    @BeforeEach
    public void setUp() {
        prodotto = new Prodotto("Apple", 1.00, 50);
    }

    @Test
    public void testGetNome() {
        assertEquals("Apple", prodotto.getNome(), "Il nome del prodotto dovrebbe essere 'Apple'");
    }

    @Test
    public void testGetPrezzo() {
        assertEquals(1.00, prodotto.getPrezzo(), "Il prezzo del prodotto dovrebbe essere 1.00");
    }

    @Test
    public void testGetQuantitaDisponibile() {
        assertEquals(50, prodotto.getQuantitaDisponibile(), "La quantità disponibile dovrebbe essere 50");
    }

    @Test
    public void testRiduciQuantita() {
        prodotto.riduciQuantita(10);
        assertEquals(40, prodotto.getQuantitaDisponibile(), "La quantità disponibile dovrebbe essere 40 dopo aver ridotto di 10");

        //Test per excessive amount
        Exception over = assertThrows(IllegalArgumentException.class, () -> {
            prodotto.riduciQuantita(100);
        });
        assertEquals("Quantità non valida.", over.getMessage(), "Dovrebbe lanciare un'eccezione con il messaggio 'Quantità non valida.'");
        
        //Test per negative amount
        Exception negative = assertThrows(IllegalArgumentException.class, () -> {
            prodotto.riduciQuantita(-1);
        });
        assertEquals("Quantità non valida.", negative.getMessage(), "Dovrebbe lanciare un'eccezione con il messaggio 'Quantità non valida.'");
        
        
        // Forcing error message
        try {
            prodotto.riduciQuantita(100);
            fail("Dovrebbe lanciare un'eccezione per quantità non valida.");
        } catch (IllegalArgumentException e) {
            System.out.println("Eccezione catturata: Over");
            assertEquals("Quantità non valida.", e.getMessage(), "Dovrebbe lanciare un'eccezione con il messaggio 'Quantità non valida.'");
        }
        
        try {
            prodotto.riduciQuantita(-1);
            fail("Dovrebbe lanciare un'eccezione per quantità non valida.");
        } catch (IllegalArgumentException e) {
            System.out.println("Eccezione catturata: Negative");
            assertEquals("Quantità non valida.", e.getMessage(), "Dovrebbe lanciare un'eccezione con il messaggio 'Quantità non valida.'");
        }
    }
}
