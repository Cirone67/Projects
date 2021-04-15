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
    
    //creation des segment du terrain a partir des points saisie par l'utilsateur + completer en forme poylgonale si necessaire 
    public static ArrayList<TerrainSegment> creationSegment( ArrayList< TerrainPoints > P, Terrain T){
        int i,nbr;
        TerrainSegment STtempo;
        ArrayList<TerrainSegment> ST = new ArrayList<TerrainSegment>();
        nbr=P.size();
        System.out.println(P);
//        System.out.println("Il y a "+(nbr)+" segment(s) du terrain");
        for(i=0;i<(nbr-1);i++){
            STtempo= new TerrainSegment(P.get(i),P.get(i+1));
            ST.add(STtempo);
        }
        if(nbr>2){
            if(P.get(nbr-1).getPy()<= P.get(0).getPy()){ 
                STtempo = new TerrainSegment(P.get(nbr-1),P.get(0));
                ST.add(STtempo);
            }
        }
        System.out.println(ST);
        return ST;
    }
    
    
    //creation des segments pour completer le terrain en vue des triangles
    public static ArrayList<TerrainSegment> creationSegmentTriangle(ArrayList< TerrainPoints > P, ArrayList<TerrainPoints> Pr, boolean verifie){
        int nbrP, nbrPr, i, j,k ,sortie , nbrSTr;
        nbrP= P.size();
        nbrPr = Pr.size();
        ArrayList<TerrainSegment> STr = new ArrayList<TerrainSegment>();
        TerrainSegment STtempo;
        if(verifie==true){
            for(i=0;i<nbrP-1;i++){
                for(j=1;j<nbrPr;j++){
                    if((P.get(i).getPx()==Pr.get(j).getPx())&&(P.get(i).getPy()!=Pr.get(j).getPy())){
                        STtempo = new TerrainSegment(P.get(i),Pr.get(j));
                        STr.add(STtempo);
                    }
                }
            }
            nbrSTr = STr.size();
            for(i=0;i<nbrSTr-1;i++){
                System.out.println(i);
                if(STr.get(i).getA().getPx()==STr.get(i+1).getA().getPx()){
                    if(STr.get(i).getA().getPy()<STr.get(i+1).getA().getPy()){
                        STr.remove(i+1);
                        nbrSTr=STr.size();
                    }else{
                        STr.remove(i);
                        nbrSTr=STr.size();
                    }
                }
            }
            j=0;
            i=2;
            if(nbrPr!=0){
            sortie=0;
            while((P.get(j).getPy()!=P.get(j+1).getPy()|| sortie == 1)){
                j=j+1;    
                if(j==nbrPr){
                System.out.println("Votre terrain saisie presente une erreur, veuillez en saisir un autre : saissez au moins un poylgone");
                sortie= 1;
                }
            }
                while(i!=(nbrP-1)){
                     STtempo = new TerrainSegment(Pr.get(j),P.get(i));
                     STr.add(STtempo);
                     if(j==(nbrPr-1)){
                     i=nbrP-1;
                     }else{
                         j=j+1;
                         i=i+1;
                     }
                }
//                for(i=2;i<nbrP;i++){
//                    STtempo = new TerrainSegment(Pr.get(j),P.get(i));
//                    STr.add(STtempo);
//                        if(j!=nbrPr){
//                            j=j+1; 
//                        }
//                }
                
            }
        }
        if(verifie==false){
            i=nbrP-1;
            k=1;
            sortie=0;
            while((P.get(i).getPy()==P.get(i-1).getPy()|| sortie == 1)){
                k=k+1;
                i=i-1;
                if(i==0){
                    sortie=1;
                    System.out.println("Votre terrain saisie presente une erreur, veuillez en saisir un autre : saissez au moins un poylgone");
                }
            }
            i=2;
            for(i=2;i<nbrP-k;i++){
                STtempo = new TerrainSegment(P.get(0),P.get(i));
                STr.add(STtempo);
            }
        }
        System.out.println(STr);
        return STr;
    }
    //creaction des segments trinagles pures pour la fonction 
    
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
        angle =Math.atan2(B.getPy() - A.getPy(), B.getPx() - A.getPx()) - Math.atan2(B.getPy() - A.getPy(), B.getPx() - A.getPx()); 
        System.out.println("L'angle entre "+A+" et "+B+" est : "+angle);
        return angle;  
    }
    
    public static void main(String[] args){
      int nbrP,nbrST;
      double angleSegment;
      boolean verifieforme;
      Terrain T; 
      ArrayList <TerrainPoints> P = new ArrayList <TerrainPoints>();
      ArrayList <TerrainPoints> Pr = new ArrayList <TerrainPoints>();
      ArrayList <TerrainSegment> ST = new ArrayList <TerrainSegment>();
      ArrayList <TerrainSegment> STr = new ArrayList <TerrainSegment>();
      T = Terrain.SaisieTerrain();
      P = TerrainPoints.SaisiePoint(T);
      verifieforme=TerrainPoints.verifieForme(P);
      P = TerrainPoints.CompletePoint(P);
      ST = TerrainSegment.creationSegment(P,T);
      if(P.size()>3){//déja un triangle pas necessaire de creer de nouveau points et segments
        Pr = TerrainPoints.TrianglePoint(P , verifieforme);
        STr= TerrainSegment.creationSegmentTriangle(P, Pr, verifieforme);
      }
//      nbrP = P.size();
//      nbrST = ST.size();
//    System.out.println("vous avez saisi "+nbrP+" point(s) pour le terrain, et donc "+nbrST+ " Segment(s)");
    }
    
}
