package net.biff.organelles;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
public interface Ribosomal {
    public List<Ellipse2D> ribosomes = new ArrayList<>();
    default public void createRibosome(int x, int y){
        ribosomes.add(new Ellipse2D.Double(x,y,10,10));
    }
}
