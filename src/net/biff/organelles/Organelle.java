package net.biff.organelles;

import java.awt.*;

public abstract class Organelle {
    public Shape hitbox;
    public boolean visible = true;
    public static BasicStroke smallStroke = new BasicStroke(7f);
    public static BasicStroke stroke = new BasicStroke(10.0f);
    public static BasicStroke smallerStroke = new BasicStroke(5f);

    public abstract void draw(Graphics2D g2d);
}
