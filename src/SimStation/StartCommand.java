package SimStation;

import mvc.Command;
import mvc.Model;

public class StartCommand extends Command {

    public StartCommand(Model model) {
        super(model);
    }

    public void execute() throws Exception {
        Simulation station = (Simulation) model;
        System.out.println("Test Start");
        station.Start();
    }
}
