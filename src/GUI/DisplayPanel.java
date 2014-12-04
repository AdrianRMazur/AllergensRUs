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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.h2.tools.DeleteDbFiles;


// panel to display trends
public class DisplayPanel extends JFrame{

	private JButton close; 
	
	private Object data[][];
	
	private String restaurantname; 
	
	public DisplayPanel(String title, int x) {
		super(title);
		
		restaurantname = FirstPanel.restaurant.getText();
		
		if (x==1){
			builder1();
		}
		else if (x==2){
			builder2();
		}
		else {
			builder3();
		}
		
		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
	}

	private void builder3(){
		close = new JButton ("Close");
		String[] columnNames = {"Nationality",
                "Allergens Per Restaurant Nationality"};
		
		try {
			DBQuery3();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
	
		 
		JLabel info = new JLabel ("Number of allergies vs restaurant nationality");
		JLabel info2 = new JLabel ("English have the least allergies");
		JLabel info3 = new JLabel ("American have the most allergies");
		
		 final JTable table = new JTable(data, columnNames);
		 table.setPreferredScrollableViewportSize(new Dimension(100, 100));
	     table.setFillsViewportHeight(true);
	     table.setEnabled(false);
	     
	     JScrollPane scrollPane = new JScrollPane(table);
		
	     JPanel finalpanel = new JPanel();
	     finalpanel.setLayout(new BoxLayout(finalpanel, BoxLayout.Y_AXIS));
	     finalpanel.add(scrollPane);
	     finalpanel.add(Box.createRigidArea(new Dimension (0, 10)));
	     finalpanel.add(info);
	     info.setAlignmentX(CENTER_ALIGNMENT);
	     finalpanel.add(info2);
	     info2.setAlignmentX(CENTER_ALIGNMENT);
	     finalpanel.add(info3);
	     info3.setAlignmentX(CENTER_ALIGNMENT);
	     finalpanel.add(Box.createRigidArea(new Dimension (0, 10)));
	     finalpanel.add(close); 
	     close.setAlignmentX(CENTER_ALIGNMENT);
	     this.add(finalpanel);

	}
	
	private void builder2(){
		close = new JButton ("Close");
		String[] columnNames = {"Day",
                "Number of Allergens"};
		
		JLabel info = new JLabel ("Number of allergies vs day");
		JLabel info2 = new JLabel ("Weekends have low number of allergies");
		
		try {
			DBQuery2();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
	
		 
		 final JTable table = new JTable(data, columnNames);
		 table.setPreferredScrollableViewportSize(new Dimension(100, 100));
	     table.setFillsViewportHeight(true);
	     table.setEnabled(false);
	     
	     JScrollPane scrollPane = new JScrollPane(table);
		
	     JPanel finalpanel = new JPanel();
	     finalpanel.setLayout(new BoxLayout(finalpanel, BoxLayout.Y_AXIS));
	     finalpanel.add(scrollPane);
	     finalpanel.add(Box.createRigidArea(new Dimension (0, 10)));
	     finalpanel.add(info);
	     info.setAlignmentX(CENTER_ALIGNMENT);
	     finalpanel.add(info2);
	     info2.setAlignmentX(CENTER_ALIGNMENT);
	     finalpanel.add(Box.createRigidArea(new Dimension (0, 10)));
	     finalpanel.add(close); 
	     close.setAlignmentX(CENTER_ALIGNMENT);
	     this.add(finalpanel);

	}
	
	private void builder1(){
		close = new JButton ("Close");
		JLabel info2 = new JLabel ("Relation between Price - Allergy");
		JLabel info3 = new JLabel ("Cheaper foods have more allergies");
		JLabel info = new JLabel ("No foods over $25 were seen in the results");
		String[] columnNames = {"Food Allergen",
                "Avg Price",
                "Number of times"};
		
		try {
			DBQuery1();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
	
		 
		 final JTable table = new JTable(data, columnNames);
		 table.setPreferredScrollableViewportSize(new Dimension(100, 100));
	     table.setFillsViewportHeight(true);
	     table.setEnabled(false);
	     
	     JScrollPane scrollPane = new JScrollPane(table);
		
	     JPanel finalpanel = new JPanel();
	     finalpanel.setLayout(new BoxLayout(finalpanel, BoxLayout.Y_AXIS));
	     finalpanel.add(scrollPane);
	     finalpanel.add(Box.createRigidArea(new Dimension (0, 10)));
	     finalpanel.add(info2);
	     info2.setAlignmentX(CENTER_ALIGNMENT);
	     finalpanel.add(info3);
	     info3.setAlignmentX(CENTER_ALIGNMENT);
	     finalpanel.add(info);
	     info.setAlignmentX(CENTER_ALIGNMENT);
	     finalpanel.add(Box.createRigidArea(new Dimension (0, 10)));
	     finalpanel.add(close); 
	     close.setAlignmentX(CENTER_ALIGNMENT);
	     this.add(finalpanel);
	     
	}
	
	
private void DBQuery1() throws SQLException, ClassNotFoundException{
		
        DeleteDbFiles.execute("~", "test", true);

        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test");
        Statement stat = conn.createStatement();
        
        String current = System.getProperty("user.dir");

        stat.execute("DROP TABLE IF EXISTS Orders; create table Orders As Select * from csvread('"+current+"\\data\\Orders1.5.csv')");
        stat.execute("DROP TABLE IF EXISTS Allergens; create table Allergens As Select * from csvread('"+current+"\\data\\Allergens1.5.csv')");
        
        ResultSet rs, count;
        
        count = stat.executeQuery("Select \"Food Allergen\", AVG(CAST(PRICE AS DOUBLE)) as \"Average Price of Food\", " +
        "Count(*) as \"Number of Allergies\" From Allergens LEFT JOIN (Select Food, AVG(Cast(PRICE as Float)) as Price From " +
        "Orders Group By Food) on Allergens.\"Food Allergen\" = Food Group By \"Food Allergen\"");
        
        
        int size = 0;
        while (count.next()){
        	size++;
        }
         
       
            
        data= new Object[size][3];
        
    
        rs = stat.executeQuery("Select \"Food Allergen\", AVG(CAST(PRICE AS DOUBLE)) as \"Average Price of Food\", " +
                "Count(*) as \"Number of Allergies\" From Allergens LEFT JOIN (Select Food, AVG(Cast(PRICE as Float)) as Price From " +
                "Orders Group By Food) on Allergens.\"Food Allergen\" = Food Group By \"Food Allergen\" Order By \"Number of Allergies\" DESC");
        
        int c =0; 
        while (rs.next()) {
        	data[c][0] = rs.getString("Food Allergen");
        	data[c][1] = rs.getString("Average Price of Food");
        	data[c][2] = rs.getString("Number of Allergies");
            c++;
        }
        stat.close();
        conn.close();
		
	}


private void DBQuery2() throws SQLException, ClassNotFoundException{
	
    DeleteDbFiles.execute("~", "test", true);

    Class.forName("org.h2.Driver");
    Connection conn = DriverManager.getConnection("jdbc:h2:~/test");
    Statement stat = conn.createStatement();
    
    String current = System.getProperty("user.dir");

    stat.execute("DROP TABLE IF EXISTS Serves; create table Serves As Select * from csvread('"+current+"\\data\\Serves1.0.csv')");
    stat.execute("DROP TABLE IF EXISTS Allergens; create table Allergens As Select * from csvread('"+current+"\\data\\Allergens1.5.csv')");
    
    ResultSet rs, count;
    
    count = stat.executeQuery("Select Day, Count(*) as \"Number of Allergens\" From Serves  Where Food " +
    "IN (Select \"Food Allergen\" From Allergens)  Group by Day");
    
    
    int size = 0;
    while (count.next()){
    	size++;
    }
     
   
        
    data= new Object[size][2];
    

    rs = stat.executeQuery("Select Day, Count(*) as \"Number of Allergens\" From Serves  Where Food " +
    	    "IN (Select \"Food Allergen\" From Allergens)  Group by Day");
    
    int c =0; 
    while (rs.next()) {
    	data[c][0] = rs.getString("Day");
    	data[c][1] = rs.getString("Number of Allergens");
    	
        c++;
    }
    stat.close();
    conn.close();
	
}

	
private void DBQuery3() throws SQLException, ClassNotFoundException{
	
    DeleteDbFiles.execute("~", "test", true);

    Class.forName("org.h2.Driver");
    Connection conn = DriverManager.getConnection("jdbc:h2:~/test");
    Statement stat = conn.createStatement();
    
    String current = System.getProperty("user.dir");

    stat.execute("DROP TABLE IF EXISTS Serves; create table Serves As Select * from csvread('"+current+"\\data\\Serves1.0.csv')");
    stat.execute("DROP TABLE IF EXISTS Allergens; create table Allergens As Select * from csvread('"+current+"\\data\\Allergens1.5.csv')");
    stat.execute("DROP TABLE IF EXISTS Restaurants; create table Restaurants As Select * from csvread('"+current+"\\data\\Restaurants1.0.csv')");
    
    ResultSet rs, count;
    
    count = stat.executeQuery("Select Nationality, Count(*) as \"Allergens Per Restaurant Nationality\" " + 
    		"From Allergens Inner Join (Select RESTAURANTS.RESTAURANT , Nationality, Food From RESTAURANTS Inner "+
    		"Join SERVES on RESTAURANTS.RESTAURANT = SERVES .RESTAURANT ) on Allergens.\"Food Allergen\" = Food Group By Nationality");
    
    
    int size = 0;
    while (count.next()){
    	size++;
    }
     
   
        
    data= new Object[size][2];
    

    rs= stat.executeQuery("Select Nationality, Count(*) as \"Allergens Per Restaurant Nationality\" " + 
    		"From Allergens Inner Join (Select RESTAURANTS.RESTAURANT , Nationality, Food From RESTAURANTS Inner "+
    		"Join SERVES on RESTAURANTS.RESTAURANT = SERVES .RESTAURANT ) on Allergens.\"Food Allergen\" = Food Group By Nationality");
    
    int c =0; 
    while (rs.next()) {
    	data[c][0] = rs.getString("Nationality");
    	data[c][1] = rs.getString("Allergens Per Restaurant Nationality");
    	
        c++;
    }
    stat.close();
    conn.close();
	
}


}
