package com.example.anas.myapplication;

public class Etape {

	private String contenu;
	private Recette laRecette;

	//Constructeur de la classe Etape
	public Etape(String c, Recette r) {
		contenu = c;
		laRecette = r;
	}

	//M�thode qui permet d'obtenir le contenu de l'�tape
	public String getContenu() {
		return contenu;		
	}
}
