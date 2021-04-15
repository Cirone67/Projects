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
public ArrayList<Terrain> terrains;
public ArrayList<Barre> barres;
public ArrayList<Noeud> noeuds;
public ArrayList<TypeBarre> typeBarre;
//liste de charge ? à la place de l'appliquer à un point ? Attribut en public ????
//Hello guillaume tu es vivant ?
public Treillis(){
    this.barres = new ArrayList<Barre>();
    this.noeuds = new ArrayList<Noeud>();
    this.terrains = new ArrayList<Terrain>();
    this.typeBarre = new ArrayList<TypeBarre>();
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
public void supprime(TypeBarre tb){
 this.typeBarre.remove(tb);   
}
public void ajoute(TypeBarre tb){
 this.typeBarre.add(tb);
}
}