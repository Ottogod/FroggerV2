package GraphicalElements;

import MovingElements.Frog;
import MovingElements.Obstacle;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public interface IFroggerGraphics {
    abstract void create_case(float left, float top, float right, float bottom, float r, float g, float b);
    abstract void show_frog(Frog frog, PImage icon);
    abstract void show_car(Obstacle.Car car);
    abstract void show_trunk(Obstacle.Trunk trunk);
}
