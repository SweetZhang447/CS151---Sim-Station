package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AppPanel extends JPanel implements PropertyChangeListener, ActionListener {

    protected AppFactory factory;
    protected View view;
    protected Model model;
    protected JPanel controlPanel;
    protected JFrame frame;

    public static int FRAME_WIDTH = 500;
    public static int FRAME_HEIGHT = 300;

    public AppPanel(AppFactory factory) {
        this.factory = factory;
        model = factory.makeModel();
        view = factory.makeView(model);
        controlPanel = new JPanel();

        setLayout((new GridLayout(1, 2)));
        add(controlPanel);
        add(view);

        controlPanel.setBackground(Color.WHITE);
        view.setBackground(Color.GRAY);

        frame = new JFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(createMenuBar());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(factory.getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public void display() {
        frame.setVisible(true);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        // add file, edit, and help menus
        JMenu fileMenu =
                Utilities.makeMenu("File", new String[]{"New", "Save", "SaveAs", "Open", "Quit"}, this);
        result.add(fileMenu);

        JMenu editMenu =
                Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);

        JMenu helpMenu =
                Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);

        return result;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            String cmmd = e.getActionCommand();
            if (cmmd == "Save") {
                Utilities.save(model, true);

            } else if (cmmd == "SaveAs") {
                //String fName = Utilities.ask("File Name?");
                String fName = Utilities.getFileName(null, false);
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                os.writeObject(model);
                os.close();
            } else if (cmmd == "Open") {
                String fName = Utilities.getFileName(null, true);
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                //model.removePropertyChangeListener(this);
                model = (Model) is.readObject();
                //this.model.initSupport();
                view.addPropertyChangeListener(this);
                model.addPropertyChangeListener(this);
                view.update(model);
                is.close();

            } else if (cmmd == "New") {
                // Warns the user of unsaved changes when starting new game
                if(JOptionPane.showConfirmDialog(frame, "There are unsaved changes. \n" +
                        "Are you sure you want to start over?", "WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    // If the user clicks yes
                    this.remove(view);
                    model = factory.makeModel();
                    view = new View(model);
                    view = factory.makeView(model);
                    view.update(model);
                    this.add(view);
                    view.setBackground(Color.GRAY);
                    SwingUtilities.updateComponentTreeUI(this);
                }

            } else if (cmmd == "Quit") {
                Utilities.saveChanges(model);
                System.exit(1);
            } else if (cmmd == "About") {
                Utilities.inform(factory.about());
            } else if (cmmd == "Help") {
                Utilities.inform(factory.getHelp());
            } else {
                Command command = factory.makeEditCommand(model, cmmd);
                command.execute();
            }
        } catch (Exception err) {
            handleException(err);
        }
    }

    // IFFY
    public void propertyChange(PropertyChangeEvent evt) {
        updateUI();
    }

    protected void handleException(Exception e) {
        Utilities.error(e);
    }
}
