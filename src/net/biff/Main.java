package net.biff;

import net.biff.organelles.Membrane;
import net.biff.organelles.Mitochondrion;
import net.biff.organelles.Organelle;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) {
        List<Organelle> organelles = new ArrayList<>();
        organelles.add(new Membrane());
        organelles.add(new Mitochondrion(-45,450,500));
        JFrame window = new JFrame("Cell Game");
        window.setSize(800,800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        Screen screen = new Screen(organelles);
        window.add(screen);
        window.setVisible(true);

    }

}
