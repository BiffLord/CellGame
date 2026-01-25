package net.biff;

import net.biff.organelles.Organelle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.List;
import java.lang.Runnable;

public class Screen extends JPanel implements Runnable, MouseListener {
    private final List<Organelle> organelles;
    public Thread gameLoop;

    private final boolean guides = false;
    Font font;

    byte GUI = -128;
    private final int FPS = 60;

    private static final Color cytoplasm = new Color(147,168,212);
    private static final Color NADH = new Color(93, 9, 224);

    private Shape[] moves = new Shape[1];
    TextBox[] texts = new TextBox[]{new TextBox("You need ATP Energy. Click the Cytoplasm to discover Glycolysis",(short)400, (short) 70,24)};

    public Screen(List<Organelle> orgs){
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
    private Polygon regularPolygon(int x, int y, int radius, int points){
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
                switch (moves.length){
                    case 1:
                        if(moves[0].getBounds().x+ moves[0].getBounds().width/2 <400){
                            AffineTransform translate = AffineTransform.getTranslateInstance(1,0);
                            moves[0] = translate.createTransformedShape(moves[0]);break;}
                        changeInstruction("So it Splits into two pyruvate...");
                        moves = new Shape[2];
                        moves[0] = regularPolygon(400,350,50,6);
                        moves[1] = regularPolygon(400,450,50,6);
                        break;
                    case 2:
                        if(moves[0].getBounds().x+ moves[0].getBounds().width/2 < 600){
                            AffineTransform translateDown = AffineTransform.getTranslateInstance(1,0.25);
                            AffineTransform translateUp = AffineTransform.getTranslateInstance(1,-0.25);
                            moves[0] = translateUp.createTransformedShape(moves[0]);
                            moves[1]=translateDown.createTransformedShape(moves[1]);
                            break;}
                        changeInstruction("Creating 2 NADH and 2 ATP!");
                        moves = new Shape[]{moves[0],moves[1],null,null,null,null};
                        moves[2] = new Ellipse2D.Double(370,320,60,60);
                        moves[3] = new Ellipse2D.Double(370,420,60,60);
                        moves[4] = new Ellipse2D.Double(300,360,80,80);
                        moves[5] = new Ellipse2D.Double(420,360,80,80);

                        addText("ATP", (short) 400, (short) 350,16);
                        addText("ATP", (short) 400, (short) 450,16);
                        addText("NADH", (short) 340, (short) 400,20);
                        addText("NADH", (short) 460, (short) 400,20);
                        break;
                }
        }
    }
    private void resetTexts(){texts = new TextBox[]{texts[0]};}
    private void addText(String text, short x, short y, int size){
        TextBox[] temp = new TextBox[texts.length+1];
        for (int i = 0; i<texts.length;i++){
            temp[i] = texts[i];
        }
        temp[texts.length] = new TextBox(text,x,y,size);
        texts = temp;
    }
    private void changeInstruction(String text){
        texts[0].setText(text);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);

        if (guides) {guide(g2d);}
        if (GUI == -128) {
            organelles.forEach(x -> x.draw(g2d));

            //g2d.setColor(Color.ORANGE);organelles.forEach(x->g2d.draw(x.hitbox));
        }
        else if (GUI == -127) {
            g2d.setColor(Color.DARK_GRAY);
            for (int i = 0; i<moves.length;i++){
                g2d.setColor((i<=1)? Color.DARK_GRAY:(i<=3)? Color.YELLOW:NADH);
                g2d.fill(moves[i]);
            }
        }
        g2d.setColor(Color.black);
        for (TextBox txbx:texts){txbx.draw(g2d);}
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
                if (organelles.get(0).hitbox.contains(e.getX(),e.getY())){GUI = -127;changeInstruction("Glucose is too complex and big to enter the mitochondria");}
                moves[0] = regularPolygon(150,400,100,6);
                this.setBackground(cytoplasm);
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