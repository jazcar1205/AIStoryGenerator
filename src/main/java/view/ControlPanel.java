package view;

import model.options.*;
import model.StoryModel;
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
    private LabeledComboBox paceField;
    private LabeledComboBox persepctiveField;
    private LabeledTextField promptField;
    private LabeledTextField characterField;
    private LabeledTextField settingField;
    private LabeledTextField timePeriodField;
    private LabeledTextField toneField;

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
	  lengthField = new LabeledComboBox("Length", new String[] {"ANY", "Short", "Medium", "Long"});
	  complexityField = new LabeledComboBox("Complexity",
		    new String[] {"ANY", "Child Friendly", "Average", "Complex"});
	  genreField = new LabeledComboBox("Genre ", new String[]{"ANY", "Fantasy", "Horror", "Romance", "SciFi"});
	  paceField = new LabeledComboBox("Development Pace", new String[]{"ANY", "Slow", "Normal", "Fast"});
	  persepctiveField = new LabeledComboBox("Perspective ", new String[]{"ANY","First", "Third"});
	  promptField = new LabeledTextField("Prompt Keywords", 5, 30);
	  toneField = new LabeledTextField("Tone ", 5,30);
	  characterField = new LabeledTextField("Characters ", 10,30);
	  settingField = new LabeledTextField("Setting", 10,30);
	  timePeriodField = new LabeledTextField("Time Period", 5,30);
    }

    /**
     * Add the components to the layout.
     */
    private void initLayout()
    {
	  add(lengthField);
	  add(complexityField);
	  add(genreField);
	  add(paceField);
	  add(persepctiveField);
	  add(promptField);
	  add(toneField);
	  add(timePeriodField);
	  add(settingField);
	  add(characterField);
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
     * Sets the options in the control panel to the correct field.
     * @param model
     */
    public void setOptions(StoryModel model)
    {
	  //String story, String prompt, Length len, Complexity complex, StrategyType strategy,String setting, String tone, String timePeriod, Pace pace, Perspective pers, String characters
	  lengthField.setSelection(model.getLength().toString());
	  promptField.setText(model.getPromptKeyWords());
	  complexityField.setSelection(model.getComplexity().toString());
	  genreField.setSelection(model.getStrategy().toString());
	  settingField.setText(model.getSetting());
	  timePeriodField.setText(model.getTimePeriod());
	  toneField.setText(model.getTone().getTone());
	  paceField.setSelection(model.getPace().toString());
	  persepctiveField.setSelection(model.getPers().toString());
	  characterField.setText(model.getCharacters().getCharacters());
    }

    /**
     * Creates a new session with the options currently selected.
     * @return
     */
    public StoryModel getOptions()
    {
	  //String story, String prompt, Length len, Complexity complex, StrategyType strategy,String setting, String tone, String timePeriod, Pace pace, Perspective pers, String characters
	  return new StoryModel(
		    promptField.getText(),
		    Length.getLength(lengthField.getSelection()),
		    Complexity.getComplexity(complexityField.getSelection()),
		    StrategyType.getStrat(genreField.getSelection()),
		    settingField.getText(),
		    toneField.getText(),
		    timePeriodField.getText(),
		    Pace.getPace(paceField.getSelection()),
		    Perspective.getPers(persepctiveField.getSelection()),
		    characterField.getText()
	  );
    }

    /**
     * Helper function to get the values out of each of the various components.
     * Mostly just for testing to make sure the correct values are there.
     * @return a string version of the fields.
     */
    public String getValues()
    {
	  StringBuilder values = new StringBuilder();
	  values.append("Prompt: " + promptField.getText() + "\n");
	  values.append("Length: " + lengthField.getSelection());
	  values.append("\nComplexity: " + complexityField.getSelection());
	  values.append("\nGenre: " + genreField.getSelection());
	  values.append("\nPacing: " + paceField.getSelection());
	  values.append("\nPerspective: " + persepctiveField.getSelection());
	  values.append("\nTone: " + toneField.getText());
	  values.append("\nCharacters: " + characterField.getText());
	  values.append("\nSetting: " + settingField.getText());
	  values.append("\nTime Period: " + timePeriodField.getText());
	  return values.toString();
    }
}