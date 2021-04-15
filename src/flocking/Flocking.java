package flocking;
import mvc.*;
import SimStation.*;
//import randomwalk.Drunk;
//import randomwalk.RandomWalkFactory;

class Bird extends Agent {
    private int speed;

    public Bird() {
        super();
        headTo = Heading.random();
        //speed = Random int from 1-5
    }

    public int getSpeed() { return speed; }

    public void setSpeed(int speed) { this.speed = speed; }

    public void update() {
        Bird neighbor = (Bird) this.world.getNeighbor(this,10);    //10 is current a random number, we can change it later on
        this.setDirection(neighbor.getDirection());
        this.setSpeed(neighbor.getSpeed());
        int steps = this.getSpeed();
        move(steps);
    }

}

//class RandomWalkSimulation extends Simulation {
//
//    public void populate() {
//        for(int i = 0; i < 15; i++)
//            addAgent(new Drunk());
//    }
//
//    public static void main(String[] args) {
//        AppPanel panel = new SimStationPanel(new RandomWalkFactory());
//        panel.display();
//    }
//
//}

class FlockingSimulation extends Simulation {
    public void populate(){
        //We can change 30 into something else
        for(int i = 0; i < 30; i++){
            this.addAgent(new Bird());
        }
    }

    public void Stats() {

    }

}
