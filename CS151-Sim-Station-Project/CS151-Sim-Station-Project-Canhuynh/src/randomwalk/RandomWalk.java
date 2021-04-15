package randomwalk;

import SimStation.*;
import mvc.AppPanel;
import mvc.Model;
import mvc.Utilities;

// Hoc Can Huynh: 4/13/2021 Set up and Updated
class Drunk extends Agent {

    public Drunk() {
        super();
        headTo = Heading.random();
    }

    public void update() {
        headTo = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);
    }

}

class RandomWalkFactory extends SimStationFactory {
    public Model makeModel() {
        return new RandomWalkSimulation();
    }

    public String getTitle() {
        return "Random Walks";
    }
}

class RandomWalkSimulation extends Simulation {

    public void populate() {
        for (int i = 0; i < 15; i++) {
            addAgent(new Drunk());
        }
    }

    public static void main(String[] args) {
        AppPanel panel = new SimStationPanel(new RandomWalkFactory());
        panel.display();
    }

}
