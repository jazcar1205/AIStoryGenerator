package view;

import controller.AIController;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    private JScrollPane scrollPane;
    private JTextArea inputArea;
    private JTextArea outputArea;
    private JButton generateButton;
    private ControlPanel controlPanel;

    private AIController controller;

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
	  inputArea = new JTextArea(10, 20);
	  inputArea.setLineWrap(true);
	  inputArea.setBorder(BorderFactory.createTitledBorder("Input"));

	  outputArea = new JTextArea(10, 40);
	  outputArea.setEditable(false);
	  outputArea.setLineWrap(true);
	  outputArea.setBorder(BorderFactory.createTitledBorder("Generated Content"));

	  generateButton = new JButton("Generate");
	  generateButton.addActionListener(e -> onGenerate());

	  controlPanel = new ControlPanel();
	  controlPanel.setSize((int) (getWidth()- (getWidth()*0.40)), getHeight()-50);

	  scrollPane = new JScrollPane(controlPanel);
	  scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    /**
     * Adds all components to the frame and organizes them in the layout.
     */
    private void layoutComponents()
    {
	  add(scrollPane, BorderLayout.WEST);
	  add(outputArea, BorderLayout.EAST);
	  add(generateButton, BorderLayout.SOUTH);
    }

    private void onGenerate() {
	  generateButton.setEnabled(false);
	  generateButton.setText("Generating...");

	  // Run API call in background thread
	  new SwingWorker<String, Void>() {
		@Override
		protected String doInBackground() throws Exception {
		    String input = inputArea.getText();
		    String testInput = "Genre: " + controlPanel.getGenre();
		    return testInput;
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