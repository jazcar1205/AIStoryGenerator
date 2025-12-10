package view;

import model.StoryModel;
import persistence.Session;
import view.components.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import controller.MainController;

/**
 * Creates the main frame of the program.
 */
public class MainFrame extends JFrame implements Observer
{

    private JScrollPane scrollPane;
    private GeneratePanel generatePanel;
    private ControlPanel controlPanel;
    private FileOptionsPanel fileOptionsPanel;

    private MainController controller;

    /**
     * Creates the main frame, initilizes components, and sets up layout.
     */
    public MainFrame(MainController controller)
    {
	  this.controller = controller;
	  this.controller.attachObserver(this);
	  setTitle("AI Story Generator");
	  setDefaultCloseOperation(EXIT_ON_CLOSE);
	  setLayout(new GridBagLayout());
	  initComponents();
	  layoutComponents();
	  pack();
	  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	  setSize((int) screenSize.getWidth(), (int) (screenSize.getHeight() - 50));
	  setVisible(true);
    }

    public MainFrame()
    {
	  setTitle("AI Story Generator");
	  setDefaultCloseOperation(EXIT_ON_CLOSE);
	  initComponents();
	  layoutComponents();
	  pack();
	  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	  setSize((int) screenSize.getWidth(), (int) (screenSize.getHeight() - 50));
	  setVisible(true);
    }

    /**
     * Initializes all components in the frame.
     */
    private void initComponents()
    {
	  generatePanel = new GeneratePanel(this);
	  //generatePanel.setPreferredSize(new Dimension(0, getHeight()));
	  controlPanel = new ControlPanel();
	  //controlPanel.setMinimumSize(new Dimension(100, 0));
	  scrollPane = new JScrollPane(controlPanel);
	  scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	  scrollPane.setMinimumSize(controlPanel.getMinimumSize());

	  fileOptionsPanel = new FileOptionsPanel(this);
	  //fileOptionsPanel.setMinimumSize(new Dimension(150, getHeight()));
    }

    /**
     * Adds all components to the frame and organizes them in the layout.
     */
    private void layoutComponents()
    {
	  GridBagConstraints gbc = new GridBagConstraints();
	  // Common settings for all components
	  gbc.fill = GridBagConstraints.BOTH; // Make the component fill its display area horizontally
	  gbc.anchor = GridBagConstraints.CENTER;
	  gbc.insets = new Insets(5, 5, 5, 5);
	  gbc.gridx = 0;
	  gbc.weighty = 1.0;
	  gbc.weightx = 0.25;
	  add(scrollPane, gbc);
	  gbc.gridx = 1;
	  gbc.weightx = 0.6;
	  add(generatePanel, gbc);
	  gbc.gridx = 2;
	  gbc.weightx = 0.15;
	  add(fileOptionsPanel, gbc);
	  pack();
    }

    /**
     * Sets the control panel options and updates the output area according
     * to the session provided.
     *
     * @param session
     */
    public void setControlPanelOptions(Session session)
    {
	  controlPanel.setOptions(session.getAsModel());
	  generatePanel.setOutputText(session.getStory());
    }

    /**
     * Get the updated session from the currently selected options.
     *
     * @return
     */
    public Session getUpdatedSession()
    {
	  updateModel();
	  return this.controller.getSession();
    }

    /**
     * Updates the model according to the GUI
     */
    public void updateModel()
    {
	  this.controller.updateModel(controlPanel.getOptions());
    }

    @Override
    public void update(Observable o, Object arg)
    {
	  generatePanel.setOutputText(controller.getStory());
    }

    public MainController getControllerState()
    {
	  return controller;
    }

    /**
     * Run that app!
     *
     * @param args
     */
    public static void main(String[] args)
    {
	  SwingUtilities.invokeLater(MainFrame::new);
    }
}