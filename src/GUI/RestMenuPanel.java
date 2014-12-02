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


// regular menu



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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		back = new JButton ("Back");
		
		String[] columnNames = {"Dish", "Day"};
		

		 
		 final JTable table = new JTable(data, columnNames);
		 table.setPreferredScrollableViewportSize(new Dimension(300, 70));
	     table.setFillsViewportHeight(true);
	     
	     
	     JScrollPane scrollPane = new JScrollPane(table);
		
	     JPanel finalpanel = new JPanel();
	     finalpanel.setLayout(new BoxLayout(finalpanel, BoxLayout.Y_AXIS));
	     finalpanel.add(scrollPane);
	     finalpanel.add(back); 
	     back.setAlignmentX(CENTER_ALIGNMENT);

	     this.add(finalpanel);
	     
		
	}
	
	
	
	public String DBQuery() throws SQLException, ClassNotFoundException{
		
        DeleteDbFiles.execute("~", "test", true);

        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test");
        Statement stat = conn.createStatement();

        // this line would initialize the database
        // from the SQL script file 'init.sql'
        //stat.execute("runscript from 'init.sql'");

       //stat.execute("create table test(id int primary key, name varchar(255))");
       // stat.execute("insert into test values(1, 'Hello')");
        ResultSet rs;
        ResultSet count;
        count = stat.executeQuery("Select Count(*) From Serves Where Restaurant = '"+restaurantName+"'");
        count.next();
        int size= Integer.parseInt(count.getString("Count(*)"));
        data= new Object[size][2];
        //System.out.println("X: " + count.getString("Count(*)"));
        rs = stat.executeQuery("Select * From Serves Where Restaurant = '"+restaurantName+"'");
        int c =0; 
        //System.out.println("X: " + data.length );
        while (rs.next()) {
        	data[c][0] = rs.getString("Food");
        	data[c][1] = rs.getString("Day");
            /*System.out.print(rs.getString("Restaurant"));
            System.out.print(" ");
            System.out.print(rs.getString("Food"));
            System.out.print(" ");
            System.out.print(rs.getString("Day"));
            System.out.println("");
            */
            c++;
        }
        stat.close();
        conn.close();
		
		
		return null;
		
	}
	
	
	

}
