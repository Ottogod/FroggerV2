package GraphicalElements;

import GameCommons.Main;
import MovingElements.Frog;
import MovingElements.Obstacle;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Element extends PApplet implements IFroggerGraphics {

    PApplet P;
    PImage im_frog;

    public Element(PApplet P){
        this.P = P;
    }

    @Override
    public void create_case(float x, float y, float w, float h, float r, float g, float b) {
        P.fill(r,g,b);
        //P.noStroke();
        P.rectMode(P.CORNERS);
        P.rect(x, y, w, h);

    }


    @Override
    public void show_frog(Frog frog, PImage icon) {
//        im_frog = P.loadImage("C:\\Users\\alexa\\IdeaProjects\\FroggerV2\\src\\main\\java\\Images\\unnamed.png");
        this.P.image(icon, frog.getLeft(), frog.getBottom(), frog.getWidth(), frog.getHeight());
    }

    @Override
    public void show_car(Obstacle.Car car) {
        create_case(car.getLeft(), car.getTop(), car.getRight(), car.getBottom(), 255, 0, 0);


    }
    @Override
    public void show_trunk(Obstacle.Trunk trunk) {
        create_case(trunk.getLeft(), trunk.getTop(), trunk.getRight(), trunk.getBottom(), 88, 41, 0);
    }
}
