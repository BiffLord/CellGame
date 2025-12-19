package net.biff.organelles;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mitochondrion extends Organelle{
    private List<Shape> matrix = new ArrayList<>();
    private double centerY;
    private double centerX;
    public Mitochondrion(double rotate, int x, int y) {
        Ellipse2D ellipse = new Ellipse2D.Double(x, y, 100, 50);
        centerY = ellipse.getCenterY();
        centerX = ellipse.getCenterX();
        AffineTransform rotator = AffineTransform.getRotateInstance(Math.toRadians(rotate),
                ellipse.getCenterX(),
                ellipse.getCenterY());;
        matrixComponent(30,-20);
        matrixComponent(15,0);
        matrixComponent(0,-4);
        matrixComponent(-15,-24);
        matrixComponent(-30,-3);
        matrixComponent(-47,-12.5);

        matrix = matrix.stream().map(rotator::createTransformedShape).collect(Collectors.toList());
        hitbox = rotator.createTransformedShape(ellipse);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(smallStroke);
        g2d.setColor(new Color(152, 0,0));
        g2d.fill(hitbox);
        g2d.setColor(new Color(204, 0,0));
        g2d.draw(hitbox);
        matrix.forEach(g2d::fill);

    }
    private void matrixComponent(double x, double y){
        matrix.add(new Ellipse2D.Double(centerX+x,centerY+y,12,25));
    }
}
