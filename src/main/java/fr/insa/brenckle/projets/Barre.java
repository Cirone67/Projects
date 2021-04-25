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
public class Barre {
 private int id;
 private Noeud debut;
 private Noeud fin;
 private TypeBarre type;
 private static int nbrBarre = 1;
 
    //Constructeur de la classe Barre avec la méthode du compteur
    Barre( TypeBarre type, Noeud debut, Noeud fin){
        this.id = nbrBarre++;
        this.debut = debut;
        this.fin = fin;
        this.type = type;
    }
    
    //Méthode spéciale import stockage
    Barre(int id, int idTypeBarre, int idNoeudDebut, int idNoeudFin,ArrayList<Noeud> noeud,ArrayList<TypeBarre> typeBarre){ 
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
      return "La barre n°"+ this.id + " de type "+ this.type + "commence par le Noeud"+ this.debut+ " et fnit par le Noeud"+ this.fin;
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

//Méthode qui donne l'angle entre deux segment/Barre
public double angle(Barre Barre2){
    //Calcul du produit scalaire
    double scalaire = ((this.fin.getAbs()-this.debut.getAbs())*(Barre2.fin.getAbs()-Barre2.debut.getAbs())+(this.fin.getOrd()-this.debut.getOrd())*(Barre2.fin.getOrd()-Barre2.debut.getOrd()));
    return (Math.acos(scalaire/(this.longueur()*Barre2.longueur()))%(2*Math.PI));  
}
/*
public static void main(String[] args){
Barre a = new Barre(new Noeud (1),new Noeud(2));
Barre b = new Barre(new Noeud (1),new Noeud(2));
System.out.println(a.id);
System.out.println(b.id);
}
 */  
    
}