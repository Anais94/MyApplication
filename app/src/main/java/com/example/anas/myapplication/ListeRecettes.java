package com.example.anas.myapplication;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Vector;


public class ListeRecettes extends ActionBarActivity {
    private ListView listeEntrees;
    private ListView listePlats;
    private ListView listeDesserts;
    private ArrayAdapter<String> listAdapter;

    final String RECETTE_SELECT = "re";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_recettes);

        //On récupère les éléments de l'activité correspondante :
        //On commence par compléter chaque liste de l'activité :

        //La liste des entrées :


        //On crée un vecteur de recettes par type de plats
        Vector<Recette> lesRecettesEntrees;
        Vector<Recette> lesRecettesPlats;
        Vector<Recette> lesRecettesDesserts;

        //On crée un RecetteOperations pour effectuer les recherches
        final RecetteOperations leRecetteOperations = new RecetteOperations(this);

        leRecetteOperations.open();

        //On complète ensuite chaque vecteur de recette :
        lesRecettesEntrees = leRecetteOperations.getRecettewithType("entrée");
        lesRecettesPlats = leRecetteOperations.getRecettewithType("plat principal");
        lesRecettesDesserts = leRecetteOperations.getRecettewithType("dessert");

        leRecetteOperations.close();

        //On construit trois tableaux de recettes à partir de ces vecteurs :
        final String[] recettesEntrees = new String[lesRecettesEntrees.size()];
        final String[] recettesPlats = new String[lesRecettesPlats.size()];
        final String[] recettesDesserts = new String[lesRecettesDesserts.size()];

        //On convertit chaque vecteur en tableau :
        //remplissage du tableau des entrées :
        for (int p = 0; p < lesRecettesEntrees.size(); p++) {
            String s = lesRecettesEntrees.get(p).getTitre();
            recettesEntrees[p] = s;
        }

        //remplissage du tableau des plats principaux :
        for (int q = 0; q < lesRecettesPlats.size(); q++) {
            String ss = lesRecettesPlats.get(q).getTitre();
            recettesPlats[q] = ss;
        }

        //remplissage du tableau des desserts :
        for (int r = 0; r < lesRecettesDesserts.size(); r++) {
            String rr = lesRecettesDesserts.get(r).getTitre();
            recettesDesserts[r] = rr;
        }

        //On récupère chaque liste sur le fichier xml :
        listeEntrees = (ListView) findViewById(R.id.listeRecEnt);
        listePlats = (ListView) findViewById(R.id.listePlatsPrinc);
        listeDesserts = (ListView) findViewById(R.id.listeDesDesserts);

        //On créée alors un adapter pour créer chaque liste sur l'activité :

        //La liste des entrées :

        ArrayAdapter entreesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recettesEntrees);
        listeEntrees.setAdapter(entreesAdapter);

        //La liste des plats :
        ArrayAdapter platsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recettesPlats);
        listePlats.setAdapter(platsAdapter);

        //La liste des desserts :

        ArrayAdapter dessertsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recettesDesserts);
        listeDesserts.setAdapter(dessertsAdapter);


        // int position = listeEntrees.getCheckedItemPosition();
        // long id = listeEntrees.getSelectedItemId();
//On s'occupe de ce qu'il se passera lorsque l'on clique sur un item de la liste :
        listeEntrees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, final View view, int position, long id) {

                Intent secondeActivite = new Intent(ListeRecettes.this, affichageRecette.class);
                String titre = recettesEntrees[position];
                Toast.makeText(getApplicationContext(), titre, Toast.LENGTH_SHORT).show();
                secondeActivite.putExtra(RECETTE_SELECT, titre);
                startActivity(secondeActivite);
            }
        });

        listePlats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, final View view, int position, long id) {

                Intent secondeActivite = new Intent(ListeRecettes.this, affichageRecette.class);
                String titre = recettesPlats[position];
                Toast.makeText(getApplicationContext(), titre, Toast.LENGTH_SHORT).show();
                secondeActivite.putExtra(RECETTE_SELECT, titre);
                startActivity(secondeActivite);
            }
        });

        listeDesserts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, final View view, int position, long id) {

                Intent secondeActivite = new Intent(ListeRecettes.this, affichageRecette.class);
                String titre = recettesDesserts[position];
                Toast.makeText(getApplicationContext(), titre, Toast.LENGTH_SHORT).show();
                secondeActivite.putExtra(RECETTE_SELECT, titre);
                startActivity(secondeActivite);
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_liste_recettes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

}