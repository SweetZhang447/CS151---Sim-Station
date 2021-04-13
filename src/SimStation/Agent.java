package SimStation;

import java.io.Serializable;

// NOTE: (Sweet + Jalen)
// What is agent state? How do we set agent state?
// Class diagram in projects shows it at a enum class, but when/how/where do you set it?

// Hoc Can Huynh: 4/21/2021 Implemented and Updated
public abstract class Agent implements Serializable, Runnable {

    private String name;
    private Heading headTo;
    private int xc;
    private int yc;
    private boolean suspend;
    private boolean stopped;
    //private AgentState state;
    private Thread myThread;
    private Simulation world;

    public Agent(String name, Simulation world, int xc, int yc, AgentState state) {
        this.name = name;
        this.world = world;
        this.xc = xc;
        this.yc = yc;
        //this.state = state;
        this.myThread = null;
        this.stopped = false;
        this.suspend = false;
    }

    //Agent at READY state when first created
    public Agent(String name , Simulation world, int xc, int yc){
        this.name = name;
        this.world = world;
        this.xc = xc;
        this.yc = yc;
        //this.state = AgentState.READY;
        this.myThread = null;
        this.stopped = false;
        this.suspend = false;
    }

    /******************Thread stuff******************/
    public synchronized void stop(){ this.stopped = true; }

    public synchronized void start(){ run();}//this.state = AgentState.READY;}

    public synchronized boolean isStopped(){return this.stopped;}

    public synchronized void suspend(){ this.suspend = true; }

    public synchronized boolean isSuspend(){return this.suspend;}

    public synchronized void resume(){ notify(); }

    public synchronized void join() throws InterruptedException {
        try{
            if (myThread != null) myThread.join();
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }

    private synchronized void checkSuspended() {
        try {
            while(!isStopped() && isSuspend()) {
                wait();
                //this.state = AgentState.SUSPEND;
                this.suspend = false;
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void run(){
        myThread = Thread.currentThread();  //catch the current thread
        while (!isStopped()) {
            try {
                update();
                Thread.sleep(2000);
//                Thread.sleep(1000);
                checkSuspended();
            } catch(InterruptedException e) {
                System.err.println(e);
            }
        }
    }

    //Need to override
    public abstract void update();

    //this function is called to check if any other Agent is around
    public double distance(Agent other){
        return Math.sqrt(Math.pow((this.getXc()- other.getXc()), 2) + Math.pow((this.getYc()- other.getYc()), 2));
    }

    /******************Getter******************/
    public String getName() { return name; }

    public int getXc() { return xc; }

    public int getYc() { return yc; }

    /******************Setter******************/
    //public void setState(AgentState state) { this.state = state;}

    public void setDirection(Heading direction){this.headTo = direction;}

    public void setName(String name) { this.name = name; }

    public void setXc(int xc) { this.xc = xc; }

    public void setYc(int yc) { this.yc = yc; }

    public void move(int steps){
        while(steps > 0){
            if(this.headTo == Heading.SOUTH){
                moveSouth();
            }else if(this.headTo == Heading.NORTH){
                moveNorth();
            }else if(this.headTo == Heading.WEST){
                moveWest();
            }else if(this.headTo == Heading.EAST){
                moveEast();
            }
            steps--;
            this.world.changed();
        }

    }

    /******************Helper functions for move******************/
    private void moveSouth(){
        //this if block will wrap around if out of bound
        if(getYc() == world.SIZE){
            this.yc = 0;
        }
        this.yc ++;
    }

    private void moveNorth(){
        //this if block will wrap around if out of bound
        if(getYc() == 0){
            this.yc = world.SIZE;
        }
        this.yc --;
    }

    private void moveWest(){
        //this if block will wrap around if out of bound
        if(getXc() == 0){
            this.xc = world.SIZE;
        }
        this.xc --;
    }

    private void moveEast(){
        //this if block will wrap around if out of bound
        if(getXc() == world.SIZE){
            this.xc = 0;
        }
        this.xc ++;
    }

}

