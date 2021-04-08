/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;

/**
 *
 * @author MAURY Robin
 */

public  class TerrainSegment extends Terrain{
    private TerrainPoints A;
    private TerrainPoints B;
    //get/set du Segment
    public TerrainPoints getA(){
        return A;
    }
    public TerrainPoints getB(){
        return B;
    }
    public void setA(TerrainPoints A){
        this.A = A;
    }
    public void setB(TerrainPoints B){
        this.B=B;
    }
    
    public String ToString(){
        return ("Le segment ["+A+";"+B+"]");
    }
    
    //calcul de longueur du segment
    public double longueurT(TerrainPoints A, TerrainPoints B){
        double l,x=4;
        l= Math.sqrt(Math.pow(Math.abs(A.getPx()-B.getPx()),2)+Math.pow(Math.abs(A.getPy()-B.getPy()),2)); 
        return l;
    }
}
