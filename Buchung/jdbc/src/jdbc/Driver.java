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
			// Connection wird hergestellt mit den Daten durch die Textfelder in GUI
			String servport;
			servport="jdbc:mysql://"+GUI.sqlserver.getText()+":"+Integer.parseInt(GUI.sqlport.getText())+"/"+GUI.sqldatabase.getText();
			con= DriverManager.getConnection(servport,GUI.sqluser.getText(),GUI.sqlpwd.getText());			
		}catch(Exception exc){
			exc.printStackTrace();
		}

	}
	
	public void getFlughafen(){
		try{
			//alle Laender,Flughaefen werden passend geholt damit land und flughafen �bereinstimmen ausserdem wird zuerst nach Land dann nach Flughafen geordnet f�r
			//bessere �bersicht
			ps2=con.prepareStatement("Select countries.name,airports.name from airports,countries WHERE airports.country = countries.code ORDER BY 1,2;");
			rs2=ps2.executeQuery();
			while(rs2.next()){
				String land=rs2.getString("countries.name");
				String airport=rs2.getString("airports.name");
				//wird so in die Arraylist gespeichert zb Algerien,International Airport
				flughafen.add(land+","+airport);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//Arraylist wird in Array umgewandelt da man so den inhalt in die Dropdownliste bekommt
		String flughafenArr[] = flughafen.toArray(new String[flughafen.size()]);
		
		for(String s: flughafenArr) {
			//Fluhaefen werden in die Dropdownliste hinzugefuegt
			GUI.abflughafenbox.addItem(s);
			GUI.zielflughafenbox.addItem(s);
		}
		
	}
	public void getFlights(){
		//alle inhalte der dropdownliste flightsbox werden gel�scht
		GUI.flightsbox.removeAllItems();
		//selected inhalte von den 2 dropdownlisten werden geholt
		String abflugh =  (String) GUI.abflughafenbox.getSelectedItem();
		String zielflugh = (String) GUI.zielflughafenbox.getSelectedItem();
		//inhalt wird gesplittet, unser inhalt war so Land,Flughafen mit abflughsplit[0] bekommt man das land mit [1] den airport
		String[] abflughsplit = abflugh.split(",");
		String[] zielflughsplit = zielflugh.split(",");
		try{
			//hier bekommen wir die ganzen daten zu einem flug muessen aber noch den richtigen flug bekommen dazu braucht man den airportcode
			ps3=con.prepareStatement("select * from flights,(select airportcode as 'abflugcode'  from airports WHERE name='"+abflughsplit[1]+"')abflugh,(select airportcode as 'zielflugcode' from airports WHERE name='"+zielflughsplit[1]+"')zielflugh WHERE abflugcode = departure_airport AND zielflugcode = destination_airport;");
			rs3=ps3.executeQuery();
			
			while(rs3.next()){
				//hier wird alles in die arraylist hinzugef�gt und so wird es dann in der Dropdownliste stehen
				flights.add("Abflug: " +rs3.getDate("departure_time")+", "+rs3.getTime("departure_time")+", " +rs3.getString("departure_airport")
				+ ", Ankunft: "+rs3.getDate("destination_time")+", "+rs3.getTime("destination_time")+", "+rs3.getString("destination_airport")+", Flugnummer: "+rs3.getInt("flightnr")+", Airline: "+rs3.getString("airline"));
			}
			
			String[] flightsArr= flights.toArray(new String[flights.size()]);
			for(String s: flightsArr){
				GUI.flightsbox.addItem(s);
			}
			
			if(flightsArr.length==0){
				//falls kein flug vorhanden roter background und schrift kommt
				GUI.p2_1.setBackground(Color.RED);
				GUI.flugbescheid.setVisible(true);
			}else{
				//falls flug vorhanden wird background gruen und schrift taucht nicht auf
				GUI.p2_1.setBackground(Color.GREEN);
				GUI.flugbescheid.setVisible(false);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void buchen(){
		
	}
}
