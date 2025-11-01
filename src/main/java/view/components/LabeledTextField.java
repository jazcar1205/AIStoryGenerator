package view.components;

import javax.swing.*;

public class LabeledTextField extends JPanel
{
    private JLabel label;
    private JTextField selector;

    public LabeledTextField(String label, int columns)
    {
	  this.label = new JLabel(label);
	  this.selector = new JTextField(columns);
	  add(this.label);
	  add(this.selector);
    }

    public String getText()
    {
	  return selector.getText();
    }
}
