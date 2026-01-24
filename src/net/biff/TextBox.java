package net.biff;

import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Canvas;

public class TextBox {
    private String text;
    Font font;
    private short x;
    private short y;
    boolean init = false;
    public TextBox(String text, short x, short y,int size) {
        font = new Font("texgyretermes-regular", Font.PLAIN, size);
        this.text = text;
        this.x = x;
        this.y = y;
        initialize();
    }
    public void draw(Graphics2D g2d){
        g2d.setFont(font);
        g2d.drawString(text,x,y);
    }
    private void initialize(){
        FontMetrics fm = new Canvas().getGraphics().getFontMetrics(font);
        x -=(short) fm.stringWidth(text);
    }
}