/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author brenc
 */
public class Treillis {
private ArrayList<TerrainTriangle> terrainTriangles;
private ArrayList<Barre> barres;
private ArrayList<Noeud> noeuds;
private ArrayList<TypeBarre> typeBarre;

private double TYmax;
private double TYmin;
private double TXmax; 
private double TXmin;
 
//liste de charge ? à la place de l'appliquer à un point ? Attribut en public ????

//Méthode qui détermine la zone constructible du Treillis
//TO DO :Programme calculant Tmin,Tmax,ect.....

    public Treillis() {
        this.barres = new ArrayList<Barre>();
        this.noeuds = new ArrayList<Noeud>();
        this.terrainTriangles = new ArrayList<TerrainTriangle>();
        this.typeBarre = new ArrayList<TypeBarre>();
    }

    public ArrayList<TerrainTriangle> getTerrainTriangles() {
        return terrainTriangles;
    }

    public ArrayList<Noeud> getNoeuds() {
        return noeuds;
    }

    public ArrayList<TypeBarre> getTypeBarre() {
        return typeBarre;
    }

    public ArrayList<Barre> getBarres() {
        return barres;
    }

    public void setBarres(ArrayList<Barre> barres) {
        this.barres = barres;
    }
    
public void supprime(TerrainTriangle tri){
    this.terrainTriangles.remove(tri);
}
public void ajoute(TerrainTriangle tri){
    this.terrainTriangles.add(tri);
}
public void supprime(Barre b){
 this.barres.remove(b);   
}
public void ajoute(Barre b){
 this.barres.add(b);
}
public void supprime(Noeud n){
 this.noeuds.remove(n);   
}
public void ajoute(Noeud n){
 this.noeuds.add(n);
}
public void supprime(TypeBarre tb){
 this.typeBarre.remove(tb);   
}
public void ajoute(TypeBarre tb){
 this.typeBarre.add(tb);
}  
public void setTYmax(double TYmax) {
        this.TYmax = TYmax;
    }
 public double getTYmin() {
   return TYmin;
}
 public void setTYmin(double TYmin) {
    this.TYmin = TYmin;
}
 public double getTXmax() {
    return TXmax;
}
public void setTXmax(double TXmax) {
    this.TXmax = TXmax;
}
 public double getTXmin() {
    return TXmin;
}
public void setTXmin(double TXmin){
    this.TXmin=TXmin;
}

}