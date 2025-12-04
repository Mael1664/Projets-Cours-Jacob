package com.example.office;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class RecherchePrixActivity extends AppCompatActivity {


    TextView tv_catalogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche2);

        // Modele.init();

        Button bouttonRecherche = (Button) findViewById(R.id.b_rechercher);

        bouttonRecherche.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {


                EditText nom = (EditText) findViewById(R.id.et_nom);
                EditText ref = (EditText) findViewById(R.id.et_reference);
                if (!nom.getText().toString().isEmpty()){
                    if (!ref.getText().toString().isEmpty()) {
                        TextView resultat = (TextView) findViewById(R.id.tv_resultat);
                        resultat.setTextColor(Color.RED);
                        resultat.setText("Veuillez remplir un seul champ");
                    }

                    else
                        rechercherParNom(nom.getText().toString());


                }

            }
        });

    }

    public void rechercherParNom(String nomARechercher) {
        TextView resultat = (TextView) findViewById(R.id.tv_resultat);
        resultat.setTextColor(Color.BLACK);
        resultat.setText("");

        for (int i = 0; i < Modele.catalogue.size(); i++) {
            if (Modele.catalogue.get(i).getNom().contains(nomARechercher)) {
                resultat.setText(resultat.getText() +
                        Modele.catalogue.get(i).getRef() + " - " +
                        Modele.catalogue.get(i).getNom() + " - " +
                        Modele.catalogue.get(i).getPrix() + " $ \n"
                );
            }
        }
    }
    public void rechercherParRef(String refARechercher) {
        TextView resultat = (TextView) findViewById(R.id.tv_resultat);
        resultat.setTextColor(Color.BLACK);
        resultat.setText("");

        for (int i = 0; i < Modele.catalogue.size(); i++) {
            if (Modele.catalogue.get(i).getRef().equals(refARechercher)) {
                resultat.setText(resultat.getText() +
                        Modele.catalogue.get(i).getRef() + " - " +
                        Modele.catalogue.get(i).getNom() + " - " +
                        Modele.catalogue.get(i).getPrix() + " $ \n"
                );
            }
        }

    }
}




