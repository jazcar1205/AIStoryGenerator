package view.components;

import javax.swing.*;
import java.awt.*;

/**
 * Create a save dialog
 */
public class SaveDialog extends JDialog
{
    private LabeledTextField nameField;

    /**
     * Creates a save dialog
     */
    public SaveDialog()
    {
	super();
	setTitle("Saving ....");
	setLayout(new BorderLayout());
	setModal(true);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	nameField = new LabeledTextField("File Name", 1,10);
	add(nameField, BorderLayout.NORTH);
	JPanel buttonPanel = new JPanel();
	JButton okButton = new JButton("OK");
	okButton.addActionListener(e -> {
	    //call save function here.
	    System.out.println("Saving... " + nameField.getText());
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
     * Get the file name from the enter field.
     * @return The value in the text field.
     */
    public String getFileName()
    {
	  return nameField.getText();
    }
}
