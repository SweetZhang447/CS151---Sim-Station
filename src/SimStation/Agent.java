package SimStation;

import java.io.Serializable;

// NOTE: (Sweet + Jalen)
// What is agent state? How do we set agent state?
// Class diagram in projects shows it at a enum class, but when/how/where do you set it?
public class Agent implements Serializable, Runnable {

    private String name;
    private Simulation world;
    private int xc;
    private int yc;
    private SimStation.AgentState state;

    public Agent(String name, Simulation world, int xc, int yc, AgentState state) {
        this.name = name;
        this.world = world;
        this.xc = xc;
        this.yc = yc;
        this.state = state;
    }

    // uhhhhh
    public void setState(AgentState state) {
        if (state == AgentState.READY) {

        }
    }

    public void run() {


    }
}

