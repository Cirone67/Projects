/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Guillaume R
 */
public abstract class Noeud extends Objet{
    
    private int id;
    protected static int nbrNoeud =1; 
    
    public Noeud(int id){
        this.id = id;
    }

    public int getIdNoeud(){
        return this.id;
    }
    
    public abstract double getAbs();
    public abstract double getOrd();
    
    @Override
    public double distancePoint(double abs, double ord) {
        double x = this.getAbs() - abs;
        double y = this.getOrd() - ord;
        return Math.sqrt(x*x+y*y);
    }    
    
    public void draw(GraphicsContext gc){  //TO DO différencier noeuds simples et appuis
        int r = 3;
        gc.setStroke(Color.web("#40b6ff"));
        gc.strokeOval(this.getAbs()- r, this.getOrd() - r, r, r);
        gc.fillOval(this.getAbs() - r, this.getOrd() - r, r, r);
    }
    public void drawSelection(GraphicsContext gc){  
        int r = 3;
        gc.setStroke(couleurSelection); gc.setFill(couleurSelection);
        gc.strokeOval(this.getAbs()- r, this.getOrd() - r, r, r);
        gc.fillOval(this.getAbs() - r, this.getOrd() - r, r, r);
    }
    
    
    /*
    
    
    public abstract void setAbs(double abs);
    public abstract void setOrd(double ord);
*/
}


    

