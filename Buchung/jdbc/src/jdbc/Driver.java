package jdbc;
import java.sql.*;
import java.util.ArrayList;
public class Driver{
	Connection con;
	PreparedStatement ps;
	PreparedStatement ps2;
	PreparedStatement ps3;
	PreparedStatement ps4;
	ResultSet rs;
	ResultSet rs2;
	ResultSet rs3;
	ResultSet rs4;
	ArrayList<String> flughafen = new ArrayList<String>();
	public Driver(){
		try {
			// Connection
			//con= DriverManager.getConnection("jdbc:mysql://"+GUI.sqlserver.getText()+":"+GUI.sqlport.getText()+"/"+GUI.sqldatabase.getText()+","+GUI.sqluser.getText()+","+GUI.sqlpwd.getText());
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flightdata","root","Toni0501");
			
			/* //Statement
			ps = con.prepareStatement("Select * from airlines;");
			//Resultset
			rs=ps.executeQuery();
			//Ausgeben
			while(rs.next()){
				System.out.println(rs.getString("id")+ " , "+rs.getString("name")+ " , "+ rs.getString("country"));*/
			
		}catch(Exception exc){
			exc.printStackTrace();
		}

	}
	
	public void getFlughafen(){
		try{
			ps=con.prepareStatement("Select name from airports;");
			rs=ps.executeQuery();
			while(rs.next()){
				flughafen.add(rs.getString("name"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		String flughafenArr[] = flughafen.toArray(new String[flughafen.size()]);
		
		for(String s: flughafenArr) {
			GUI.abflughafenbox.addItem(s);
			GUI.zielflughafenbox.addItem(s);
		}
		
	}
	
	public static void main(String[] arqs){
		Driver d = new Driver();
		d.getFlughafen();
	}
}
