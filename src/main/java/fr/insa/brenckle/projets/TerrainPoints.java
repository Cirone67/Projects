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
    //saisie d'un point 
    public static TerrainPoints SaisiePoint(){
        double x,y;
        TerrainPoints P;
        System.out.println("Saisir les points dans l'ordre croissant des abcisses");
        System.out.println("Saisir le x de votre point :(abscisse)");
        x=Lire.d();
        System.out.println("Saisir le y de votre point :(ordonnee)");
        y=Lire.d();
        P = new TerrainPoints(x,y);
        return P;
    }
    //tostring
    public String toString(){
        return ("("+this.Px+";"+this.Py+")");
    }
    /*public static void main(String[] args){
        int i,nbr,j;
        TerrainPoints PT[];
        System.out.println("Saisir le nombre de point que vous voulez rentrer pour le terrain");
        nbr = Lire.i();
        PT = new TerrainPoints[nbr];
        for(i=0;i<(nbr);i++){
            PT[i] = Saisie();
        }
        for(i=0;i<nbr;i++){
        System.out.println(PT[i]);//PT pour PointTerrain
    }*/
        
    }

