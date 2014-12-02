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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;



// personalized menu 
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
	
	// rest food day
	
	private void builder(){
		
		
		JLabel info = new JLabel ("Displaying " + RestPanel.name.getText() + "'s personalized menu");
		back = new JButton ("Back");
		
		String[] columnNames = {"Dish", "Weekday"};
		
		 Object[][] data = {
			        {"Kathy", "Smith",
			         "Snowboarding"},
			        {"John", "Doe",
			         "Rowing"},
			        {"Sue", "Black",
			         "Knitting"},
			        {"Jane", "White",
			         "Speed reading"},
			        {"Joe", "Brown",
			         "Pool"}
			        };
		 
		
		 final JTable table = new JTable(data, columnNames);
		 table.setPreferredScrollableViewportSize(new Dimension(50, 50));
	     table.setFillsViewportHeight(true);
	     table.setEnabled(false);
	     
	     JScrollPane scrollPane = new JScrollPane(table);
			
	     JPanel finalpanel = new JPanel();
	     finalpanel.setLayout(new BoxLayout(finalpanel, BoxLayout.Y_AXIS));	
	     finalpanel.add(info);
	     info.setAlignmentX(CENTER_ALIGNMENT);
	     finalpanel.add(scrollPane);
	    
	     finalpanel.add(back); 
	     back.setAlignmentX(CENTER_ALIGNMENT);
	     
	     this.add(finalpanel);
		
	
	}
	
}
