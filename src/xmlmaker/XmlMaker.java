package xmlmaker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.DeleteDbFiles;
import org.h2.tools.DeleteDbFiles;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
 

public class XmlMaker {

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException, ParserConfigurationException, TransformerException {

		DeleteDbFiles.execute("~", "test", true);

		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test");
		Statement stat = conn.createStatement();

		String current = System.getProperty("user.dir");

		stat.execute("DROP TABLE IF EXISTS Customers; create table Customers As Select * from csvread('"
				+ current + "\\data\\Customers1.0.csv')");

		ResultSet rs = null, count;

		rs = stat.executeQuery("Select * From Customers");

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
	
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("Customers");
		doc.appendChild(rootElement);
		
		
		while (rs.next()) {
			 

			Element staff = doc.createElement("CustomerID");
			rootElement.appendChild(staff);

			Attr attr = doc.createAttribute("id");
			attr.setValue(rs.getString("id"));
			staff.setAttributeNode(attr);
 

			Element name = doc.createElement("Name");
			name.appendChild(doc.createTextNode(rs.getString("Name")));
			staff.appendChild(name);
 
			
			Element city = doc.createElement("City");
			city.appendChild(doc.createTextNode(rs.getString("City")));
			staff.appendChild(city);
 
	
			Element State = doc.createElement("State");
			State.appendChild(doc.createTextNode(rs.getString("State")));
			staff.appendChild(State);
 
		
			Element nationality = doc.createElement("Nationality");
			nationality.appendChild(doc.createTextNode(rs.getString("Nationality")));
			staff.appendChild(nationality);
		}	  
			// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("Customers.xml"));
		 
				// Output to console for testing
				// StreamResult result = new StreamResult(System.out);
		 
				transformer.transform(source, result);
		 
				System.out.println("File saved!");
			  
		

	}

}
