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

public class RestPanel extends JFrame{

	
	private JButton next;
	
	private JButton back; 
	
	private JTextField restaurant; 
	
	public RestPanel(String title) {
		
		super(title);
		
		builder(); 
	
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				FirstPanel.main(null);
			}
		});
		
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e1){
				
				if ( restaurant.getText().equals("")){
					error();
					restaurant.setText("");
				}
				else {
					prompt(); 
					
				}
			}
		});
	}
	
	private void nextmenu(){
		JFrame personframe = new RestMenuPanel("Allergy's R Us - Restaurant Menu");
		personframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		personframe.setResizable(true);
		personframe.setLocationRelativeTo(null);
		personframe.setVisible(true);
		personframe.setMinimumSize(new Dimension(450, 250));
	}
	
	private void prompt(){
		
		String x = "Under Penalty of Death: I certify that I am the restaurant manager";
		int result = JOptionPane.showConfirmDialog(null, x, "Warning", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION){
			dispose();
			nextmenu();
		}
		else {
			restaurant.setText("");
		}
		
	}
	
	private void error(){
		String x = "Error: Please enter the restaurant name ";
		JOptionPane.showMessageDialog(null, x, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private void builder(){
		 next = new JButton("Display Menu");
		 back = new JButton ("Back");
		 restaurant = new JTextField(); 
		
		JLabel info2 = new JLabel("Restaurant Name:");
		JLabel info = new JLabel("Please type the restaurant name to display the menu");
		
		JPanel input = new JPanel();
		input.setLayout(new BoxLayout(input, BoxLayout.X_AXIS));
		input.add(info2);
		input.add(Box.createRigidArea(new Dimension(10,0)));
		input.add(restaurant);
		input.setMaximumSize(new Dimension(300, 30));
		
		JPanel input2 = new JPanel(); 
		input2.setLayout(new BoxLayout(input2, BoxLayout.X_AXIS));
		input2.add(back);
		input2.add(Box.createRigidArea(new Dimension (20, 0)));
		input2.add(next);
		
		
		
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
		
		
		this.add(finalpanel);
		
	}
	
	
	
	
	

}
