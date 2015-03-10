package com.example.anas.myapplication;

import java.util.*;

public class Mixeur extends Appareil {

	//Constructeur de la classe Mixeur, classe d�riv�e de la classe Appareil
	public Mixeur(Vector<int[]> durPui) {
		super(durPui);
		for (int i=0 ; i<getInformations().size() ;i++ ) {
			getInformations().get(i)[1] = 350;
		}
		typeAppareil = "Mixeur";
	}

	//M�thode qui calcule l'�nergie, � partir de la m�thode getEnergie de la classe Appareil
	public int getEnergieMixeur() {
		return super.getEnergie();
	}

}

