package com.example.anas.myapplication;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class Ecriture extends ActionBarActivity {

    private Button creer = null;
    private EditText monContenu = null;
    private String nomFich = null;

    final String NOM_FICHIER = "a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecriture);

        Intent i = getIntent();

        final String nom_fichier  = i.getStringExtra(NOM_FICHIER);

        creer = (Button) findViewById(R.id.creation);
        monContenu = (EditText)  findViewById(R.id.preparation);

        creer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mContenu = monContenu.getText().toString();


                //ecrireFichier(nomFich, mContenu);

                ecriture_fichier_HTML(nom_fichier, mContenu);

// On souhaite revenir à l'activité principale (menu) donc on le signale à l'activité Administrateur
                setResult(1);

                // On ferme cette activité
                finish();
            }
        });

    }

    private void ecrireFichier(String nomFichier,String monText) {
        BufferedWriter writer = null;
        try {
            File dir = getDir("assets",MODE_PRIVATE);
            File newfile = new File(dir.getAbsolutePath() + File.separator + nomFichier);
            newfile.createNewFile();
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newfile)));
            writer.write(monText);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Méthode pour créer un fichier HTML, qui va contenir la préparation de la recette

    public void ecriture_fichier_HTML(String nom_fichier, String contenu_fichier) {

//Nom du fichier (d'extension .HTML)
        String fichier_HTML = nom_fichier + ".html";

//Création du fichier
        File file = new File(Environment.getExternalStorageDirectory(), fichier_HTML);
        //Environment.getExternalStorageDirectory()
// Texte à insérer dans le fichier
        String Info = "<html>" + "\n" + "<body>" + "\n"  + contenu_fichier + "\n" + "</body>" + "\n" + "</html>";

        try {
// On crée le fichier HTML
            file.createNewFile();

// On met le boolean à "true" pour indiquer qu'on ajoute les éléments à la fin du fichier, et non pas au début
            FileWriter filewriter = new FileWriter(file, true);

// On écrit dans le fichier
            filewriter.write(Info);

            filewriter.close();
        } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Settings not read", Toast.LENGTH_SHORT)
                        .show();
            }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ecriture, menu);
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
