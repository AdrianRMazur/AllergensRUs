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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class FirstPanel extends JFrame{

	
	
	private JButton next;
	
	private JButton help;
	
	
	private JButton admin; 
	
	public static JTextField restaurant;
	
	public FirstPanel(String title){
		super (title);
		
		builder();
		
	
		
		
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e1){
				
				if ( restaurant.getText().equals("")){
					error();
					restaurant.setText("");
				}
				else {
					dispose(); 
					nextrestaurant(); 
				}
			}
		});
				
		
		help.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String x = "-Select the prompts as you move along.\n-This program is a free trial.\n-If you paid for it then you got ripped off."
						+ "\n-Professor Imielinski ROCKS!!!\n-Kosti & Adrian 2014.  "  ;
				JOptionPane.showMessageDialog(null, x, "Help Window", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		
		admin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				if ( restaurant.getText().equals("")){
					error();
					restaurant.setText("");
				}
				else {
					dispose();
					nextadmin(); 
				}
			}
		});
		
	}
	
	
	private void error(){
		String x = "Error: Please enter the restaurant name ";
		JOptionPane.showMessageDialog(null, x, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	
	public static void nextadmin(){
		JFrame adminframe = new AdminPanel("Allergy's R Us - Admin Page");
		adminframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminframe.setResizable(true);
		adminframe.setLocationRelativeTo(null);
		adminframe.setVisible(true);
		adminframe.setMinimumSize(new Dimension(370, 170));
	}
	
	
	public static void nextrestaurant(){
		JFrame restframe = new RestPanel("Allergy's R Us - Restaurant Page");
		restframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		restframe.setResizable(true);
		restframe.setLocationRelativeTo(null);
		restframe.setVisible(true);
		restframe.setMinimumSize(new Dimension(380, 290));
	}
	
	private void builder(){

		
		 next = new JButton("Load Restaurant");
		 restaurant = new JTextField(); 
		 admin = new JButton ("Admin");
		 
		 
		 help = new JButton ("Help");
		
		JLabel info2 = new JLabel("Restaurant Name:");
		JLabel info = new JLabel("Please type your restaurant name to load the details");
		
		JPanel input = new JPanel();
		input.setLayout(new BoxLayout(input, BoxLayout.X_AXIS));
		input.add(info2);
		input.add(Box.createRigidArea(new Dimension(10,0)));
		input.add(restaurant);
		input.setMaximumSize(new Dimension(300, 30));
		
		JPanel input2 = new JPanel(); 
		input2.setLayout(new BoxLayout(input2, BoxLayout.X_AXIS));
		input2.add(next);
		input2.add(Box.createRigidArea(new Dimension (20, 0)));
		input2.add(help);
		input2.add(Box.createRigidArea(new Dimension (20, 0)));
		input2.add(admin);
		
		
		
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
	
	

	public static void main(String[] args) {
		JFrame firstframe = new FirstPanel("Allergy's R Us - Restaurant Page");
		firstframe.pack();
		firstframe.setVisible(true);
		firstframe.setLocationRelativeTo(null);
		firstframe.setMinimumSize(new Dimension(400, 185));
		firstframe.setResizable(true);
		firstframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
	}

}
