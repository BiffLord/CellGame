package net.biff.organelles;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Centriole extends Organelle{
    List<Shape> components = new ArrayList<>();
    public Centriole(int x, int y){
        getBar(x,y,0);
        getBar(x,y,120);
        hitbox = oval(x,y,90,90);
        //components.add(rect(150,100,100,25,0));
        //components.add(oval(100,100,10,25,0));
        //components.add(rect(150,100,100,25,-45));
    }
    @Override
    public void draw(Graphics2D g2d) {
        //bar -
        g2d.setStroke(smallestStroke);
        for (int i = 0; i < 4; i++){
            g2d.setColor((i%2 == 0)? Color.DARK_GRAY : Color.GRAY);
            g2d.fill(components.get(i));
            g2d.setColor(Color.BLACK);
            g2d.draw(components.get(i));
        }


    }
    private Ellipse2D oval(float x, int y,int w, int h){
        return new Ellipse2D.Double(x- (double) w /2,y- (double) h /2,w,h);
    }
    private Rectangle2D rect(int x, int y,int w, int h){
        return new Rectangle2D.Double(x- (double) w /2,y- (double) h /2,w,h);
    }
    private void getBar(int x, int y, float angle){
        Shape bar = rect(x,y,75,25);
        Shape flat = oval(x-37.5f,y,10,25);
        AffineTransform rotator = AffineTransform.getRotateInstance(Math.toRadians(angle),x,y);
        components.add(rotator.createTransformedShape(bar));
        components.add(rotator.createTransformedShape(flat));
    }
}
