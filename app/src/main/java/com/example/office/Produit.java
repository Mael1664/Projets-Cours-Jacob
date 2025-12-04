package com.example.office;

public class Produit {
    private String ref;
    private String nom;
    private double prix;

    public Produit(String ref, String nom, double prix) {
        this.ref = ref;
        this.nom = nom;
        this.prix = prix;
    }

    public String getRef() {
        return this.ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return this.prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "ref='" + this.ref + '\'' +
                ", nom='" + this.nom + '\'' +
                ", prix=" + this.prix +
                '}';
    }
}
