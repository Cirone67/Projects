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
public class Noeud {
    
    private int id;

    
    public Noeud(int id){
        this.id = id;
    }
    public Noeud(){
        this(0);
    }

    public int getIdNoeud(){
        return this.id;
    }
    
}


    

