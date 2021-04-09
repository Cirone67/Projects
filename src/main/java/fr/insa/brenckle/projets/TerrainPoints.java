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
    public static TerrainPoints[] SaisiePoint(Terrain T, int nbr){
        double x,y;
        int i;
        TerrainPoints P[];
        System.out.println("Saisir les points dans l'ordre croissant des abcisses");
        P = new TerrainPoints[nbr];
        for(i=0;i<nbr;i++){
            System.out.println(" NOUVEAU POINT DU TERRAIN ");
            System.out.println("Saisir le x de votre point numero "+i+":(abscisse)");
            x=Lire.d();
            System.out.println("Saisir le y de votre point numero "+i+":(ordonnee)");
            y=Lire.d();
            P[i] = new TerrainPoints(x,y);
                while(false==TerrainPoints.verifiePT(T, P[i])){
                    System.out.println("Votre point n'appartient pas au terrain, rentrez un nouveau point appartenant au terrain");
                    System.out.println("Saisir le x de votre point numero "+i+":(abscisse)");
                    x=Lire.d();
                    System.out.println("Saisir le y de votre point numero "+i+":(ordonnee)");
                    y=Lire.d();
                    P[i] = new TerrainPoints(x,y);
                }
            System.out.println("");
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


