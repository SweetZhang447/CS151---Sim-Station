package flocking;
import mvc.*;
import SimStation.*;

class Bird extends Agent {
    private int speed;

    public Bird() {
        super();
        headTo = Heading.random();
        speed = Utilities.rng.nextInt(6) + 1;
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

//    public void Stats() {
//        //Check how many !bird.isStopped() and their speed then put into string[]
//        //Utilities.inform(string[])
//    }

    public static void main(String[] args) {
        AppPanel panel = new SimStationPanel(new FlockingFactory());
        panel.display();
    }

}
