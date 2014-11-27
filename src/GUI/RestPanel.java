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

	
	private JButton nextpersonalized;
	
	private JButton next; 
	
	private JTextField name;  
	
	public RestPanel(String title) {
		
		super(title);
		
		builder(); 
	
		nextpersonalized.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if ( name.getText().equals("")){
					error();
					name.setText("");
				}
				else {
					custmenu(); 
					dispose();
				}
			}
		});
		
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e1){
				
				nextmenu(); 
				dispose();
				
			}
		});
	}
	
	private void nextmenu(){
		JFrame personframe = new RestMenuPanel("Allergy's R Us - Restaurant Menu");
		personframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		personframe.setResizable(true);
		personframe.setLocationRelativeTo(null);
		personframe.setVisible(true);
		personframe.setMinimumSize(new Dimension(600, 600));
	}
	
	private void custmenu(){
	
		JFrame firstframe = new CustPanel("Allergy's R Us - Personalized Menu");
		firstframe.pack();
		firstframe.setVisible(true);
		firstframe.setLocationRelativeTo(null);
		firstframe.setMinimumSize(new Dimension(400, 185));
		firstframe.setResizable(true);
		firstframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	
	private void error(){
		String x = "Error: Please enter the restaurant name ";
		JOptionPane.showMessageDialog(null, x, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private void builder(){
		 next = new JButton("Display Menu");
		 nextpersonalized = new JButton ("Display Personalized Menu");
		 
		 name = new JTextField(); 
		
		JLabel info2 = new JLabel(" Name:");
		JLabel info = new JLabel ("Click here to view the full menu");
		JLabel info4 = new JLabel ("----- OR -----");
		JLabel info3 = new JLabel("Enter your name to view your personalized menu");
		
		JPanel input = new JPanel();
		input.setLayout(new BoxLayout(input, BoxLayout.X_AXIS));
		input.add(info2);
		input.add(Box.createRigidArea(new Dimension(10,0)));
		input.add(name);
		input.setMaximumSize(new Dimension(200, 30));
		

		
		JPanel finalpanel = new JPanel ();
		finalpanel.setLayout(new BoxLayout(finalpanel, BoxLayout.Y_AXIS));
		finalpanel.add(Box.createRigidArea(new Dimension(0, 15)));
		
		finalpanel.add(info);
		info.setAlignmentX(CENTER_ALIGNMENT);
		
		finalpanel.add(Box.createRigidArea(new Dimension(0, 8)));
		
		finalpanel.add(next);
		next.setAlignmentX(CENTER_ALIGNMENT);
		
		finalpanel.add(Box.createRigidArea(new Dimension(0, 15)));
		
		finalpanel.add(info4);
		info4.setAlignmentX(CENTER_ALIGNMENT);
		
		finalpanel.add(Box.createRigidArea(new Dimension(0, 15)));
		
		finalpanel.add(info3);
		info3.setAlignmentX(CENTER_ALIGNMENT);
		
		finalpanel.add(Box.createRigidArea(new Dimension(0, 15)));
			
		finalpanel.add(input);
		input.setAlignmentX(CENTER_ALIGNMENT);
		finalpanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		finalpanel.add(nextpersonalized);
		nextpersonalized.setAlignmentX(CENTER_ALIGNMENT);
		
		this.add(finalpanel);
		
	}
	
	
	
	
	

}
