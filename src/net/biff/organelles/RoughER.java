package net.biff.organelles;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

//Rough Endoplasmic Reticulum
public class RoughER extends Organelle{
    private List<Shape> sacs = new ArrayList<>();
    private List<Ellipse2D> ribosomes = new ArrayList<>();
    private List<Line2D> lines = new ArrayList<>();

    public RoughER(){
        Ellipse2D sac = new Ellipse2D.Double(330,250,25,155);
        sacs.add(rotate(70d,sac));
        sac = new Ellipse2D.Double(370,200,25,125);
        sacs.add(rotate(75d, sac));
        sac = new Ellipse2D.Double(350,260,25,75);
        sacs.add(rotate(10d,sac));
        sac = new Ellipse2D.Double(300,180,25,150);
        sacs.add(rotate(70d, sac));
        sac = new Ellipse2D.Double(325,235,25,40);
        sacs.add(rotate(-20d,sac));
        sac = new Ellipse2D.Double(290,350,25,100);
        sacs.add(rotate(20d, sac));
    }
    private Shape rotate(Double degrees, Ellipse2D shape){
        AffineTransform rotator = AffineTransform.getRotateInstance(Math.toRadians(degrees),
                shape.getCenterX(),
                shape.getCenterY());
        return rotator.createTransformedShape(shape);
    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(new Color(147,168,212));
        sacs.forEach(g2d::fill);
    }
}