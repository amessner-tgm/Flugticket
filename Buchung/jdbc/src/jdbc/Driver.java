package jdbc;
import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;
public class Driver{
	Connection con;
	PreparedStatement ps;
	PreparedStatement ps2;
	PreparedStatement ps3;
	PreparedStatement ps4;
	PreparedStatement ps5;
	ResultSet rs;
	ResultSet rs2;
	ResultSet rs3;
	ResultSet rs4;
	ResultSet rs5;
	ArrayList<String> flughafen = new ArrayList<String>();
	ArrayList<String> flights = new ArrayList<String>();
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
	
	public void getFlughafen(){
		try{
			ps2=con.prepareStatement("Select countries.name,airports.name from airports,countries WHERE airports.country = countries.code ORDER BY 1,2;");
			rs2=ps2.executeQuery();
			while(rs2.next()){
				String land=rs2.getString("countries.name");
				String airport=rs2.getString("airports.name");
				flughafen.add(land+","+airport);
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
	public void getFlights(){
		GUI.flightsbox.removeAllItems();
		String abflugh =  (String) GUI.abflughafenbox.getSelectedItem();
		String zielflugh = (String) GUI.zielflughafenbox.getSelectedItem();
		String[] abflughsplit = abflugh.split(",");
		String[] zielflughsplit = zielflugh.split(",");
		try{
			ps3=con.prepareStatement("select * from flights,(select airportcode as 'abflugcode'  from airports WHERE name='"+abflughsplit[1]+"')abflugh,(select airportcode as 'zielflugcode' from airports WHERE name='"+zielflughsplit[1]+"')zielflugh WHERE abflugcode = departure_airport AND zielflugcode = destination_airport;");
			rs3=ps3.executeQuery();
			
			while(rs3.next()){
				flights.add("Abflug: " +rs3.getDate("departure_time")+", "+rs3.getTime("departure_time")+", " +rs3.getString("departure_airport")
				+ ", Ankunft: "+rs3.getDate("destination_time")+", "+rs3.getTime("destination_time")+", "+rs3.getString("destination_airport")+", Flugnummer: "+rs3.getInt("flightnr")+", Airline: "+rs3.getString("airline"));
			}
			
			String[] flightsArr= flights.toArray(new String[flights.size()]);
			for(String s: flightsArr){
				GUI.flightsbox.addItem(s);
				System.out.println(s);
			}
			if(flightsArr.length==0){
				GUI.p2_1.setBackground(Color.RED);
				GUI.flugbescheid.setVisible(true);
			}else{
				GUI.p2_1.setBackground(Color.GREEN);
				GUI.flugbescheid.setVisible(false);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
