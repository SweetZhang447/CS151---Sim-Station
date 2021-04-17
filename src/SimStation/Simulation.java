package SimStation;

import mvc.Model;
import mvc.Utilities;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Simulation extends Model {

    private ArrayList<Agent> agentsLists;   // List of agents SHOULD CHANGE THE NAME
    private int clock;
    public static int SIZE = 250;   //this should be the bound for Agent to wrap around ???
    private Timer timer;

    public Simulation() {
        agentsLists = new ArrayList<>();
        clock = 0;
        //numberOfAgents = 0;
    }

    /******************Thread Stuff******************/
    public void Start() {
        agentsLists.clear();    //this will solve restart problem - Can
        clock = 0;
        populate();
        for (Agent a : agentsLists) {
            Thread thread = new Thread(a);
            thread.start(); //start will call run method.
        }
        startTimer();
    }

    public synchronized void Suspend() {
        for (Agent a : agentsLists) {
            a.suspend();
        }
        stopTimer();

    }

    public synchronized void Resume() {
        for (Agent a : agentsLists) {
            a.resume();
        }
        startTimer();

    }

    public void Stop() {
        for (Agent a : agentsLists) {
            a.stop();

        }
        stopTimer();
    }

    //Get the neighbour in radius distance
    public synchronized Agent getNeighbor(Agent thisAgent, double radius) {
        Agent neighbor = null;
        boolean found = false;
        int i = Utilities.rng.nextInt(agentsLists.size());
        int begin = i;
        while (!found) {
            Agent potential = agentsLists.get(i);
            if (potential != thisAgent && !potential.isStopped() && thisAgent.distance(potential) < radius) {
                neighbor = agentsLists.get(i);
                found = true;
            } else {
                i = (i + 1) % agentsLists.size();
                if (i == begin) {
                    break;
                }
            }
        }

        return neighbor;
    }

    public ArrayList<Agent> getList() {
        return this.agentsLists;
    }

    //Adding newAgent into this Simulation
    public synchronized void addAgent(Agent newAgent) {
        this.agentsLists.add(newAgent);
        //We missed the line below.
        //This will make sure the newAgent know which world it is into. -Can
        newAgent.setWorld(this);
    }

    //Remove rmAgent out of this Simulation
    public  void removeAgent(Agent rmAgent) {
        rmAgent.stop();
        this.agentsLists.remove(rmAgent);

    }

    /******************No-op need to override******************/
    //Need to override
    public void populate() {
    }

    // I think you can do this in the StatsCommand class
    // since it just returns a message on the GUI regarding
    // the number of agents and what counter the clock is at
    // probably will delete this method later - Sweet
    // I think we can also override this for each customization - Can

    //Use inform from Utilies.
    //Number of alive agent(!isStopped) 1
    //Values of clock 2
    //1,2 is in an array of String.
    public void Stats() {
        String[] infor = {"#agents: "+this.agentsLists.size(), "clock: "+this.clock};
        Utilities.inform(infor);
    }

    public int getClock(){return this.clock;}
    /******************Clock Stuff******************/

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    private class ClockUpdater extends TimerTask {
        public void run() {
            clock++;
            //changed();
        }
    }

}
