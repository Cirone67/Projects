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
          
}






