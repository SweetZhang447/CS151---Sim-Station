package mvc;

public interface AppFactory {
    public Model makeModel();

    public View makeView(Model model);

    public String getTitle();

    public String[] getHelp();

    public String about();

    public String[] getEditCommands();

    // in subclass, if type of command is not recognized, return null
    public Command makeEditCommand(Model model, String type);
}
