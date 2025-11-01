package view.components;

import javax.swing.*;

public class LabeledTextField extends JPanel
{
    private final JTextArea selector;

    public LabeledTextField(String labelText, int rows, int columns)
    {
	  setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	  JLabel label = new JLabel(labelText);
	  this.selector = new JTextArea(rows,columns);
	  selector.setLineWrap(true);
	  selector.setVisible(true);
	  JScrollPane scroll = new JScrollPane(selector);
	  scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	  setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
	  add(label);
	  add(scroll);
    }

    public String getText()
    {
	  return selector.getText();
    }
}
