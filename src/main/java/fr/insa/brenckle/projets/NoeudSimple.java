/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;

/**
 *
 * @author Guillaume R
 */
public class NoeudSimple extends Noeud{
    
    private double abs;
    private double ord;
    
    public NoeudSimple(double abs, double ord){
        super(1);
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
    public NoeudSimple(){
        super(0);
        this.abs = 0;
        this.ord = 0;
        nbrNoeud++;
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
