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
public class Appui extends Noeud{
    
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
    for(int i=0; i<= terrainTriangle.size();i++){
        if(terrainTriangle.get(i).getidT()== idtriangleAppui){
           this.triangleAppui = terrainTriangle.get(i);
        }
    }
    this.posSegment = posSegment;
    this.premierPoint = premierPoint;
    nbrNoeud++;
    }
    
    public double getOrd(){
      return  segmentAppui.getA().getPy()*posSegment+segmentAppui.getB().getPy()*(1-posSegment);
    }
    
    public double getAbs(){
      return segmentAppui.getA().getPx()*posSegment+segmentAppui.getB().getPx()*(1-posSegment); 
    }
    
}
