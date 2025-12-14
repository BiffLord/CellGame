package net.biff.organelles;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class Mitochondrion extends Organelle{
    public Mitochondrion(double rotate, int x, int y) {
        Ellipse2D ellipse = new Ellipse2D.Double(x, y, 100, 50);
        AffineTransform rotator = AffineTransform.getRotateInstance(Math.toRadians(rotate),
                ellipse.getCenterX(),
                ellipse.getCenterY());
        hitbox = rotator.createTransformedShape(ellipse);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(smallStroke);
        g2d.draw(hitbox);

    }
}
