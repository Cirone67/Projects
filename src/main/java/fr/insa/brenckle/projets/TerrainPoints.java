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
import javafx.scene.canvas.GraphicsContext;


public class TerrainPoints extends Objet{
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
    
    //saisie des points + complete si necessaire en vue de la creation en segment
    public static ArrayList<TerrainPoints> SaisiePoint(Terrain T){
        double x,y;
        int sortie=0 , i ;
        ArrayList< TerrainPoints> P = new ArrayList< TerrainPoints>();
        TerrainPoints PT; 
        System.out.println("Saisir les points dans l'ordre croissant des abcisses pour avoir un terrain ''special'' sinon polygone");
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
    //ajoute point dans une liste
    public static ArrayList<TerrainPoints> AjoutPoint(ArrayList <TerrainPoints> P, Terrain T){
        double x ,y;
        TerrainPoints Pt;
        int c,j;
        System.out.println("Voulez vous rentre un nouveau point dans la liste ? OUi = 1");
        c=Lire.i();
        if(c==1){
            System.out.println("Saisissez la position :");
            j=Lire.i();
            System.out.println("Saissiez le point , d'abord x puis y ");
            x=Lire.d();
            y=Lire.d();
            Pt = new TerrainPoints (x ,y );
            while(false==TerrainPoints.verifiePT(T, Pt)){
                    System.out.println("Votre point n'appartient pas au terrain, rentrez un nouveau point appartenant au terrain");
                    System.out.println("Saisir le x de votre point numero "+j+":(abscisse)");
                    x=Lire.d();
                    System.out.println("Saisir le y de votre point numero "+j+":(ordonnee)");
                    y=Lire.d();
                    Pt = new TerrainPoints(x,y);
                }
            P.add(j, Pt);
        }
        return P;
    }
   
    //completer la liste de point en vue des segments si necessaire(fermer le terrain pour former un polygone spéciale)
    public static ArrayList<TerrainPoints> CompletePoint(ArrayList<TerrainPoints> P , boolean verifie){
        int i;
        double yminPT, xminPT, xmaxPT;
        yminPT = P.get(0).getPy();
        xminPT = P.get(0).getPx();
        xmaxPT = P.get(0).getPx();
        int nbr = P.size();
        for(i=1;i<nbr;i++){
            if(yminPT>(P.get(i).getPy())){
                yminPT=P.get(i).getPy();
            }
            if(xminPT>(P.get(i).getPy())){
                xminPT=P.get(i).getPy();
            }
            if(xmaxPT<(P.get(i).getPx())){
                xmaxPT=P.get(i).getPx();
            }
        }
        TerrainPoints PTa, PTb;
        if((P.get(nbr-1).getPy()==P.get(0).getPy())&&(yminPT==P.get(nbr-1).getPy())){ 
            }else{
                if(P.get(nbr-1).getPy()!= yminPT){
                PTa= new TerrainPoints(P.get(nbr-1).getPx(),yminPT);
                P.add(PTa);
                }
                if((P.get(0).getPy()!=yminPT)&&(verifie == false)){
                PTb = new TerrainPoints(P.get(0).getPx(),yminPT);
                P.add(0,PTb);
                }
            }
            return P;
    }
    
    //verifie la forme saisie 
    public static boolean verifieForme(ArrayList <TerrainPoints> P){
        boolean verifie = false;
        int nbr=P.size(),i;
        for(i=0;i<nbr-1;i++){
            if(P.get(i).getPx()<=P.get(i+1).getPx()){
                verifie=true;
            }else{
                i=nbr-2;
                verifie=false;
                }
        }
        if(verifie==true){
            System.out.println("Vous avez rentre une forme ''special''");
        }else{
            System.out.println("Vous avez rentre une forme polygonale classique");
        }
            return verifie;
    }
    
    //creer des points pour le decoupage en triangle
    public static ArrayList<TerrainPoints> TrianglePoint(ArrayList<TerrainPoints> P , boolean verifie){
        int i,nbr,j;
        double yminPT;
        nbr=P.size();
        ArrayList< TerrainPoints> Pr = new ArrayList< TerrainPoints>();
        TerrainPoints Ptempo;
        yminPT=P.get(0).getPy();
        for(i=1;i<nbr;i++){
            if(yminPT>(P.get(i).getPy())){
                yminPT=P.get(i).getPy();
            }
        }
        if(verifie=true){
            j=0;
            i=0;
            while(P.get(i).getPx()==P.get(i+1).getPx()){
                j=j+1;
                i=i+1;
            }
            for(i=j;i<nbr-1;i++){
                if(P.get(i).getPx()==P.get(i+1).getPx()){
                }else{
                    Ptempo = new TerrainPoints(P.get(i).getPx(), yminPT);
                    Pr.add(Ptempo);
                }
            }
            for(i=0;i<nbr;i++){
                for(j=1;j<Pr.size();j++){
                    if((P.get(i).getPx()==Pr.get(j).getPx())&&(P.get(i).getPy()==Pr.get(j).getPy())){
                        Pr.remove(j);
                    }
                }
            }
            System.out.println("Point creer : "+Pr);
        }
        return Pr;
    }
            
    // verifie que le point appartient au terrain 
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
    
    public void draw(GraphicsContext gc){
        int r = 3;
        gc.strokeOval(this.getPx()- r, this.getPy() - r, r, r);
        gc.fillOval(this.getPx() - r, this.getPy() - r, r, r);
    }

    @Override
    public double distancePoint(double abs, double ord) {
        double x = this.getPx() - abs;
        double y = this.getPy() - ord;
        return Math.sqrt(x*x+y*y);
    }
    
}


