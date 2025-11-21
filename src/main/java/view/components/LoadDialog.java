package view.components;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Create a dialog to load a file.
 */
public class LoadDialog extends JDialog
{
    private LabeledComboBox nameField;
    private String selection;

    public LoadDialog()
    {
	  super();
	  setTitle("Saving ....");
	  setLayout(new BorderLayout());
	  setModal(true);
	  setLocationRelativeTo(null);
	  setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	  String[] fileNames = getFileNames();
	  nameField = new LabeledComboBox("File Name", fileNames);//put other options from resources/save_files folder.);
	  add(nameField, BorderLayout.NORTH);
	  JPanel buttonPanel = new JPanel();
	  JButton okButton = new JButton("OK");
	  okButton.addActionListener(e ->
	  {
		//call load function here.
		System.out.println("Opening..." + nameField.getSelection());
		this.selection = nameField.getSelection();
		dispose();
	  });
	  JButton cancelButton = new JButton("Cancel");
	  cancelButton.addActionListener(e -> dispose());
	  buttonPanel.add(okButton);
	  buttonPanel.add(cancelButton);
	  add(buttonPanel, BorderLayout.SOUTH);
	  pack();
	  setSize(200, 150);
    }

    /**
     * Grabs the file names from the save_files directory
     * to list them in the drop down menu.
     * @return
     */
    private String[] getFileNames()
    {
	  //Getting the potential save files from the folder.
	  ArrayList<String> results = new ArrayList<String>();
	  File[] files = new File("save_files").listFiles();
	  if(files != null)
	  {
		for (File file : files) {
		    if (file.isFile()) {
			  results.add(file.getName());
		    }
		}
		return results.toArray(new String[results.size()]);
	  }
	  return new String[]{"Select a file name"};
    }
    /**
     * Get the file name from the enter field.
     * @return The value in the selector.
     */
    public String getFileName()
    {
	  return selection;
    }
}
