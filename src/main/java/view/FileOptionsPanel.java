package view;

import view.components.CustomButton;
import view.components.LabeledTextField;
import view.components.LoadDialog;
import view.components.SaveDialog;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Panel for file options like saving, loading, and tagging.
 */
public class FileOptionsPanel extends JPanel
{
    private CustomButton saveButton;
    private CustomButton loadButton;
    private LabeledTextField tagsLabel;
    private CustomButton addTags;
    private ArrayList<String> tags;
    private String fileName;

    /**
     * Constructs a file options pane, requiring the name for the current file.
     * @param fileName
     */
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
		this.fileName = sd.getFileName();
		//need save actions from session.
		sd.setVisible(true);
	  });
	  add(saveButton);
	  add(Box.createRigidArea(new Dimension(0,20)));
	  add(loadButton);
	  loadButton.addActionListener(e -> {
		LoadDialog ld = new LoadDialog();
		this.fileName = ld.getFileName();
		//need load actions from session.
		ld.setVisible(true);
	  });
	  add(Box.createRigidArea(new Dimension(0,20)));
	  add(tagsLabel);
	  tagsLabel.setText(loadTags());
	  add(addTags);
	  addTags.addActionListener(e -> readTags());
    }

    /**
     * Reads the tags written in the tags field.
     */
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

    /**
     * Loads the tags into the
     * @return
     */
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
