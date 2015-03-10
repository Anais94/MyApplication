package com.example.anas.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.Vector;

public class RecetteOperations {
	public DatabaseHandler dbHelper;
	public SQLiteDatabase database;

	// Construction de la classe RecetteOperations
	public RecetteOperations(Context context) {
		dbHelper = new DatabaseHandler(context);
	}

	// Cr�ation de la base de donn�es avec un acc�s en lecture et �criture
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	// Fermeture de la connexion � la base de donn�es
	public void close() {
		dbHelper.close();
	}

	// M�thode pour ajouter une recette � la table de donn�es "RECETTES"
	public long addRecette(Recette rec) {

		ContentValues valeurs = new ContentValues();

		// Initialisation de la variable valeurs avec les couples de valeurs suivants :
		//    - (TITRE, titre)
		//    - (TYPE, type)
		//    - (TpsPreparation, tpsPreparation)
		//	  - (CONTENU, contenu)
		valeurs.put(dbHelper.getTitre_Rec(), rec.getTitre());
		valeurs.put(dbHelper.getType(), rec.getType());
		valeurs.put(dbHelper.getTpspreparation(), rec.getTpsPreparation());
		valeurs.put(dbHelper.getContenu(), rec.getContenu());

		// Insertion de l'enregistrement dans la table "RECETTES"
		long recetteId = database.insert(dbHelper.getTableRecettes(),null, valeurs);
		return recetteId;
	}

	// M�thode pour ajouter un enregistrement � la table de donn�es "RELATION_REC_ING"
	public void addEntree(Recette rec) {

		// Initialisation de la variable valeurs avec les couples de valeurs suivants :
		//    - (TITRE, titre)
		//    - (INGREDIENT, ingredient)
		//    - (QUANTITE, quantite)
		for (int i=0; i<rec.getLesIngredients().size(); i++) {
			ContentValues valeurs = new ContentValues();
			valeurs.put(dbHelper.getTitre_Ing(), rec.getTitre());
			valeurs.put(dbHelper.getIngredient(), rec.getLesIngredients().get(i).getNom());
			valeurs.put(dbHelper.getQuantite(), rec.getLesIngredients().get(i).getQuantite());

			// Insertion de l'enregistrement dans la table "RELATION_REC_ING"
			long entree = database.insert(dbHelper.getTableRecIng(),null, valeurs);
		}
	}


	// Pour r�cup�rer une recette gr�ce � son ingr�dient
	public Vector<Recette> getRecettewithIngredient(String nom_ingredient){

		// On cr�e un vecteur de Recette qui contiendra toutes les recettes qui r�sultent de la recherche
		Vector<Recette> lesRecettes = new Vector<Recette>();

		//On ex�cute la requ�te dont le r�sultat sera stock� dans la variable cursor
		Cursor cursor = dbHelper.getReadableDatabase().rawQuery("SELECT RECETTES.TITRE, RECETTES.TYPE "
				+ "FROM  RELATION_REC_ING,RECETTES "
				+ "WHERE RELATION_REC_ING.INGREDIENT = '" + nom_ingredient + "' and RELATION_REC_ING.TITRE = RECETTES.TITRE;",

				new String[] {});
		// On r�cup�re les valeurs  des colonnes des enregistrements stock�s dans l'objet cursor.
		// On construit un vecteur de Recette
		int numeroColonneTitre = cursor.getColumnIndexOrThrow(dbHelper.getTitre_Rec());
		int numeroColonneType = cursor.getColumnIndexOrThrow(dbHelper.getType());
		//int numeroColonneTps = cursor.getColumnIndexOrThrow(dbHelper.getTpspreparation());

		if (cursor.moveToFirst() == true) {
			do {
				// On cr�e une recette on lui affecte toutes les informations contenues dans le cursor
				String titre = cursor.getString(numeroColonneTitre);
				String type = cursor.getString(numeroColonneType);
				//int tpsPreparation = cursor.getInt(numeroColonneTps);
				Recette rec = new Recette(titre, type, 0);
				lesRecettes.add(rec);
			}while (cursor.moveToNext());
		}
		cursor.close();
		return lesRecettes;
	}



