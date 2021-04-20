/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;

/**
 *
 * @author Guillaume R
 */
public abstract class Noeud {
    
    private int id;
    protected static int nbrNoeud =1; 
    
    public Noeud(int id){
        this.id = id;
    }

    public int getIdNoeud(){
        return this.id;
    }
    
    public abstract double getAbs();
    public abstract double getOrd();
}


    

