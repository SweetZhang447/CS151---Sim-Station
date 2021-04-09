package SimStation;

import mvc.Command;
import mvc.Model;

public class SuspendCommand extends Command {

    public SuspendCommand(Model model) {
        super(model);
    }

    public void execute() throws Exception {
        SimStation station = (SimStation) model;
        station.Suspend();
    }


}
