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
 private Noeuds debut;
 private Noeuds fin;
 private TypeBarre type;
 
 //Constructeur de la classe Barre
    Barre(int id, Noeuds debut, Noeuds fin, TypeBarre type){
        this.id = id;
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
    public Noeuds getDebut() {
        return debut;
    }
    public void setDebut(Noeuds debut) {
        this.debut = debut;
    }
    public Noeuds getFin() {
        return fin;
    }
    public void setFin(Noeuds fin) {
        this.fin = fin;
    }
    public TypeBarre getType() {
        return type;
    }
    public void setType(TypeBarre type) {
        this.type = type;
    }
 


public double longeur(){
double longueur; 

return longueur;  
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