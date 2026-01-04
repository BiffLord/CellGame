package net.biff.organelles;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class GolgiApparatus extends Organelle {
    private static final Color background = new Color(147,168,212);
    private List<Ellipse2D> parts = new ArrayList<>();
    public GolgiApparatus(float x, int y){
        parts.add(oval(x,y,37,200));
        for (int i = -1; i <= 1; i += 2){
            parts.add(oval(x + 37.5f * i,400,37,190));
            parts.add(oval(x+52.5f*i,400,37,190));
            parts.add(oval(x+62.5f*i,400,37,150));
            parts.add(oval(x+77.5f*i,400,37,150));
        }
        hitbox = oval(x,y,170,200);
    }
    @Override
    public void draw(Graphics2D g2d) {
        for (int i = 0; i < parts.size(); i++){
            g2d.setColor((i%2==0 && i != 0)? background : Color.PINK);
            g2d.fill(parts.get(i));
        }
    }
    private Ellipse2D oval(float x, float y, int width, int height){
        return  new Ellipse2D.Double(x- (double) width /2,y- (double) height /2,width,height);
    }
}
