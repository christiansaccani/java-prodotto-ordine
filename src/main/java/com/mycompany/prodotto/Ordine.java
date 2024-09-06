package com.mycompany.prodotto;

import java.util.HashMap;
import java.util.Map;

public class Ordine {

    public Map<Prodotto, Integer> prodotti;

    public Ordine() {
        this.prodotti = new HashMap<>();
    }

    public void AggiungiProdotto(Prodotto prodotto, int quantita) {
        if (prodotto == null || quantita <= 0) {
            throw new IllegalArgumentException("Quantità non valida.");
        }
        prodotti.merge(prodotto, quantita, Integer::sum);
    }
    
    public void rimuoviProdotto(Prodotto prodotto, int quantita) {
        if (prodotto == null || quantita <= 0) {
            throw new IllegalArgumentException("Quantità non valida.");
        }
        if (!prodotti.containsKey(prodotto)) {
            throw new IllegalArgumentException("Prodotto non presente nell'ordine.");
        }
        int quantitaAttuale = prodotti.get(prodotto);
        if (quantita > quantitaAttuale) {
            throw new IllegalArgumentException("Quantità da rimuovere maggiore di quella presente nell'ordine.");
        }
        if (quantita == quantitaAttuale) {
            prodotti.remove(prodotto);
        } else {
            prodotti.put(prodotto, quantitaAttuale - quantita);
        }
    }

    public double calcolaTotale() {
        return prodotti.entrySet().stream()
            .mapToDouble(entry -> entry.getKey().getPrezzo() * entry.getValue())
            .sum();
    }
    
    public int numeroProdotti() {
        return prodotti.values().stream()
            .mapToInt(Integer::intValue)
            .sum();
    }

    public void mostraOrdine() {
        System.out.println("Dettagli dell'ordine:");
        for (Map.Entry<Prodotto, Integer> entry : prodotti.entrySet()) {
            Prodotto prodotto = entry.getKey();
            Integer quantita = entry.getValue();
            System.out.println("Prodotto: " + prodotto.getNome() + ", Prezzo: " + prodotto.getPrezzo() + ", Quantità: " + quantita);
        }
    }
}