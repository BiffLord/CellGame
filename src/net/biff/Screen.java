package net.biff;

import net.biff.organelles.Organelle;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Screen extends JPanel {
    private List<Organelle> organelles;
    public Screen(List<Organelle> orgs){
        this.organelles = orgs;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        organelles.forEach(x -> {if (x.visible) {x.draw(g2d);}});
        g2d.dispose();
        g.dispose();
    }
}
