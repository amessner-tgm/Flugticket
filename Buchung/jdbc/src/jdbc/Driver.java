package jdbc;
import java.sql.*;
public class Driver {

	public Driver(){
		try {
			// Connection
			
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/flightdata","root","");
			
		}catch(Exception exc){
			exc.printStackTrace();
		}

	}

}
