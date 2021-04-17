package PDT;

import SimStation.SimStationFactory;
import mvc.Model;

public class PDTFactory extends SimStationFactory {
    public Model makeModel() {
        return new PDTSimulation();
    }

    public String getTitle() {
        return "Prisoner's Dilemma Tournament";
    }
}
