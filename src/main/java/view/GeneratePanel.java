package view;

import view.components.CustomButton;

import javax.swing.*;
import java.awt.*;

public class GeneratePanel extends JPanel
{
    private MainFrame parent;
    private CustomButton generateButton;
    private CustomButton cancelButton;
    public GeneratePanel(MainFrame parent)
    {
	  this.parent = parent;
	  setLayout(new GridBagLayout());

	  GridBagConstraints gbc = new GridBagConstraints();
	  // Common settings for all components
	  gbc.fill = GridBagConstraints.HORIZONTAL; // Make the component fill its display area horizontally
	  gbc.insets = new Insets(5, 5, 5, 5);

	  CustomButton blank = new CustomButton("");
	  blank.setEnabled(false);
	  gbc.gridx = 0;
	  gbc.weightx = 0.3;
	  add(blank, gbc);
	  //setting up generate button to take up 70% of the space
	  generateButton = new CustomButton("Generate");
	  generateButton.addActionListener(e -> onGenerate());
	  gbc.gridx = 1;
	  gbc.weightx = 0.5;
	  add(generateButton, gbc);

	  //cancel button will take up 30%
	  cancelButton = new CustomButton("Cancel");
	  cancelButton.addActionListener(e -> cancelRequest());
	  cancelButton.setEnabled(false);
	  gbc.gridx = 2;
	  gbc.weightx = 0.2;
	  add(cancelButton, gbc);
    }

    private void cancelRequest()
    {
	  System.out.println("Cancel Request");
    }

    private void fakeGenerate()
    {
	  parent.setOutputArea(parent.getControllerState().generateStoryDummy());
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
		    return parent.getControllerState().generateStory("coworkers");
		}

		//When the background thread is done, the GUI will be updated.
		@Override
		protected void done()
		{
		    try
		    {
			  parent.setOutputArea(get());
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
