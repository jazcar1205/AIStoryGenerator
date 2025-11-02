package view;

import view.components.CustomButton;
import view.components.LabeledTextField;
import view.components.LoadDialog;
import view.components.SaveDialog;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOptionsPanel extends JPanel
{
    private CustomButton saveButton;
    private CustomButton loadButton;
    private LabeledTextField tagsLabel;
    private CustomButton addTags;
    private ArrayList<String> tags;
    private String fileName;

    public FileOptionsPanel(String fileName)
    {
	  this.fileName = fileName;
	  this.tags = new ArrayList<>(); //need to set this to what is gotten from the file.
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
	  loadButton.addActionListener(e -> {
		LoadDialog ld = new LoadDialog();
		ld.setVisible(true);
	  });
	  add(Box.createRigidArea(new Dimension(0,20)));
	  add(tagsLabel);
	  tagsLabel.setText(loadTags());
	  add(addTags);
	  addTags.addActionListener(e -> {

	  });
    }

    private void readTags()
    {
	  Scanner scanner = new Scanner(tagsLabel.getText());
	  while(scanner.hasNextLine())
	  {
		String tag = scanner.nextLine();
		tags.add(tag);
		//add tag to the fileName as well.
	  }
    }

    private String loadTags()
    {
	  //load tags from fileName.
	  StringBuilder output = new StringBuilder();
	  for(String tag : tags)
	  {
		output.append(tag + "\n");
	  }
	  return output.toString();
    }
}
