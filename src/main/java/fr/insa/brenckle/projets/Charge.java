/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;

import java.util.ArrayList;

/**
 *
 * @author brenc
 */
public class Charge {
    
   private int id ;
   private int noeud ;
   private double angle; //Angle par rapport à l'horizon
   private double norme;
   protected static int nbrCharge = 1;
   
   Charge(int id, int noeud, double norme, double angle){
       this.id = id;
       this.noeud = noeud;
       this.angle = angle;
       this.norme = norme;
   }
   
      Charge(int noeud, double norme, double angle){
       this.id = nbrCharge;
       this.noeud = noeud;
       this.angle = angle;
       this.norme = norme;
       nbrCharge++;
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

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getNorme() {
        return norme;
    }

    public void setNorme(double norme) {
        this.norme = norme;
    }
    
    //Méthode de projection de charge pour le visuel
        public static double xprojection(double angle, double norme){
     return norme*Math.cos(angle);   
    }
    
    public static double yprojection(double angle, double norme){
     return  norme*Math.sin(angle); 
    }
    
    //Méthode qui trouve le noeud sur laquel s'applique la charge
    
    public static Noeud trouveNoeud(Charge charge, ArrayList<Noeud> noeud){
    Noeud temp = null;
        for(int i =0; i<noeud.size();i++){
         if(noeud.get(i).getIdNoeud()== charge.getNoeud()){
            temp = noeud.get(i); 
            }  
         }
        return temp;
        
    }
}
