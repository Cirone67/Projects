/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;
import java.util.ArrayList;
/**
 *
 * @author Guillaume R
 */
public abstract class Appui extends Noeud{
    
    private TerrainTriangle triangleAppui;
    private int premierPoint;
    private double posSegment;
    
    public Appui(int id,TerrainTriangle triangleAppui,int premierPoint ,double posSegment){
    super(id);
    this.triangleAppui = triangleAppui;
    this.posSegment = posSegment;
    this.premierPoint = premierPoint;
    nbrNoeud++;
    }
    //Méthode pour le téléchargement
    public Appui(int id,int idtriangleAppui,int premierPoint ,double posSegment, ArrayList<TerrainTriangle> terrainTriangle){
    super(id);
    for(int i=0; i< terrainTriangle.size();i++){
        if(terrainTriangle.get(i).getidT()== idtriangleAppui){
           this.triangleAppui = terrainTriangle.get(i);
        }
    }
    this.posSegment = posSegment;
    this.premierPoint = premierPoint;
    nbrNoeud++;
    }
    
    public double getOrd(){// problème de sens !!!!!!!!!!!!!!!!!
        
      return  conv(premierPoint,triangleAppui).getA().getPy()*posSegment+conv(premierPoint,triangleAppui).getB().getPy()*(1-posSegment);         
    }
    
    public double getAbs(){
      return conv(premierPoint,triangleAppui).getA().getPx()*posSegment+conv(premierPoint,triangleAppui).getB().getPx()*(1-posSegment); 
    }
    
    public void setOrd(double ord){
           
    }
    
    public void setAbs(double abs){
        
    }
    
    //Méthode qui convertie les nbr en coté du triangle pour methode getOrd/get Abs;
    public static TerrainSegment conv(int premierPoint, TerrainTriangle terraintriangle){
        if(premierPoint == 0){
            return terraintriangle.getC1();
        }    
        if(premierPoint == 1){
        return terraintriangle.getC2();
    }
        else{
            return terraintriangle.getC3();
        }
    }

    public TerrainTriangle getTriangleAppui() {
        return triangleAppui;
    }

    public void setTriangleAppui(TerrainTriangle triangleAppui) {
        this.triangleAppui = triangleAppui;
    }

    public int getPremierPoint() {
        return premierPoint;
    }

    public void setPremierPoint(int premierPoint) {
        this.premierPoint = premierPoint;
    }

    public double getPosSegment() {
        return posSegment;
    }

    public void setPosSegment(double posSegment) {
        this.posSegment = posSegment;
    }
    
    
}
