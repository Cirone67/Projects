/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;

import static fr.insa.brenckle.projets.Appui.conv;
import static fr.insa.brenckle.projets.Terrain.noeudTerrain;
import static fr.insa.brenckle.projets.TerrainSegment.longueurT;
import static java.lang.Double.MIN_VALUE;
import java.util.ArrayList;

/**
 *
 * @author brenc
 */
public class Calcul {

    
    public static double xprojection(double angle, double norme){
     return norme*Math.cos(angle);   
    }
    
    public static double yprojection(double angle, double norme){
     return  norme*Math.sin(angle); 
    }
    //Méthode qui donne l'angle entre une Barre et l'horizontale
    public static double anglAvecHoriz(Barre barre, Noeud debut){
         double scalaire ;
         double signe;
        if(barre.getDebut() == debut){
           scalaire = ((barre.getFin().getAbs()-barre.getDebut().getAbs()));
           signe = barre.getFin().getOrd()-barre.getDebut().getOrd();
        }else{
          scalaire = ((barre.getDebut().getAbs()-barre.getFin().getAbs()));    
          signe = (-barre.getFin().getOrd()+barre.getDebut().getOrd());
        }

          if(signe>=0){
            return (Math.acos(scalaire/(barre.longueur())));
                  
          }else{
            return -(Math.acos(scalaire/(barre.longueur()))); 
          }
    }
    
    //Méthode qui donne l'angle entre une la normal du terrain et l'horizontale
    public static double anglNormal(TerrainTriangle terrainTriangle, int premierPoint, ArrayList<TerrainTriangle> triangle){
        
        TerrainSegment segment = conv(premierPoint,terrainTriangle);
                 //Pour placer le vecteur au milieu du sol
           double xmilieu = (segment.getA().getPx()+segment.getB().getPx())/2;
           double ymilieu = (segment.getA().getPy()+segment.getB().getPy())/2;

           double scalaire = ((segment.getB().getPx()-segment.getA().getPx()));
           NoeudSimple fictif = new NoeudSimple(-1,xprojection(Math.acos(scalaire/(longueurT(segment.getA(),segment.getB())))+Math.PI/2,MIN_VALUE)+ xmilieu,yprojection(Math.acos(scalaire/(longueurT(segment.getA(),segment.getB())))+Math.PI/2, MIN_VALUE)+ ymilieu);
           
           //Test si le vecteur normal fictif est dans le terrain ou pas.
           if((noeudTerrain(triangle,fictif)==true)){ //Renvoie vrai si dedans (false si telecharger true si saisie
            //System.out.println("dans");
                if(Math.PI==Math.acos(scalaire/(longueurT(segment.getA(),segment.getB())))+Math.PI/2){
                    return 0;
                    
                }else if(0 == Math.acos(scalaire/(longueurT(segment.getA(),segment.getB())))+Math.PI/2){
                      return Math.PI ;     
                            
                }else{
                    return -(Math.acos(scalaire/(longueurT(segment.getA(),segment.getB())))+Math.PI/2);
                }
            }else{
               //System.out.println("dehors");
            return (Math.acos(scalaire/(longueurT(segment.getA(),segment.getB())))+Math.PI/2);
           }  
    }    
        
        
    

    /*//Donne le nbr de barre sur un noeud
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
    */
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
                  //System.out.println(treillis.getNoeuds().get(t).getIdNoeud());
                 //System.out.println(anglAvecHoriz(treillis.getBarres().get(j),treillis.getNoeuds().get(t)));
                 
