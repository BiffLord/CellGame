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
    Font resoruces;
    byte GUI = -128;
    private String text = "You need ATP Energy. Click the Cytoplasm to discover Glycolysis";
    private static final Color cytolasm = new Color(147,168,212);
    private Polygon[] moveables = new Polygon[1];

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
    private Polygon sugar(int x, int y, int radius, int points){
        //K'th angle  = originalAngel +(2(PI)(K)/sides)
        Polygon polygon = new Polygon();
        double angle = Math.toRadians(0);
        for (int k = 0; k < points; k++){
            double currentAngle = angle + ((2*Math.PI*k)/points);
            polygon.addPoint((int) (x+(radius*Math.cos(currentAngle))), (int) (y+(radius*Math.sin(currentAngle))));
        }
        return polygon;
    }
    private void update(){
        switch (GUI){
            case -127:
                switch (moveables.length){
                    case 1:
                        if(moveables[0].getBounds().x+ moveables[0].getBounds().width/2 <400){
                            moveables[0].translate(1,0);}
                        else{text = "So it Splits into two pyruvate...";
                            moveables = new Polygon[2];
                            moveables[0] = sugar(400,350,50,6);
                            moveables[1] = sugar(400,450,50,6);
                        }break;
                    case 2:
                        if(moveables[0].getBounds().x+ moveables[0].getBounds().width/2 < 600){
                            moveables[0].translate(1,-1);
                            moveables[1].translate(1,1);
                        }else{
                        }
                        break;
                }
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        g2d.drawString(text, 10, 50);
        if (guides) {guide(g2d);}
        if (GUI == -128) {
            organelles.forEach(x -> x.draw(g2d));

            g2d.setColor(Color.ORANGE);organelles.forEach(x->g2d.draw(x.hitbox));
        }
        else if (GUI == -127) {
            this.setBackground(cytolasm);
            g2d.setColor(Color.DARK_GRAY);
            for (Polygon p: moveables){g2d.fill(p);}
        }
        g2d.dispose();
        g.dispose();
    }

    @Override
    public void run() {
        while (gameLoop != null){
            long startTime = System.nanoTime();
            update();
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
        switch (GUI){
            case -128:
                if (organelles.get(0).hitbox.contains(e.getX(),e.getY())){GUI = -127;text = "Glucose is too complex and big to enter the mitochondria";}
                moveables[0] =sugar(150,400,100,6);
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