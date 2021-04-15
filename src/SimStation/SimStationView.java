package SimStation;

import mvc.Model;
import mvc.View;

import java.awt.*;
import java.util.ArrayList;

public class SimStationView extends View {
    private Simulation station;
    private ArrayList<Agent> viewList;


    public SimStationView(Model station) {
        super(station);
    }


        public void paintComponent(Graphics gc) {
            super.paintComponent(gc);
            Simulation station = (Simulation) model;
            viewList = station.getList();
            for (Agent agent : viewList) {
                gc.fillRect(7, 7, agent.getXc(), agent.getYc());
            }

        }
    }