package SimStation;

import mvc.*;

public class SimStationCommand extends Command {
    private String cmmd;

    public SimStationCommand(Model model, String cmmd) {
        super(model);
        this.cmmd = cmmd;
    }

    public void execute() {
        Simulation station = (Simulation)model;
        //station.setState(cmmd);
    }


}
