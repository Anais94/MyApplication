package com.example.anas.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;

import java.util.Vector;

// Cette classe correspond la
public class MainActivity extends ActionBarActivity {
    //Attributs :

    private Button boutonRecette;
    private TextView titrePrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //On récupère les différents objets de l'activité main :
        titrePrincipal = (TextView) findViewById(R.id.titreP);
        boutonRecette = (Button) findViewById(R.id.buttonRecette);


        //On va créer des liens entre les boutons et les activités qui s'ouvrent.
        //Pour cela, on doit créer des intent.

        // Intent : Permet d'ouvrir l'activité avec la liste des recettes
        // lorsque l'on clique sur "consulter une recette"

       boutonRecette.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            //Le premier paramètre est le nom de l'activité actuelle
            //Le second paramètre est le nom de l'activité de destination
            Intent secondeActivite = new Intent(MainActivity.this, ListeRecettes.class );
            //On lance l'intent :
            startActivity(secondeActivite);
        }

    });

        final RecetteOperations leRecetteOperations = new RecetteOperations(this);

        // Cr�ation des objets : Recette, Ingredient

        /**
         *Toast au ch�vre
         */

        Recette toast = new Recette("Toast au chevre", "plat principal", 20);

        Ingredient pain = new Ingredient ("pain", 4, 0.2, "tranches de");
        Ingredient pomme = new Ingredient ("pomme", 1, 0.35, "");
        Ingredient chevre = new Ingredient ("chèvre", 4, 0.2, "tranches de" );
        toast.setIngredient(pain);
        toast.setIngredient(pomme);
        toast.setIngredient(chevre);

        Four fourToast = new Four();
        Plaque plaqueToast = new Plaque();
        toast.setAppareil(fourToast);
        toast.setAppareil(plaqueToast);
        int[] tripToastP = new int[3];
        tripToastP[0] = 3;
        tripToastP[1] = 0 ;
        tripToastP[2] = 5;
        plaqueToast.ajoutDurPuiNiv(tripToastP);
        int[] tripToastF = new int[3];
        tripToastF [0] = 10; tripToastF[1] = 0 ; tripToastF[2] = 180;
        fourToast.ajoutDurPuiTemp(tripToastF);

        toast.setContenu("toast_chevre");

        /**
         * Mousse au chocolat
         */

        Recette mousseChocolat = new Recette("Mousse au chocolat", "dessert", 180);

        Ingredient tabletteChocolat = new Ingredient ("chocolat", 1, 1.2, "tablette de");
        Ingredient oeuf = new Ingredient ("oeufs", 6, 0.3, "");
        mousseChocolat.setIngredient(tabletteChocolat);
        mousseChocolat.setIngredient(oeuf);


        int[] tripMousseFri = new int[3];
        tripMousseFri[0]=160;
        Vector<int[]> vecMousseFri = new Vector<int[]>();
        vecMousseFri.add(tripMousseFri);
        Frigidaire frigoMousse = new Frigidaire(vecMousseFri);
        int[]tripMousseM = new int[3];
        tripMousseM[0]=10;
        Vector<int[]> vecMousseM= new Vector<int[]>();
        vecMousseM.add(tripMousseM);
        Mixeur mixeurMousse = new Mixeur(vecMousseM);
        Plaque plaqueMousse = new Plaque();
        int[] tripMousse = new int[3];
        tripMousse[0] = 3;
        tripMousse[1] = 0 ;
        tripMousse[2] = 5;
        plaqueMousse.ajoutDurPuiNiv( tripMousse);
        mousseChocolat.setAppareil(plaqueMousse);
        mousseChocolat.setAppareil(frigoMousse);
        mousseChocolat.setAppareil(mixeurMousse);

        mousseChocolat.setContenu("mousse_chocolat");

        /**
         * Oeufs Mimosa
         */

        Recette oeufsMimosa = new Recette("Oeufs mimosa", "entrée", 90);

        Ingredient mayonnaise = new Ingredient ("mayonnaise", 3, 0.05, "cuillère à café de");
        Ingredient thon = new Ingredient ("thon", 1, 1, "boîte de");
        Ingredient oeuf2 = new Ingredient ("oeufs", 2, 0.2, "");
        oeufsMimosa.setIngredient(mayonnaise);
        oeufsMimosa.setIngredient(oeuf2);
        oeufsMimosa.setIngredient(thon);

        int[] tripOeufsF = new int[3];
        tripOeufsF[0]=60;
        Vector<int[]> vecOeufs = new Vector<int[]>();
        vecOeufs.add(tripOeufsF);
        Frigidaire frigoOeufs = new Frigidaire(vecOeufs);
        Plaque plaqueOeufs = new Plaque();
        oeufsMimosa.setAppareil(plaqueOeufs);
        oeufsMimosa.setAppareil(frigoOeufs);
        int[] tripOeufsP1 = new int[3];
        tripOeufsP1[0] = 3; tripOeufsP1[1] = 0 ; tripOeufsP1[2] = 5;
        plaqueOeufs.ajoutDurPuiNiv(tripOeufsP1);
        int[] tripOeufsP2 = new int[3];
        tripOeufsP2[0] = 10; tripOeufsP2[1] = 0 ; tripOeufsP2[2] = 4;
        plaqueOeufs.ajoutDurPuiNiv(tripOeufsP2);
        oeufsMimosa.setAppareil(frigoOeufs);
        oeufsMimosa.setAppareil(plaqueOeufs);

        oeufsMimosa.setContenu("oeufs_mimosa");

        /**
         * Taboulé
         */

        Recette taboule = new Recette("Taboulé", "plat principal", 30);

        Ingredient semoule = new Ingredient ("semoule",50 , 0.002, "grammes de");
        Ingredient tomate1 = new Ingredient ("tomate", 3, 0.3, "");
        Ingredient poivron = new Ingredient ("poivron", 0.5, 0.75, "" );
        Ingredient citron = new Ingredient ("citron", 0.5,0.2, "");
        Ingredient dinde = new Ingredient ("dinde", 1, 1.91, "");
        taboule.setIngredient(semoule);
        taboule.setIngredient(tomate1);
        taboule.setIngredient(poivron);
        taboule.setIngredient(citron);
        taboule.setIngredient(dinde);

        int[] tripTabF = new int[3];
        tripTabF[0]=60;
        Vector<int[]> vecTab = new Vector<int[]>();
        vecTab.add(tripTabF);
        Frigidaire frigoTab = new Frigidaire(vecTab);
        Plaque plaqueTab = new Plaque();
        taboule.setAppareil(frigoTab);
        taboule.setAppareil(plaqueTab);
        int[] tripTabP1 = new int[3];
        tripTabP1[0] = 3; tripTabP1[1] = 0 ; tripTabP1[2] = 6;
        int[] tripTabP2 = new int[3];
        tripTabP2[0] = 5; tripTabP2[1] = 0 ; tripTabP2[2] = 4;
        plaqueTab.ajoutDurPuiNiv(tripTabP1);
        plaqueTab.ajoutDurPuiNiv(tripTabP2);

        taboule.setContenu("taboule");

        leRecetteOperations.open();
        leRecetteOperations.dbHelper.onUpgrade(leRecetteOperations.database,2,1);
        leRecetteOperations.addRecette(toast);
        leRecetteOperations.addEntree(toast);
        leRecetteOperations.addRecette(mousseChocolat);
        leRecetteOperations.addEntree(mousseChocolat);
        leRecetteOperations.addRecette(oeufsMimosa);
        leRecetteOperations.addEntree(oeufsMimosa);
        leRecetteOperations.addRecette(taboule);
        leRecetteOperations.addEntree(taboule);
        leRecetteOperations.close();
}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
