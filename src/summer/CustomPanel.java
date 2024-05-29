package summer;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class CustomPanel extends JPanel {
	
	public CustomPanel (CustomFrame container, int top, int left , int bot, int right) {
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		this.setBorder(new EmptyBorder(new Insets(top,left,bot,right)));
		
		container.add(this);
	}
	
	public CustomPanel (CustomPanel container) {
		
		
		container.add(this);
		
	}
	
	
	
}
