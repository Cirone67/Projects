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

public  class TerrainSegment {
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
    
    //toString
    public String toString(){
        return ("["+A+";"+B+"]");
    }
    
    //constructeur Segment
    public TerrainSegment (TerrainPoints PT1, TerrainPoints PT2){
       this.A=PT1;
       this.B=PT2;
    }
    
    //calcul de longueur du segment
    public static double longueurT(TerrainPoints A, TerrainPoints B){
        double l;
        l= Math.sqrt(Math.pow(A.getPx()-B.getPx(),2)+Math.pow(A.getPy()-B.getPy(),2)); 
        return l;
    }
    
    //creation des segment du terrain a partir dun tableau des points 
    public static TerrainSegment[] creationSegment(TerrainPoints PT[], int nbr){
        int i;
        TerrainSegment ST[];
        ST = new TerrainSegment [nbr-1];
        System.out.println("Il y a "+(nbr-1)+" segment(s) de saisi");
        for(i=0;i<(nbr-1);i++){
        ST[i]= new TerrainSegment(PT[i],PT[i+1]);
        //System.out.println(ST[i]);
        }
        return ST;
    }
    
    //creation des segments pour completer le triangle 
    public static TerrainSegment SegmentTriangle( TerrainPoints PT1, Terrain ){
        //droite non reelle du terrain a coder definir quand le terrain peut etre en triangle 
    }
    
   //calcul d'un angle entre deux droites adjacentes
    public static double angle(TerrainSegment ST1, TerrainSegment ST2 ){
        double angle;
        TerrainPoints A = ST1.getA();
        TerrainsPoints B = ST1.getB();
        TerrainsPoints C = ST2.getB();
        angle =Math.atan2(B.getPy() - A.getPy(), B.getPx() - A.getPx()) - Math.atan2(C.getPy() - B.getPy(), C.getPx() - B.getPx()); 
        return angle;
    }
    /*public static void main(String[] args){
      int nbr;
      Terrain T;
      TerrainPoints PT[];
      TerrainSegment ST[];
      T = Terrain.SaisieTerrain();
      System.out.println("Saisir le niombre de point que vous voulez dans votre terrain, attention aux limites saisi pour votre terrain");
      nbr = Lire.i();
      PT = new TerrainPoints[nbr];
      ST = new TerrainSegment[nbr-1];
      PT = TerrainPoints.SaisiePoint(T, nbr);
      ST = TerrainSegment.creationSegment(PT , nbr);
    }*/
}
