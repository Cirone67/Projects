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
    public double getYin(){
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
}






