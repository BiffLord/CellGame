package net.biff;

import net.biff.organelles.Organelle;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.lang.Runnable;

public class Screen extends JPanel implements Runnable{
    private final List<Organelle> organelles;
    public Thread gameLoop;

    public Screen(List<Organelle> orgs){
        this.organelles = orgs;
        gameLoop = new Thread(this);
        gameLoop.start();
    }

    @Override
    protected void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            organelles.forEach(x -> {
                if (x.visible) {
                    x.draw(g2d);
                }
            });
            g2d.dispose();
            g.dispose();
    }

    @Override
    public void run() {
        while (gameLoop != null){
            long startTime = System.nanoTime();
            repaint();
            long endTime = System.nanoTime();
            long n;
            long x;
            final int FPS = 60;
            if ((n = endTime-startTime) < (x = 1000000000/ FPS)){
                try {
                    synchronized (gameLoop) {
                        gameLoop.wait(x/1000000-n/1000000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}