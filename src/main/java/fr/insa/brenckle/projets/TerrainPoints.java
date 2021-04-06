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
public class TerrainPoints extends Terrain{
    private double Py; 
    private double Px; 
    
    //get/set des pointterrains
    public double getPy(){
        return Py;
    }
    public double getPx(){
        return Px;
    }
    public void setPx(double X){
        this.Px = X;
    }public void setPy(double Y){
        this.Py = Y;
    }
}
