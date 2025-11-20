package view.components;

import javax.swing.*;

/**
 * Custom button creation.
 */
public class CustomButton extends JButton
{
    /**
     * Create a custom button.
     * @param text the text included in the button.
     */
    public CustomButton(String text)
    {
	  setText(text);
	  setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
	  setAlignmentX(CENTER_ALIGNMENT);
    }
}
