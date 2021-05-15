/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;

import static fr.insa.brenckle.projets.Objet.couleurSelection;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Guillaume R
 */
public class NoeudSimple extends Noeud{
    
    private double abs;
    private double ord;
    
    public NoeudSimple(double abs, double ord){
        super(nbrNoeud);
        this.abs = abs;
        this.ord = ord;
        nbrNoeud++;
    }
        public NoeudSimple(int id, double abs, double ord){
        super(id);
        this.abs = abs;
        this.ord = ord;
        nbrNoeud++;
    }
    
    public String toString(){
        return "("+this.abs+";"+this.ord+")";
    }
    
    public void draw(GraphicsContext gc){  
        int r = 4;
        gc.setLineWidth(1);
        gc.setStroke(this.getCouleur()); gc.setFill(this.getCouleur());
        gc.strokeOval(this.getAbs()- r, this.getOrd() - r, r, r);
        gc.fillOval(this.getAbs() - r, this.getOrd() - r, r, r);
    }
    public void drawSelection(GraphicsContext gc){  
        int r = 4;
        gc.setLineWidth(1);
        gc.setStroke(couleurSelection); gc.setFill(couleurSelection);
        gc.strokeOval(this.getAbs()- r, this.getOrd() - r, r, r);
        gc.fillOval(this.getAbs() - r, this.getOrd() - r, r, r);
    }    
       
    public double getAbs(){
        return this.abs;
    }
    
    public double getOrd(){
        return this.ord;
    }

    public void setAbs(double abs) {
        this.abs = abs;
    }

    public void setOrd(double ord) {
        this.ord = ord;
    }

}
