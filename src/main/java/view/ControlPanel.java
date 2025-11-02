package view;

import view.components.LabeledComboBox;
import view.components.LabeledTextField;

import javax.swing.*;

public class ControlPanel extends JPanel
{
    private JLabel label;
    private LabeledComboBox lengthField;
    private LabeledComboBox complexityField;
    private LabeledTextField genreField;
    private LabeledTextField characterField;
    private LabeledTextField worldBuilding;
    private LabeledTextField styleField;

    public ControlPanel()
    {
	  setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	  initComponents();
	  initLayout();
    }

    private void initComponents()
    {
	  label = new JLabel("Options");
	  lengthField = new LabeledComboBox("Length", new String[] {"Short", "Medium", "Long"});
	  complexityField = new LabeledComboBox("Complexity", new String[] {"Medium", "Long"});
	  genreField = new LabeledTextField("Genre ", 1,16);
	  styleField = new LabeledTextField("Style ", 1,16);
	  characterField = new LabeledTextField("Characters ", 10,30);
	  worldBuilding = new LabeledTextField("World Building ", 10,30);
    }
    private void initLayout()
    {
	  add(label);
	  add(lengthField);
	  add(complexityField);
	  add(genreField);
	  add(styleField);
	  add(characterField);
	  add(worldBuilding);
    }

    /**
     * Get the genre included in the text field.
     * If none has been entered, return fantasy.
     * @return
     */
    public String getGenre()
    {
	  if(!genreField.getText().isBlank())
	  {
		return genreField.getText();
	  }
	  return "Fantasy";
    }
}