package net.biff.organelles;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

//Rough Endoplasmic Reticulum
public class RoughER extends Organelle implements Ribosomal{
    private final List<Shape> sacs = new ArrayList<>();

    public RoughER(){
        sac(330,250,25,155,70d);
        sac(370,200,25,125,75d);
        sac(350,260,25,75,10d);
        sac(300,180,25,150,70d);
        sac(325,235,25,40,-20d);
        sac(290,350,25,100,20d);
        sac(295,335,25,45,-30d);
        sac(250,275,25,90,80d);
        sac(280,300,25,65,-70d);
        sac(200,285,25,140,20d);
        sac(350,420,25,125,-60d);
        sac(350,480,25,100,-60d);
        sac(350,480,25,50,75d);
        sac(250,400,25,120,-50d);
        sac(300,450,20,50,75d);
        createRibosome(300,240);
        createRibosome(260,255);
        createRibosome(250,278);
        createRibosome(360,240);
        createRibosome(380,295);
        createRibosome(390,240);
        createRibosome(400,260);
        createRibosome(325,275);
        createRibosome(345,290);
        createRibosome(320,315);
        createRibosome(335,335);
        createRibosome(250,305);
        createRibosome(200,400);
        createRibosome(195,350);
        createRibosome(215,300);
        createRibosome(210,375);
        createRibosome(278,420);
        createRibosome(290,380);
        createRibosome(305,410);
        createRibosome(240,460);
        createRibosome(260,440);
        createRibosome(300,490);
        createRibosome(225,440);
        createRibosome(400,490);
        createRibosome(345,457);
        createRibosome(338,480);
        createRibosome(400,550);
        createRibosome(350,535);
        Rectangle2D hitboxSection = new Rectangle2D.Double(250,250,100,100);
        Area hb = new Area(hitboxSection);
        hitboxSection = new Rectangle2D.Double(300,200,100,110);
        hb.add(new Area(hitboxSection));
        Ellipse2D hitboxB = new Ellipse2D.Double(400,225,50,50);
        hb.add(new Area(hitboxB));
        hitboxSection = new Rectangle2D.Double(350,290,80,35);
        hb.add(new Area(hitboxSection));
        hitboxSection = new Rectangle2D.Double(200,300,50,100);
        hb.add(new Area(hitboxSection));
        hitboxSection = new Rectangle2D.Double(180,350,40,75);
        hb.add(new Area(hitboxSection));
        hitboxSection = new Rectangle2D.Double(225,275,25,25);
        hb.add(new Area(hitboxSection));
        hitboxSection = new Rectangle2D.Double(250,350,75,100);
        hb.add(new Area(hitboxSection));
        hitboxSection = new Rectangle2D.Double(200,400,100,100);
        hb.add(new Area(hitboxSection));
        hitboxSection = new Rectangle2D.Double(300,450,50,100);
        hb.add(new Area(hitboxSection));
        hitboxSection = new Rectangle2D.Double(350,468,50,82);
        hb.add(new Area(hitboxSection));
        hitboxSection = new Rectangle2D.Double(400,490,40,25);
        hb.add(new Area(hitboxSection));
        hitboxSection = new Rectangle2D.Double(380,540,40,25);
        hb.add(new Area(hitboxSection));
        hitbox = hb;
    }
    private Shape rotate(Double degrees, Ellipse2D shape){
        AffineTransform rotator = AffineTransform.getRotateInstance(Math.toRadians(degrees),
                shape.getCenterX(),
                shape.getCenterY());
        return rotator.createTransformedShape(shape);
    }
    private void sac(int x, int y, int width, int height, double rotation){
        Ellipse2D sac = new Ellipse2D.Double(x,y,width,height);
        sacs.add(rotate(rotation,sac));
    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(new Color(147,168,212));
        sacs.forEach(g2d::fill);
        g2d.setColor(Color.BLACK);
        ribosomes.forEach(g2d::fill);
    }
}