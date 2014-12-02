package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.h2.tools.DeleteDbFiles;



public class FirstPanel extends JFrame{

	
	
	private JButton next;
	
	private JButton help;
	
	public static DefaultListModel <String>listModel = new DefaultListModel<String>();
	public static  JList list = new JList<String>(listModel);
	
	private JButton admin; 
	
	public static JTextField restaurant;
	
	public FirstPanel(String title){
		super (title);
		
		builder();
		

		list.addListSelectionListener( new ListSelectionListener (){
			public void valueChanged(ListSelectionEvent e){
				restaurant.setText( listModel.get(list.getSelectedIndex()));
			}			
		});
		
		
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e1){
				
				if ( restaurant.getText().equals("")){
					error();
					restaurant.setText("");
				}
				else{
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
		String x = "Error: Please enter the restaurant name in proper capitalization ";
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
		
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.setVisibleRowCount(5);
		JScrollPane listscroller = new JScrollPane(list);
		
		try {
			DBQuery();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		
		
		 next = new JButton("Load Restaurant");
		 restaurant = new JTextField();
		 restaurant.setEditable(false);
		 admin = new JButton ("Admin");
		 help = new JButton ("Help");
		
		JLabel info2 = new JLabel("Restaurant Name:");
		JLabel info3 = new JLabel ("Select the restaurant from the list");
		
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
		
		finalpanel.add(Box.createRigidArea(new Dimension(0, 10)));
		finalpanel.add(info3);
		info3.setAlignmentX(CENTER_ALIGNMENT);
		finalpanel.add(Box.createRigidArea(new Dimension(0, 10)));
		finalpanel.add(listscroller);
		finalpanel.add(Box.createRigidArea(new Dimension(0, 20)));
		finalpanel.add(input);
		input.setAlignmentX(CENTER_ALIGNMENT);
		finalpanel.add(Box.createRigidArea(new Dimension(0, 20)));
		finalpanel.add(input2);
		input2.setAlignmentX(CENTER_ALIGNMENT);
		finalpanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		
		this.add(finalpanel);
		
	}
	
	
	
public void DBQuery() throws SQLException, ClassNotFoundException{
		

        DeleteDbFiles.execute("~", "test", true);

        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test");
        Statement stat = conn.createStatement();

        String current = System.getProperty("user.dir");

        
       stat.execute("DROP TABLE IF EXISTS SERVES; create table Serves As Select * from csvread('"+current+"\\data\\Serves1.0.csv')");
        
        
       //stat.execute("create table test(id int primary key, name varchar(255))");
       // stat.execute("insert into test values(1, 'Hello')");
        ResultSet rs;
    
        //System.out.println("X: " + count.getString("Count(*)"));
        rs = stat.executeQuery("Select Distinct(Restaurant) From Serves");
        while (rs.next()) {
        	listModel.addElement(rs.getString("Restaurant"));
        }
        stat.close();
        conn.close();
		
	}


	public static void main(String[] args) {
		JFrame firstframe = new FirstPanel("Allergy's R Us - Restaurant Page");
		firstframe.pack();
		firstframe.setVisible(true);
		firstframe.setLocationRelativeTo(null);
		firstframe.setMinimumSize(new Dimension(400, 300));
		firstframe.setResizable(true);
		firstframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
	}

}
