package view;

import model.StoryModel;
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

	  fileOptionsPanel = new FileOptionsPanel("test");
	  fileOptionsPanel.setSize((int) (getWidth()- (getWidth()*0.2)), getHeight()-50);

	  scrollPane = new JScrollPane(controlPanel);
	  scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
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