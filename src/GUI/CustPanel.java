package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustPanel extends JFrame {

	
	private JButton back; 
	
	
	
	
	
	public CustPanel(String title) {
		super (title);
		
		builder();
		
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				FirstPanel.nextrestaurant();
			}
		});

	}
	
	
	
	private void builder(){
		back = new JButton ("Back");
		
	
	}
	
}
