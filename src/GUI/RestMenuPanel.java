package GUI;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RestMenuPanel extends JFrame{

	
	private JButton back; 
	
	public RestMenuPanel(String title) {
		
		super(title);
		
		builder();
		
		
	}
	
	private void builder(){
		back = new JButton ("Back");
		
		String[] columnNames = {"Dish",
                "Price",
                "Weekday"};
		
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
		 table.setPreferredScrollableViewportSize(new Dimension(300, 70));
	     table.setFillsViewportHeight(true);
	     
	     
	     JScrollPane scrollPane = new JScrollPane(table);
		
	     JPanel finalpanel = new JPanel();
	     finalpanel.setLayout(new BoxLayout(finalpanel, BoxLayout.Y_AXIS));
	     finalpanel.add(scrollPane);
	     
	     this.add(finalpanel);
	     
		
	}

}
