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

public class Terrain {
    private double Ymax;
    private double Ymin; 
    private double Xmax; 
    private double Xmin; 
    
    
    
    //tout les get/set pour la superclasse Terrain
    public double getYmax(){
        return Ymax;
    }
    public double getYmin(){
        return Ymin;
    }
    public double getXmax(){
        return Xmax;
    }
    public double getXmin(){
        return Xmin;
    }
    
    public void setYmax(double Ymax){
        this.Ymax = Ymax;
    }
    public void setYmin(double Ymin){
        this.Ymin = Ymin;
    }
    public void setXmax(double Xmax){
        this.Xmax = Xmax;
    }
    public void setXmin(double Xmin){
        this.Xmin= Xmin;
    }
    
    
    //constructeur Terrain
    public Terrain (double xmin, double xmax ,double ymin, double ymax){
        this.Xmax=xmax;
        this.Xmin=xmin;
        this.Ymax=ymax;
        this.Ymin=ymin; 
    }
    
    public String toString(){
        return ("Le terrain a pour Xmin :"+getXmin()+", Xmax : "+getXmax()+" ,Ymin : "+getYmin()+" ,Ymax : "+ getYmax());
    }
    //regarde si un noeud simple est dans le terrain 
     public static boolean  noeudTerrain(ArrayList<TerrainTriangle> TT , NoeudSimple N){
         int i, j=0, nbrTT;
         nbrTT = TT.size();
         double PX, PY,angle;
         boolean app=false;
         
             PX=TT.get(0).getC1().getA().getPx();
             PY=TT.get(0).getC1().getA().getPy();
             angle =Math.atan2(N.getOrd() - PY, N.getAbs() - PX) - Math.atan2(N.getOrd() - PY, N.getAbs() - PX); 
             if(angle>0){
                j=j+1;
                PX=TT.get(0).getC2().getA().getPx();
                PY=TT.get(0).getC2().getA().getPy();
                angle =Math.atan2(N.getOrd() - PY, N.getAbs() - PX) - Math.atan2(N.getOrd() - PY, N.getAbs() - PX); 
                    if(angle>0){
                        j=j+1;
                        PX=TT.get(0).getC3().getB().getPx();
                        PY=TT.get(0).getC3().getB().getPy();
                        angle =Math.atan2(N.getOrd() - PY, N.getAbs() - PX) - Math.atan2(N.getOrd() - PY, N.getAbs() - PX);
                            if(angle>0){
                               j=j+1;
                               app = true;
                               //System.out.println("le point appartient au triangle 0");
                            }else{
                                j=0;
                            }
                    }
             }
       if(j!=3){
         for(i=1;i<nbrTT-1;i++){
             j=0;
             PX=TT.get(i).getC1().getA().getPx();
             PY=TT.get(i).getC1().getA().getPy();
             angle =Math.atan2(N.getOrd() - PY, N.getAbs() - PX) - Math.atan2(N.getOrd() - PY, N.getAbs() - PX); 
             if(angle>0){
                j=j+1;
                PX=TT.get(i).getC2().getB().getPx();
                PY=TT.get(i).getC2().getB().getPy();
                angle =Math.atan2(N.getOrd() - PY, N.getAbs() - PX) - Math.atan2(N.getOrd() - PY, N.getAbs() - PX); 
                    if(angle>0){
                        j=j+1;
                        PX=TT.get(i).getC3().getA().getPx();
                        PY=TT.get(i).getC3().getA().getPy();
                        angle =Math.atan2(N.getOrd() - PY, N.getAbs() - PX) - Math.atan2(N.getOrd() - PY, N.getAbs() - PX);
                            if(angle>0){
                               j=j+1;
                               app = true;
                               //System.out.println("le point appartient au triangle "+i);
                               i=nbrTT-1;
                            }
                    }
             }
        }
      }
      if(j!=3){
         PX=TT.get(nbrTT-1).getC1().getA().getPx();
             PY=TT.get(nbrTT-1).getC1().getA().getPy();
             angle =Math.atan2(N.getOrd() - PY, N.getAbs() - PX) - Math.atan2(N.getOrd() - PY, N.getAbs() - PX); 
             if(angle>0){
                j=j+1;
                PX=TT.get(nbrTT-1).getC2().getA().getPx();
                PY=TT.get(nbrTT-1).getC2().getA().getPy();
                angle =Math.atan2(N.getOrd() - PY, N.getAbs() - PX) - Math.atan2(N.getOrd() - PY, N.getAbs() - PX); 
                    if(angle>0){
                        j=j+1;
                        PX=TT.get(nbrTT-1).getC3().getA().getPx();
                        PY=TT.get(nbrTT-1).getC3().getA().getPy();
                        angle =Math.atan2(N.getOrd() - PY, N.getAbs() - PX) - Math.atan2(N.getOrd() - PY, N.getAbs() - PX);
                            if(angle>0){
                               app = true;
                               //System.out.println("le point appartient au triangle "+i);
                            }else{
                                j=0;
                            }
                    }
             } 
      }
      return app;
     }
     

