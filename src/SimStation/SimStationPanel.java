package SimStation;

import mvc.AppFactory;
import mvc.AppPanel;

import javax.swing.*;
import java.awt.*;

// Controller: executes user commands by updating the model
public class SimStationPanel extends AppPanel {

    // Buttons
    private JButton Start, Suspend, Resume, Stop, Stats;

    public SimStationPanel(AppFactory factory) {
        super(factory);
        // Create buttons for button panel
        Start = new JButton("Start");
        Suspend = new JButton("Suspend");
        Resume = new JButton("Resume");
        Stop = new JButton("Stop");
        Stats = new JButton("Stats");
        // Add action listeners to buttons in factory
        Start.addActionListener(this);
        Suspend.addActionListener(this);
        Resume.addActionListener(this);
        Stop.addActionListener(this);
        Stats.addActionListener(this);
        // Add newly created buttons to the button panel
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 70, 15));
        controlPanel.add(Start);
        controlPanel.add(Suspend);
        controlPanel.add(Resume);
        controlPanel.add(Stop);
        controlPanel.add(Stats);
    }

    public static void main(String[] args) {
        AppFactory factory = new SimStationFactory();
        AppPanel panel = new SimStationPanel(factory);
        panel.display();
    }
}