	// Pour r�cup�rer une recette gr�ce � son type (entr�e, plat ou dessert)
	public Vector<Recette> getRecettewithType(String unType){

		// On cr�e un vecteur de Recette qui contiendra toutes les recettes qui r�sultent de la recherche
		Vector<Recette> lesRecettes = new Vector<Recette>();

		// La requ�te r�cup�re toutes les informations sur le recette-r�sultat de la recherche
		String tabColonne[] = new String[4];
		tabColonne[0] = dbHelper.getId();
		tabColonne[1] = dbHelper.getTitre_Rec();
		tabColonne[2] = dbHelper.getType();
		tabColonne[3] = dbHelper.getTpspreparation();

		//On ex�cute la requ�te dont le r�sultat sera stock� dans la variable cursor
		Cursor cursor = database.query(dbHelper.getTableRecettes(), tabColonne , dbHelper.getType() + " LIKE \"" + unType +"\"", null, null, null, null);

		// On r�cup�re les valeurs  des colonnes des enregistrements stock�s dans l'objet cursor
		int numeroColonneId = cursor.getColumnIndexOrThrow(dbHelper.getId());
		int numeroColonneTitre = cursor.getColumnIndexOrThrow(dbHelper.getTitre_Rec());
		int numeroColonneType = cursor.getColumnIndexOrThrow(dbHelper.getType());
		int numeroColonneTps = cursor.getColumnIndexOrThrow(dbHelper.getTpspreparation());

		if (cursor.moveToFirst() == true) {
			do {
				// On cr�e une recette on lui affecte toutes les informations contenues dans le cursor
				String titre = cursor.getString(numeroColonneTitre);
				String type = cursor.getString(numeroColonneType);
				int tps = cursor.getInt(numeroColonneTps);
				Recette rec = new Recette(titre, type, tps);
				lesRecettes.add(rec);
			}
			while (cursor.moveToNext());
		}
		cursor.close();
		return lesRecettes;
	}

	// Pour r�cup�rer une recette gr�ce � un ingr�dient et un type
	public Vector<Recette> getRecettewithIngredientAndType(String nom_ingredient, String unType){
nom_ingredient=nom_ingredient.toLowerCase();
        unType=unType.toLowerCase();
		// On cr�e un vecteur de Recette qui contiendra toutes les recettes qui r�sultent de la recherche
		Vector<Recette> lesRecettes = new Vector<Recette>();

		//S'il n'y a que l'ingr�dient qui est renseign�, on ex�cute la m�thode getRecettewithIngredient()
        // S'il n'y a que le type qui est renseign�, on ex�cute la m�thode getRecettewithType()
        if (unType.equals("")) {
            lesRecettes = getRecettewithIngredient(nom_ingredient);
        }
        else if (nom_ingredient.equals("")) {
			lesRecettes = getRecettewithType(unType);

			// Si on a un nom d'ingr�dient et un nom de type :
		} else {
            // On ex�cute la requ�te dont le r�sultat sera stock� dans la variable cursor
            Cursor cursor = dbHelper.getReadableDatabase().rawQuery("SELECT RECETTES.TITRE, RECETTES.TYPE "
                            + "FROM  RELATION_REC_ING,RECETTES "
                            + "WHERE RELATION_REC_ING.INGREDIENT = '" + nom_ingredient + "' and RECETTES.TYPE ='" + unType + "' and RELATION_REC_ING.TITRE = RECETTES.TITRE;",

                    new String[]{});
            // On r�cup�re les valeurs  des colonnes des enregistrements stock�s dans l'objet cursor.
            // On construit un vecteur de Recette
            int numeroColonneTitre = cursor.getColumnIndexOrThrow(dbHelper.getTitre_Rec());
            int numeroColonneType = cursor.getColumnIndexOrThrow(dbHelper.getType().toUpperCase());
            //int numeroColonneTps = cursor.getColumnIndexOrThrow(dbHelper.getTpspreparation());

            if (cursor.moveToFirst() == true) {
                do {
                    // On cr�e une recette on lui affecte toutes les informations contenues dans le cursor
                    String titre = cursor.getString(numeroColonneTitre);
                    String type = cursor.getString(numeroColonneType);
                    //int tps = cursor.getInt(numeroColonneTps);
                    Recette rec = new Recette(titre, type, 0);
                    lesRecettes.add(rec);
                } while (cursor.moveToNext());
            }
            cursor.close();

        }
		return lesRecettes;
	}
}

