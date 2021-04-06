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
private List<Terrain> terrains;
private List<Barre> barres;
private List<Noeud> noeuds;
//liste de charge ? à la place de l'appliquer à un point ?
//Hello guillaume tu es vivant ?
public Treillis(){
    this.barres = new ArrayList<Barre>();
    this.noeuds = new ArrayList<Noeud>();
    this.terrains = new ArrayList<Terrain>();
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
public void supprime(Terrain t){
 this.terrains.remove(t);   
}
public void ajoute(Terrain t){
 this.terrains.add(t);
}
}
