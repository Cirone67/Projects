/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import fr.insa.brenckle.projets.Barre;
import fr.insa.brenckle.projets.Noeud;
import fr.insa.brenckle.projets.TerrainPoints;
import fr.insa.brenckle.projets.TerrainSegment;
import fr.insa.brenckle.projets.TerrainTriangle;
import fr.insa.brenckle.projets.Treillis;
import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

/**
 *
 * @author Guillaume R
 */
public class NeuCanvas extends Pane {
    
    private Interface I;
    private Canvas canvas;
    
    
    public NeuCanvas (Interface I){
        this.I = I;
        this.canvas = new Canvas (this.getWidth(), this.getHeight());
        this.getChildren().add(this.canvas);
        this.canvas.heightProperty().bind(this.heightProperty());
        this.canvas.widthProperty().bind(this.widthProperty());
        
        this.heightProperty().addListener((o) -> {
           this.redraw(); 
        });
        this.widthProperty().addListener((o) -> {
            this.redraw();
        });     
    }
    
    public void redraw(){
        int i =0;
        GraphicsContext context = this.canvas.getGraphicsContext2D();
        Treillis modele = this.I.getTreillis();
        ArrayList<TerrainPoints> pT = new ArrayList<TerrainPoints>();
        ArrayList<TerrainSegment> sT = new ArrayList<TerrainSegment>();
        
        for (TerrainTriangle TT: modele.getTerrainTriangles()){  //dessine tous les points et segments terrain
            for (TerrainPoints TP: pT){    //1er point du triangle --> vérifie s'il n'a pas déjà été dessiné
                if (TT.getC1().getA() == TP){
                   i=1; 
                }
            }
            if (i!=1){  //le dessine
                TT.getC1().getA().draw(context);
                pT.add(TT.getC1().getA());  
            } i=0;
            
            for (TerrainPoints TP: pT){   //2eme point du triangle --> idem
                if (TT.getC1().getB() == TP){
                   i=1; 
                }
            }
            if (i!=1){
                TT.getC1().getB().draw(context);
                pT.add(TT.getC1().getB());  
            } i=0; 

            for (TerrainPoints TP: pT){   //3eme point 
                if (TT.getC2().getB() == TP){
                   i=1; 
                }
            }
            if (i!=1){
                TT.getC2().getB().draw(context);
                pT.add(TT.getC2().getB());  
            } i=0; 
            
            for (TerrainSegment TS: sT){ //1er segment du triangle --> vérifie s'il n'a pas déjà été dessiné 
               if (TT.getC1() == TS){
                   i=1;
               }
               if (i!=1){ //le dessine
                   TT.getC1().draw(context);
                   sT.add(TT.getC1());
               } i=0;
                
            }
            for (TerrainSegment TS: sT){ //2eme segment du triangle --> idem 
               if (TT.getC2() == TS){
                   i=1;
               }
               if (i!=1){ 
                   TT.getC2().draw(context);
                   sT.add(TT.getC2());
               } i=0;
                
            }           
            for (TerrainSegment TS: sT){ //3eme segment du triangle 
               if (TT.getC3() == TS){
                   i=1;
               }
               if (i!=1){ 
                   TT.getC3().draw(context);
                   sT.add(TT.getC3());
               } i=0;
                
            }              
        }
        for (Noeud n: modele.getNoeuds()){ //dessine tous les noeuds
            n.draw(context);
        }
        for (Barre b: modele.getBarres()){ //dessine toutes les barres
            b.draw(context);
        }
    }
    
}
