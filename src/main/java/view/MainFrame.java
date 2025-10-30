package view;

import controller.AIController;
import javax.swing.*;

public class MainFrame extends JFrame
{
    private JTextArea inputArea;
    private JTextArea outputArea;
    private JButton generateButton;
    private JComboBox<String> strategySelector;
    private AIController controller;

    public MainFrame() {
	  setTitle("AI Story Generator");
	  setSize(800, 600);
	  setDefaultCloseOperation(EXIT_ON_CLOSE);

	  initComponents();
	  //layoutComponents();
    }

    private void initComponents() {
	  inputArea = new JTextArea(10, 40);
	  inputArea.setLineWrap(true);
	  inputArea.setBorder(BorderFactory.createTitledBorder("Input"));

	  outputArea = new JTextArea(15, 40);
	  outputArea.setEditable(false);
	  outputArea.setLineWrap(true);
	  outputArea.setBorder(BorderFactory.createTitledBorder("Generated Content"));

	  generateButton = new JButton("Generate");
	  generateButton.addActionListener(e -> onGenerate());

	  strategySelector = new JComboBox<>(new String[]{
		    "Creative Writing",
		    "Professional Writing",
		    "Academic Writing"
	  });
    }

    private void onGenerate() {
	  generateButton.setEnabled(false);
	  generateButton.setText("Generating...");

	  // Run API call in background thread
	  new SwingWorker<String, Void>() {
		@Override
		protected String doInBackground() throws Exception {
		    String input = inputArea.getText();
		    return controller.generate(input);
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
}