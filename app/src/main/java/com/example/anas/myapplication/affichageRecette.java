package com.example.anas.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class affichageRecette extends ActionBarActivity {
    final String RECETTE_SELECT = "re";
    final String RECETTE_RECH = "re";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_recette);

        //On récupère l'intent qui a lancé l'activité
        Intent i = getIntent();
        String titreRec = i.getStringExtra(RECETTE_SELECT);
String titreRecRech = i.getStringExtra(RECETTE_RECH);
        Toast.makeText(getApplicationContext(), titreRec, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), titreRecRech, Toast.LENGTH_SHORT).show();
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
