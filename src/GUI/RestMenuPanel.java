package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.h2.tools.DeleteDbFiles;


public class RestMenuPanel extends JFrame{

	
	private JButton back; 
	private String restaurantName; 
	private Object[][] data; 
	
	
	public RestMenuPanel(String title) {
		super(title);
		restaurantName=FirstPanel.restaurant.getText(); 
		builder();
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				FirstPanel.nextrestaurant();
			}
		});
	}
	
	private void builder(){
		
		try {
			DBQuery();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		back = new JButton ("Back");
		
		String[] columnNames = {"Dish", "Day"};
		

		 
		 final JTable table = new JTable(data, columnNames);
		 table.setPreferredScrollableViewportSize(new Dimension(100, 100));
	     table.setFillsViewportHeight(true);
	     table.setEnabled(false);

	     
	     JScrollPane scrollPane = new JScrollPane(table);
		
	     JPanel finalpanel = new JPanel();
	     finalpanel.setLayout(new BoxLayout(finalpanel, BoxLayout.Y_AXIS));
	     finalpanel.add(scrollPane);
	     finalpanel.add(back); 
	     back.setAlignmentX(CENTER_ALIGNMENT);

	     this.add(finalpanel);
	     
		
	}
	
	
	
	public void DBQuery() throws SQLException, ClassNotFoundException{
		
       // DeleteDbFiles.execute("~", "test", true);

        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test");
        Statement stat = conn.createStatement();

        // this line would initialize the database
        // from the SQL script file 'init.sql'
        //stat.execute("runscript from 'init.sql'");

        String current = System.getProperty("user.dir");
        //System.out.println("Current working directory in Java : " + current);

        
       stat.execute("DROP TABLE IF EXISTS SERVES; create table Serves As Select * from csvread('"+current+"\\data\\Serves1.0.csv')");
       // stat.execute("insert into test values(1, 'Hello')");
        
        
        ResultSet rs;
        ResultSet count;
        count = stat.executeQuery("Select Count(*) From Serves Where Restaurant = '"+restaurantName+"'");
        count.next();
        
        int size= Integer.parseInt(count.getString("Count(*)"));
        data= new Object[size][2];
        rs = stat.executeQuery("Select * From Serves Where Restaurant = '"+restaurantName+"'");
        int c =0; 
        while (rs.next()) {
        	data[c][0] = rs.getString("Food");
        	data[c][1] = rs.getString("Day");
        
            c++;
        }
        stat.close();
        conn.close();
		
			
	}
	
	
	

}
