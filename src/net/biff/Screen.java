package net.biff;

import net.biff.molecules.Macromolecule;
import net.biff.organelles.Organelle;
import net.biff.molecules.Stack;
import net.biff.organelles.Vacuole;


import java.util.List;
import java.lang.Runnable;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;



public class Screen extends JPanel implements Runnable{
    private final List<Organelle> organelles;
    public Thread gameLoop;

    private final boolean guides = true;

    byte GUI = -128;
    private final int FPS = 60;
    private byte tick = 0;
    private final byte MAX_TICKS = 60;

    private static final Color cytoplasm = new Color(147,168,212);
    private static final Color NADH = new Color(93, 9, 224);
    private Shape[] moves = new Shape[1];
    TextBox[] texts = new TextBox[]{new TextBox("You need ATP Energy. Click the Cytoplasm to discover Glycolysis",(short)400, (short) 50,24)};
    Rectangle2D[] buttons = new Rectangle2D[0];
    public Stack[] inventory;
    public Screen(List<Organelle> orgs){
        setBackground(Color.WHITE);
        this.organelles = orgs;
        addMouseListener(new MouseHandler());
        inventory = new Stack[]{new Stack(Macromolecule.ATP,0),
                new Stack(Macromolecule.CARBOHYDRATE,0),
                new Stack(Macromolecule.PROTEIN,0),
                new Stack(Macromolecule.LIPID,0),
        };
        addText("ATP: 0", (short) 100, (short) 25,14);
        addText("CARBOHYDRATE: 0", (short) 300, (short) 25,14);
        addText("PROTEIN: 0", (short) 500, (short) 25,14);
        addText("LIPID: 0", (short) 700, (short) 25,14);
        ((Vacuole)organelles.get(10)).inventory = inventory;

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
    private Shape regularPolygon(int x, int y, int radius, int points,double degrees){
        //K'th angle = originalAngel +(2(PI)(K)/sides)
        Polygon polygon = new Polygon();
        double angle = Math.toRadians(degrees);
        for (int k = 0; k < points; k++){
            double currentAngle = angle + ((2*Math.PI*k)/points);
            polygon.addPoint((int) (x+(radius*Math.cos(currentAngle))), (int) (y+(radius*Math.sin(currentAngle))));
        }
        return polygon;
    }
    private void update(){
        if (tick == 59){
            resources();
        }

        switch (GUI){
            case -127:
                switch (moves.length){
                    case 1:
                        if(moves[0].getBounds().x+ moves[0].getBounds().width/2 <400){
                            AffineTransform translate = AffineTransform.getTranslateInstance(1,0);
                            moves[0] = translate.createTransformedShape(moves[0]);break;}
                        changeInstruction("So it Splits into two pyruvate...");
                        moves = new Shape[2];
                        moves[0] = regularPolygon(400,350,50,6,45);
                        moves[1] = regularPolygon(400,450,50,6,-45);
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

                        addText("ATP: ", (short) 400, (short) 350,16);
                        addText("ATP: ", (short) 400, (short) 450,16);
                        addText("NADH: ", (short) 340, (short) 400,20);
                        addText("NADH: ", (short) 460, (short) 400,20);

                        break;
                    default:
                        if ((tick+1)%20==0 && buttons.length==0){
                            addText("Back", (short)625, (short) 75,16);
                            addButton(575,60,100,30);
                        }
                }
        }
    }
    private void resetTexts(){texts = new TextBox[]{texts[0],
            texts[1],
            texts[2],
            texts[3],
            texts[4]};
    }
    private void addText(String text, short x, short y, int size){
        TextBox[] temp = new TextBox[texts.length+1];
        System.arraycopy(texts, 0, temp, 0, texts.length);
        temp[texts.length] = new TextBox(text,x,y,size);
        texts = temp;
    }
    private void addButton(int x, int y,int w, int h){
        Rectangle2D[] temp = new Rectangle2D[buttons.length+1];
        System.arraycopy(buttons, 0, temp, 0, buttons.length);
        temp[buttons.length] = new Rectangle2D.Double(x,y,w,h);
        buttons = temp;
    }
    private void changeInstruction(String text){
        texts[0].setText(text);
    }
    private void resources(){
        for (int i = 0;i<4;i++){
            texts[i+1].setText(inventory[i].toString());
        }
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);


        if (GUI == -128) {
            organelles.forEach(x -> x.draw(g2d));

            //g2d.setColor(Color.ORANGE);organelles.forEach(x->g2d.draw(x.hitbox));
        }
        else if (GUI == -127) {
            g2d.setColor(Color.DARK_GRAY);
            for (int i = 0; i<moves.length;i++){
                g2d.setColor((i<=1)? Color.DARK_GRAY:(i<=3)? Color.YELLOW:NADH);
                g2d.fill(moves[i]);
                for(Rectangle2D rect:buttons){
                    g2d.setColor(Color.PINK);
                    g2d.fill(rect);
                    g2d.setColor(Color.ORANGE);
                    g2d.draw(rect);
                }
            }
        }
        g2d.setColor(Color.black);
        for (TextBox txbx:texts){txbx.draw(g2d);}
        if (guides) {guide(g2d);}
        g2d.dispose();
        g.dispose();
    }

    @Override
    public void run() {
        while (gameLoop != null){
            long startTime = System.nanoTime();
            update();
            repaint();
            tick = (byte)((tick+1)%MAX_TICKS);
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
    private class MouseHandler extends MouseAdapter{
        @Override
        public void mouseReleased(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            switch (GUI){
                case -128:
                    if (organelles.get(0).hitbox.contains(x,y)){

                        changeInstruction("Glucose is too complex and big to enter the mitochondria");
                        moves[0] = regularPolygon(150,400,100,6,0);
                        setBackground(cytoplasm);
                        GUI = -127;
                    }
                case -127:
                    for (Rectangle2D button : buttons){
                        if (button.contains(x,y)){
                            resetTexts();
                            moves = new Shape[1];
                            setBackground(Color.white);
                            buttons = new Rectangle2D[0];
                            GUI = -128;
                        }
                    }
            }
        }
    }
}
