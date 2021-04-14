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

TypeBarre(int categorie, double cout, double longmax, double longmin, double restension, double rescompression){
 this.categorie = categorie;
 this.cout = cout;
 this.longmax = longmax;
 this.longmin = longmin;
 this.rescompression = rescompression;
 this.restension = restension;

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
// TO DO catalogue
}
