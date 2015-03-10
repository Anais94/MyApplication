package com.example.anas.myapplication;

import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.view.animation.AccelerateInterpolator;

import android.view.View.OnKeyListener;
/**
 * Created by Anaïs on 06/01/2015.
 */
public class Slider {
    boolean isOpen;
    LinearLayout toHide;
    final static int SPEED = 0;

    public Slider (boolean t, LinearLayout l){
        isOpen = t;
        toHide = l;
    }

    //Méthode utilisée pour ouvrir le menu dans activity_critere_et_choix.xml
    //Elle retourne true si le menu est désormais ouvert

    /* Listener pour l'animation de fermeture du menu */
    Animation.AnimationListener closeListener = new Animation.AnimationListener() {
        public void onAnimationEnd(Animation animation) {
            //On dissimule le menu
            toHide.setVisibility(View.GONE);
        }

        public void onAnimationRepeat(Animation animation) {

        }

        public void onAnimationStart(Animation animation) {

        }
    };

/* Listener pour l'animation d'ouverture du menu */
    Animation.AnimationListener openListener = new Animation.AnimationListener() {
        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
            //On affiche le menu
            toHide.setVisibility(View.VISIBLE);
        }
    };
    //Méthode utilisée pour ouvrir le menu dans activity_critere_et_choix.xml
    //Elle retourne true si le menu est désormais ouvert
    public boolean toggle() {
        //Animation de transition
        TranslateAnimation animation =null;

        //On passe de ouvert à fermé (ou vice versa)
        isOpen=!isOpen;

        //Si le menu est déjà ouvert
        if (isOpen) {
            //Animation de transition du bas vers le haut
            animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, - toHide.getHeight());
            animation.setAnimationListener(openListener);
            }
       else {
            //Animation de transition du haut vers le bas
            animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, - toHide.getHeight());
            animation.setAnimationListener(closeListener);
        }

        //Ondétermine la durée de l'animation
        animation.setDuration(SPEED);
        //On ajoute un effet d'accélération
        animation.setInterpolator(new AccelerateInterpolator());
        //Enfin on lance l'animation
        toHide.startAnimation(animation);

        return isOpen;
    }

}
