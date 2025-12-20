package net.biff.organelles;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Membrane extends Organelle{
    public Membrane(){
        hitbox = new Area(new Ellipse2D.Double(150,150,500,500));
        visible = true;
    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(stroke);
        g2d.draw(hitbox);

    }
}