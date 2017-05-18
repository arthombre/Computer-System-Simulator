/**
 * 
 */
package csasimulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.JTextField;


/**
 * @author phani
 *
 * By default, it uses standard input
 * But if the user enters a string in the Console "Keyboard" field
 *  then Keyboard will switch over to the Text enterred in that field. 
 */
public class Keyboard {
	
	Scanner kb;
	/**
	 * 
	 */
	public Keyboard() {
		kb = new Scanner(System.in);
	}
	
	public void setScanner(String s) {
		kb = new Scanner(s);
	}
	
	public int getInt() {
		try {
			return kb.nextInt();
		} catch(Exception e) {
			return 0;
		}
	}
	
	public String getString() {
		return kb.next();
	}
	
	public String getLine() {
		return kb.nextLine();
	}

	public char getChar() {
		kb.useDelimiter("");
		try {
			
			return kb.next().charAt(0);
		} catch(Exception e) {
			System.out.print("end of input: returning 0 ");
			return 0;
		}
	}
	
	public int getStatus() {
		return 0;
	}
}
