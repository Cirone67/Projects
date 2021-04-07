/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;

/**
 *
 * @author brenc
 */
public class Charge {
    
   private int id ;
   private int noeud ;
   private double norme;
   
   Charge(int id, int noeud, double norme){
       this.id = id;
       this.norme = norme;
       this.noeud = noeud;
   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoeud() {
        return noeud;
    }

    public void setNoeud(int noeud) {
        this.noeud = noeud;
    }

    public double getNorme() {
        return norme;
    }

    public void setNorme(double norme) {
        this.norme = norme;
    }
   
   
  // public decompose(){
    //   return;
  // }
         
}
