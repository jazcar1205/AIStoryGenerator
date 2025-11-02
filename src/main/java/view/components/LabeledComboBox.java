package view.components;

import javax.swing.*;

public class LabeledComboBox extends JPanel
{
    public LabeledComboBox(String labelText, String[] options)
    {
	  setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	  JLabel label = new JLabel(labelText);
	  JComboBox<String> comboBox = new JComboBox<>(options);
	  setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
	  add(label);
	  add(comboBox);
    }
}
