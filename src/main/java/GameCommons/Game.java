package GameCommons;

import MovingElements.Obstacle;
import processing.core.PApplet;
import MovingElements.Frog;
import MovingElements.Obstacle.Car;
import MovingElements.Obstacle.Trunk;

import java.util.ArrayList;
import java.util.Random;


public class Game implements IFrog, IEnvironment{
    private int grid;
    private int ranges;
    private int columns;
    private boolean gameState;
    private int game_width;
    private int game_height;

    public Game(int grid, int ranges, int columns){
        this.grid = grid;
        this.ranges = ranges;
        this.columns = columns;
        this.game_width = columns*grid;
        this.game_height = ranges*grid;
        this.gameState = false;
    }

    public int getGrid() {
        return grid;
    }

    public void setGrid(int grid) {
        this.grid = grid;
    }

    public int getRanges() {
        return ranges;
    }

    public void setRanges(int ranges) {
        this.ranges = ranges;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public boolean getGameState() {
        return gameState;
    }

    public void setGameState(boolean gameState) {
        this.gameState = gameState;
    }

    public int getGame_width() {
        return game_width;
    }

    public void setGame_width(int game_width) {
        this.game_width = game_width;
    }

    public int getGame_height() {
        return game_height;
    }

    public void setGame_height(int game_height) {
        this.game_height = game_height;
    }




    @Override
    public ArrayList<Car> car_range(int range) {
        int x = random_btw(0, getGame_width());


        int number = random_btw(1,3);
        int speed;
        int k = random_btw(0,1);
        if (k ==0){
            speed = random_btw(-2,-1);
        }
        else {
            speed = random_btw(1,2);
        }

        ArrayList<Car> cars = new ArrayList<Car>();
        for (int i=0; i<number; i ++){
            int size = random_btw(1,3)*grid;
            x = x - size- Math.abs(speed)*grid;

            cars.add(new Car(x , game_height-(range+1)*grid,size, grid, range, speed, game_width));

        }
        return cars;
    }

    @Override
    public ArrayList<Trunk> trunk_range(int range) {
        int x = random_btw(0, getGame_width());
        int size = 3 *grid;

        int speed;
        int number = 3;
        int k = random_btw(0,1);
        if (k ==0){
            speed = random_btw(-2,-1);
        }
        else {
            speed = random_btw(1,2);
        }

        ArrayList<Trunk> trunks = new ArrayList<Trunk>();
        for (int i=0; i<number; i ++){
            x = x - size-Math.abs(speed)*grid;
            trunks.add(new Trunk(x, game_height-(range+1)*grid,size, grid, range, speed, game_width));

        }
        return trunks;
    }

    @Override
    public int random_btw(int begin, int end) {
        Random rand = new Random();
        int nb_aleatoire = rand.nextInt(end-begin + 1)+begin;
        return nb_aleatoire;
    }

//    @Override
//    public ArrayList<Obstacle> defile_all(int begin_car, int end_car, int begin_trunk, int end_trunk) {
//        ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
//        ArrayList<Obstacle> cars = defile_cars(begin_car, end_car);
//        ArrayList<Obstacle> trunks = defile_trunks(begin_trunk, end_trunk);
//        obstacles.addAll(cars);
//        obstacles.addAll(trunks);
//        return obstacles;
//    }


    @Override
    public void deal_state_frog(Frog frog) {
        if ((frog.isCar_intersection()==frog.isTrunk_intersection()) || (frog.getLeft()<0 || frog.getRight()>this.game_width||frog.getBottom()<0||frog.getTop()>this.game_height)){

            System.out.println("GAME OVER");
            resetFrog(frog);
        }

        if (frog.getRange() >= this.getRanges()-1){
            setGameState(true);
            System.out.println("GAME CLEARED");
        }

    }


    @Override
    public void resetFrog(Frog frog) {
        frog.setLeft(columns*grid/2-grid/2);
        frog.setRight(columns*grid/2-grid/2+grid);
        frog.setBottom(ranges*grid-grid);
        frog.setTop(ranges*grid );
        frog.setCar_intersection(false);
        frog.setTrunk_intersection(true);
        frog.setRange(0);

    }

    @Override
    public Frog set_Frog() {
        Frog frog = new Frog(this.game_width/2-grid/2,this.game_height-grid, grid) ;
        return frog;
    }

    @Override
    public Frog set_Frog2P1() {
        Frog frog = new Frog(getGame_width()/4, this.game_height-grid, grid);
        return frog;
    }

    @Override
    public Frog set_Frog2P2() {
        Frog frog = new Frog(3*getGame_width()/4, this.game_height-grid, grid);
        return frog;
    }

    @Override
    public void resetFrog2P1(Frog frog) {
        frog.setLeft(getGame_width()/4);
        frog.setRight(getGame_width()/4+grid);
        frog.setBottom(ranges*grid-grid);
        frog.setTop(ranges*grid );
        frog.setCar_intersection(false);
        frog.setTrunk_intersection(true);
        frog.setRange(0);
    }

    @Override
    public void resetFrog2P2(Frog frog) {
        frog.setLeft(3*getGame_width()/4);
        frog.setRight(3*getGame_width()/4+grid);
        frog.setBottom(ranges*grid-grid);
        frog.setTop(ranges*grid );
        frog.setCar_intersection(false);
        frog.setTrunk_intersection(true);
        frog.setRange(0);

    }

    @Override
    public void deal_state_frog2P1(Frog frog) {
        if ((frog.isCar_intersection()==frog.isTrunk_intersection()) || (frog.getLeft()<0 || frog.getRight()>this.game_width||frog.getBottom()<0||frog.getTop()>this.game_height)){

            System.out.println("GAME OVER");
            resetFrog2P1(frog);
        }

        if (frog.getRange() >= this.getRanges()-1){
            setGameState(true);
            System.out.println("GAME CLEARED");
        }
    }

    @Override
    public void deal_state_frog2P2(Frog frog) {
        if ((frog.isCar_intersection()==frog.isTrunk_intersection()) || (frog.getLeft()<0 || frog.getRight()>this.game_width||frog.getBottom()<0||frog.getTop()>this.game_height)){

            System.out.println("GAME OVER");
            resetFrog2P2(frog);
        }

        if (frog.getRange() >= this.getRanges()-1){
            setGameState(true);
            System.out.println("GAME CLEARED");
        }

    }

//
}
