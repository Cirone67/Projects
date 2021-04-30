/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;

import javafx.scene.paint.Color;

/**
 *
 * @author Guillaume R
 */
public abstract class Objet {
    
    private Color couleur;
    
    public Objet(Color couleur){
        this.couleur = couleur;
    }
    
    public Objet(){
        this.couleur = Color.BLACK;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
    
    public abstract double distancePoint(double abs, double ord);
}
