/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.brenckle.projets;

/**
 *
 * @author Guillaume R
 */
public class Appui extends Noeud{
    
    private TerrainTriangle triangleAppui;
    private int premierPoint;
    private double posSegment;
    private TerrainSegment segmentAppui;
    
    public double getOrd(){
      return  segmentAppui.getA().getPy()*posSegment+segmentAppui.getB().getPy()*(1-posSegment);
    }
    
    public double getAbs(){
      return segmentAppui.getA().getPx()*posSegment+segmentAppui.getB().getPx()*(1-posSegment); 
    }
    
}
