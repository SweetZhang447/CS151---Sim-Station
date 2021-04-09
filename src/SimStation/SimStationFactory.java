package SimStation;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

import java.io.Serializable;

public class SimStationFactory implements AppFactory, Serializable {
    public Model makeModel() {
        return new SimStation();
    }

    public View makeView(Model model) {
        return new SimStationView((SimStation) model);
    }

    public String getTitle() {
        return "SimStation";
    }

    public String[] getHelp() {
        return new String[0];
    }

    public String about() {
        return null;
    }

    public String[] getEditCommands() {
        return new String[]{"Start", "Suspend", "Resume", "Stop", "Stats"};
    }

    public Command makeEditCommand(Model model, String type) {
        switch (type) {
            case "Start": {
                return new StartCommand(model);
            }
            case "Suspend": {
                return new SuspendCommand(model);
            }
            case "Resume": {
                return new ResumeCommand(model);
            }
            case "Stop": {
                return new StopCommand(model);
            }
            case "Stats": {
                return new StatsCommand(model);
            }
        }
        return null;
    }
}
