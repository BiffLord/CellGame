package net.biff;

import net.biff.organelles.Organelle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.lang.Runnable;

public class Screen extends JPanel implements Runnable, MouseListener {
    private final List<Organelle> organelles;
    public Thread gameLoop;
    private final boolean guides = false;
    Font font;
    private String text = "You need ATP Energy. Click the Cytoplasm";

    public Screen(List<Organelle> orgs){
        try{
            InputStream fontFile = Screen.class.getResourceAsStream("/texgyretermes-regular.otf");
            assert fontFile != null;
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Font.createFont(Font.TRUETYPE_FONT, fontFile));
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
        this.font = new Font("texgyretermes-regular",Font.PLAIN, 24);
        setBackground(Color.WHITE);
        this.organelles = orgs;
        addMouseListener(this);
        gameLoop = new Thread(this);
        gameLoop.start();
    }
    private void guide(Graphics2D g2d){
        g2d.setColor(Color.BLACK);
        for (int coordinate = 0; coordinate <= 800; coordinate += 50){
            g2d.drawLine(coordinate,0,coordinate,800);
            g2d.drawLine(0,coordinate,800,coordinate);
        }
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
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        g2d.drawString(text,10,50);
        //organelles.forEach(x->g2d.draw(x.hitbox));
        if (guides) {guide(g2d);}
        //g2d.setColor(Color.ORANGE);
        //g2d.draw(organelles.get(6).hitbox);
        //organelles.forEach(x->g2d.draw(x.hitbox));
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (organelles.get(2).hitbox.contains(e.getX(),e.getY()) && organelles.get(2).visible){
            organelles.forEach(x->x.visible = false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}