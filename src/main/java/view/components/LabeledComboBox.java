package view.components;

import javax.swing.*;
import java.awt.event.ItemListener;

/**
 * A class to create a Jpanel with a label and ComboBox for easy containment.
 */
public class LabeledComboBox extends JPanel
{

    private JComboBox<String> comboBox;
    /**
     *
     * @param labelText The text for the label.
     * @param options The options to include in the combo box.
     */
    public LabeledComboBox(String labelText, String[] options)
    {
	  setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	  JLabel label = new JLabel(labelText);
	  comboBox = new JComboBox<>(options);
	  setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
	  add(label);
	  add(comboBox);
    }

    /**
     * Function to add a listener onto the custom box.
     * @param listener
     */
    public void addItemListener(ItemListener listener)
    {
	  comboBox.addItemListener(listener);
    }
}
