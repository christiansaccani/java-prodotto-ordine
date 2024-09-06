package com.mycompany.prodotto;

public class Prodotto {
    private String nome;
    private double prezzo;
    private int quantitaDisponibile;

    public Prodotto(String nome, double prezzo, int quantitaDisponibile) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.quantitaDisponibile = quantitaDisponibile;
    }

    public String getNome() {
        return nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public int getQuantitaDisponibile() {
        return quantitaDisponibile;
    }

    public void riduciQuantita(int quantita) {
        if (quantita > 0 && quantita <= quantitaDisponibile) {
            quantitaDisponibile -= quantita;
        } else {
            throw new IllegalArgumentException("Quantità non valida.");
        }
    }
    
    public static void main(String[] args) {
    }
}