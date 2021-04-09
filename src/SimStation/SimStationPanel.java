package SimStation;

import mvc.AppFactory;
import mvc.AppPanel;

public class SimStationPanel extends AppPanel {

    // Buttons

    public SimStationPanel(AppFactory factory) {
        super(factory);
        // Create buttons for button panel
        // Add action listeners to buttons in factory
        // Add newly created buttons to the button panel
    }

    public static void main(String[] args) {
        AppFactory factory = new SimStationFactory();
        AppPanel panel = new SimStationPanel(factory);
        panel.display();
    }
}
