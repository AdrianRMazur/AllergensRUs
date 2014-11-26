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
	
	private JButton next; 
	
	private JTextField name; 
	
	private JTextField restaurant; 
	
	
	public CustPanel(String title) {
		super (title);
		
		builder();
		
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				FirstPanel.main(null);
				 
			}
		});
		
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e1){
				
				if (name.getText().equals("") || restaurant.getText().equals("")){
					error();
					name.setText("");
					restaurant.setText("");
				}
				else {

					dispose();
					nextmenu();
				}
			}
		});
	}

	
	private void error(){
		String x = "Error: Please enter your name and the restaurant name ";
		JOptionPane.showMessageDialog(null, x, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private void nextmenu(){
		JFrame personframe = new CustMenuPanel("Allergy's R Us - Customer Menu");
		personframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		personframe.setResizable(true);
		personframe.setLocationRelativeTo(null);
		personframe.setVisible(true);
		personframe.setMinimumSize(new Dimension(450, 250));
	}
	
	
	private void builder(){
		back = new JButton ("Back");
		next = new JButton ("Display Menu");
		name = new JTextField();
		name.setText("");
		restaurant = new JTextField(); 
		restaurant.setText("");
		
		JLabel info = new JLabel ("Enter your name and current restaurant you are at to view personalized menu");
		JLabel infoname = new JLabel ("Your Name: ");
		JLabel inforest = new JLabel ("Restaurant: ");
		
		
		JPanel input = new JPanel (); 
		input.setLayout(new BoxLayout(input, BoxLayout.X_AXIS));
		input.add(infoname);
		input.add(Box.createRigidArea(new Dimension(10,0)));
		input.add(name);
		input.setMaximumSize(new Dimension (360, 30));
		
		
		JPanel input2 = new JPanel(); 
		input2.setLayout(new BoxLayout(input2, BoxLayout.X_AXIS));
		input2.add(inforest);
		input2.add(Box.createRigidArea(new Dimension(10,0)));
		input2.add(restaurant);
		input2.setMaximumSize(new Dimension (360, 30));
		
		JPanel input3 = new JPanel(); 
		input3.setLayout(new BoxLayout(input3, BoxLayout.X_AXIS));
		input3.add(back);
		input3.add(Box.createRigidArea(new Dimension(20,0)));
		input3.add(next);
		
		
		
		JPanel finalpanel = new JPanel ();
		finalpanel.setLayout(new BoxLayout(finalpanel, BoxLayout.Y_AXIS));
		finalpanel.add(Box.createRigidArea(new Dimension(0, 20)));
		finalpanel.add(info);
		info.setAlignmentX(CENTER_ALIGNMENT);
		finalpanel.add(Box.createRigidArea(new Dimension(0, 20)));
		finalpanel.add(input);
		input.setAlignmentX(CENTER_ALIGNMENT);
		finalpanel.add(Box.createRigidArea(new Dimension(0, 20)));
		finalpanel.add(input2);
		input2.setAlignmentX(CENTER_ALIGNMENT);
		finalpanel.add(Box.createRigidArea(new Dimension(0, 20)));
		finalpanel.add(input3);
		input3.setAlignmentX(CENTER_ALIGNMENT);
		finalpanel.add(Box.createRigidArea(new Dimension(0, 20)));
		
		
		
		this.add(finalpanel);
	}
	
}
