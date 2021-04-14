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
    public static ArrayList<TerrainSegment> creationSegment( ArrayList< TerrainPoints > P, Terrain T){
        int i;
        TerrainSegment STtempo;
        double yminPT;
        double xminPT;
        double xmaxPT;
        yminPT = P.get(0).getPy();
        xminPT = P.get(0).getPx();
        xmaxPT = P.get(0).getPx();
        int nbr = P.size();
        ArrayList<TerrainSegment> ST = new ArrayList<TerrainSegment>();
        for(i=1;i<nbr;i++){
            if(yminPT>(P.get(i).getPy())){
                yminPT=P.get(i).getPy();
            }
            if(xminPT>(P.get(i).getPy())){
                xminPT=P.get(i).getPy();
            }
            if(xmaxPT<(P.get(i).getPx())){
                xmaxPT=P.get(i).getPx();
            }
        }
            TerrainPoints PTa, PTb, PTc;
            PTa= new TerrainPoints(xmaxPT,yminPT);
            P.add(PTa);
            PTb = new TerrainPoints(xminPT,yminPT);
            P.add(PTb);
//            if(P.get(0).getPy()!=yminPT){
//                PTc = new TerrainPoints(xminPT,P.get(1).getPy());
//                P.add(0,PTc);
//            }
        nbr=P.size();
        System.out.println(P);
        System.out.println("Il y a "+(nbr)+" segment(s) du terrain");
        for(i=0;i<(nbr-1);i++){
            STtempo= new TerrainSegment(P.get(i),P.get(i+1));
            ST.add(STtempo);
        }
        if(P.get(0).getPy()!=yminPT){
            STtempo = new TerrainSegment(P.get(nbr-1),P.get(0));
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
    
    
   //calcul d'un angle entre deux segment adjacents renvoie un nombre positif si l'angle est inferieur a pi 
    public static double angleSegment(TerrainSegment ST1, TerrainSegment ST2 ){
        double angle;
        TerrainPoints A = ST1.getA();
        TerrainPoints B = ST1.getB();
        TerrainPoints C = ST2.getB();
        angle =Math.atan2(B.getPy() - A.getPy(), B.getPx() - A.getPx()) - Math.atan2(C.getPy() - B.getPy(), C.getPx() - B.getPx()); 
        System.out.println("L'angle entre "+ST1+" et "+ST2+" est : "+angle);
        return angle;
    }
       
    //calcul d'un angle entre deux points renvoie un nombre positif si l'angle est inferieur a pi
    public static double anglepoint(TerrainPoints A, TerrainPoints B){
      double angle;
        angle =Math.atan2(B.getPy() - A.getPy(), B.getPx() - A.getPx()) - Math.atan2(C.getPy() - B.getPy(), C.getPx() - B.getPx()); 
        System.out.println("L'angle entre "+A+" et "+B+" est : "+angle);
        return angle;  
    }
    
    public static void main(String[] args){
      int nbrP,nbrST;
      double angleSegment;
      Terrain T;
      ArrayList <TerrainPoints> P = new ArrayList <TerrainPoints>();
      ArrayList <TerrainSegment> ST = new ArrayList <TerrainSegment>();
      T = Terrain.SaisieTerrain();
      P = TerrainPoints.SaisiePoint(T);
      ST = TerrainSegment.creationSegment(P,T);
//      nbrP = P.size();
//      nbrST = ST.size();
//    System.out.println("vous avez saisi "+nbrP+" point(s) pour le terrain, et donc "+nbrST+ " Segment(s)");
    }
    
}
