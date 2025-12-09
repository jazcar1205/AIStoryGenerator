package view;

import model.Complexity;
import model.Length;
import model.StoryModel;
import model.StrategyType;
import view.components.LabeledComboBox;
import view.components.LabeledTextField;

import javax.swing.*;
/**
 * Creates the control panel.
 */
public class ControlPanel extends JPanel
{
    private LabeledComboBox lengthField;
    private LabeledComboBox complexityField;
    private LabeledComboBox genreField;
    private LabeledTextField characterField;
    private LabeledTextField worldBuilding;
    private LabeledTextField styleField;

    /**
     * Create a control panel.
     */
    public ControlPanel()
    {
	  setBorder(BorderFactory.createTitledBorder("Options"));
	  setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	  initComponents();
	  initLayout();
    }

    /**
     * Initialize the componets
     */
    private void initComponents()
    {
	  lengthField = new LabeledComboBox("Length", new String[] {"Short", "Medium", "Long"});
	  complexityField = new LabeledComboBox("Complexity",
		    new String[] {"Child Friendly", "Average", "Complex"});
	  genreField = new LabeledComboBox("Genre ", new String[]{"Fantasy", "Horror", "Romance", "SciFi"});
	  styleField = new LabeledTextField("Style ", 1,16);
	  characterField = new LabeledTextField("Characters ", 10,30);
	  worldBuilding = new LabeledTextField("World Building ", 10,30);
    }

    /**
     * Sets the options in the control panel to the correct field.
     * @param length
     * @param complexity
     * @param genre
     */
    public void setOptions(Length length, Complexity complexity, StrategyType genre)
    {
	  lengthField.setSelection(length.toString());
	  complexityField.setSelection(complexity.toString());
	  genreField.setSelection(genre.toString());
    }

    /**
     * Creates a new session with the options currently selected.
     * @return
     */
    public StoryModel getOptions()
    {
	  return new StoryModel(Length.getLength(lengthField.getSelection()),
		    Complexity.getComplexity(complexityField.getSelection()), StrategyType.getStrat(genreField.getSelection()));
    }
    /**
     * Add the components to the layout.
     */
    private void initLayout()
    {
	  add(lengthField);
	  add(complexityField);
	  add(genreField);
	  add(styleField);
	  add(characterField);
	  add(worldBuilding);
    }

    /**
     * Helper function to get the values out of each of the various components.
     * Mostly just for testing to make sure the correct values are there.
     * @return a string version of the fields.
     */
    public String getValues()
    {
	  StringBuilder values = new StringBuilder();
	  values.append("Length: " + lengthField.getSelection());
	  values.append("\nComplexity: " + complexityField.getSelection());
	  values.append("\nGenre: " + genreField.getSelection());
	  values.append("\nStyle: " + styleField.getText());
	  values.append("\nCharacters: " + characterField.getText());
	  values.append("\nWorld Building: " + worldBuilding.getText());
	  return values.toString();
    }
}