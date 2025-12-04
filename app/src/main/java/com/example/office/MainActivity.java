package com.example.office;


import android.app.Activity;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {
    TextView tv_catalogue ;
    private ActivityResultLauncher<Intent> activityAvecRetour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View maVue = findViewById(R.id.mainLayout);
        registerForContextMenu(maVue);


        tv_catalogue = findViewById(R.id.tv_catalogue);
        //charger dans l'appli mobile le catalogue des 4 produits
        Modele.init();


        Button boutonActu = (Button) findViewById(R.id.b_actualiser);


        boutonActu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualiser();
            }
        });




        Button boutonRechercher2 = (Button) findViewById(R.id.b_rechercher2);


        boutonRechercher2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rechercher2();
            }
        });


        activityAvecRetour= registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        switch (result.getResultCode()) {
                            case 10 :
                                String ref = result.getData().getExtras().getString("ref");
                                String nom = result.getData().getExtras().getString("nom");
                                Double prix = result.getData().getExtras().getDouble("prix");
                                Modele.catalogue.add(new Produit(ref, nom, prix));


                        }
                    }
                }
        );


    }
    public void actualiser(){
        tv_catalogue.setText("");
        for (int i=0 ; i<Modele.catalogue.size() ; i++) {
            tv_catalogue.setText(tv_catalogue.getText()+Modele.catalogue.get(i).getRef()
                    + " / " + Modele.catalogue.get(i).getNom()
                    + " / " + Modele.catalogue.get(i).getPrix()
                    + " \n ");
            Log.d("AfficheCatalogue", Modele.catalogue.get(i).getNom());
        }
    }


    //fonction permettant d'appeler une nouvelle fenetre de recherche
    public void rechercher2(){
        Intent fentRechercher2 = new Intent(this, RechercheActivity2.class);
        startActivity(fentRechercher2);
    }


           /*public void ajouter() {
               Intent fentAjouter = new Intent(this, AjoutActivity.class);
               startActivity(fentAjouter);
           }*/


    public void ajouter() {
        Class<?> AjoutActivity= AjoutActivity.class;
        Intent nouvProduit = new Intent(getApplicationContext(), AjoutActivity);
        activityAvecRetour.launch(nouvProduit);
    }




    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.main, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_ajout) {
            ajouter ();
            return true;
        }
        else
            return super.onContextItemSelected(item);
    }




}
