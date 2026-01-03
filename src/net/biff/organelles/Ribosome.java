package net.biff.organelles;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ribosome extends Organelle implements Ribosomal{
    public Ribosome(int x, int y){
        hitbox = new Ellipse2D.Double(x,y,120,120);
        createRibosome(450,550);
        createRibosome(x+60,y+60);
        createRibosome(490,540);
        createRibosome(500,600);
        createRibosome(525,575);
        createRibosome(460,590);
    }
    @Override
    public void draw(Graphics2D g2d){
        ribosomes.forEach(g2d::draw);
    }
}
