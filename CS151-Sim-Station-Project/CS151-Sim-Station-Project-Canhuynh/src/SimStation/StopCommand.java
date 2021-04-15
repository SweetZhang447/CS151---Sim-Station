package SimStation;

import mvc.Command;
import mvc.Model;

public class StopCommand extends Command {

    public StopCommand(Model model) {
        super(model);
    }

    public void execute() throws Exception {
        Simulation station = (Simulation) model;
        System.out.println("Test Stop");
        station.Stop();
    }


}
