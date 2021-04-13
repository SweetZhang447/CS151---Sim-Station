package SimStation;

import mvc.Model;

import java.util.ArrayList;

public class Simulation extends Model {

    private ArrayList<Agent> list;   // List of agents
    private int clock;
    private int numberOfAgents;
    public static int SIZE = 250;

    public Simulation() {
        list = new ArrayList<>();
        clock = 0;
        numberOfAgents = 0;
    }


    public void Start() {
        this.populate();
    }

    public void Suspend() {

    }

    public void Resume() {

    }

    public void Stop() {

    }

    public Agent getNeighbor(Agent a) {

    }

    public void populate() { }

    // I think you can do this in the StatsCommand class
    // since it just returns a message on the GUI regarding
    // the number of agents and what counter the clock is at
    // probably will delete this method later - Sweet
    public void Stats() {

    }
}
