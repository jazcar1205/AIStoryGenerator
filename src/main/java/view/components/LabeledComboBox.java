package view.components;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * A class to create a Jpanel with a label and ComboBox for easy containment.
 */
public class LabeledComboBox extends JPanel
{

    private JComboBox<String> comboBox;
    private String selection;
    /**
     *
     * @param labelText The text for the label.
     * @param options The options to include in the combo box.
     */
    public LabeledComboBox(String labelText, String[] options)
    {
	  setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	  JLabel label = new JLabel(labelText);
	  label.setAlignmentX(CENTER_ALIGNMENT);
	  selection = options[0]; // first option is default.
	  comboBox = new JComboBox<>(options);
	  comboBox.addItemListener(new ItemListener()
	  {
		@Override
		public void itemStateChanged(ItemEvent e)
		{
		    selection = comboBox.getSelectedItem().toString();
		}
	  });
	  setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
	  add(label);
	  add(comboBox);
    }

    public String getSelection()
    {
	  return selection;
    }
}
