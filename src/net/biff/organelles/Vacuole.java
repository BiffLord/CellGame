package net.biff.organelles;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Vacuole extends Organelle{
    private static final Color color = new Color(133, 100, 179);
    public Vacuole(int x, int y){
        hitbox = new Ellipse2D.Double(x-37.5f,y-37.5f,75,75);
    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fill(hitbox);
    }
}
