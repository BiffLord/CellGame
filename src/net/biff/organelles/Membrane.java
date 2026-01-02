package net.biff.organelles;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Membrane extends Organelle{
    public Membrane(){
        int radius = 600;
        int coordinate = 400 - radius/2;
        hitbox = new Ellipse2D.Double(coordinate,coordinate,radius,radius);
        visible = true;
    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(stroke);
        g2d.draw(hitbox);

    }
}