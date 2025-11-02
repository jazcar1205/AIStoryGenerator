package view.components;

import javax.swing.*;
import java.awt.*;

public class LoadDialog extends JDialog
{
    private LabeledComboBox nameField;

    public LoadDialog()
    {
	  super();
	  setTitle("Saving ....");
	  setLayout(new BorderLayout());
	  setModal(true);
	  setLocationRelativeTo(null);
	  setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	  nameField = new LabeledComboBox("File Name", new String[]{
		    "Select a file name", //put other options from resources/save_files folder.
	  });
	  add(nameField, BorderLayout.NORTH);
	  JPanel buttonPanel = new JPanel();
	  JButton okButton = new JButton("OK");
	  okButton.addActionListener(e ->
	  {
		//call load function here.
		System.out.println("Opening..." + nameField.getSelection());
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

    public String getFileName()
    {
	  return nameField.getSelection();
    }
}
