/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author brenc
 */
public class Charge extends Objet{
    
   private int id ;
   private int noeud ;
   private double angle; //Angle par rapport à l'horizon
   private double norme;
   private Noeud n; //c'est mon anniv je fais ce que je veux
   protected static int nbrCharge = 1;
   
   public Charge(int id, int noeud, double norme, double angle){
       this.id = id;
       this.noeud = noeud;
       this.angle = angle;
       this.norme = norme;
       this.n = null;
   }
   
     public Charge(int noeud, double norme, double angle){
       this.id = nbrCharge;
       this.noeud = noeud;
       this.angle = angle;
       this.norme = norme;
       this.n = null;
       nbrCharge++;
   }
      public Charge(Noeud noeud, double norme, double angle){
       this.id = nbrCharge;
       this.noeud = noeud.getIdNoeud();
       this.angle = angle;
       this.norme = norme;
       this.n = noeud;  
       nbrCharge++;
   }
    
     public void draw(GraphicsContext gc){
         gc.setStroke(this.getCouleur());
         int r = 10;
         double pi = Math.PI;
         
         double x = this.n.getAbs();                       //création segment
         double y = this.n.getOrd();
         double x2 = x + Math.cos(this.angle)*this.norme;
         double y2 = y + Math.sin(this.angle)*this.norme;
         
         double x1flèche = (0.3*x + 0.7*x2); //A FAIRE flèche
         
         double [] abs = new double [5]; double [] ord = new double [5];
         abs[0] = x; ord[0] = y;
         abs[1] = x2; ord[1] = y2;
         abs[2] = x2 - r*Math.sin(pi/4); ord[2] = y2 - r*Math.sin(pi/4);
         abs[3] = abs[1]; ord[3] = ord[1];
         abs[4] = x2 - r*Math.sin(pi/4); ord[4] = y2 + r*Math.sin(pi/4);
         gc.setLineWidth(0.5);
         gc.strokePolyline(abs, ord, 5);
     }
     
    public void drawSelection(GraphicsContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    public double distancePoint(double abs, double ord) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
