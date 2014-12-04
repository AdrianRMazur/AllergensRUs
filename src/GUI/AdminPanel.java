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
import javax.swing.JPanel;



// admin panel displays trends
public class AdminPanel extends JFrame {
	
	private JButton pattern1; 
	private JButton pattern2; 
	private JButton pattern3; 
	

	public AdminPanel(String title) {
		super(title);
		
		builder();
		
		pattern1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				nextdisplay(1);
			}
		});
		
		pattern2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				nextdisplay2(2);
			}
		});
		
		
		pattern3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				nextdisplay(3);
			}
		});
		
		
	}
	
	
	private void nextdisplay(int x){
		JFrame displayframe = new DisplayPanel("Allergy's R Us - Trends", x);
		displayframe.setResizable(true);
		displayframe.setLocationRelativeTo(null);
		displayframe.setVisible(true);
		displayframe.setMinimumSize(new Dimension(400, 300));
	}
	
	private void nextdisplay2(int x){
		JFrame displayframe = new DisplayPanel("Allergy's R Us - Trends", x);
		displayframe.setResizable(true);
		displayframe.setLocationRelativeTo(null);
		displayframe.setVisible(true);
		displayframe.setMinimumSize(new Dimension(400, 200));
	}
	
	private void builder(){
		JLabel info = new JLabel("General Patters");
		info.setForeground(Color.GREEN);
		JLabel info2 = new JLabel ("Click to view a pattern");
		pattern1 = new JButton ("Pattern 1");
		pattern2 = new JButton ("Pattern 2");
		pattern3 = new JButton ("Pattern 3");
		
		JPanel input = new JPanel();
		
		input.setLayout(new BoxLayout(input, BoxLayout.X_AXIS));
		input.add(pattern1);
		input.add(Box.createRigidArea(new Dimension (10, 0)));
		input.add(pattern2);
		input.add(Box.createRigidArea(new Dimension (10, 0)));
		input.add(pattern3);
		
		
		JPanel finalpanel = new JPanel();
		finalpanel.setLayout(new BoxLayout(finalpanel, BoxLayout.Y_AXIS));
		finalpanel.add(Box.createRigidArea(new Dimension(0, 15)));
		finalpanel.add(info);
		info.setAlignmentX(CENTER_ALIGNMENT);
		finalpanel.add(Box.createRigidArea(new Dimension(0, 10)));
		finalpanel.add(info2);
		info2.setAlignmentX(CENTER_ALIGNMENT);
		finalpanel.add(Box.createRigidArea(new Dimension(0, 15)));
		finalpanel.add(input);
		input.setAlignmentX(CENTER_ALIGNMENT);
		
		this.add(finalpanel);
	}    
}
