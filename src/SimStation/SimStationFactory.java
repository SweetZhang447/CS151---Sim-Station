package SimStation;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

import java.io.Serializable;

public class SimStationFactory implements AppFactory, Serializable {
    public Model makeModel() {
        return null;
    }

    public View makeView(Model model) {
        return null;
    }

    public String getTitle() {
        return null;
    }

    public String[] getHelp() {
        return new String[0];
    }

    public String about() {
        return null;
    }

    public String[] getEditCommands() {
        return new String[0];
    }

    public Command makeEditCommand(Model model, String type) {
        return null;
    }
}
