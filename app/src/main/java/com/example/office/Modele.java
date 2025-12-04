package com.example.office;

import java.util.ArrayList;

public class Modele {
    public static ArrayList<Produit> catalogue = new ArrayList<Produit>();

    public static void init(){
        Modele.catalogue.add(new Produit("A1R","clavier USB",12.5));
        Modele.catalogue.add(new Produit("E89","souris USB",9.95));
        Modele.catalogue.add(new Produit("SD98","SSD 1 To",149.9));
        Modele.catalogue.add(new Produit("Q0D9","cle USB 128 Go",25));
    }
}



