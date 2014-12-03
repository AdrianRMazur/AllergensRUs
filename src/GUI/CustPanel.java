package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

import org.h2.tools.DeleteDbFiles;



// personalized menu 
public class CustPanel extends JFrame {

	
	private JButton back; 
	
	private String restaurantName; 
	private String personName;
	
	private Object[][] data; 
	
	public CustPanel(String title) {
		super (title);
		
		restaurantName=FirstPanel.restaurant.getText();
		personName = RestPanel.name.getText();
		
		// querring customers 
		
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
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
	
		
		JLabel info = new JLabel ("Displaying " + RestPanel.name.getText() + "'s personalized menu");
		back = new JButton ("Back");
		
		String[] columnNames = {"Dish", "Day"};
		 
		
		 final JTable table = new JTable(data, columnNames);
		 table.setPreferredScrollableViewportSize(new Dimension(100, 100));
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
	
public void DBQuery() throws SQLException, ClassNotFoundException{
		

        DeleteDbFiles.execute("~", "test", true);

        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test");
        Statement stat = conn.createStatement();
        
        
        // this line would initialize the database
        // from the SQL script file 'init.sql'
        //stat.execute("runscript from 'init.sql'");

        String current = System.getProperty("user.dir");
       // System.out.println("Current working directory in Java : " + current);

        
        stat.execute("DROP TABLE IF EXISTS SERVES; create table Serves As Select * from csvread('"+current+"\\data\\Serves1.0.csv')");
        stat.execute("DROP TABLE IF EXISTS Allergens; create table Allergens As Select * from csvread('"+current+"\\data\\Allergens1.5.csv')");
        
       //stat.execute("create table test(id int primary key, name varchar(255))");
       // stat.execute("insert into test values(1, 'Hello')");
        ResultSet rs, count, test;
        count = stat.executeQuery("Select Distinct(Food), Day From Serves Where Restaurant= '"+ restaurantName +
                "' AND  Serves.Food NOT IN (Select Distinct(\"Food Allergen\") From Allergens Where Customer = '" +personName+ "')");
        
        int size = 0;
        while (count.next()){
        	size++;
        }
         
        test = stat.executeQuery("Select Distinct(Food) From Serves Where Restaurant= '"+ restaurantName +
                "' AND  Serves.Food IN (Select Distinct(\"Food Allergen\") From Allergens Where Customer = '" +personName+ "')");
        
        if (test.first() == false){
        	warning(); 
        }
      
        data= new Object[size][2];
        
    
        //System.out.println("X: " + count.getString("Count(*)"));
        rs = stat.executeQuery("Select Distinct(Food), Day From Serves Where Restaurant= '"+ restaurantName +
        "' AND  Serves.Food NOT IN (Select Distinct(\"Food Allergen\") From Allergens Where Customer = '" +personName+ "')");
        
        int c =0; 
        //System.out.println("X: " + data.length );
        while (rs.next()) {
        	data[c][0] = rs.getString("Food");
        	data[c][1] = rs.getString("Day");
           System.out.println(c);
            c++;
        }
        stat.close();
        conn.close();
		
	}
	
	private void warning(){
		String x = "Warning: The person does not exist. Displaying full menu instead";
		JOptionPane.showMessageDialog(null, x, "Warning", JOptionPane.ERROR_MESSAGE);
	}
}
