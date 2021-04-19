package Plague;

import SimStation.*;
import mvc.AppPanel;
import mvc.Model;
import mvc.Utilities;
import mvc.View;

import java.util.HashMap;
import java.util.Map;

//class Person extends Agent {
//    public static int RESISTANCE = 2; // % chance of resisting infection
//    private final int speed = 1;
//    private boolean infected = false;
//
//    public Person() {
//        super();
//        headTo = Heading.random();
//    }
//
//    public boolean isInfected() {
//        return infected;
//    }
//
//    public void setInfected(boolean infected) {
//        this.infected = infected;
//    }
//
//    public void update() {
//        Plague.Person neighbor = (Plague.Person) this.world.getNeighbor(this, 10);    //10 is current a random number, we can change it later on
//        double randomValue = Math.random() * 100;
//        if (neighbor != null && neighbor.isInfected() && randomValue > RESISTANCE) {
//            this.setInfected(true);
//        }
//        move(speed);
//    }
//}

class PlagueFactory extends SimStationFactory {
    public Model makeModel() {
        return new Plague.PlagueSimulation();
    }

    public String getTitle() {
        return "Plague";
    }

    @Override
    public View makeView(Model model) {
        return new PlagueSimulationView((Simulation) model);
    }

}

//class PlagueSimulation extends Simulation {
//    //public static int VIRULENCE = 50; // % chance of infection
//    public static int INITIAL_INFECTED = 10;    //Initial %
//    public void populate() {
//        //Start with 50 agents
//
//        for (int i = 0; i < 50; i++) {
//            Person newPerson = new Person();
////            double randomValue = Math.random() * 100;
////            if(randomValue < VIRULENCE){
////                newPerson.setInfected(true);
////            }
//            addAgent(newPerson);
//        }
//
////        for (Agent agent : this.getList()) {
////            Plague.Person person = (Plague.Person) agent;
////            double random = Math.random() * 100;
////            System.out.println(random);
////            if (random < VIRULENCE) {
////                person.setInfected(true);
////            }
////        }
//
//    }
//
//    public void Stats() {
//        String[] infor = new String[3];
//        double infectNum = 0;
//        double infectPercent = 0;
//        Map<Integer, Integer> countSpeed = new HashMap<>();
//
//        //This loop will count the number of infected agents
//        for (Agent agent : this.getList()) {
//            Plague.Person person = (Plague.Person) agent;
//            if (person.isInfected()) {
//                infectNum++;
//            }
//            infectPercent = infectNum / this.getList().size();
//        }
//
//        //Initiate array of String
//        infor[0] = "agents:" + this.getList().size();
//        infor[1] = "%infected " + infectPercent * 100;
//        infor[infor.length - 1] = "clock: " + getClock();
//        Utilities.inform(infor);
//    }
//
//
//    public static void main(String[] args) {
//        AppPanel panel = new SimStationPanel(new Plague.PlagueFactory());
//        panel.display();
//    }
//}

class Person extends Agent{
    public static int RESISTANCE = 2;
    public static int VIRULENCE = 10;   //Radius
    private boolean infected;
    private int resistence;

    public Person(){
        super();
        headTo = Heading.random();
        int randomValue = Utilities.rng.nextInt(100);
        this.resistence = Utilities.rng.nextInt(RESISTANCE);
        this.infected = randomValue < PlagueSimulation.INITIAL_INFECTED;
    }

    public synchronized boolean isInfected(){return this.infected;}

    public synchronized void infect(){
        if(!this.isInfected()){
            int randomValue = Utilities.rng.nextInt(100);
            this.infected = this.resistence < randomValue;
        }
    }

    public void update(){
        if(this.isInfected()){
            Person neighbor = (Person) this.world.getNeighbor(this, VIRULENCE);
            if(neighbor != null && !neighbor.isInfected()){
                neighbor.infect();
            }
        }
        move(1);
    }
}

class PlagueSimulation extends Simulation {
    public static int INITIAL_INFECTED = 10;    //Initial %

    public void populate() {
        //Start with 50 agents

        for (int i = 0; i < 50; i++) {
            addAgent(new Person());
        }
    }

    public void Stats() {
        String[] infor = new String[3];
        double infectNum = 0;
        double infectPercent = 0;
        Map<Integer, Integer> countSpeed = new HashMap<>();

        //This loop will count the number of infected agents
        for (Agent agent : this.getList()) {
            Plague.Person person = (Plague.Person) agent;
            if (person.isInfected()) {
                infectNum++;
            }
            infectPercent = infectNum / this.getList().size();
        }

        //Initiate array of String
        infor[0] = "agents:" + this.getList().size();
        infor[1] = "%infected " + infectPercent * 100;
        infor[infor.length - 1] = "clock: " + getClock();
        Utilities.inform(infor);
    }


    public static void main(String[] args) {
        AppPanel panel = new SimStationPanel(new Plague.PlagueFactory());
        panel.display();
    }
}

