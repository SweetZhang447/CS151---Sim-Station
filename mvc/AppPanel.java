package mvc;

import tools.Utilities;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;
import javax.swing.*;
//import javax.swing.text.Utilities;

public class AppPanel extends JPanel implements ActionListener, PropertyChangeListener {

    protected Model model;
    protected View view;
    protected JPanel controlPanel;
    protected AppFactory factory;

    private JFrame frame;
    public static int FRAME_WIDTH = 500;
    public static int FRAME_HEIGHT = 300;

    public AppPanel(AppFactory factory) {
        super();
        this.factory = factory;
        model = factory.makeModel();
        controlPanel = new JPanel();
        view = factory.makeView(model);

        setLayout((new GridLayout(1, 2)));
        add(controlPanel);
        add(view);

        controlPanel.setBackground(Color.PINK);
        view.setBackground(Color.GRAY);

        if(model != null) {
            model.addPropertyChangeListener(this);
            model.addPropertyChangeListener(view);
        }

        frame = new JFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(createMenuBar());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(factory.getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);


    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        // add file, edit, and help menus
        JMenu fileMenu =
                Utilities.makeMenu("File", new String[] {"New",  "Save", "SaveAs", "Open", "Quit"}, this);
        result.add(fileMenu);

        JMenu editMenu =
                Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);

        JMenu helpMenu =
                Utilities.makeMenu("Help", new String[] {"About", "Help"}, this);
        result.add(helpMenu);

        return result;
    }

    public void display() { frame.setVisible(true); }

    public void actionPerformed(ActionEvent ae) {
        try {
            String cmmd = ae.getActionCommand();
            if (cmmd == "Save") {
                Utilities.save(model, false);
            } else if (cmmd == "SaveAs") {
                Utilities.save(model, true);
            } else if (cmmd == "Open") {
                Model openModel = Utilities.open(model);
                setModel(openModel);
            } else if (cmmd == "New") {
                Utilities.saveChanges(model);
                setModel(factory.makeModel());
                //model = factory.makeModel();
                //view.setModel(model);
                model.setUnsavedChanges(false);
            } else if (cmmd == "Quit") {
                Utilities.saveChanges(model);
                System.exit(1);
            } else if (cmmd == "About") {
                Utilities.inform(factory.about());
            } else if (cmmd == "Help") {
                Utilities.inform(factory.getHelp());
            } else {
                //Utilities.error("Unrecognized command: " + cmmd);
                // from the edit menu
                Command command = factory.makeEditCommand(model, cmmd);
                command.execute();
            }
        } catch(Exception e) {
            handleException(e);
        }
    }

    protected void handleException(Exception e) {
        Utilities.error(e);
    }

    public void propertyChange(PropertyChangeEvent evt) {

    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model m) {
        this.model.removePropertyChangeListener(this);
        this.model = m;
        this.model.initSupport();
        this.model.addPropertyChangeListener(this);
        view.setModel(this.model);
        model.changed();
    }
}
