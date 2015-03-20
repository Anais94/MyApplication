package com.example.anas.myapplication;


import java.util.*;

public class Recette {
	public String titre;
	public String type;
	public int tpsPreparation;
	private Vector<Ingredient> lesIngredients;
	private Vector<Appareil> lesAppareils;
	private Vector<Etape> lesEtapes;
	public String contenu;

	// Constructeur de la classe Recette
	public Recette(String unTitre, String unType, int unTpsPreparation) {
		titre=unTitre;
		type=unType;
		tpsPreparation=unTpsPreparation;
		lesIngredients = new Vector<Ingredient>();
		lesEtapes = new Vector<Etape>();
		lesAppareils = new Vector<Appareil>();
	}

	// M�thode permettant d'affilier un ingr�dient � une recette 
	public void setIngredient(Ingredient ing) {
		lesIngredients.add(ing);
	}
	
	// M�thode permettant d'ajouter le contenu d'une recette
	public void setContenu(String unContenu) {
		contenu = unContenu;
	}

	// M�thode pour r�cup�rer l'ensemble des ingr�dients de la recette
	public Vector<Ingredient> getLesIngredients() {
		return lesIngredients;
	}

	// M�thode permettant d'affilier un appareil � une recette 
	public void setAppareil(Appareil app) {
		lesAppareils.add(app);
	}

	// M�thode pour r�cup�rer l'ensemble des appareils n�cessaires � la recette
	public Vector<Appareil> getLesAppareils() {
		return lesAppareils;
	}



	// Getters de la classe Recette
	public String getTitre() {
		return titre;
	}


	public String getType() {
		return type;
	}


	public int getTpsPreparation() {
		return tpsPreparation;
	}

    public String getTpsStringPreparation() { return Integer.toString(tpsPreparation);}

	public String getContenu() {
return contenu;
	}


	/**
	 * Cr�ation et remplissage de la table RELATION_REC_APP : table r�unissant toutes les recettes, avec les appareils utilis�s
	 */

	// M�thode servant � nommer la table de donn�es

	public String getNomTable_RecApp() {
		return "RELATION_REC_APP";
	}


	// M�thode permettant de r�cup�rer les champs de la table RELATION_REC_APP et leurs types 

	protected String[] getChampsTypes_RecApp() {
		String[] res = 
			{"TITRE","TEXT","TypeAppareil","TEXT","DureeUtilisation","INTEGER","PUISSANCE","INTEGER","TEMPERATUREouNIVEAU","INTEGER"};
		return res;
	}


	// M�thode permettant de r�cup�rer les noms des champs de la table RELATION_REC_APP

	protected String[] getChamps_RecApp() {
		String[] res = {"TITRE", "TypeAppareil", "DureeUtilisation","PUISSANCE","TEMPERATUREouNIVEAU"};
		return res;
	}


	// M�thode pour cr�er la table RELATION_REC_APP

	public String sqlCreateTable_RecApp() {
		String res = "CREATE TABLE " + getNomTable_RecApp() + "(";
		boolean first = true ;
		int nb=0;
		for ( String s : getChampsTypes_RecApp()) {
			if (first)
				first = false;
			else if (nb % 2 == 0)
				res += ", ";
			else
				res += " ";
			res += s ;
			++nb ;
		}
		res += ");" ;
		System.out.println(res);
		return res ;
	}


	// M�thode pour ins�rer une entr�e dans la table RELATION_REC_APP

	public String sqlInsert_RecApp() {
		String res = "";

		for (int i=0 ; i<getLesAppareils().size() ; i++) {
			Appareil app = getLesAppareils().get(i);
			for (int j=0 ; j<app.getInformations().size() ; j++) {
				res += "INSERT INTO " + getNomTable_RecApp() + "(";
				boolean b = true;
				for (String val : getChamps_RecApp()) {
					if (b)
						b = false;
					else
						res += ",";
					res += val ;
				}
				res += ") VALUES (";
				res += "'" + getTitre() + "','" + app.getType() + "'," + app.getInformations().get(j)[0] + "," + app.getInformations().get(j)[1] + "," + app.getInformations().get(j)[2] ;	
				res += ");"  ;
			}
		}
		return res ;
	}
}
