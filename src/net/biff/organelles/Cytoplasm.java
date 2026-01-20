package net.biff.organelles;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class Cytoplasm extends Organelle{
    private static boolean demo = true;
    private static final Color color = new Color(147,168,212);
    public Cytoplasm(){
        int radius = 600;
        int coordinate = 400 - radius/2;
        hitbox = new Ellipse2D.Double(coordinate,coordinate,radius,radius);
    }
    public boolean getDemo(){return demo;}
    public void updateDemo(){demo = false;}

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fill(hitbox);
    }
}
