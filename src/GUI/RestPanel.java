package GUI;

import java.awt.Color;
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



// restaurant panel. the panel that gives option to display normal menu or personalized menu
public class RestPanel extends JFrame{

	
	private JButton nextpersonalized;
	
	private JButton next; 
	
	public static JTextField name;
	
	
	
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
		personframe.setMinimumSize(new Dimension(400, 185));
	}
	
	private void custmenu(){
	
		JFrame custframe = new CustPanel("Allergy's R Us - "+ name.getText() + "'s Menu");
		custframe.pack();
		custframe.setVisible(true);
		custframe.setLocationRelativeTo(null);
		custframe.setMinimumSize(new Dimension(400, 185));
		custframe.setResizable(true);
		custframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	
	private void error(){
		String x = "Error: Please enter your name ";
		JOptionPane.showMessageDialog(null, x, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private void builder(){
		 next = new JButton("Display Menu");
		 nextpersonalized = new JButton ("Display Personalized Menu");
		 
		 name = new JTextField(); 
		 
		JLabel info1 = new JLabel("Information for "+FirstPanel.restaurant.getText() +" has been loaded");
		info1.setForeground(Color.GREEN);
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
		finalpanel.add(info1);
		info1.setAlignmentX(CENTER_ALIGNMENT);
		
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
