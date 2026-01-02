package net.biff.organelles;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;
import java.awt.geom.Ellipse2D;

public class SmoothER extends Organelle{
    private final List<Shape> tubes = new ArrayList<>();
    public SmoothER(){
        //Exceptionaly Based
        Shape based = oval(250,200,150,75,-45d);
        Area hb = new Area(based);
        based = oval(170,300,175,75,-70d);
        hb.add(new Area(based));
        based = oval(160,435,60,175,-10d);
        hb.add(new Area(based));
        based = oval(235,555,175,75,40d);
        hb.add(new Area(based));
        based = oval(225,250,50,75,0);
        hb.add(new Area(based));
        hitbox = hb;
        tubes.add(oval(275,175,50,50,0d));
        tubes.add(oval(235,215,25,25,0d));
        tubes.add(oval(185,270,100,60,-70d));
        tubes.add(oval(200,250,20,20,0d));
        tubes.add(oval(175,300,20,20,0d));
        tubes.add(oval(150,350,40,40,0d));
        tubes.add(oval(165,400,30,30,0d));
        tubes.add(oval(150,450,20,20,0d));
        tubes.add(oval(180,525,25,25,0d));
        tubes.add(oval(225,538,30,30,0d));
        tubes.add(oval(250,576,40,40,0d));


    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(new Color(147,168,250));
        g2d.fill(hitbox);
        tubes.forEach(g2d::fill);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(smallerStroke);
        tubes.forEach(g2d::draw);

    }
    private Shape oval(int x, int y, int width, int height, double angle){
        Ellipse2D circle = new Ellipse2D.Double(x- (double) width /2,y- (double) height /2,width,height);
        AffineTransform rotator = AffineTransform.getRotateInstance(Math.toRadians(angle),
               circle.getCenterX(), circle.getCenterY());
        return rotator.createTransformedShape(circle);
    }
}
