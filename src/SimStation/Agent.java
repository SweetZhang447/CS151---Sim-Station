package SimStation;

import jdk.javadoc.internal.doclets.formats.html.markup.Head;

import java.io.Serializable;

public class Agent implements Serializable, Runnable{


    enum State{
        READY,
        RUNNING,
        SUSPENDED,
        STOPPED
    }



    private String name;
    private Simulation world;
    private int xc;
    private int yc;
    private State state;

    public Agent(String name, Simulation world, int xc, int yc, State state) {
        this.name = name;
        this.world = world;
        this.xc = xc;
        this.yc = yc;
        this.state = state;
    }


    public void setState(State state){
        if (state == State.READY)
            ;
    }

    public void run(){

    }
}
