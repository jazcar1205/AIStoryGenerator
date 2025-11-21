package view;

import model.Complexity;
import model.Length;
import model.StoryModel;
import model.StrategyType;
import persistence.Session;
import view.components.CustomButton;

import javax.swing.*;
import java.awt.*;

import controller.MainController;
/**
 * Creates the main frame of the program.
 */
public class MainFrame extends JFrame
{
    private JLabel fileNameLabel;
    private JScrollPane scrollPane;
    private JTextArea outputArea;
    private CustomButton generateButton;
    private ControlPanel controlPanel;
    private FileOptionsPanel fileOptionsPanel;

    private MainController controller;
    private StoryModel storyModel;
    private Session session;

    /**
     * Creates the main frame, initilizes components, and sets up layout.
     */
    public MainFrame(StoryModel storyModel, MainController controller)
    {
	  this.controller = controller;
	  this.storyModel = storyModel;
	  new MainFrame();
    }
    public MainFrame() {
	  this.session = new Session();
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
    private void initComponents() {
	  fileNameLabel = new JLabel("Untitled.txt");
	  fileNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

	  outputArea = new JTextArea(10, 100);
	  outputArea.setEditable(false);
	  outputArea.setLineWrap(true);
	  outputArea.setBorder(BorderFactory.createTitledBorder("Generated Content"));

	  generateButton = new CustomButton("Generate");
	  generateButton.setSize(50, 50);
	  generateButton.addActionListener(e -> onGenerate());

	  controlPanel = new ControlPanel();
	  controlPanel.setSize((int) (getWidth()- (getWidth()*0.40)), getHeight()-50);

	  fileOptionsPanel = new FileOptionsPanel(this);
	  fileOptionsPanel.setSize((int) (getWidth()- (getWidth()*0.2)), getHeight()-50);

	  scrollPane = new JScrollPane(controlPanel);
	  scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    /**
     * Sets the label for the file name.
     * @param fileNameLabel
     */
    public void setFileNameLabel(String fileNameLabel)
    {
	  this.fileNameLabel.setText(fileNameLabel);
    }

    /**
     * Sets the control panel options and updates the output area according
     * to the session provided.
     * @param session
     */
    public void setControlPanelOptions(Session session)
    {
	  controlPanel.setOptions(session.getLength(), session.getComplexity(), session.getStoryStrategy());
	  outputArea.setText(session.getStory());
    }

    /**
     * Get the updated session from the currently selected options.
     * @return
     */
    public Session getUpdatedSession()
    {
	  this.session = controlPanel.getOptions();
	  this.session.setStory(outputArea.getText());
	  return session;
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
	  add(generateButton, BorderLayout.SOUTH);
	  pack();
    }

    /**
     * Used to send info to the API to generate text.
     */
    private void onGenerate() {
	  generateButton.setEnabled(false);
	  generateButton.setText("Generating...");

	  // Run API call in background thread
	  new SwingWorker<String, Void>() {
		@Override
		protected String doInBackground() throws Exception {
		    return controlPanel.getValues();
		}

		@Override
		protected void done() {
		    try {
			  outputArea.setText(get());
		    } catch (Exception e) {
			  JOptionPane.showMessageDialog(
				    MainFrame.this,
				    "Error: " + e.getMessage(),
				    "API Error",
				    JOptionPane.ERROR_MESSAGE
								 );
		    } finally {
			  generateButton.setEnabled(true);
			  generateButton.setText("Generate");
		    }
		}
	  }.execute();
    }

    /**
     * Run that app!
     * @param args
     */
    public static void main(String[] args) {
	  SwingUtilities.invokeLater(MainFrame::new);
    }
}