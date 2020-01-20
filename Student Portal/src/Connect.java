import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Connect {

	public static Connection dbConnector() {
	try{
		
	
		Class.forName("org.sqlite.JDBC");
		Connection con = DriverManager.getConnection("jdbc:sqlite:dbs//octa.sqlite");
		JOptionPane.showMessageDialog(null, "Connected");
		
     	return con;
	}
	catch(Exception e)
	{
		JOptionPane.showMessageDialog(null, e);
		return null;
	}
	
	}
	
}
