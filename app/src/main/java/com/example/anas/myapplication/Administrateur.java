package com.example.anas.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Administrateur extends ActionBarActivity {
    private Spinner unites=null;
    private Spinner unites2 = null;
    private Spinner unites3 = null;
    private Spinner unites4 = null;
    private Spinner unites5 = null;
    private Spinner unites6 = null;
    private Spinner unites7 = null;
    private Spinner unites8 = null;
    private Spinner unites9 = null;
    private Spinner unites10 = null;

    private EditText duree = null;
    private EditText titre = null;

    private EditText nom1 = null;
    private EditText nom2 = null;
    private EditText nom3 = null;
    private EditText nom4 = null;
    private EditText nom5 = null;
    private EditText nom6 = null;
    private EditText nom7 = null;
    private EditText nom8 = null;
    private EditText nom9 = null;
    private EditText nom10 = null;

    private EditText quantite1 = null;
    private EditText quantite2 = null;
    private EditText quantite3 = null;
    private EditText quantite4 = null;
    private EditText quantite5 = null;
    private EditText quantite6 = null;
    private EditText quantite7 = null;
    private EditText quantite8 = null;
    private EditText quantite9 = null;
    private EditText quantite10 = null;

    private LinearLayout lin1 = null;
    private LinearLayout lin2 = null;
    private LinearLayout lin3 = null;
    private LinearLayout lin4 = null;
    private LinearLayout lin5 = null;
    private LinearLayout lin6 = null;
    private LinearLayout lin7 = null;
    private LinearLayout lin8 = null;
    private LinearLayout lin9 = null;
    private LinearLayout lin10 = null;

    private RadioGroup plat = null;

    private int compteur = 0;

    private String nomFichier;

    private Button okay = null;
    private Button plus = null;

    final String NOM_FICHIER = "a";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrateur);

        unites = (Spinner) findViewById(R.id.unite);
        unites2 = (Spinner) findViewById(R.id.unite2);
        unites3 = (Spinner) findViewById(R.id.unite3);
        unites4 = (Spinner) findViewById(R.id.unite4);
        unites5 = (Spinner) findViewById(R.id.unite5);
        unites6 = (Spinner) findViewById(R.id.unite6);
        unites7 = (Spinner) findViewById(R.id.unite7);
        unites8 = (Spinner) findViewById(R.id.unite8);
        unites9 = (Spinner) findViewById(R.id.unite9);
        unites10 = (Spinner) findViewById(R.id.unite10);


        String[] array_spinner = new String[6];
        array_spinner[0] = "";
        array_spinner[1] = "cuillère à café de";
        array_spinner[2] = "grammes de";
        array_spinner[3] = "cuillères à soupe de";
        array_spinner[4] = "tranches";
        array_spinner[5] = "litres";

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_spinner);
        unites.setAdapter(adapter);
        unites2.setAdapter(adapter);
        unites3.setAdapter(adapter);
        unites4.setAdapter(adapter);
        unites5.setAdapter(adapter);
        unites6.setAdapter(adapter);
        unites7.setAdapter(adapter);
        unites8.setAdapter(adapter);
        unites9.setAdapter(adapter);
        unites10.setAdapter(adapter);


        //On récupère les différentes données principales de la recette :
        titre = (EditText) findViewById(R.id.titreRecette);
        duree = (EditText) findViewById(R.id.tps);


        //ingrédient 1
        lin1 = (LinearLayout) findViewById(R.id.linear1);
        quantite1 = (EditText) findViewById(R.id.quantite1);
        nom1 = (EditText) findViewById(R.id.nom1);

        //ingrédient 2
        lin2 = (LinearLayout) findViewById(R.id.linear2);
        quantite2 = (EditText) findViewById(R.id.quantite2);
        nom2 = (EditText) findViewById(R.id.nom2);

        //ingrédient 3
        lin3 = (LinearLayout) findViewById(R.id.linear3);
        quantite3 = (EditText) findViewById(R.id.quantite3);
        nom3 = (EditText) findViewById(R.id.nom3);

        //ingrédient 4
        lin4 = (LinearLayout) findViewById(R.id.linear4);
        quantite4 = (EditText) findViewById(R.id.quantite4);
        nom4 = (EditText) findViewById(R.id.nom4);

        //ingrédient 5
        lin5 = (LinearLayout) findViewById(R.id.linear5);
        quantite5 = (EditText) findViewById(R.id.quantite5);
        nom5 = (EditText) findViewById(R.id.nom5);

        //ingrédient 6
        lin6 = (LinearLayout) findViewById(R.id.linear6);
        quantite6 = (EditText) findViewById(R.id.quantite6);
        nom6 = (EditText) findViewById(R.id.nom6);

        //ingrédient 7
        lin7 = (LinearLayout) findViewById(R.id.linear7);
        quantite7 = (EditText) findViewById(R.id.quantite7);
        nom7 = (EditText) findViewById(R.id.nom7);

        //ingrédient 8
        lin8 = (LinearLayout) findViewById(R.id.linear8);
        quantite8 = (EditText) findViewById(R.id.quantite8);
        nom8 = (EditText) findViewById(R.id.nom8);

        //ingrédient 9
        lin9 = (LinearLayout) findViewById(R.id.linear9);
        quantite9 = (EditText) findViewById(R.id.quantite9);
        nom9 = (EditText) findViewById(R.id.nom9);

        //ingrédient 10
        lin10 = (LinearLayout) findViewById(R.id.linear10);
        quantite10 = (EditText) findViewById(R.id.quantite10);
        nom10 = (EditText) findViewById(R.id.nom10);

        okay = (Button) findViewById(R.id.ok);
        plus = (Button) findViewById(R.id.ajouter);


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (compteur == 0) {
                    lin4.setVisibility(View.VISIBLE);
                    compteur += 1;
                } else if (compteur == 1) {
                    lin5.setVisibility(View.VISIBLE);
                    compteur += 1;
                } else if (compteur == 2) {
                    lin6.setVisibility(View.VISIBLE);
                    compteur += 1;
                } else if (compteur == 3) {
                    lin7.setVisibility(View.VISIBLE);
                    compteur += 1;
                } else if (compteur == 4) {
                    lin8.setVisibility(View.VISIBLE);
                    compteur += 1;
                } else if (compteur == 5) {
                    lin9.setVisibility(View.VISIBLE);
                    compteur += 1;
                } else if (compteur == 6) {
                    lin10.setVisibility(View.VISIBLE);
                    compteur += 1;
                } else if (compteur > 6) {
                    Toast.makeText(getApplicationContext(), "Vous ne pouvez plus ajouter d'ingrédients", Toast.LENGTH_SHORT).show();
                }


            }

        });
        plat = (RadioGroup) findViewById(R.id.typePla);

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (titre.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Veuillez mettre un titre", Toast.LENGTH_SHORT).show();
                } else if (duree.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Veuillez indiquer le temps de préparation",
                            Toast.LENGTH_SHORT).show();
                } else {


                    Vector<String[]> lesIng = new Vector<String[]>();

                    String[] ing1 = new String[]{quantite1.getText().toString(), unites.getSelectedItem().toString(), nom1.getText().toString()};
                    lesIng.add(ing1);
                    String[] ing2 = new String[]{quantite2.getText().toString(), unites2.getSelectedItem().toString(), nom2.getText().toString()};
                    lesIng.add(ing2);
                    String[] ing3 = new String[]{quantite3.getText().toString(), unites3.getSelectedItem().toString(), nom3.getText().toString()};
                    lesIng.add(ing3);
                    String[] ing4 = new String[]{quantite4.getText().toString(), unites4.getSelectedItem().toString(), nom4.getText().toString()};
                    lesIng.add(ing4);
                    String[] ing5 = new String[]{quantite5.getText().toString(), unites5.getSelectedItem().toString(), nom5.getText().toString()};
                    lesIng.add(ing5);
                    String[] ing6 = new String[]{quantite6.getText().toString(), unites6.getSelectedItem().toString(), nom6.getText().toString()};
                    lesIng.add(ing6);
                    String[] ing7 = new String[]{quantite7.getText().toString(), unites7.getSelectedItem().toString(), nom7.getText().toString()};
                    lesIng.add(ing7);
                    String[] ing8 = new String[]{quantite8.getText().toString(), unites8.getSelectedItem().toString(), nom8.getText().toString()};
                    lesIng.add(ing8);
                    String[] ing9 = new String[]{quantite9.getText().toString(), unites9.getSelectedItem().toString(), nom9.getText().toString()};
                    lesIng.add(ing9);
                    String[] ing10 = new String[]{quantite10.getText().toString(), unites10.getSelectedItem().toString(), nom10.getText().toString()};
                    lesIng.add(ing10);


                    for (int q = 9; q >= 0; q--) {
                        if (lesIng.get(q)[0].equals("") && lesIng.get(q)[1].equals("") && lesIng.get(q)[2].equals("")) {
                            lesIng.remove(lesIng.get(q));
                        }

                    }


                    for (int l = 0; l < lesIng.size(); l++) {
                        if (lesIng.get(l)[0].equals("")) {
                            int p = l + 1;
                            Toast.makeText(getApplicationContext(), "Veuillez bien compléter votre ingrédient n° " + p +
                                    " et rappuyez sur ok", Toast.LENGTH_SHORT).show();
                        } else if (lesIng.get(l)[1].equals("")) {
                            int p = l + 1;
                            Toast.makeText(getApplicationContext(), "Veuillez bien compléter votre ingrédient n° " + p +
                                    " et rappuyez sur ok", Toast.LENGTH_SHORT).show();
                        } else if (lesIng.get(l)[2].equals("")) {
                            int p = l + 1;
                            Toast.makeText(getApplicationContext(), "Veuillez bien compléter votre ingrédient n° " + p +
                                    " et rappuyez sur ok", Toast.LENGTH_SHORT).show();
                        }
                    }

                    int plat_selection = plat.getCheckedRadioButtonId();
                    //On récupère alors l'id du radiobutton sélectionné
                    RadioButton radioType = (RadioButton) findViewById(plat_selection);


                    String titreR = titre.getText().toString();
                    String typeR = radioType.getText().toString();
                    int tpsR = Integer.parseInt(duree.getText().toString());


                    Recette theRecette = new Recette(titreR, typeR, tpsR);

                    nomFichier = remplacement_espaces(titreR);

                    theRecette.setContenu(nomFichier);

                    for (int n = 0; n < lesIng.size(); n++) {
                        Ingredient a = new Ingredient(lesIng.get(n)[2], Double.parseDouble(lesIng.get(n)[0]), 0, lesIng.get(n)[1]);
                        theRecette.setIngredient(a);
                    }


                    // On crée les objets de type RecetteOperations qui permettra de faire des
                    // opérations sur les tables de la base de données.
                    final RecetteOperations leRecetteOperations = new RecetteOperations(getApplicationContext());

                    //On ouvre la base de données
                    leRecetteOperations.open();

                    leRecetteOperations.addRecette(theRecette);
                    leRecetteOperations.addEntree(theRecette);


                    //On ferme la base de données
                    leRecetteOperations.close();

                    Toast.makeText(getApplicationContext(), nomFichier + "1", Toast.LENGTH_SHORT).show();



                  Intent secondeActivite = new Intent(Administrateur.this, Ecriture.class);
                  secondeActivite.putExtra(NOM_FICHIER, nomFichier);


                    // On lance l'activité suivante et on récupérera le résultat lorsque celle-ci se terminera.
                    // On donne le numéro "2" à l'activité suivante.
                  startActivityForResult(secondeActivite, 2);


                }



            }

        });

    }





// Methode pour remplacer les espaces par des _
// afin de rendre les noms de fichier plus lisible

    public String remplacement_espaces(String texte_a_remplacer) {

// On indique l'expression régulière à repérer, qui est ici l'espace
        Pattern p = Pattern.compile(" ") ;

// On indique la chaîne de caractère avec laquelle on travaille
        Matcher m = p.matcher(texte_a_remplacer) ;


// On indique par quel caractère on remplace les espaces
        String nouveau_texte = m.replaceAll("_") ;

        return nouveau_texte;

    }

    // Méthode pour récupérer le résultat de cette activité

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        // On récupère le statut de retour de cette activité, c'est à dire l'activité numéro 2
        if(requestCode==2){
            // Si le code de retour est égal à 2 on stoppe l'activité 1
            if(resultCode==1){
                finish();
            }
        }
        super.onActivityResult (requestCode, resultCode, data);
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
