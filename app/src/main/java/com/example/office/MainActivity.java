package com.example.office;

import java.util.ArrayList;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Toast;




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

    private LinearLayout ll_lstProduits;
    private ArrayList<CheckBox> lstCheckBox = new ArrayList<>();


    private ActivityResultLauncher<Intent> activityAvecRetour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View maVue = findViewById(R.id.mainLayout);
        registerForContextMenu(maVue);

        ll_lstProduits = findViewById(R.id.ll_lstProduits);

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


        // gestion du bouton supprimer qui supprime les produits cochés
        Button boutonSupprimer = (Button) findViewById(R.id.b_supprimer);
        boutonSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { supprimer(); }

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
                                actualiser();
                                break;



                        }
                    }
                }
        );


    }
    public void actualiser(){
        // Pour nettoyer le ScrollView avant de le remplir à nouveau
        // ainsi que la liste des CheckBox
        ll_lstProduits.removeAllViews();
        lstCheckBox.clear();

        for (int i=0 ; i<Modele.catalogue.size() ; i++) {
            LinearLayout unLayout = new LinearLayout(getApplicationContext());
            unLayout.setOrientation(LinearLayout.HORIZONTAL);
            CheckBox cb_suppr = new CheckBox(getApplicationContext());
            cb_suppr.setActivated(false);
            TextView tv_ref = new TextView(getApplicationContext());
            tv_ref.setText(Modele.catalogue.get(i).getRef());
            TextView tv_nom = new TextView(getApplicationContext());
            tv_nom.setText(Modele.catalogue.get(i).getNom());
            TextView tv_prix = new TextView(getApplicationContext());
            tv_prix.setText(Modele.catalogue.get(i).getPrix()+" €");


            LinearLayout.LayoutParams params = new
                    LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            tv_ref.setLayoutParams(params);
            tv_nom.setLayoutParams(params);
            tv_prix.setLayoutParams(params);

            unLayout.addView(cb_suppr);
            unLayout.addView(tv_ref);
            unLayout.addView(tv_nom);
            unLayout.addView(tv_prix);

            ll_lstProduits.addView(unLayout);
            lstCheckBox.add(cb_suppr);


            /*
            Ancienne version d'affichage du catalogue dans un TextView

            tv_catalogue.setText(tv_catalogue.getText()+Modele.catalogue.get(i).getRef()
                    + " / " + Modele.catalogue.get(i).getNom()
                    + " / " + Modele.catalogue.get(i).getPrix()
                    + " \n ");

             */

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



    public void rechercheParPrix() {
        Intent fenrechercheParPrix = new Intent(this, RecherchePrixActivity.class);
        startActivity(fenrechercheParPrix);
    }



    public void supprimer() {
        int nbProdSuppr = 0;
        for (int i = Modele.catalogue.size() -1; i >= 0 ; i--) {
            if (lstCheckBox.get(i).isChecked()) {
                Modele.catalogue.remove(i);
                nbProdSuppr++;
            }
        }
        Toast.makeText(this, nbProdSuppr + " produits supprimés", Toast.LENGTH_SHORT).show();
        actualiser();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.main, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_ajout) {
            ajouter();
            return true;
        }
        if (item.getItemId() == R.id.menu_recherche_prix) {
            rechercheParPrix();
            return true;
        } else {
            return super.onContextItemSelected(item);
        }


    }
}
