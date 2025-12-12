package net.biff.organelles;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public abstract class Organelle {
    public Ellipse2D hitbox;

    public abstract void draw(Graphics2D g2d);
}