                 systeme.setMij(i, j, Math.cos(anglAvecHoriz(treillis.getBarres().get(j),treillis.getNoeuds().get(t))));
                 systeme.setMij(i+1, j, Math.sin(anglAvecHoriz(treillis.getBarres().get(j),treillis.getNoeuds().get(t))));
             //systeme.String();
             }
             }
         }
       System.out.println(systeme);
       //Partie Réaction du support :
       
           int j=treillis.getBarres().size();
           
           while(j<treillis.getBarres().size()+nbrAppui(treillis.getNoeuds())){
           for(int t =0; t<treillis.getNoeuds().size(); t++){
           int i = t*2;

              if(treillis.getNoeuds().get(t) instanceof AppuiDouble){
                  systeme.setMij(i, j, 1);
                  systeme.setMij(i+1, j+1, 1);
              j = j+2;
              } else if(treillis.getNoeuds().get(t) instanceof AppuiSimple){
                  //systeme.String();
                  systeme.setMij(i, j, Math.cos(anglNormal(((AppuiSimple)treillis.getNoeuds().get(t)).getTriangleAppui(),((AppuiSimple)treillis.getNoeuds().get(t)).getPremierPoint(), treillis.getTerrainTriangles())));
                  systeme.setMij(i+1, j, Math.sin(anglNormal(((AppuiSimple)treillis.getNoeuds().get(t)).getTriangleAppui(),((AppuiSimple)treillis.getNoeuds().get(t)).getPremierPoint(), treillis.getTerrainTriangles())));
              j= j+1;  
              }
              
           }
       System.out.println(systeme); 
       }
           //elimine les valeurs très petite négligeable lié aux arrondis
           for(int i=0; i <treillis.getNoeuds().size()*2;i++){
               for(int m=0; m <treillis.getBarres().size()+nbrAppui(treillis.getNoeuds());m++){
                   if(systeme.getMij(i, m)<Math.pow(10, -5)&&systeme.getMij(i, m)>-Math.pow(10, -5)){
                       systeme.setMij(i, m, 0);
                   }
               }
           }
    System.out.println(systeme);
    return systeme;
   
   }
   
    // Méthode qui créée le vecteur de l'autre coté de l'égalité  
   public static Matrice vecteurCharge(Treillis treillis){
       Matrice vecteur = new Matrice(treillis.getNoeuds().size()*2,1);
       for(int t =0;t<treillis.getNoeuds().size();t++){
           int i=2*t;
           for(int j =0; j<treillis.getCharge().size(); j++){
            if(treillis.getNoeuds().get(t).getIdNoeud() == treillis.getCharge().get(j).getNoeud()){
           vecteur.setMij(i, 0, -treillis.getCharge().get(j).getNorme()*Math.cos(treillis.getCharge().get(j).getAngle())); //angle par rapport à horizon
           vecteur.setMij(i+1,0,-treillis.getCharge().get(j).getNorme()*Math.sin(treillis.getCharge().get(j).getAngle()));
           }
           }
         for(int a=0; a <treillis.getNoeuds().size()*2;a++){
                   if(vecteur.getMij(a, 0)<Math.pow(10, -5)&&vecteur.getMij(a, 0)>-Math.pow(10, -5)){
                       vecteur.setMij(a, 0, 0);
                   }
               }
           }  
       System.out.println(vecteur);
       return vecteur;
   }
   
   //méthode qui renvoie vrai si le matrice est carré ( et donc le système a une unique solution)
   public static boolean matriceCarree(Matrice matrice){
       boolean res = false;
       System.out.println(matrice);
      if(matrice.getNbrCol()==matrice.getNbrLig()) {
          res = true;
      }
      return res;
   }
   
   
   /*
   public String affichage(Treillis treillis){
       String res = "";
       Matrice affichage;
       for(int i =0; i<treillis.getBarres().size();i++){
       res= res+"Barre "+treillis.getBarres().get(i).getId()+" = \n";
       }
       
      int  j=treillis.getBarres().size();
       while(j<treillis.getBarres().size()+nbrAppui(treillis.getNoeuds())){
           if(treillis.getNoeuds().get(j-treillis.getBarres().size()) instanceof AppuiDouble){
           res = res+ ("Rx"+(treillis.getNoeuds().get(j-treillis.getBarres())).getIdNoeud()+" = \n");
           res = res+"Ry"+treillis.getNoeuds().get(j-treillis.getBarres())+" = \n"
           }
           if(treillis.getNoeuds().get(j-treillis.getBarres().size()) instanceof AppuiSimple){
            res = res+"Rx"+treillis.getNoeuds().get(j-treillis.getBarres())+" = \n";   
           }    
           }
       return res;
   }
   */
}
