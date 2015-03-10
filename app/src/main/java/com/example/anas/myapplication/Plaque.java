package com.example.anas.myapplication;

import java.util.*;

public class Plaque extends Appareil {

	//Constructeur de la classe Plaque, classe d�riv�e de la classe Appareil
	public Plaque() {
		super(new Vector<int[]>());
		typeAppareil = "Plaque";
	}

	//M�thode qui permet d'ajouter un nouveau triplet au vecteur Informations, 
	//� partir d'une dur�e d'utilisation et d'un niveau, selon si le type de plaque est Induction ou ELectrique
	public void ajoutDurPuiNiv(int[] durPuiNiv) {	
		durPuiNiv[1] = (int) (1220/(Math.pow(20000,1.5))*Math.pow((20000 + 5*(durPuiNiv[2]-1)*1000),1.5));
		super.ajoutInformation(durPuiNiv);
	}

	//M�thode qui calcule l'�nergie, � partir de la m�thode getEnergie de la classe Appareil
	public int getEnergie() {
		return super.getEnergie();
	}


}



