package view;

import view.components.LabeledTextField;

import javax.swing.*;

public class ControlPanel extends JPanel
{
    private JLabel label;
    private LabeledTextField genreField;
    private LabeledTextField characterField;
    private LabeledTextField worldBuilding;

    public ControlPanel()
    {
	  setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	  initComponents();
	  initLayout();
    }

    private void initComponents()
    {
	  label = new JLabel("Options");
	  genreField = new LabeledTextField("Genre ", 1,16);
	  characterField = new LabeledTextField("Characters ", 10,30);
	  worldBuilding = new LabeledTextField("World Building ", 10,30);
    }
    private void initLayout()
    {
	  add(label);
	  add(genreField);
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