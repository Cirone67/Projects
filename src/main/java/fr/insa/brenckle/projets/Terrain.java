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
}






