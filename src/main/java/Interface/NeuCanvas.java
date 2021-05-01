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
import java.io.FileInputStream;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author Guillaume R
 */
public class NeuCanvas extends Pane {
    
    private Interface I;
    private Canvas canvas;
  //  private ArrayList<Double> dxy;
    
    
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
        this.canvas.setOnMouseDragged((t) -> {
            System.out.println(t.getX());
        });
        this.canvas.setOnScroll((t) -> {  //zoom, dézoom
            double x = t.getSceneX();
            double y = t.getSceneY();
            if (t.getDeltaY()>0){
                this.zoom(1.1, x, y);               
            } else if (t.getDeltaY()<0){
                this.zoom(0.9, x, y);
            }                    
        });
        ArrayList<Double> xy = new ArrayList<Double>();
        this.canvas.setOnMouseDragged((t) -> {  //déplacer graph avec la souris       
            canvas.setCursor(Cursor.CLOSED_HAND);
            if (xy.size()<4){   //liste qui retient les coordonnées de déplacement de la souris
                xy.add(t.getSceneX()); xy.add(t.getSceneY());
            } else if (xy.size() >= 4){   //déplace
                double dx = xy.get(2)-xy.get(0);
                double dy = xy.get(3)-xy.get(1);
                canvas.setTranslateX(canvas.getTranslateX()+dx);
                canvas.setTranslateY(canvas.getTranslateY()+dy);
                xy.clear();
                this.redraw();
            }
        });
        this.canvas.setOnMouseReleased((t) -> {
            xy.clear();
            canvas.setCursor(Cursor.DEFAULT);
        });

    }
    
    public GraphicsContext getGraphicsContext2D(){
        return this.getCanvas().getGraphicsContext2D();
    }
    
    public void redraw(){
        GraphicsContext context = this.getCanvas().getGraphicsContext2D();
        Treillis modele = this.getI().getTreillis();
        ArrayList<TerrainPoints> pT = new ArrayList<TerrainPoints>();
        ArrayList<TerrainSegment> sT = new ArrayList<TerrainSegment>();
        
        ArrayList<TerrainPoints> listPT = new ArrayList<TerrainPoints>(this.getI().getMenuPrincipal().getMenuCreation().getSaisiePointTerrain().getP());
            for (TerrainPoints TP: listPT){   //redessine les premiers points
                TP.draw(context);
            }        
        
        for (TerrainTriangle TT: modele.getTerrainTriangles()){  //dessine tous les points et segments terrain
            
            //TT.draw(context);
            
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
            
        }
        
        for (Noeud n: modele.getNoeuds()){ //dessine tous les noeuds
            n.draw(context);
        }
        for (Barre b: modele.getBarres()){ //dessine toutes les barres
            b.draw(context);
        }
    }
    
    public void zoom(double zoomRatio, double posX, double posY){
        double scaleX = canvas.getScaleX(); double scaleY = canvas.getScaleY();
        canvas.setScaleX(canvas.getScaleX()*zoomRatio); 
        canvas.setScaleY(canvas.getScaleY()*zoomRatio);  
        double neuScaleX = canvas.getScaleX(); double neuScaleY = canvas.getScaleX();
        this.redraw();
    }
    
    
    
    /**
     * @return the I
     */
    public Interface getI() {
        return I;
    }

    /**
     * @return the canvas
     */
    public Canvas getCanvas() {
        return canvas;
    }
    
    
    
    
 
}

