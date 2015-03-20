package com.example.anas.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;


public class affichageRecette extends ActionBarActivity {
    final String RECETTE_SELECT = "re";
    final String RECETTE_RECH = "re";
    private TextView recetteContenu=null;
    private TextView duree =null;
    private TextView typePlat=null;
    private TextView titre = null;
    private TextView ingdt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_recette);

        //On récupère l'intent qui a lancé l'activité
        Intent i = getIntent();
        String titreRec = i.getStringExtra(RECETTE_SELECT);
        String titreRecRech = i.getStringExtra(RECETTE_RECH);

       Recette laRecette = null;
        // On crée les objets de type RecetteOperations qui permettra de faire des
        // opérations sur les tables de la base de données.
        final RecetteOperations leRecetteOperations = new RecetteOperations(getApplicationContext());

        //On ouvre la base de données
        leRecetteOperations.open();

        laRecette = leRecetteOperations.getRecettewithTitre(titreRec);

        Vector<Ingredient> vectIngredients = new Vector<Ingredient>();
        vectIngredients = leRecetteOperations.getIngredientwithTitre(titreRec);
        //On ferme la base de données
        leRecetteOperations.close();

        typePlat= (TextView) findViewById(R.id.platPrincipal);
        duree = (TextView) findViewById(R.id.dure);
        titre = (TextView) findViewById(R.id.titreRecette);
        ingdt = (TextView) findViewById(R.id.ingdt);


        typePlat.setText(laRecette.getType());
        duree.setText(laRecette.getTpsStringPreparation());
        titre.setText(laRecette.getTitre());


        String a = "";
        for (int p =0;p<vectIngredients.size();p++){
            a = a +" - "+ vectIngredients.get(p).getQuantite()+ " " + vectIngredients.get(p).getUnite()+
                    " " + vectIngredients.get(p).getNom();

        }
        ingdt.setText(a);

        // On récupère par son identifiant la WebView
        final WebView pageView = (WebView) findViewById(R.id.maWebView);
        pageView.setVisibility(WebView.VISIBLE);
        String contenuFichier;

        // On lit le contenu du fichier .html qu'on va récupérer sous forme de String
        contenuFichier = ReadSettings(getApplicationContext(),laRecette.getContenu());

        // On charge le code html dans la WebView
        pageView.loadData(contenuFichier, "text/html; charset=utf-8", "UTF-8");

    }






    public String ReadSettings(Context context, String recette) {
        InputStream unInputStream = null;
        InputStreamReader unInputStreamReader = null;

        char[] inputBuffer = new char[1000];
        String contenuFichier = null;


        try {
            unInputStream = context.getAssets().open(recette + ".html");
            unInputStreamReader = new InputStreamReader(unInputStream);
            unInputStreamReader.read(inputBuffer);
            contenuFichier = new String(inputBuffer);

        } catch (Exception e) {
            Toast.makeText(context, "Settings not read", Toast.LENGTH_SHORT)
                    .show();
        }
        return contenuFichier;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_affichage_recette, menu);
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
