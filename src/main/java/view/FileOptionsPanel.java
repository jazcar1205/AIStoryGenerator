package view;

import view.components.CustomButton;
import view.components.LabeledTextField;
import view.components.SaveDialog;

import javax.swing.*;
import java.awt.*;

public class FileOptionsPanel extends JPanel
{
    private CustomButton saveButton;
    private CustomButton loadButton;
    private LabeledTextField tagsLabel;
    private CustomButton addTags;

    public FileOptionsPanel(String tags)
    {
	  setBorder(BorderFactory.createTitledBorder("File Management"));
	  setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	  saveButton = new CustomButton("Save");
	  loadButton = new CustomButton("Load");
	  tagsLabel = new LabeledTextField("Tags", 3, 10);
	  addTags = new CustomButton("Add Tags");
	  add(Box.createRigidArea(new Dimension(0,20)));
	  saveButton.addActionListener(e -> {
		SaveDialog sd = new SaveDialog();
		sd.setVisible(true);
	  });
	  add(saveButton);
	  add(Box.createRigidArea(new Dimension(0,20)));
	  add(loadButton);
	  add(Box.createRigidArea(new Dimension(0,20)));
	  add(tagsLabel);
	  add(addTags);
    }

}
