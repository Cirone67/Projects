/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;
import java.util.Arrays;
import java.util.Optional;
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
    }
    public void setPy(double Y){
        this.Py = Y;
    }
    
    //constructeur
    public TerrainPoints(double Px , double Py){
        this.Px=Px;
        this.Py=Py;
    }
    //affichage d'un point
    
    public void Affiche(TerrainPoints P){
        System.out.println("("+this.Px+";"+this.Py+")");
    }
    //saisie d'un point 
    private static TerrainPoints Saisie(){
        double x,y;
        TerrainPoints P;
        System.out.println("Saisir le x de votre point :(abscisse)");
        x=Lire.d();
        System.out.println("Saisir le y de votre point :(ordonnee)");
        y=Lire.d();
        P = new TerrainPoints(x,y);
        return P;
    }
    
    public String toString(){
        
        return ("Le point a pour coordonnee : ("+this.Px+";"+this.Py+")");
    }
    
    public static void main(String[] args){
        TerrainPoints TEST;
        TEST= Saisie();
        System.out.println(TEST);
    }
    
    
}
