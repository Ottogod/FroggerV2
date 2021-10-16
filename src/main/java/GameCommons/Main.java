package GameCommons;

import MovingElements.Frog;
import MovingElements.Obstacle;
import MovingElements.Obstacle.Car;
import MovingElements.Obstacle.Trunk;
import processing.core.PApplet;
import GraphicalElements.Element;
import processing.core.PImage;

import java.util.ArrayList;

public class Main extends PApplet {

    public PApplet processing;


    Frog frog1;
    Frog frog2;
    ArrayList<ArrayList<Car>> cars;
    ArrayList<ArrayList<Trunk>> trunks;
    Element board;
    Game game;
    int ranges = 14;
    int columns = 16;
    int grid = 50;
    int separate = ranges/2 ;
    float t1;
    float t2;
    float t_fin;
    PImage im_frog;
    PImage im_frog2;
    int PlayerMode;


    public static void main(String[] args) {
        PApplet.main(new String[]{Main.class.getName()});
    }

    @Override
    public void settings() {
        PlayerMode =2;
        processing = this;
        board = new Element(processing);
        game = new Game(grid, ranges, columns);
        size(game.getGame_width(), game.getGame_height());
    }

    @Override
    public void setup() {
        t1 = millis();

        //frog = game.set_Frog();
        if (PlayerMode==2){
        frog1 = game.set_Frog2P1();
        frog2 = game.set_Frog2P2();
        }
        else {
            frog2 = game.set_Frog();
        }
        im_frog2 = loadImage("C:\\Users\\alexa\\IdeaProjects\\FroggerV2\\src\\main\\java\\Images\\unnamed.png");
        im_frog = loadImage("C:\\Users\\alexa\\IdeaProjects\\FroggerV2\\src\\main\\java\\Images\\frog.png");

        cars = new ArrayList<ArrayList<Car>>();
        trunks = new ArrayList<ArrayList<Trunk>>();

        for (int i=0; i<separate; i++){
            ArrayList<Car> range_ip1 = game.car_range(i+1);
            cars.add(range_ip1);
        }
        for (int i=0; i<ranges-3-separate; i++){
            ArrayList<Trunk> rangee = game.trunk_range(i+separate+2);
            trunks.add(rangee);
        }
    }




    @Override
    public void draw() {
        background(0);

        board.create_case(0, (separate - 1) * grid, game.getGame_width(), game.getGame_height() - grid, 20, 20, 30);
        board.create_case(0, (separate - 2) * grid, game.getGame_width(), grid, 0, 50, 100);
        board.create_case(0, grid, game.getGame_width(), 0, 295, 192, 203);

        if (PlayerMode == 2) {

            for (int i = 0; i < cars.size(); i++) {
                for (int j = 0; j < cars.get(i).size(); j++) {
                    Car car = cars.get(i).get(j);
                    car.move(car.getSpeed(), 0);
                    board.show_car(car);
                    if (frog1.intersect(car)) {
                        frog1.setCar_intersection(true);
                    }
                    if (frog2.intersect(car)) {
                        frog2.setCar_intersection(true);
                    }
                }
            }
            int count_inter = 0;
            int count2 = 0;
            for (int i = 0; i < trunks.size(); i++) {
                for (int j = 0; j < trunks.get(i).size(); j++) {
                    Trunk trunk = trunks.get(i).get(j);
                    trunk.move(trunk.getSpeed(), 0);
                    board.show_trunk(trunk);
                    if (frog1.intersect(trunk)) {
                        count_inter++;
                        if (count_inter <= 1) {
                            frog1.setLeft(frog1.getLeft() + trunk.getSpeed());
                            frog1.setRight(frog1.getRight() + trunk.getSpeed());
                        }
                    }
                    if (frog2.intersect(trunk)) {
                        count2++;
                        if (count2 <= 1) {
                            frog2.setLeft(frog2.getLeft() + trunk.getSpeed());
                            frog2.setRight(frog2.getRight() + trunk.getSpeed());
                        }
                    }
                }
            }


            if (frog1.getRange() > (separate + 1) && frog1.getRange() < ranges - 1 && count_inter == 0) {
                frog1.setTrunk_intersection(false);
            }
            if (frog2.getRange() > (separate + 1) && frog2.getRange() < ranges - 1 && count2 == 0) {
                frog2.setTrunk_intersection(false);
            }
            board.show_frog(frog1, im_frog);
            board.show_frog(frog2, im_frog2);
            game.deal_state_frog2P1(frog1);
            game.deal_state_frog2P2(frog2);
        }
        else {
            for (int i = 0; i < cars.size(); i++) {
                for (int j = 0; j < cars.get(i).size(); j++) {
                    Car car = cars.get(i).get(j);
                    car.move(car.getSpeed(), 0);
                    board.show_car(car);
                    if (frog2.intersect(car)) {
                        frog2.setCar_intersection(true);
                    }
                }
            }
            int count2 = 0;
            for (int i = 0; i < trunks.size(); i++) {
                for (int j = 0; j < trunks.get(i).size(); j++) {
                    Trunk trunk = trunks.get(i).get(j);
                    trunk.move(trunk.getSpeed(), 0);
                    board.show_trunk(trunk);
                    if (frog2.intersect(trunk)) {
                        count2++;
                        if (count2 <= 1) {
                            frog2.setLeft(frog2.getLeft() + trunk.getSpeed());
                            frog2.setRight(frog2.getRight() + trunk.getSpeed());
                        }
                    }
                }
            }

            if (frog2.getRange() > (separate + 1) && frog2.getRange() < ranges - 1 && count2 == 0) {
                frog2.setTrunk_intersection(false);
            }
            board.show_frog(frog2, im_frog2);
            game.deal_state_frog(frog2);

        }


        if (game.getGameState() == true) {
            t2 = millis();
            t_fin = t2 - t1;
            processing.stop();

            fill(255);
            textSize(32);
            text("Conguratulations, you beat Frogger in " + t_fin / 1000 + "s", game.getGame_width() / 2 - 5 * grid, game.getGame_height() / 2);
            }
        }



    @Override
    public void keyPressed() {
        if (keyCode == UP){
            frog2.move(0,-grid);
            frog2.setRange(frog2.getRange()+1);
            //System.out.println("bottom :" + frog.getBottom());

        }
        else if (keyCode == DOWN){
            frog2.move(0,grid);
            frog2.setRange(frog2.getRange()-1);


        }
        else if (keyCode == RIGHT){
            frog2.move(grid, 0);

        }
        else if (keyCode == LEFT){
            frog2.move(-grid,0);

        }
        else if (keyCode == 90){
            frog1.move(0,-grid);
            frog1.setRange(frog1.getRange()+1);


        }
        else if (keyCode == 83){
            frog1.move(0,grid);
            frog1.setRange(frog1.getRange()-1);


        }
        else if (keyCode == 68){
            frog1.move(grid, 0);

        }
        else if (keyCode == 81){
            frog1.move(-grid,0);

        }


    }
}
