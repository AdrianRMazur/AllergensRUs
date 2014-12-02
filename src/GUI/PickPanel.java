package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.h2.tools.DeleteDbFiles;

public class PickPanel extends JFrame{

	private DefaultListModel <String>listModel = new DefaultListModel<String>();
	private JList list = new JList<String>(listModel);
	
	public PickPanel(String title) {
		super(title);
		
		final DefaultListModel <String>listModel = new DefaultListModel<String>();
		final JList list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.setVisibleRowCount(5);
		//list.setSize(10, 5);
		JScrollPane listscroller = new JScrollPane(list);

		JPanel finalpanel = new JPanel ();
		finalpanel.setLayout(new BoxLayout(finalpanel, BoxLayout.Y_AXIS));
		finalpanel.add(listscroller);
		
		this.add(finalpanel);
		
		list.addListSelectionListener( new ListSelectionListener (){
			public void valueChanged(ListSelectionEvent e){
				FirstPanel.restaurant.setText( listModel.get(list.getSelectedIndex()));
				dispose();
			}			
		});
	}
	
	
	
	public void DBQuery() throws SQLException, ClassNotFoundException{
		

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
    
        //System.out.println("X: " + count.getString("Count(*)"));
        rs = stat.executeQuery("Select Restaurant From Serves");
        int c =0; 
        //System.out.println("X: " + data.length );
        while (rs.next()) {
        	
        	listModel.addElement(rs.getString("Restaurant"));
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
		
	}

}
