package net.biff.organelles;

import net.biff.molecules.Macromolecule;
import net.biff.molecules.Stack;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Vacuole extends Organelle{
    private static final Color color = new Color(133, 100, 179);
    public Stack atps = new Stack(Macromolecule.ATP,0);
    public Stack carbs = new Stack(Macromolecule.CARBOHYDRATE,0);
    public Stack protiens = new Stack(Macromolecule.PROTIEN,0);
    public Stack lipids = new Stack(Macromolecule.LIPID,0);
    public Stack[] inventory = new Stack[]{atps,carbs,protiens,lipids};
    public Vacuole(int x, int y){
        hitbox = new Ellipse2D.Double(x-37.5f,y-37.5f,75,75);
    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fill(hitbox);
    }
}
