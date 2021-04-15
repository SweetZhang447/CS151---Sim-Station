package SimStation;

import mvc.*;

public class SimStationView extends View {
    private Simulation station;
    public SimStationView(Model model) {
        super(model);
        station = (Simulation) model;
    }
}
