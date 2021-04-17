package PDT;

import SimStation.Agent;
import SimStation.SimStationPanel;
import SimStation.Simulation;
import mvc.AppPanel;
import mvc.Utilities;

import java.util.HashMap;
import java.util.Map;

public class PDTSimulation extends Simulation {
    public void populate() {
        for (int i = 0; i < 40; i++) {
            Prisoner s = new Prisoner();
            addAgent(s);
            if (i >= 0 && i < 10) {
                s.setStrategy(new Tit4Tat(s));
            } else if (i >= 10 && i < 20) {
                s.setStrategy(new Cheat(s));
            } else if (i >= 20 && i < 30) {
                s.setStrategy(new Cooperate(s));
            } else {
                s.setStrategy(new RandomlyCooperate(s));
            }
        }
        System.out.print("populated");
    }

    // Shows average fitness for each strategy
    public void Stats() {
        Map<Integer, Integer> avgStrat = new HashMap<>();
        for (Agent agent : this.getList()) {
            Prisoner p = (Prisoner) agent;
            if (p.getStrategy().equals("Cooperate")) {
                avgStrat.put(1, p.getFitness());
            } else if (p.getStrategy().equals("Tit4Tat")) {
                avgStrat.put(2, p.getFitness());
            } else if (p.getStrategy().equals("RandomlyCooperate")) {
                avgStrat.put(3, p.getFitness());
            } else {
                avgStrat.put(4, p.getFitness());
            }
        }

        String infor = "Cooperate: " + avgStrat.get(1) / 10 + "\n" +
                "Tit4Tat: " + avgStrat.get(2) / 10 + "\n" +
                "RandomlyCooperate: " + avgStrat.get(3) / 10 + "\n" +
                "Cheat: " + avgStrat.get(4) / 10;

        Utilities.inform(infor);

    }

    public static void main(String[] args) {
        AppPanel panel = new SimStationPanel(new PDTFactory());
        panel.display();
    }
}
