package _03_Hangman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Hangman implements KeyListener {

	
	
	String currentWord;
	
	JFrame frame;
	JPanel panel;
	JLabel label;
	
	int lives = 15;
	
	Stack<String> words;
	
	public static void main(String[] args) {
		new Hangman().setup();
		
	}
	
	void setup() {
		int numWords = Integer.parseInt(JOptionPane.showInputDialog("Enter a number of words"));
		
		words = new Stack<>();
		String testWord = "";
		
		
		for(int i = 0; i < numWords; i++) {
			testWord = Utilities.readRandomLineFromFile("dictionary.txt");
			while(words.contains(testWord)) {
				testWord = Utilities.readRandomLineFromFile("dictionary.txt");
			}
			words.push(testWord);
			//System.out.println(testWord);
		}
		
		currentWord = words.pop();
		
		frame = new JFrame();
		panel = new JPanel();
		label = new JLabel();
		
		frame.add(panel);
		panel.add(label);
		
		frame.setSize(500, 500);
		frame.addKeyListener(this);
		frame.setVisible(true);
		
		label.setText(currentWord);
		label.setText(label.getText().replaceAll(".", "_ "));
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(currentWord.contains(e.getKeyChar() + "")) {
			while(currentWord.contains(e.getKeyChar() + "")) {
				int index = currentWord.indexOf(e.getKeyChar());
				label.setText(label.getText().substring(0, index * 2) + currentWord.charAt(index) + label.getText().substring(index * 2+1));
				currentWord = currentWord.substring(0, index) + "_" + currentWord.substring(index + 1);
			}
		}else {
			lives--;
			System.out.println("Lives: " + lives);
			if(lives <= 0) {
				System.out.println("Game Over!");
				if(JOptionPane.showInputDialog("Play Again?(Y/N)").equalsIgnoreCase("y")) {
					new Hangman().setup();
				}else {
					System.exit(0);
				}
			}
		}
		//System.out.println(currentWord);
		if(currentWord.replaceAll("_", " ").trim().equals("")) {
			if(words.size() > 0) {
				System.out.println(label.getText().replaceAll(" ", ""));
				currentWord = words.pop();
				label.setText(currentWord);
				label.setText(label.getText().replaceAll(".", "_ "));
				System.out.println("Words Remaining: " + (words.size() + 1));
				lives = 15;
				System.out.println("Lives: " + lives);
			}else {
				System.out.println(label.getText().replaceAll(" ", ""));
				System.out.println("You Win!");
				System.exit(0);
			}
			
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
