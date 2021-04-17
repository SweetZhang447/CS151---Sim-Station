package flocking;
import mvc.*;
import SimStation.*;

import java.util.*;


class Bird extends Agent {
    private int speed;
    public static int MAXSPEED = 6;
    public Bird() {
        super();
        headTo = Heading.random();
        speed = Utilities.rng.nextInt(MAXSPEED) + 1;    //random speed from 1 to MAXSPEED
    }

    public int getSpeed() { return speed; }

    public void setSpeed(int speed) { this.speed = speed; }

    public void update() {
        Bird neighbor = (Bird) this.world.getNeighbor(this,10);    //10 is current a random number, we can change it later on
        if(neighbor != null){
            this.setDirection(neighbor.getDirection());
            this.setSpeed(neighbor.getSpeed());
        }

        int steps = this.getSpeed();
        move(steps);
    }

}

class FlockingFactory extends SimStationFactory {
    public Model makeModel() { return new FlockingSimulation(); }
    public String getTitle() { return "Flocking";}
}

class FlockingSimulation extends Simulation {
    public void populate(){
        //We can change 30 into something else
        for(int i = 0; i < 30; i++){
            addAgent(new Bird());
        }
    }

    public void Stats() {
        String[] infor = new String[Bird.MAXSPEED+1];
        Map<Integer, Integer> countSpeed = new HashMap<>();

        //This loop will count the number of bird in that particular speed
        for(Agent agent: this.getList()){
            Bird bird = (Bird) agent;
            int currentSpeed = bird.getSpeed();
            if(!countSpeed.containsKey(currentSpeed)){
                countSpeed.put(currentSpeed, 0);
            }
            int currentCount = countSpeed.get(currentSpeed) ;
            countSpeed.put(currentSpeed, currentCount+1);
        }

        //Initiate array of String
        for(int i = 0; i < infor.length-1; i++){
            int currentCount = 0;
            if( countSpeed.get(i+1) != null){
                currentCount= countSpeed.get(i+1);
            }
            infor[i] = "#birds @speed "+(i+1)+" ="+currentCount;
        }
        infor[infor.length-1] = "clock: "+ getClock();
        Utilities.inform(infor);
    }

    public static void main(String[] args) {
        AppPanel panel = new SimStationPanel(new FlockingFactory());
        panel.display();
    }

}
