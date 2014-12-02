package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


// panel to display trends
public class DisplayPanel extends JFrame{

	private JButton close; 
	
	public DisplayPanel(String title, int x) {
		super(title);
		
		builder();
		
		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		
		if (x ==1){
			tablefill1();
		}
		else if (x==2){
			tablefill2();
		}
		else {
			tablefill3();
		}
	}
	
	private void tablefill1(){
		
	}
	
	private void tablefill2(){
		
		
	}
	
	private void tablefill3(){
		
	}
	
	private void builder(){
		close = new JButton ("Close");
		
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
	     
	     finalpanel.add(close); 
	     close.setAlignmentX(CENTER_ALIGNMENT);
	     
	     
	     this.add(finalpanel);
	     
	}

}
