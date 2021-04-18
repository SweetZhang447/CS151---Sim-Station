package Plague;

import SimStation.Agent;
import SimStation.SimStationView;
import SimStation.Simulation;

import java.awt.*;

public class PlagueSimulationView extends SimStationView {

    public PlagueSimulationView(Simulation station) {
        super(station);
    }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        gc.setColor(Color.GREEN);
        for (Agent agent : viewList) {
            Plague.Person person = (Plague.Person) agent;
            if (person.isInfected()) {
                gc.setColor(Color.RED);
            } else
                gc.setColor(Color.GREEN);
            gc.fillRect(agent.getXc(), agent.getYc(), 7, 7);
        }
    }
}
