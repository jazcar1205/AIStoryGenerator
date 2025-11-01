package view;

import view.components.LabeledTextField;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel
{
    private LabeledTextField genreField;

    public ControlPanel()
    {
	  initComponents();
	  initLayout();
    }

    private void initComponents()
    {
	  genreField = new LabeledTextField("Genre", 16);
    }
    private void initLayout()
    {
	  add(genreField);
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