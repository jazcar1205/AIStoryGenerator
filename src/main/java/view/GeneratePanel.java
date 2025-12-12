package view;

import view.components.CustomButton;

import javax.swing.*;
import java.awt.*;

/**
 * A panel containing the buttons for generation and the ouput area.
 */
public class GeneratePanel extends JPanel
{
    private final MainFrame parent;
    private final CustomButton generateButton;
    private final CustomButton cancelButton;
    private final JTextArea outputArea;

    public GeneratePanel(MainFrame parent)
    {
	  this.parent = parent;
	  setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

	  outputArea = new JTextArea(50, 50);
	  outputArea.setEditable(false);
	  outputArea.setLineWrap(true);
	  outputArea.setBorder(BorderFactory.createTitledBorder("Generated Content"));
	  setPreferredSize(outputArea.getPreferredSize());
	  setMinimumSize(outputArea.getMinimumSize());

	  JPanel buttons = new JPanel(new GridBagLayout());
	  GridBagConstraints gbc = new GridBagConstraints();
	  // Common settings for all components
	  gbc.fill = GridBagConstraints.HORIZONTAL;
	  gbc.insets = new Insets(5, 5, 5, 5);

	  //setting up generate button to take up 70% of the space
	  generateButton = new CustomButton("Generate");
	  generateButton.addActionListener(e -> onGenerate());
	  gbc.gridx = 0;
	  gbc.weightx = 0.8;
	  buttons.add(generateButton, gbc);

	  //cancel button will take up 30%
	  cancelButton = new CustomButton("Cancel");
	  cancelButton.addActionListener(e -> cancelRequest());
	  cancelButton.setEnabled(false);
	  gbc.gridx = 1;
	  gbc.weightx = 0.2;
	  buttons.add(cancelButton, gbc);
	  add(outputArea, BorderLayout.NORTH);
	  add(buttons, BorderLayout.SOUTH);
    }

    public void setOutputText(String text)
    {
	  outputArea.setText(text);
    }

    /**
     * Used to stop a generate task that is currently running.
     */
    private void cancelRequest()
    {
        System.out.println("Cancel Request");
        parent.getControllerState().cancelRequest();
    }

    private void fakeGenerate()
    {
	  parent.updateModel();
	  outputArea.setText(parent.getControllerState().generateStoryDummy());
    }
    /**
     * Used to send info to the API to generate text.
     */
    private void onGenerate()
    {
	  generateButton.setEnabled(false);
	  cancelButton.setEnabled(true);
	  generateButton.setText("Generating...");
	  parent.updateModel();
	  new SwingWorker<String, Void>()
	  {
		//Creates a new thread that will run in background while GUI is still responsive.
		@Override
		protected String doInBackground() throws Exception
		{
		    return parent.getControllerState().generateStory();
		}

		//When the background thread is done, the GUI will be updated.
		@Override
		protected void done()
		{
		    try
		    {
			  outputArea.setText(get());
			  //model also updated accordingly both here and in controller.
			  parent.updateModel();
		    } catch (Exception e)
		    {
			  JOptionPane.showMessageDialog(
				    parent,
				    "Error: " + e.getMessage(),
				    "API Error",
				    JOptionPane.ERROR_MESSAGE
								 );
		    } finally
		    {
			  //Let you use the generate button again now.
			  generateButton.setEnabled(true);
			  cancelButton.setEnabled(false);
			  generateButton.setText("Generate");
		    }
		}
	  }.execute();
    }
}
