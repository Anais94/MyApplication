package com.example.anas.myapplication;

import java.util.Vector;

public class Four extends Appareil {

	//Constructeur de la classe Four
	public Four() {
		super(new Vector<int[]>());
		typeAppareil = "Four";
	}

	//M�thode qui permet d'ajouter un nouveau triplet au vecteur Informations
	//On prend en argument un triplet d'entiers : dur�e, grandeur nulle, temp�rature
	//On calcule la seconde composante du triplet (qui repr�sente la puissance) gr�ce � la temp�rature
	public void ajoutDurPuiTemp(int[] durPuiTemp) {
		durPuiTemp[1] = (int) (1.7*5.670373*0.00000001*Math.pow(durPuiTemp[2]+273,4.0));
		super.ajoutInformation(durPuiTemp);
	}

	//M�thode qui calcule l'�nergie, � partir de la m�thode getEnergie de la classe Appareil
	public int getEnergie() {
		return super.getEnergie();
	}

}
