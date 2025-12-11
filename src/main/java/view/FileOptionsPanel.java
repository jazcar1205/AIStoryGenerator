package view;

import persistence.Session;
import persistence.SessionManager;
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
    private JLabel fileNameLabel;
    private CustomButton saveButton;
    private CustomButton loadButton;
    private LabeledTextField tagsLabel;
    private CustomButton addTags;
    private ArrayList<String> tags;
    private SessionManager sessionManager;
    private MainFrame parent;

    /**
     * Constructs a file options pane, requiring the name for the current file.
     * @param parent the parent frame, the MainFrame
     */
    public FileOptionsPanel(MainFrame parent)
    {
	  this.parent = parent;
	  fileNameLabel = new JLabel("Untitled.txt");
	  this.tags = new ArrayList<>(); //need to set this to what is gotten from the file.
	  sessionManager = new SessionManager();
	  setBorder(BorderFactory.createTitledBorder("File Management"));
	  setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	  saveButton = new CustomButton("Save");
	  loadButton = new CustomButton("Load");
	  tagsLabel = new LabeledTextField("Tags", 3, 10);


	  //centering everything.
	  fileNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
	  saveButton.setHorizontalAlignment(SwingConstants.CENTER);
	  loadButton.setHorizontalAlignment(SwingConstants.CENTER);


	  setMinimumSize(new Dimension((int)loadButton.getPreferredSize().getWidth(), getHeight()));
	  addTags = new CustomButton("Add Tags");
	  add(fileNameLabel);
	  add(Box.createRigidArea(new Dimension(0,20)));
	  //setup save option with dialog.
	  saveButton.addActionListener(e -> {
		SaveDialog sd = new SaveDialog();
		sd.setVisible(true);
		//System.out.println("File name" + this.fileName);
		//need save actions from session.
		if(!sd.getFileName().isEmpty())
		{
		    sessionManager.saveSession(parent.getUpdatedSession(), sd.getFileName());
		    fileNameLabel.setText("FILE: " + sd.getFileName());
		}
		sd.setVisible(false);
	  });
	  add(saveButton);
	  add(Box.createRigidArea(new Dimension(0,20)));
	  add(loadButton);

	  //setup load option with dialog.
	  loadButton.addActionListener(e -> {
		LoadDialog ld = new LoadDialog();
		ld.setVisible(true);
		//need load actions from session.
		Session newSession = sessionManager.loadSession(ld.getFileName());
		//System.out.println(newSession);
		if(!ld.getFileName().isEmpty())
		{
		    fileNameLabel.setText("FILE: " + ld.getFileName());
		    this.parent.setControlPanelOptions(newSession);
		}
	  });
	  add(Box.createRigidArea(new Dimension(0,20)));
	  add(tagsLabel);
	  tagsLabel.setText(loadTags());
	  add(addTags);
	  addTags.addActionListener(e -> readTags());
    }

    /**
     * Reads the tags written in the tags field. -- currently not in use
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

    public String getFilePath()
    {
	  return sessionManager.getFilePath().toString();
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
