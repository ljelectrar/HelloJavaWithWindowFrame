import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class HelloJava {
	public static void main(String args[]) {
	JFrame frame = new JFrame("Hello Java, @ljelectrar");
	JLabel label = new JLabel("Hello Java, @ljelectrar", 
		JLabel.CENTER);
	frame.add(label);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(300, 300);
	frame.setVisible(true);
	//System.out.println("Hello Java, Leandro");
	} 
}

class HelloComponent extends JComponent 
	implements MouseMotionListener, 
	ActionListener, 
	Runnable
{

	String theMessage;
	int messageX = 125;
	int messageY = 95; 

	JButton theButton;

	int colorIndex;
	static Color[] someColors = 
	{
		Color.black, Color.red, 
		Color.green, Color.blue, Color.magenta
	};

	boolean blinkState;

	public HelloComponent(String message){
		theMessage = message;
		theButton = new JButton("Change Color");
		setLayout(new FlowLayout());
		add(theButton);
		theButton.addActionListener(this);
		addMouseMotionListener(this);
		Thread t = new Thread(this);
		t.start();
	}

	public void painComponet(Graphics g){
		g.setColor(blinkState? getBackground() : currentColor());
		g.drawString(theMessage, messageX, messageY);
	}

	public void mouseDragged(MouseEvent e) {
		messageX = e.getX();
		messageY = e.getY();
		repaint();
	}

	public void mouseMoved(MouseEvent e) {}

	public void actionPerformed (ActionEvent e){
		if (e.getSource() == theButton)
			changeColor();
	}

	synchronized private void changeColor() {
		if(++colorIndex == someColors.length)
			colorIndex = 0;
		setForeground(currentColor());
		repaint();
	}

	synchronized private Color currentColor() {
		return someColors[colorIndex];
	}

	public void run() {
		try {
			while(true) {
				blinkState = !blinkState;
				repaint();
				Thread.sleep(300);
			}
		} 
		catch (InterruptedException ie)
		{}
	}

}