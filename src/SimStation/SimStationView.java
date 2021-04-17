package SimStation;

import mvc.View;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class SimStationView extends View {
    private Simulation station;
    private ArrayList<Agent> viewList;


    public SimStationView(Simulation station) {
        super(station);
        this.station = (Simulation) station;
        viewList = station.getList();
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        gc.setColor(Color.WHITE);
        for (Agent agent : viewList) {
            gc.fillRect(agent.getXc(), agent.getYc(), 7, 7);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}