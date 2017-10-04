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
	ArrayList<String> abflughafen = new ArrayList<String>();
	ArrayList<String> zielflughafen = new ArrayList<String>();
	public Driver(){
		try {
			// Connection
			String servport;
			servport="jdbc:mysql://"+GUI.sqlserver.getText()+":"+Integer.parseInt(GUI.sqlport.getText())+"/"+GUI.sqldatabase.getText();
			con= DriverManager.getConnection(servport,GUI.sqluser.getText(),GUI.sqlpwd.getText());			
			
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
	public void getAbFlughafen(){
		try{
			ps2=con.prepareStatement("Select countries.code,countries.name,airports.name from airports,countries WHERE airports.country=countries.code");
			rs2=ps2.executeQuery();
			while(rs2.next()){
				abflughafen.add(rs2.getString("countries.name")+", "+rs2.getString("airports.name"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		String abflughafenArr[] = abflughafen.toArray(new String[abflughafen.size()]);
		
		for(String s: abflughafenArr) {
			GUI.abflughafenbox.addItem(s);
		}
		
	}
	public void getZielFlughafen(){
		try{
			ps3=con.prepareStatement("Select countries.code,countries.name,airports.name from airports,countries WHERE airports.country=countries.code");
			rs3=ps3.executeQuery();
			while(rs3.next()){
				zielflughafen.add(rs3.getString("countries.name")+", "+rs3.getString("airports.name"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		String zielflughafenArr[] = zielflughafen.toArray(new String[zielflughafen.size()]);
		
		for(String s: zielflughafenArr) {
			GUI.zielflughafenbox.addItem(s);
		}
		
	}
	public void getFlights(){
		try{
			ps4=con.prepareStatement("Select flights.airline,flights.flightnr,flights.planetype from flights");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] arqs){
		Driver d= new Driver();
		d.getAbFlughafen();
	}
}
