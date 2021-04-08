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
    
    public String toString(){
        return ("["+A+";"+B+"]");
    }
    //constructeur Segment
    public TerrainSegment (TerrainPoints PT1, TerrainPoints PT2){
       this.A=PT1;
       this.B=PT2;
    }
    //
    
    //calcul de longueur du segment
    public double longueurT(TerrainPoints A, TerrainPoints B){
        double l,x=4;
        l= Math.sqrt(Math.pow(Math.abs(A.getPx()-B.getPx()),2)+Math.pow(Math.abs(A.getPy()-B.getPy()),2)); 
        return l;
    }
    public void creationSegment(){
        int i,nbr,j;
        TerrainPoints PT[];
        TerrainSegment ST[];
        System.out.println("Saisir le nombre de point que vous voulez rentrer pour le terrain");
        nbr = Lire.i();
        PT = new TerrainPoints[nbr];
        ST = new TerrainSegment[nbr-1];
        for(i=0;i<(nbr);i++){
            PT[i] = TerrainPoints.SaisiePoint();
        }
        for(i=0;i<nbr;i++){
        //System.out.println(PT[i]);//PT pour PointTerrain
        }
        System.out.println("Il y a "+(nbr-1)+" Segments dans le terrain");
        for(i=0;i<(nbr-1);i++){
            ST[i] = new TerrainSegment(PT[i],PT[i+1]);
            //System.out.println(ST[i]);
        }
       
    }
}
