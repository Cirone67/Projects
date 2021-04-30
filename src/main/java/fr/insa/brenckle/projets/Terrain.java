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
    //ange entre un noeud et un segment
    public static double angleNoeud(NoeudSimple N,TerrainSegment ST1){
        double angle,NY,NX;
        TerrainPoints A = ST1.getA();
        TerrainPoints B = ST1.getB();
        NX = N.getAbs();
        NY= N.getOrd();
        angle =Math.atan2(B.getPy() - A.getPy(), B.getPx() - A.getPx()) - Math.atan2(NY - B.getPy(), NX - B.getPx()); 
        //System.out.println(angle);
        return angle;
    }
    
    //regarde si un noeud simple est dans le terrain  renvoie true si le point est dans le terrain
      public static boolean  noeudTerrain(ArrayList<TerrainTriangle> TT , NoeudSimple N, boolean tele){
         int i, j=0, nbrTT;
         nbrTT = TT.size();
         double angle;
         boolean app=false;
         if(tele==false){//pour des triangles saisies
             angle=Terrain.angleNoeud(N, TT.get(0).getC1());
             if(0<angle){
                if(angle<=Math.PI){
                    j=j+1;
                    angle=Terrain.angleNoeud(N, TT.get(0).getC2());
                    if(angle>Math.PI){
                        j=j+1;
                            angle=Terrain.angleNoeud(N, TT.get(0).getC3());
                            if(0<angle){
                               if(angle<=Math.PI){
                               j=j+1;
                               app = true;
                               //System.out.println("le point appartient au triangle 0");
                               }
                            }
                    }
                }
            }
        if(j!=3){
            for(i=1;i<nbrTT-1;i++){
             j=0;
             angle=Terrain.angleNoeud(N, TT.get(i).getC1());
             if(angle>0){
                    if(angle<=Math.PI){
                    j=j+1;
                    angle=Terrain.angleNoeud(N, TT.get(i).getC2());
                        if(angle>Math.PI){
                            j=j+1;
                            angle=Terrain.angleNoeud(N, TT.get(i).getC3());
                                if(angle>0){
                                    if(angle<=Math.PI){
                                        j=j+1;
                                        app = true;
                                        //System.out.println("le point appartient au triangle "+i);
                                        i=nbrTT-1;
                                    }               
                                }
                        }
                    }
                }
            }
            if(j!=3){
                angle=Terrain.angleNoeud(N, TT.get(nbrTT-1).getC1());
                if(angle>0){
                    if(angle<=Math.PI){
                    j=j+1;
                    angle=Terrain.angleNoeud(N, TT.get(nbrTT-1).getC2());
                        if(angle>0){
                            if(angle<=Math.PI){
                                j=j+1;
                                angle=Terrain.angleNoeud(N, TT.get(nbrTT-1).getC3());
                                    if(angle>0){
                                        if(angle<=Math.PI){
                                            j=j+1;
                                            app = true;
                                            //System.out.println("le point appartient au triangle "+(nbrTT-1));
                                        }
                                    }
                            }
                        }
                    }
                }
            }
    }
        if(j!=3){
            System.out.println("Le noeud n'appartient a aucun triangle du terrain.");
        }
    }if(tele==true){//pour des triangles télécharger 
        angle=Terrain.angleNoeud(N, TT.get(0).getC1());
             if(0<angle){
                if(angle<=Math.PI){
                    j=j+1;
                    angle=Terrain.angleNoeud(N, TT.get(0).getC2());
                    if(angle<=Math.PI){
                        if(0<angle){
                        j=j+1;
                            angle=Terrain.angleNoeud(N, TT.get(0).getC3());
                            if(0<angle){
                               if(angle<=Math.PI){
                               j=j+1;
                               app = true;
                               //System.out.println("le point appartient au triangle 0");
                               }
                            }
                    }
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






