

package com.example.office;




import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
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


                EditText prix = (EditText) findViewById(R.id.et_prix);
                RadioGroup choixoperateur = (RadioGroup) findViewById(R.id.rg_operateurcomparasion);
                int operateurCoche = choixoperateur.getCheckedRadioButtonId();
                Log.d("case coches", "id case cochee :" + operateurCoche);


                if (!prix.getText().toString().isEmpty()) {
                    rechercheParPrix(prix.getText().toString(), operateurCoche);
                } else {
                    TextView resultat = (TextView) findViewById(R.id.tv_resultat_recherche_prix);
                    resultat.setTextColor(Color.RED);
                    resultat.setText("Vous devez renseigner un prix pour pouvoir lancer une recherche !");
                    // le prix est vide
                }
            }
        }); // fin setOnClickListener
    } // fin onCreate


public void rechercheParPrix(String prixARechercher, int operateurCoche) {
    TextView resultat = (TextView) findViewById(R.id.tv_resultat_recherche_prix);
    resultat.setText("");
    resultat.setTextColor(Color.BLUE);


    for (int i = 0; i < Modele.catalogue.size(); i++) {
        if (operateurCoche == R.id.rb_egale){
            if (Modele.catalogue.get(i).getPrix() == Double.parseDouble(prixARechercher)) {
                integre_ocurance (resultat ,Modele.catalogue.get(i));
            }
        }
        else {
            if (operateurCoche == R.id.rb_inferieur) {
                if (Modele.catalogue.get(i).getPrix() < Double.parseDouble(prixARechercher)) {
                    integre_ocurance(resultat, Modele.catalogue.get(i));
                }
            }

        else {
                if (Modele.catalogue.get(i).getPrix() > Double.parseDouble(prixARechercher)) {
                    integre_ocurance (resultat ,Modele.catalogue.get(i));
                }
            }
        }
    }
}

    public void integre_ocurance (TextView resultat , Produit occuranceAInterger) {
        resultat.append(occuranceAInterger.getNom() + " -- " + occuranceAInterger.getRef() + " - " + occuranceAInterger.getPrix() + " euros\n");
    }
} // fin classe RecherchePrixActivity







