/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.StrokeLineCap;

/**
 *
 * @author brenc
 */
public class Barre extends Objet {
 private int id;
 private Noeud debut;
 private Noeud fin;
 private TypeBarre type;
 private static int nbrBarre = 1;
 
    //Constructeur de la classe Barre avec la méthode du compteur
    public Barre( TypeBarre type, Noeud debut, Noeud fin){
        this.id = nbrBarre++;
        this.debut = debut;
        this.fin = fin;
        this.type = type;
    }
    
    //Méthode spéciale import stockage
    public Barre(int id, int idTypeBarre, int idNoeudDebut, int idNoeudFin,ArrayList<Noeud> noeud,ArrayList<TypeBarre> typeBarre){ 
        this.id = id;
        //Attribut le noeud de début et de fin
        for(int i=0; i< noeud.size();i++){
            if(idNoeudDebut == noeud.get(i).getIdNoeud()){
             this.debut = noeud.get(i);   
            }
           
        }
        for(int j=0; j< noeud.size();j++){
            if(idNoeudFin == noeud.get(j).getIdNoeud()){
             this.fin = noeud.get(j);
            }
           
        }
        
        for(int i=0; i< typeBarre.size();i++){
            if(idTypeBarre == typeBarre.get(i).getCategorie()){
             this.type = typeBarre.get(i);   
            }
           
        } 
        nbrBarre++;
    }
        
        
        
        
    //Constructeur à supprimer à la fin ----------------------------------------------------------------------------
       Barre(Noeud debut, Noeud fin){
        this.id = nbrBarre++;
        this.debut = debut;
        this.fin = fin;
    } //---------------------------------------------------------------
    
    //Get et Set de la classe Barre
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Noeud getDebut() {
        return debut;
    }
    public void setDebut(Noeud debut) {
        this.debut = debut;
    }
    public Noeud getFin() {
        return fin;
    }
    public void setFin(Noeud fin) {
        this.fin = fin;
    }
    public TypeBarre getType() {
        return type;
    }
    public void setType(TypeBarre type) {
        this.type = type;
    }
    
    public String toString(){
      return "La barre n°"+ this.id + " de type "+ this.type + "possède Noeud debut : "+ this.debut+ ",Noeud fin : "+ this.fin;
    }
 

//Méthode qui calcule la longueur d'un segment
public double longueur(){
double longueur = Math.sqrt(Math.pow(this.fin.getAbs()-this.debut.getAbs(),2)+ Math.pow(this.fin.getOrd()-this.debut.getOrd(),2));
return longueur;
}

//Calcul du prix de la barre
public double prix(){
    return this.longueur()*this.type.getCout();
}


//Calcul du prix total du treillis
public static double PrixTreillis(ArrayList<Barre> B){
    double prixT=0;
    int i;
    for(i=0;i<B.size();i++){
        prixT=prixT+B.get(i).prix();
    }
    return Math.round(prixT*100.0)/100.0;
}

//Méthode autour des restrictions de longueur renvoie false si il y a un probleme de longueur 
public boolean RestrictionL(){
    TypeBarre TBarre = this.getType();
    double longB = this.longueur();
    boolean verifie=true;
        if(longB<TBarre.getLongmin()){
            verifie = false;
        }
        if(longB>TBarre.getLongmax()){
            verifie = false;
        }
        if(verifie==false){
//            System.out.println("Votre barre n'est pas de bonne longueur");
        }
        return verifie;
}

//Méthode qui donne l'angle entre deux segment/Barre
public double angle(Barre Barre2){
    //Calcul du produit scalaire
    double scalaire = ((this.fin.getAbs()-this.debut.getAbs())*(Barre2.fin.getAbs()-Barre2.debut.getAbs())+(this.fin.getOrd()-this.debut.getOrd())*(Barre2.fin.getOrd()-Barre2.debut.getOrd()));
    return (Math.acos(scalaire/(this.longueur()*Barre2.longueur()))%(Math.PI));  
}

    public void draw(GraphicsContext context){
        context.setStroke(this.getCouleur());
        context.setLineWidth(2);
        context.setLineCap(StrokeLineCap.ROUND);
        context.strokeLine(this.getDebut().getAbs(), this.getDebut().getOrd(), this.getFin().getAbs(), this.getFin().getOrd());
    }
    public void drawSelection (GraphicsContext context){
        context.setStroke(couleurSelection);
        context.setLineWidth(2); context.setLineCap(StrokeLineCap.ROUND);
        context.strokeLine(this.getDebut().getAbs(), this.getDebut().getOrd(), this.getFin().getAbs(), this.getFin().getOrd());
    }
    
/*
public static void main(String[] args){
Barre a = new Barre(new Noeud (1),new Noeud(2));
Barre b = new Barre(new Noeud (1),new Noeud(2));
System.out.println(a.id);
System.out.println(b.id);
}
 */  

    public double distancePoint(double abs, double ord) {
        double Ax = this.debut.getAbs();
        double Ay = this.debut.getOrd();
        double Bx = this.fin.getAbs();
        double By = this.fin.getOrd();
        double Cx = abs; double Cy = ord;
        double r = ((Cx-Ax)*(Bx-Ax)+(Cy-Ay)*(By-Ay))/(Math.pow(Math.sqrt((Bx-Ax)*(Bx-Ax)+(By-Ay)*(By-Ay)), 2));
        if (r<0){
            return this.debut.distancePoint(abs, ord);
        } else if (r>1){
            return this.fin.distancePoint(abs, ord);
        } else {
            Noeud n = new NoeudSimple(Ax + r*(Bx-Ax), Ay + r*(By-Ay)); 
            return n.distancePoint(abs, ord);
        }
    }
    
}