package net.biff.organelles;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Nucleus extends Organelle{
    Ellipse2D nucleolis;
    public Nucleus() {
        hitbox = new Ellipse2D.Double(325,325,150,150);
        nucleolis = new Ellipse2D.Double(387.5,387.5,25,25);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(new Color(133, 29, 179));
        g2d.fill(hitbox);
        g2d.setColor(new Color(109, 19, 149));
        g2d.fill(nucleolis);
    }
}
