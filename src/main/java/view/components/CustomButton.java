package view.components;

import javax.swing.*;

public class CustomButton extends JButton
{
    public CustomButton(String text)
    {
	  setText(text);
	  setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
	  setAlignmentX(CENTER_ALIGNMENT);
    }
}
