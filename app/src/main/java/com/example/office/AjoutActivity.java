package com.example.office;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;




public class AjoutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);


        Button BtnEnregistrer = (Button) findViewById(R.id.b_enregistrer);


        BtnEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enregistrer();
            }
        });


    }


    public void enregistrer() {
        EditText ref = (EditText) findViewById(R.id.et_reference);
        EditText nom = (EditText) findViewById(R.id.et_nom);
        EditText prix = (EditText) findViewById(R.id.et_prix);




        if (!ref.getText().toString().isEmpty() && !nom.getText().toString().isEmpty() && !prix.getText().toString().isEmpty()) {
            /* Produit prod = new Produit(ref.getText().toString(), nom.getText().toString(), Double.valueOf(prix.getText().toString()));
            Modele.catalogue.add(prod);
            Log.d("ajout produit", "produit ajouté : "+prod.toString()); */

            String refARetourner = ref.getText().toString();
            String nomARetourner = nom.getText().toString();
            String prixStr = prix.getText().toString();
            Double prixARetourner = Double.parseDouble(prixStr);

            Intent intentDeRetour = new Intent();
            intentDeRetour.putExtra("ref", refARetourner);
            intentDeRetour.putExtra("nom", nomARetourner);
            intentDeRetour.putExtra("prix", prixARetourner);

            setResult(10, intentDeRetour);
            finish();


        }
    }


}





