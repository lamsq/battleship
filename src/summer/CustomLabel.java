package summer;

import javax.swing.*;

public class CustomLabel extends JLabel {	
	public CustomLabel(CustomPanel container, String title) {	//label constructor
		//sets parameters
		this.setText(title);
		container.add(this);
	}
}
