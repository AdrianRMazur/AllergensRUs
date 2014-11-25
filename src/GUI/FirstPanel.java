package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class FirstPanel extends JFrame{

	
	private  JButton person; 
	
	private  JButton restaurant; 
	
	public FirstPanel(String title){
		super (title);
		
		builder();
		

		person.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				nextperson();
			}
		});
		

		restaurant.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				nextrestaurant(); 
			}
		});
		
	}
	
	
	private void nextperson(){
		JFrame personframe = new CustPanel("Allergy's R Us - Customer Page");
		personframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		personframe.setResizable(true);
		personframe.setLocationRelativeTo(null);
		personframe.setVisible(true);
		personframe.setMinimumSize(new Dimension(450, 280));
	}
	
	private void nextrestaurant(){
		JFrame restframe = new RestPanel("Allergy's R Us - Restaurant Page");
		restframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		restframe.setResizable(true);
		restframe.setLocationRelativeTo(null);
		restframe.setVisible(true);
		restframe.setMinimumSize(new Dimension(350, 350));
	}
	
	private void builder(){
		person = new JButton("Customer");
		restaurant = new JButton ("Restaurant");
		JLabel instruction = new JLabel("Will you be using this program as a restaurant or a customer?");
		
		JPanel inputs = new JPanel();
		inputs.setLayout(new BoxLayout(inputs, BoxLayout.X_AXIS));
		
		
		
		JPanel finalpanel = new JPanel(); 
		
		finalpanel.setLayout(new BoxLayout(finalpanel, BoxLayout.Y_AXIS));
		finalpanel.add(Box.createRigidArea(new Dimension(0, 20)));
		finalpanel.add(instruction);
		instruction.setAlignmentX(CENTER_ALIGNMENT);
		finalpanel.add(Box.createRigidArea(new Dimension(0, 20)));
		finalpanel.add(person);
		person.setAlignmentX(CENTER_ALIGNMENT);
		finalpanel.add(Box.createRigidArea(new Dimension(0, 20)));
		finalpanel.add(restaurant);
		restaurant.setAlignmentX(CENTER_ALIGNMENT);
		finalpanel.add(Box.createRigidArea(new Dimension(0, 20)));
		this.add(finalpanel);
	}
	

	public static void main(String[] args) {
		JFrame firstframe = new FirstPanel("Allergy's R Us - Decision Page");
		firstframe.pack();
		firstframe.setVisible(true);
		firstframe.setLocationRelativeTo(null);
		firstframe.setMinimumSize(new Dimension(400, 190));
		firstframe.setResizable(true);
		firstframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
	}

}
