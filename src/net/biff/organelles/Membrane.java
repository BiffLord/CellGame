package net.biff.organelles;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Membrane extends Organelle{
    public Membrane(){
        hitbox = new Ellipse2D.Double(200,200,400,400);
        visible = true;
    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(stroke);
        g2d.draw(hitbox);

    }
}
