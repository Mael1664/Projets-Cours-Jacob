package com.example.office;

import android.text.InputType;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class RechercheActivity2 extends AppCompatActivity {

    private LinearLayout layoutRecherche;
    private EditText champRecherche;
    private ArrayList<String> lstChoixRecherche = new ArrayList<>();
    private Spinner typeChoixRecherche;
    TextView resultatRecherche = findViewById(R.id.tv_resultat);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche2);

        // appel de fonction pour peuple le spinner
        initSpinner();

        champRecherche = findViewById(R.id.et_champsRecherche);

        layoutRecherche = findViewById(R.id.layoutRecherche);


        // Modele.init();

        Button bouttonRecherche = (Button) findViewById(R.id.b_rechercher);
        bouttonRecherche.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                rechercheResultats();

            }
        });


        typeChoixRecherche.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                layoutRecherche.removeAllViewsInLayout();
                switch (i) {
                    case 0: // recherche par ref
                        champRecherche.setHint("Référence du produit");
                        champRecherche.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case 1: // recherche nom
                        champRecherche.setHint("Nom du produit");
                        champRecherche.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case 2: // recherche par prix
                        champRecherche.setHint("Prix du produit");
                        champRecherche.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    };

    private void initSpinner() {
        lstChoixRecherche.add("Référence");
        lstChoixRecherche.add("Nom");
        lstChoixRecherche.add("Prix");

        typeChoixRecherche = findViewById(R.id.spinner);

        ArrayAdapter<String> spinAdapter = new ArrayAdapter<>
                (this, R.layout.spinner_element);

        for (int i = 0; i < lstChoixRecherche.size(); i++) {
            spinAdapter.add(lstChoixRecherche.get(i));
        typeChoixRecherche.setAdapter(spinAdapter);
        }

    }

    private void rechercheResultats() {
        resultatRecherche.setText("");
        String valeurARechercher = champRecherche.getText().toString();
        switch (typeChoixRecherche.getSelectedItemPosition()){
            case 0 :
                for (Produit produit : Modele.catalogue) {
                    if (produit.getRef().equals(valeurARechercher)) {
                        resultatRecherche.setText(produit.toString());

                    }
                }
                break;

            case 1 :
            for (Produit produit : Modele.catalogue) {
                if (produit.getNom().contains(valeurARechercher)) {
                    resultatRecherche.setText(resultatRecherche.getText().toString()+"\n"+produit.toString());

                }
            }
            break;

            case 2 :
            for (Produit produit : Modele.catalogue) {
                if (Double.valueOf(valeurARechercher).compareTo(produit.getPrix()) == 0) {
                    resultatRecherche.setText(resultatRecherche.getText().toString()+"\n"+produit.toString());

                }
        }

    }



/*

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

    }   ne sert plus car remplacer par le spinner  */







