package net.biff.organelles;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class Lysosome extends Organelle{
    private final List<Ellipse2D> enzymes = new ArrayList<>();
    public Lysosome(int x, int y){
        hitbox = new Ellipse2D.Double(x-50,y-50,100,100);
        newEnzyme(x-10,y-10,10);
        newEnzyme(x-40,y,15);
        newEnzyme(x+10,y+20,20);
        newEnzyme(x+10,y-20,10);
        newEnzyme(x-20,y-30,15);
        newEnzyme(x-20,y+20,10);
    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.ORANGE);
        g2d.fill(hitbox);
        g2d.setColor(Color.BLACK);
        g2d.draw(hitbox);
        g2d.setColor(Color.YELLOW);
        enzymes.forEach(g2d::fill);
    }
    private void newEnzyme(int x, int y, int radius){
        enzymes.add(new Ellipse2D.Double(x,y,radius,radius));
    }
}
