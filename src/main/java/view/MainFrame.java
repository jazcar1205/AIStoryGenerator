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
    private JLabel fileNameLabel;
    private JScrollPane scrollPane;
    private JTextArea outputArea;
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
	  fileNameLabel = new JLabel("Untitled.txt");
	  fileNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

	  outputArea = new JTextArea(10, 100);
	  outputArea.setEditable(false);
	  outputArea.setLineWrap(true);
	  outputArea.setBorder(BorderFactory.createTitledBorder("Generated Content"));

	  generatePanel = new GeneratePanel(this);
	  generatePanel.setSize(getWidth() - 50, (int) (getWidth() - (getWidth() * 0.1)));

	  controlPanel = new ControlPanel();
	  controlPanel.setSize((int) (getWidth() - (getWidth() * 0.40)), getHeight() - 50);

	  fileOptionsPanel = new FileOptionsPanel(this);
	  fileOptionsPanel.setSize((int) (getWidth() - (getWidth() * 0.2)), getHeight() - 50);

	  scrollPane = new JScrollPane(controlPanel);
	  scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    /**
     * Sets the label for the file name.
     *
     * @param fileNameLabel
     */
    public void setFileNameLabel(String fileNameLabel)
    {
	  this.fileNameLabel.setText(fileNameLabel);
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
	  outputArea.setText(session.getStory());
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

    /**
     * Adds all components to the frame and organizes them in the layout.
     */
    private void layoutComponents()
    {
	  add(fileNameLabel, BorderLayout.NORTH);
	  add(scrollPane, BorderLayout.WEST);
	  add(outputArea, BorderLayout.CENTER);
	  add(fileOptionsPanel, BorderLayout.EAST);
	  add(generatePanel, BorderLayout.SOUTH);
	  pack();
    }

    @Override
    public void update(Observable o, Object arg)
    {
	  outputArea.setText(controller.getStory());
    }

    public void setOutputArea(String text)
    {
	  outputArea.setText(text);
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