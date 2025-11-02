package view.components;

import javax.swing.*;

/**
 * Creates a text field and a label.
 */
public class LabeledTextField extends JPanel
{
    private final JTextArea selector;

    /**
     *
     * @param labelText The text for the label of the text field.
     * @param rows	The number of rows in the text box.
     * @param columns	The number of columns in the text box.
     */
    public LabeledTextField(String labelText, int rows, int columns)
    {
	  setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	  JLabel label = new JLabel(labelText);
	  label.setAlignmentX(CENTER_ALIGNMENT);
	  this.selector = new JTextArea(rows,columns);
	  selector.setLineWrap(true);
	  selector.setVisible(true);
	  JScrollPane scroll = new JScrollPane(selector);
	  scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	  setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
	  add(label);
	  add(scroll);
    }

    /**
     * Helps to get the text from the selector.
     * @return contents of the provided text box.
     */
    public String getText()
    {
	  return selector.getText();
    }
}
