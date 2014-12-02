package GUI;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PickPanel extends JFrame{

	public PickPanel(String title) {
		super(title);
		
		final DefaultListModel <String>listModel = new DefaultListModel<String>();
		final JList list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.setVisibleRowCount(5);
		list.setSize(10, 5);
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

}
