package net.biff.organelles;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Cytoplasm extends Organelle{
    public Cytoplasm(){
        int radius = 600;
        int coordinate = 400 - radius/2;
        hitbox = new Ellipse2D.Double(coordinate,coordinate,radius,radius);
    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(new Color(147,168,212));
        g2d.fill(hitbox);
    }
}
