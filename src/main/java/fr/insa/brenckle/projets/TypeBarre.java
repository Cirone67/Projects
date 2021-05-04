/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;

/**
 *
 * @author brenc
 */
public final class TypeBarre {
private int categorie;
private double cout;
private double longmax;
private double longmin;
private double restension;
private double rescompression;
private static int nbrTypeBarre=0;

public TypeBarre(int categorie, double cout, double longmax, double longmin, double restension, double rescompression){
 this.categorie = categorie;
 this.cout = cout;
 this.longmax = longmax;
 this.longmin = longmin;
 this.rescompression = rescompression;
 this.restension = restension;

}

    public static int getNbrTypeBarre() {
        return nbrTypeBarre;
    }


    public int getCategorie() {
        return categorie;
    }

    public double getCout() {
        return cout;
    }

    public double getLongmax() {
        return longmax;
    }

    public double getLongmin() {
        return longmin;
    }

    public double getRestension() {
        return restension;
    }

    public double getRescompression() {
        return rescompression;
    }
    
    
    //Catalogue à télécharger par défault
    public void BarreDefault(Treillis treillis){
      TypeBarre cuivre = new TypeBarre(1,100.0,1.0,5.0,1000.0,2000.0);
      treillis.ajoute(cuivre);
      nbrTypeBarre++;
      TypeBarre fer = new TypeBarre(2,100.0,1.0,5.0,3550.0,2540.0);
      treillis.ajoute(fer);
      nbrTypeBarre++;
      TypeBarre acier = new TypeBarre(3,100.0,1.0,8.0,4253.0,7523.0);
      treillis.ajoute(acier);
      nbrTypeBarre++;
      TypeBarre aluminium = new TypeBarre(4,100.0,1.0,10.0,1623.0,850.0);
      treillis.ajoute(aluminium);
      nbrTypeBarre++;
      
      //Ajoute à la liste ce qui te plait...
    
    }
    

}
