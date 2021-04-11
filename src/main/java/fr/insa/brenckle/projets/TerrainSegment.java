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
import java.util.ArrayList;
import java.util.*;

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
    public static ArrayList<TerrainSegment> creationSegment( ArrayList< TerrainPoints > P){
        int i;
        TerrainSegment STtempo;
        int nbr = P.size();
        ArrayList<TerrainSegment> ST = new ArrayList<TerrainSegment>();
        System.out.println("Il y a "+(nbr-1)+" segment(s) de saisi");
        for(i=0;i<(nbr-1);i++){
            STtempo= new TerrainSegment(P.get(i),P.get(i+1));
            ST.add(STtempo);
        }
        System.out.println(ST);
        return ST;
    }
    
    //creation des segments pour completer le triangle 
    public static TerrainSegment SegmentTriangle( TerrainPoints PT1, TerrainPoints PT3 ){
        //droite non reelle du terrain a coder definir quand le terrain peut etre en triangle 
        TerrainSegment AST;
        AST= new TerrainSegment(PT1,PT3);
        return AST;
    }
    
   //calcul d'un angle entre deux droites adjacentes renvoie un nombre positif si l'angle est superieur a pi 
    public static double angle(TerrainSegment ST1, TerrainSegment ST2 ){
        double angle;
        TerrainPoints A = ST1.getA();
        TerrainPoints B = ST1.getB();
        TerrainPoints C = ST2.getB();
        angle =Math.atan2(B.getPy() - A.getPy(), B.getPx() - A.getPx()) - Math.atan2(C.getPy() - B.getPy(), C.getPx() - B.getPx()); 
        System.out.println("L'angle entre "+ST1+" et "+ST2+" est : "+angle);
        return angle;
    }
    /*public static void main(String[] args){
      int nbrP,nbrST;
      double angle;
      Terrain T;
      ArrayList< TerrainPoints> P = new ArrayList< TerrainPoints>();
      ArrayList<TerrainSegment> ST = new ArrayList<TerrainSegment>();
      T = Terrain.SaisieTerrain();
      P = TerrainPoints.SaisiePoint(T);
      ST = TerrainSegment.creationSegment(P);
      nbrP = P.size();
      nbrST = ST.size();
      System.out.println("vous avez saisi "+nbrP+" point(s) pour le terrain, et donc "+nbrST+ " Segment(s)");
      if(nbrST>1){
        angle = angle(ST.get(0),ST.get(1));
      }
    }*/
    
}
