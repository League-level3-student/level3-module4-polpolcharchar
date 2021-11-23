package _00_IntroToStacks;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class _02_TextUndoRedo implements KeyListener {
    /* 
     * Create a JFrame with a JPanel and a JLabel.
     * 
     * Every time a key is pressed, add that character to the JLabel. It should
     * look like a basic text editor.
     * 
     * Make it so that every time the BACKSPACE key is pressed, the last
     * character is erased from the JLabel.
     * 
     * Save that deleted character onto a Stack of Characters.
     * 
     * Choose a key to be the Undo key. Make it so that when that key is
     * pressed, the top Character is popped  off the Stack and added back to
     * the JLabel.
     */
	
	JFrame frame;
	JPanel panel;
	JLabel label;
	
	Stack<Character> chars = new Stack<>();
	
	
	
	public static void main(String[] args) {
		new _02_TextUndoRedo().setup();
	}
	
	void setup() {
		frame = new JFrame();
		panel = new JPanel();
		label = new JLabel();
		
		frame.add(panel);
		panel.add(label);
		
		
		frame.setSize(500, 500);
		frame.setVisible(true);
		
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		label.setText(label.getText() + e.getKeyChar());
		if(KeyEvent.VK_BACK_SPACE == e.getKeyCode() && label.getText().length() > 0) {
		    String text = label.getText();
			chars.push(text.charAt(text.length()-2));
			label.setText(label.getText().substring(0, label.getText().length() - 2));
		}
		else if(KeyEvent.VK_ENTER == e.getKeyCode() && chars.size() > 0) {
			char popped = chars.pop();
			label.setText(label.getText() + popped);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	


}
