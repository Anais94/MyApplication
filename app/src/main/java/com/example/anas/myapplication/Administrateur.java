package com.example.anas.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class Administrateur extends ActionBarActivity {
    private Spinner unites=null;
    private Spinner unites2 = null;

    private EditText duree = null;
    private EditText titre = null;

    private EditText nom1 = null;
    private EditText nom2 = null;

    private EditText quantite1 = null;
    private EditText quantite2 = null;

    private RadioGroup plat = null;

    private Button okay = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrateur);

        unites = (Spinner)  findViewById(R.id.unite);
        unites2 = (Spinner)  findViewById(R.id.unite2);


        String [] array_spinner=new String[6];
        array_spinner[0]="";
        array_spinner[1]="cuillère à café de";
        array_spinner[2]="grammes de";
        array_spinner[3]="cuillères à soupe de";
        array_spinner[4]="tranches";
        array_spinner[5]="litres";

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_spinner);
        unites.setAdapter(adapter);
        unites2.setAdapter(adapter);


        //On récupère les différentes données principales de la recette :
        titre = (EditText)  findViewById(R.id.titreRecette);
        duree = (EditText)  findViewById(R.id.tps);


        //ingrédient 1
        quantite1 = (EditText)  findViewById(R.id.quantite1);
        nom1 = (EditText)  findViewById(R.id.nom1);

        //ingrédient 2
        quantite2 = (EditText)  findViewById(R.id.quantite2);
        nom2 = (EditText)  findViewById(R.id.nom2);

        okay = (Button)  findViewById(R.id.ok);

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a1 = unites.getSelectedItem().toString();
                String a2 = unites2.getSelectedItem().toString();

                Toast.makeText(getApplicationContext(), a1, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), a2, Toast.LENGTH_SHORT).show();
            }


        });
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_administrateur, menu);
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
