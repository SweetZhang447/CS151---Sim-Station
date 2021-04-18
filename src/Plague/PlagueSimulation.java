package Plague;

import SimStation.*;
import mvc.AppPanel;
import mvc.Model;
import mvc.Utilities;
import mvc.View;

import java.util.HashMap;
import java.util.Map;

class Person extends Agent {
    public static int RESISTANCE = 5; // % chance of resisting infection
    private final int speed = 1;
    private boolean infected = false;

    public Person() {
        super();
        headTo = Heading.random();

    }

    public boolean isInfected() {
        return infected;
    }

    public void setInfected(boolean infected) {
        this.infected = infected;
    }

    public void update() {
        Plague.Person neighbor = (Plague.Person) this.world.getNeighbor(this, 10);    //10 is current a random number, we can change it later on

        if (neighbor != null && neighbor.isInfected() && Math.random() * 100 > RESISTANCE) {
            this.setInfected(true);
        }

        move(speed);
    }

}

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

class PlagueSimulation extends Simulation {
    public static int VIRULENCE = 50; // % chance of infection

    public void populate() {
        //Start with 50 agents
        for (int i = 0; i < 50; i++) {

            addAgent(new Plague.Person());
        }
        for (Agent agent : this.getList()) {
            Plague.Person person = (Plague.Person) agent;
            if (Math.random() * 100 < VIRULENCE) {
                person.setInfected(true);
            }
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
        infor[1] = "%infected" + infectPercent * 100;
        infor[infor.length - 1] = "clock: " + getClock();
        Utilities.inform(infor);
    }


    public static void main(String[] args) {
        AppPanel panel = new SimStationPanel(new Plague.PlagueFactory());
        panel.display();
    }
}

