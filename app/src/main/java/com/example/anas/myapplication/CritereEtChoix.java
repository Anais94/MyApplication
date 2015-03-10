package com.example.anas.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class CritereEtChoix extends ActionBarActivity {

    private RelativeLayout grillePrincipale = null;

    private TextView titre = null;

    private Button hideShowIng = null;
    private Slider sliderIng = null;
    private LinearLayout toHideIng = null;
    public EditText ingredientRecherche = null;


    private Button hideShowType = null;
    private Slider sliderType = null;
    private LinearLayout toHideType = null;
    private RadioGroup typeChooser = null;
    private Button rechercheType = null;

    final String EXTRA_INGREDIENT = "ing";
    final String EXTRA_TYPE = "type";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critere_et_choix);

        //On récupère le bouton Ingrédient permettant de cacher et ouvrir le menu

        /*//On récupère le slider
        sliderIng=new Slider(false, toHideIng);
        //On rajoute un Listener sur le click du bouton
        hideShowIng.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View vue){
                // pour afficher ou cacher le menu
                if(sliderIng.toggle()){
                    // Si le slide est ouvert, changer le texte en
                    hideShowIng.setHintTextColor(002200);
                }else{
                    //Sinon on met le texte en noir
                    hideShowIng.setHintTextColor(000000);
                }
            }
        });*/

       /* //On récupère le bouton Type permettant de cacher et ouvrir le menu
        hideShowType=(Button) findViewById(R.id.hideShowType);
        //On rajoute un Listener sur le click du bouton
        hideShowType.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vue){
                // pour afficher ou cacher le menu
                if(sliderType.toggle()){
                    // Si le slide est ouvert, changer le texte en
                    hideShowType.setHintTextColor(002200);
                }else{
                    //Sinon on met le texte en noir
                    hideShowType.setHintTextColor(000000);
                }
            }
        });*/
        //On récupère le menu
        grillePrincipale=(RelativeLayout) findViewById(R.id.grilleGenerale);
// On récupère le premier LinearLayout des ingrédients
        toHideIng=(LinearLayout)  findViewById(R.id.grilleIng);
        //On récupère le second LinearLayout des types de plats
       //toHideType=(LinearLayout)  findViewById(R.id.grilleType);

        //On récupère l'éditeur de texte où l'on rentre ses ingrédients
        ingredientRecherche=(EditText)  findViewById(R.id.ingredientRentre);


        //On récupère les deux boutons de recherche

        rechercheType=(Button)  findViewById(R.id.RechercherType);
        ingredientRecherche=(EditText)  findViewById(R.id.ingredientRentre);


        //On récupère le Radiogroup de sélection de type de plat
        typeChooser=(RadioGroup)  findViewById(R.id.groupeType);

        rechercheType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                //Le premier paramètre est le nom de l'activité actuelle
                //Le second paramètre est le nom de l'activité de destination
                Intent secondeActivite = new Intent(CritereEtChoix.this, ListeRecherche.class );

                //On regarde quel radiobutton est sélectionné
               int button_selected = typeChooser.getCheckedRadioButtonId();

                //On récupère alors l'id du radiobutton sélectionné
                RadioButton radioType = (RadioButton) findViewById(button_selected);
                //On ajoute un extra, ie une valeur que l'on transmettra au second intent
                //Ici il s'agit du type de plat choisi
                if (button_selected > -1) {
                    secondeActivite.putExtra(EXTRA_TYPE, radioType.getText());
                    //Toast.makeText(getApplicationContext(),
                            //radioType.getText(), Toast.LENGTH_SHORT).show();
                }else {
                    secondeActivite.putExtra(EXTRA_TYPE, "");
                }
                secondeActivite.putExtra(EXTRA_INGREDIENT, ingredientRecherche.getText().toString());
                //On indique que l'on fait une recherche par type de plat par le numéro 2
                //secondeActivite.putExtra(RECH, "0");

                //On lance l'intent :
                startActivity(secondeActivite);
            }

        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_critere_et_choix, menu);
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