    //creer un terrain avec une liste de point de terrain
    public static Terrain SaisieTerrain(){
        double xmin,ymin,xmax,ymax;
        Terrain T;
        System.out.println("Saisir le xmin de votre Terrain  puis le xmax (abscisse)");
        xmin=Lire.d();
        xmax = Lire.d();
        System.out.println("Saisir le ymin de votre Terrain puis le ymax (ordonnee)");
        ymin=Lire.d();
        ymax=Lire.d();
        T = new Terrain(xmin,xmax,ymin,ymax);
        return T;
    }
//          double xmin, xmax, ymin, ymax;
//          Terrain T;
//          TerrainPoints PTtempo;
//          int nbr ; 
//          nbr=PT.size();
//          PTtempo=PT.get(0);
//          xmin=PTtempo.getPx();
//          xmax=PTtempo.getPx();
//          ymin=PTtempo.getPy();
//          ymax=PTtempo.getPy();
//          for(int i=1; i<nbr ; i++){
//              PTtempo=PT.get(i);
//              if(xmin>PTtempo.getPx()){
//                  xmin=PTtempo.getPx();
//              }
//              if(xmax<PTtempo.getPx()){
//                  xmax=PTtempo.getPx();
//              }
//              if(ymin>PTtempo.getPy()){
//                  ymin=PTtempo.getPy();
//              }
//              if(ymax<PTtempo.getPy()){
//                  ymax=PTtempo.getPy();
//              }
//          }
//          T= new Terrain(xmin , xmax, ymin ,ymax);
//          return T; 
//    }
//    public static void main(String[] args) {
//        int nbrP, nbrST;
//        double angleSegment;
//        boolean verifieforme;
//        Terrain T;
//        ArrayList<TerrainPoints> P = new ArrayList<TerrainPoints>();
//        ArrayList<TerrainPoints> Pr = new ArrayList<TerrainPoints>();
//        ArrayList<TerrainSegment> ST = new ArrayList<TerrainSegment>();
//        ArrayList<TerrainSegment> STr = new ArrayList<TerrainSegment>();
//        ArrayList<TerrainTriangle> TT = new ArrayList<TerrainTriangle>();
//        T = Terrain.SaisieTerrain();// saisie du terrain
//        P = TerrainPoints.SaisiePoint(T);//saisie des points terrains
//        verifieforme = TerrainPoints.verifieForme(P);//verifie la forme saisi , speciale , ou polygnoale classique
//        P = TerrainPoints.CompletePoint(P, verifieforme);//rajoute des points si necessaires pour fermer la forme
//        if (verifieforme == true) {
//            Pr = TerrainPoints.TrianglePoint(P, verifieforme);//rajoute des points pour le decoupage en triangle
//        }
//        ST = TerrainSegment.creationSegment(P, Pr, T, verifieforme);
//        if (P.size() > 3) {//déja un triangle pas necessaire de creer de nouveau points et segments
//            STr = TerrainSegment.creationSegmentTriangle(P, Pr, verifieforme);//rajouter des segments pour le decoupage en triangle
//            STr = TerrainSegment.Suppsegmendouble(ST, STr);//supprime les doublons avec la liste de segment deja existante
//            System.out.println(STr);
//        }
//        //creer les triangles
//        TT = TerrainTriangle.Creationtriangle(ST, STr, verifieforme);
//        System.out.println(TT);
//
//    }
          
}






