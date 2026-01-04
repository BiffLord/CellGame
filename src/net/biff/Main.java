package net.biff;

import net.biff.organelles.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) {
        List<Organelle> organelles = new ArrayList<>();
        organelles.add(new Cytoplasm());
        organelles.add(new Membrane());
        organelles.add(new Mitochondrion(-45,525,575));
        organelles.add(new Nucleus());
        organelles.add(new RoughER());
        organelles.add(new SmoothER());
        organelles.add(new Lysosome(550,250));
        organelles.add(new GolgiApparatus(587.5f,400));
        organelles.add(new Centriole(375,150));
        organelles.add(new Ribosome(425,505));
        organelles.add(new Vacuole(475,175));
        JFrame window = new JFrame("Cell Game");
        window.setSize(800,800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        Screen screen = new Screen(organelles);
        window.add(screen);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}