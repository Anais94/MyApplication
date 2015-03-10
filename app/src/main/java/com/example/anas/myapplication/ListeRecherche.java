package com.example.anas.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;



public class ListeRecherche extends ActionBarActivity {
    private ListView listeDesRecettes;
    private TextView ingRech;
    private TextView typeRech;

    final String EXTRA_INGREDIENT = "ing";
    final String EXTRA_TYPE = "type";
    final String RECETTE_RECH = "re";

    //private ArrayAdapter<String> listAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_recette);
        listeDesRecettes = (ListView) findViewById(R.id.recetteList);
        ingRech = (TextView) findViewById(R.id.ing);
        typeRech = (TextView) findViewById(R.id.typ);

        //On récupère l'intent qui a lancé l'activité
        Intent i = getIntent();

//On récupère l'ingrédient donné par l'autre activité, celle qui l'a ouvert, ou 0 s'il n'y a pas de valeurs.
        String ingredientRech = i.getStringExtra(EXTRA_INGREDIENT);
        ingRech.setText(ingredientRech);


        String typePlatRech = i.getStringExtra(EXTRA_TYPE);
        typeRech.setText(typePlatRech);


//On récupère le type donné par l'autre activité, celle qui l'a ouvert, ou 0 s'il n'y a pas de valeurs.


            //On fait de même pour la recherche par type
            Vector<Recette> lesRecettes;
            // On crée les objets de type RecetteOperations qui permettra de faire des
            // opérations sur les tables de la base de données.
            final RecetteOperations leRecetteOperations = new RecetteOperations(getApplicationContext());

            //On ouvre la base de données
            leRecetteOperations.open();

            lesRecettes = leRecetteOperations.getRecettewithIngredientAndType(ingredientRech.toString(), typePlatRech.toString());
            //On ferme la base de données
            leRecetteOperations.close();

            //On crée un tableau pour la recherche par type de plat
            final String[] typeRec = new String[lesRecettes.size() + 1];

            //On transfère les données du vecteur de recettes vers un tableau de String avec les noms des recettes
            if (lesRecettes != null) {
                for (int p = 0; p < lesRecettes.size(); p++) {
                    String s = lesRecettes.get(p).getTitre();
                    typeRec[p] = s;
                }
            } else {
                Toast.makeText(getApplicationContext(), "Aucune recette correspondante", Toast.LENGTH_LONG).show();
            }

            ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, typeRec);
            listeDesRecettes.setAdapter(adapterType);

        listeDesRecettes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, final View view, int position, long id) {

                Intent secondeActivite = new Intent(ListeRecherche.this, affichageRecette.class);
                String rec = typeRec[position];
                Toast.makeText(getApplicationContext(), rec, Toast.LENGTH_SHORT).show();
                secondeActivite.putExtra(RECETTE_RECH, rec);
                startActivity(secondeActivite);
            }
        });

        }



        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_liste_recette, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

    public void showPopUp(MenuItem it, String name) {

    }
}

