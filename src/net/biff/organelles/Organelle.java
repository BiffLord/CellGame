package net.biff.organelles;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public abstract class Organelle {
    public Shape hitbox;
    public boolean visible = true;
    public BasicStroke smallStroke = new BasicStroke(5.0f);
    public BasicStroke stroke = new BasicStroke(10.0f);

    public abstract void draw(Graphics2D g2d);
}
