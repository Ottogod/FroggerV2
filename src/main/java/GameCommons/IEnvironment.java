package GameCommons;
import GraphicalElements.Element;
import MovingElements.Obstacle;
import MovingElements.Obstacle.Car;
import MovingElements.Obstacle.Trunk;

import java.util.ArrayList;

public interface IEnvironment {
    abstract ArrayList<Car> car_range(int range);
    abstract ArrayList<Trunk> trunk_range(int range);
    abstract int random_btw(int begin, int end);
}
