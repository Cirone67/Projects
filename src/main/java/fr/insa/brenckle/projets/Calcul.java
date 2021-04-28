/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;

import static fr.insa.brenckle.projets.Appui.conv;
import static fr.insa.brenckle.projets.TerrainSegment.angleSegment;
import java.util.ArrayList;

/**
 *
 * @author brenc
 */
public class Calcul {
    
    //private Matrice système;
    
    public double xprojection(double angle, double norme){
     return norme*Math.cos(angle);   
    }
    
    public double yprojection(double angle, double norme){
     return  norme*Math.sin(angle); 
    }
    //Méthode qui donne l'angle entre une Barre et l'horizontale
    public static double anglAvecHoriz(Barre barre){
        
         double scalaire = ((barre.getFin().getAbs()-barre.getFin().getAbs()));
            return (Math.acos(scalaire/(barre.longueur()))); //%(Math.PI));
        //if((barre.getFin().getOrd()-barre.getDebut().getOrd()) !=0){
        //return Math.atan((barre.getFin().getAbs()-barre.getDebut().getAbs())/(barre.getFin().getOrd()-barre.getDebut().getOrd()));
        //}else{
           // return Math.PI/2;
        }
    
    //Méthode qui donne l'angle entre une la normal du terrain et l'horizontale
    public static double anglNormal(TerrainTriangle terrainTriangle, int premierPoint){
        
        return angleSegment(conv(premierPoint,terrainTriangle),new TerrainSegment(new TerrainPoints(0,0),new TerrainPoints(1,0)))+Math.PI/2;
        
    }
    //Donne le nbr de barre sur un noeud
    public int concourantes(int idnoeud, ArrayList<Barre> barres){
        int res =0;
        for(int i = 0; i<barres.size();i++){
        if(barres.get(i).getDebut().getIdNoeud()== idnoeud){
          res++;  
        }
        if(barres.get(i).getFin().getIdNoeud()== idnoeud){
         res++;   
        }  
    }
        return res;
    }
    
    public static int nbrAppui(ArrayList<Noeud> noeud){
        int res = 0;
            for(int i = 0; i<noeud.size();i++){
                if(noeud.get(i) instanceof AppuiDouble){
                 res = res+2; 
                }
                if (noeud.get(i) instanceof AppuiSimple){
                res++;
                }
                
            }
    return res;
    } 
    
   public static Matrice miseSousSystem(Treillis treillis){
       //créé la matrice de la bonne taille
       Matrice systeme = new Matrice(treillis.getNoeuds().size()*2,treillis.getBarres().size()+nbrAppui(treillis.getNoeuds()));
       
       //Partie Barre :
       
       //Boucle pour parcourir les noeud
       for(int t =0; t<treillis.getNoeuds().size(); t++){
        //Boucle pour remplir la matrice
            int i=t*2;
         for(int j =0; j<treillis.getBarres().size();j++){
 
             if(treillis.getBarres().get(j).getDebut().getIdNoeud()== treillis.getNoeuds().get(t).getIdNoeud()||treillis.getBarres().get(j).getFin().getIdNoeud()== treillis.getNoeuds().get(t).getIdNoeud()){
                 System.out.println(treillis.getNoeuds().get(t).getIdNoeud());
                 systeme.setMij(i, j, Math.cos(anglAvecHoriz(treillis.getBarres().get(j))));
                 systeme.setMij(i+1, j, Math.sin(anglAvecHoriz(treillis.getBarres().get(j))));
             systeme.String();
             }
             }
         }
       //Partie Réaction du support :
       
       
       //Boucle pour remplir la matrice
           for(int j =treillis.getBarres().size(); j<treillis.getBarres().size()+nbrAppui(treillis.getNoeuds());j++){
           //Boucle sur les noeuds
           for(int t =0; t<treillis.getNoeuds().size(); t++){
           int i = t*2;    
               
               System.out.println(i);
               System.out.println(j);
              if(treillis.getNoeuds().get(t) instanceof AppuiDouble){
                  systeme.setMij(i, j, 1);
                  systeme.setMij(i+1, j+1, 1);
              j = j+1;
              }
              
              if(treillis.getNoeuds().get(t) instanceof AppuiSimple){
                  systeme.setMij(i, j, Math.cos(anglNormal(((AppuiSimple)treillis.getNoeuds().get(t)).getTriangleAppui(),((AppuiSimple)treillis.getNoeuds().get(t)).getPremierPoint())));
                  systeme.setMij(i+1, j, Math.sin(anglNormal(((AppuiSimple)treillis.getNoeuds().get(t)).getTriangleAppui(),((AppuiSimple)treillis.getNoeuds().get(t)).getPremierPoint())));
              }
              System.out.println("caca");
              systeme.String();
           }
       }  
    return systeme;   
   }
      
   public static Matrice vecteurcharge(Treillis treillis){
       Matrice vecteur = new Matrice(treillis.getNoeuds().size()*2,1);
       for(int i =0;i<treillis.getNoeuds().size()*2;i++){
           for(int j =1; i<treillis.getCharge().size(); j++){
            if(treillis.getNoeuds().get(i).getIdNoeud() == treillis.getCharge().get(j).getNoeud()){
           vecteur.setMij(i, 0, treillis.getCharge().get(j).getNorme()*Math.cos(treillis.getCharge().get(j).getAngle())); //angle par rapport à horizon
           vecteur.setMij(i+1,0,treillis.getCharge().get(j).getNorme()*Math.sin(treillis.getCharge().get(j).getAngle())); 
           }
           }
           
           i=i+1;
       }
       return vecteur;
   }
}
