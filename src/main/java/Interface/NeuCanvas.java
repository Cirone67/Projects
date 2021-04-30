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
        
        this.canvas.heightProperty().addListener((o) -> {
           this.redraw(); 
        });
        this.canvas.widthProperty().addListener((o) -> {
            this.redraw();
        });     
        
    }
    
    public GraphicsContext getGraphicsContext2D(){
        return this.canvas.getGraphicsContext2D();
    }
    
    public void redraw(){
        GraphicsContext context = this.canvas.getGraphicsContext2D();
        Treillis modele = this.I.getTreillis();
        ArrayList<TerrainPoints> pT = new ArrayList<TerrainPoints>();
        ArrayList<TerrainSegment> sT = new ArrayList<TerrainSegment>();
        
        ArrayList<TerrainPoints> listPT = new ArrayList<TerrainPoints>(this.I.getMenuPrincipal().getMenuCreation().getSaisiePointTerrain().getP());
            for (TerrainPoints TP: listPT){   //redessine les premiers points
                TP.draw(context);
            }        
        
        for (TerrainTriangle TT: modele.getTerrainTriangles()){  //dessine tous les points et segments terrain
            
            if (pT.contains(TT.getC1().getA()) == false){    //1er point du triangle --> vérifie s'il n'a pas déjà été dessiné (vérification nécessaire?)
                TT.getC1().getA().draw(context); //le dessine
                pT.add(TT.getC1().getA());
            }
            
            if (pT.contains(TT.getC1().getB()) == false){    //2eme point du triangle --> idem
                TT.getC1().getB().draw(context); 
                pT.add(TT.getC1().getB());
            }

            if (pT.contains(TT.getC1().getB()) == false){    //3eme point du triangle
                TT.getC2().getB().draw(context); 
                pT.add(TT.getC2().getB());
            }
            
            if (sT.contains(TT.getC1()) == false){ //1er segment du triangle --> vérifie s'il n'a pas déjà été dessiné 
                   TT.getC1().draw(context); //le dessine
                   sT.add(TT.getC1());
               }
                
            
            if (sT.contains(TT.getC2()) == false){ //2eme segment du triangle --> idem 
                   TT.getC2().draw(context); 
                   sT.add(TT.getC2());
               }
                
                     
            if (sT.contains(TT.getC3()) == false){ //2eme segment du triangle --> idem 
                   TT.getC3().draw(context); 
                   sT.add(TT.getC3());
               }                         
        
        for (Noeud n: modele.getNoeuds()){ //dessine tous les noeuds
            n.draw(context);
        }
        for (Barre b: modele.getBarres()){ //dessine toutes les barres
            b.draw(context);
        }
        }
    }
 
}

