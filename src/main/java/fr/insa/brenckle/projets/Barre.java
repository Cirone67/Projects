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
public class Barre {
 private int id;
 private Noeud debut;
 private Noeud fin;
 private TypeBarre type;
 private static int nbrBarre = 0;
 
 //Constructeur de la classe Barre avec la méthode du compteur
    Barre(Noeud debut, Noeud fin, TypeBarre type){
        this.id = nbrBarre++;
        this.debut = debut;
        this.fin = fin;
        this.type = type;
    }
    
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
    public Noeuds getFin() {
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
 

//Méthode qui calcule la longueur d'un segment
public double longueur(){
double longueur = Math.sqrt(Math.pow(this.fin.x-this.debut.x,2)+ Math.pow(this.fin.y-this.debut.y,2));
return longueur;
}
//Méthode qui donne l'angle entre deux segment/Barre
public double angle(Barre Barre2){
    //Calcul du produit scalaire
    double scalaire = ((this.fin.x-this.debut.x)*(Barre2.fin.x-Barre2.debut.x)+(this.fin.y-this.debut.y)*(Barre2.fin.y-Barre2.debut.y));
    return (Math.acos(scalaire/(this.longueur()*Barre2.longueur()))%(2*Math.PI));   
}


}


final class TypeBarre {
private int categorie;
private double cout;
private double longmax;
private double longmin;
private double restension;
private double rescompression;

TypeBarre(int categorie, double cout, double longmax, double longmin, double restension, double rescompression){
 this.categorie = categorie;
 this.cout = cout;
 this.longmax = longmax;
 this.longmin = longmin;
 this.rescompression = rescompression;
 this.restension = restension;
 
    
}
//TO DO catologue de barre

}