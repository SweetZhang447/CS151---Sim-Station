package SimStation;

import mvc.Model;
import mvc.Utilities;

import java.util.ArrayList;

public class Simulation extends Model {

    private ArrayList<Agent> list;   // List of agents
    private int clock;
    private int numberOfAgents;
    public static int SIZE = 250;   //this should be the bound for Agent to wrap around ???

    public Simulation() {
        list = new ArrayList<>();
        clock = 0;
        //numberOfAgents = 0;
    }

    public void Start() {
        list = new ArrayList<Agent>();
        clock = 0;
        this.populate();
        //startTimer();
        for(Agent a : list){
            Thread thread = new Thread(a);
            thread.start();
        }
    }

    public synchronized void Suspend() {
        for(Agent a: list){
            a.suspend();
        }
    }

    public synchronized void Resume() {
        for(Agent a: list){
            a.resume();
        }
    }

    public void Stop() {
        for(Agent a: list){
            a.stop();
        }
    }

    //Get the neighbour in radius distance
    public synchronized Agent getNeighbor(Agent thisAgent, double radius) {
        Agent neighbor = null;
        boolean found = false;
        int i = Utilities.rng.nextInt(list.size());
        int begin = i;
        while(!found){
            Agent potential = list.get(i);
            if(potential != thisAgent && thisAgent.distance(potential) < radius){
                neighbor = list.get(i);
                found = true;
            }else{
                i = (i+1) % list.size();
                if(i == begin){
                    break;
                }
            }
        }

        return neighbor;

    }

    //Need to override
    public void populate() { }

    // I think you can do this in the StatsCommand class
    // since it just returns a message on the GUI regarding
    // the number of agents and what counter the clock is at
    // probably will delete this method later - Sweet
    // I think we can also override this for each customization - Can
    public void Stats() {

    }


}
