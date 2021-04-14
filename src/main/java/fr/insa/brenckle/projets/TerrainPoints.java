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

public class TerrainPoints {
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
    
    //saisie des points             ( nbr = nombre de point a rentrer )
    public static ArrayList<TerrainPoints> SaisiePoint(Terrain T){
        double x,y;
        int sortie=0 , i ;
        ArrayList< TerrainPoints> P = new ArrayList< TerrainPoints>();
        TerrainPoints PT; 
        System.out.println("Saisir les points dans l'ordre croissant des abcisses");
        while(sortie==0){
            i=P.size();
            System.out.println(" NOUVEAU POINT DU TERRAIN ");
            System.out.println("Saisir le x de votre point numero "+i+":(abscisse)");
            x=Lire.d();
            System.out.println("Saisir le y de votre point numero "+i+":(ordonnee)");
            y=Lire.d();
            PT = new TerrainPoints(x,y);
            //plus utile car le programme complete le terrain lui meme.
                while(false==TerrainPoints.verifiePT(T, PT)){
                    System.out.println("Votre point n'appartient pas au terrain, rentrez un nouveau point appartenant au terrain");
                    System.out.println("Saisir le x de votre point numero "+i+":(abscisse)");
                    x=Lire.d();
                    System.out.println("Saisir le y de votre point numero "+i+":(ordonnee)");
                    y=Lire.d();
                    PT = new TerrainPoints(x,y);
                } 
                P.add(PT);
            System.out.println("Voulez vous rajouter un point ? Taper 0, sinon 1");
            sortie=Lire.i();
        }
        return P;
    }
    
    //verifie que le point appartient au terrain 
    public static boolean verifiePT(Terrain T,  TerrainPoints A){
        boolean U;
        if((A.getPx()>T.getXmax())||(A.getPy()>T.getYmax())||(A.getPx()<T.getXmin())||(A.getPy()<T.getYmin())){
            U=false;
        }else{
            U=true;
        }
        return U;
    } 
    
    
    //tostring
    public String toString(){
        return ("("+this.Px+";"+this.Py+")");
    }    
    
    
}


