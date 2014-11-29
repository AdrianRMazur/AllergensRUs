package GUI;

import java.awt.Dimension;
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
	
	public RestMenuPanel(String title) {
		
		super(title);
		
		builder();
		
		
	}
	
	private void builder(){
		
		try {
			DBQuery();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
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
	
	public String DBQuery() throws SQLException, ClassNotFoundException{
		
        DeleteDbFiles.execute("~", "test", true);

        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test");
        Statement stat = conn.createStatement();

        // this line would initialize the database
        // from the SQL script file 'init.sql'
        // stat.execute("runscript from 'init.sql'");

       // stat.execute("create table test(id int primary key, name varchar(255))");
        //stat.execute("insert into test values(1, 'Hello')");
        ResultSet rs;
        rs = stat.executeQuery("select * from Customers");
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }
        stat.close();
        conn.close();
		
		
		return null;
		
	}
	
	
	

}
