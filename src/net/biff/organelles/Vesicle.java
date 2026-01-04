package net.biff.organelles;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Vesicle extends Organelle{
    private static Color color = new Color(118, 179, 100);
    public Vesicle(int x, int y){
        hitbox = new Ellipse2D.Double(x,y,25,25);
    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.draw(hitbox);
    }
}
