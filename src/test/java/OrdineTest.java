import com.mycompany.prodotto.Ordine;
import com.mycompany.prodotto.Prodotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrdineTest {

    private Ordine ordine;
    private Prodotto p1;
    private Prodotto p2;

    @BeforeEach
    public void setUp() {
        ordine = new Ordine();
        p1 = new Prodotto("Apple", 1.00, 50);
        p2 = new Prodotto("Banana", 0.50, 30);
    }

    @Test
    public void testAggiungiProdotto() {

        ordine.AggiungiProdotto(p1, 10);
        ordine.AggiungiProdotto(p2, 5);

        assertEquals(10, ordine.prodotti.get(p1), "La quantità di Apple dovrebbe essere 10");
        assertEquals(5, ordine.prodotti.get(p2), "La quantità di Banana dovrebbe essere 5");

        // Test for null product
        IllegalArgumentException nullo = assertThrows(IllegalArgumentException.class, () -> {
            ordine.AggiungiProdotto(null, 10);
        });
        assertEquals("Quantità non valida.", nullo.getMessage(), "Dovrebbe lanciare un'eccezione per prodotto nullo");

        // Test for negative amount
        IllegalArgumentException negative = assertThrows(IllegalArgumentException.class, () -> {
            ordine.AggiungiProdotto(p1, -5);
        });
        assertEquals("Quantità non valida.", negative.getMessage(), "Dovrebbe lanciare un'eccezione per quantità negativa");
    }
    
    @Test
    public void testRimuoviProdotto() {
        ordine.AggiungiProdotto(p1, 10);
        ordine.rimuoviProdotto(p1, 5);
        
        assertEquals(5, ordine.prodotti.get(p1), "La quantità di Apple dovrebbe essere 5 dopo la rimozione di 5");
        
        // Test per excessive amount
        assertThrows(IllegalArgumentException.class, () -> {
            ordine.rimuoviProdotto(p1, 15);
        }, "Dovrebbe lanciare un'eccezione per rimozione di più di quanto presente");
    }

    @Test
    public void testCalcolaTotale() {
        ordine.AggiungiProdotto(p1, 10);
        ordine.AggiungiProdotto(p2, 5);
        
        assertEquals(12.50, ordine.calcolaTotale(), "Il totale dell'ordine dovrebbe essere 12.50");
    }
    
    @Test
    public void testNumeroProdotti() {
        ordine.AggiungiProdotto(p1, 10);
        ordine.AggiungiProdotto(p2, 5);

        assertEquals(15, ordine.numeroProdotti(), "Il numero totale di prodotti dovrebbe essere 15");
    }

    @Test
    public void testMostraOrdine() {
        ordine.AggiungiProdotto(p1, 10);
        ordine.AggiungiProdotto(p2, 5);
        ordine.mostraOrdine();
    }
}
