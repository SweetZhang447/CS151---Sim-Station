package SimStation;

import java.io.Serializable;

// NOTE: (Sweet + Jalen)
// What is agent state? How do we set agent state?
// Class diagram in projects shows it at a enum class, but when/how/where do you set it?
public abstract class Agent implements Serializable, Runnable {

    private String name;
    private Simulation world;
    private int xc;
    private int yc;
    private AgentState state;
    private Thread myThread;

    public Agent(String name, Simulation world, int xc, int yc, AgentState state) {
        this.name = name;
        this.world = world;
        this.xc = xc;
        this.yc = yc;
        this.state = state;
    }

    //Agent at READY state when first created
    public Agent(String name , Simulation world, int xc, int yc){
        this.name = name;
        this.world = world;
        this.xc = xc;
        this.yc = yc;
        this.state = AgentState.READY;
    }

    // uhhhhh
    public void setState(AgentState state) { this.state = state;}

    public synchronized void run(){
        if(this.state == AgentState.READY){
            this.state = AgentState.RUNNING;
        }
        myThread = Thread.currentThread();
        while(this.state != AgentState.STOPPED){
            update();
            cooperate();
            checkSuspended();
        }
    }

    public synchronized void start(){this.state = AgentState.READY;}

    public synchronized void suspend(){ this.state = AgentState.SUSPEND; }


    public synchronized void resume(){ notify(); }

    public synchronized void stop(){
//        if(this.state == AgentState.RUNNING){
//            this.state = AgentState.STOPPED;
//        }
        this.state = AgentState.STOPPED;
    }

    private synchronized void cooperate() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    private synchronized void checkSuspended() {
        try {
            while(this.state == AgentState.SUSPEND) {
                wait();
//                suspended = false;
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
    public abstract void update();

}

