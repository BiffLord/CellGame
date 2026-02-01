package net.biff;

import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Canvas;
import java.awt.image.BufferedImage;

public class TextBox {
    private String text;
    Font font;
    private short x;
    private short y;
    public TextBox(String text, short x, short y,int size) {
        font = new Font("texgyretermes-regular", Font.PLAIN, size);
        this.text = text;
        this.x = x;
        this.y=y;
        initializeX();
        initializeY();
    }
    public void draw(Graphics2D g2d){
        g2d.setFont(font);
        g2d.drawString(text,x,y);
    }
    private void initializeX(){
        FontMetrics fm = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics().getFontMetrics(font);
        x -= (short) ((short)fm.stringWidth(text)/2);
    }
    private void initializeY(){
        FontMetrics fm = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics().getFontMetrics(font);
        y = (short) (y-(fm.getHeight()/2)+fm.getAscent());
    }

    public void setText(String text) {
        FontMetrics fm = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics().getFontMetrics(font);
        x += (short) ((short)fm.stringWidth(this.text)/2);
        this.text = text;
        initializeX();
    }
    @Override
    public String toString(){
        return text;
    }
}