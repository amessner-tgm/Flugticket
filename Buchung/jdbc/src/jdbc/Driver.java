package jdbc;
import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;
/*
 * git@github.com:amessner-tgm/Flugticket.git
 */

import javax.swing.JOptionPane;
public class Driver{
	Connection con;
	PreparedStatement ps;
	PreparedStatement ps2;
	PreparedStatement ps3;
	PreparedStatement ps4;
	PreparedStatement ps5;
	PreparedStatement ps6;
	PreparedStatement ps7;
	PreparedStatement ps8;
	ResultSet rs;
	ResultSet rs2;
	ResultSet rs3;
	ResultSet rs4;
	ResultSet rs5;
	ResultSet rs6;
	ResultSet rs7;
	ResultSet rs8;
	ArrayList<String> flughafen = new ArrayList<String>();
	ArrayList<String> flights = new ArrayList<String>();
	ArrayList<String> rowslist=new ArrayList<String>();
	
	String maxseats;
	String seatsperrow;
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
	public String getFlightNr(){
		//Selected Flug von Dropdownliste holen
		String flightdata=(String) GUI.flightsbox.getSelectedItem();
		//diesen splitten auf vor Flugnummer und nach Flugnummer
		String[] flightdatasplit=flightdata.split("Flugnummer: ");
		//zweiter split n�tig da airline auch noch nach flugnummer kommt
		String[] flightnrsplit=flightdatasplit[1].split(", Airline: ");
		String flugnr = flightnrsplit[0];
		return flugnr;
	}
	//Airline bekommen
	public String getAirline(){
		//Airline vom ausgewaehlten Flug holen
		String flightdata=(String) GUI.flightsbox.getSelectedItem();
		String[] flightdatasplit=flightdata.split("Airline: ");
		String airline=flightdatasplit[1];
		return airline;
	}
	
	public int getRows() {
		int rows = 0;
		try {
			//maximalen sitze und sitze pro reihe holen von einem flieger
			ps5 = con.prepareStatement("select planes.maxseats,planes.seatsperrow,planes.id from planes INNER JOIN flights ON planes.id=flights.planetype WHERE flights.flightnr='"+getFlightNr()+"' AND flights.airline='"+getAirline()+"';");
			rs5 = ps5.executeQuery();
			
			while (rs5.next()) {
				maxseats=rs5.getString("planes.maxseats");
				seatsperrow=rs5.getString("planes.seatsperrow");
				//sitze im flugzeug / sitze pro reihe um die reihen zu bekommen
				rows = rs5.getInt("planes.maxseats")/rs5.getInt("planes.seatsperrow");;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	public void adding(){
		try{
		//Sitzreihen
		String[] seatsArr ={"A","B","C","D","E","F","G","H","I","J","K","L","M","N"};
		//Sitze werden in die Dropdownlist geaddet man sollte es aber mit seatsperrow machen damit es stimmt hat aber nicht funktoniert
		for(int i=0;i<seatsArr.length;i++){
			GUI.seatbox.addItem(seatsArr[i]);
		}
		//Sitzreihen werden in die arraylist geaddet also 1,2,3,4,5 usw. bis die maximal reihen anzahl in flugzeug erreich wurde die in getRows() errechnet wurde
		for(int i=1;i<=getRows();i++){
			rowslist.add(String.valueOf(i));
		}
		//arraylist in array
		String[] rowsArr=rowslist.toArray(new String[rowslist.size()]);
		//einzelnen reihen also 1,2,3,4,usw. werden in die Dropdownliste geaddet
		for(String s:rowsArr){
			GUI.rowbox.addItem(s);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void buchen(){
		//daten bekommen, notwendig um Passenger hinzuzuf�gen
		String vname=GUI.vnamefield.getText();
		String nname=GUI.nnamefield.getText();
		String flightnr=getFlightNr();
		String airline=getAirline();
		String reihe=(String) GUI.rowbox.getSelectedItem();
		String seat=(String) GUI.seatbox.getSelectedItem();
		//falls der Vorname oder Nachname leer/einen ; beinhaltet muss man ihn neu eintragen
		if(GUI.vnamefield.getText().isEmpty() || GUI.vnamefield.getText().contains(";")|| GUI.vnamefield.getText().isEmpty() || GUI.vnamefield.getText().contains(";")){
			GUI.vnamefield.setText("Vorname �berpr�fen, keine Sonderzeichen!");
			GUI.nnamefield.setText("Nachname �berpr�fen, keine Sonderzeichen!");
		}else{
			try {
				//wenn Namen korrekt sind dann kann der Passargier hinzugef�gt werden
				ps4 = con.prepareStatement("INSERT INTO passengers VALUES(NULL,'"+vname+"','"+nname+"','"+airline+"','"+flightnr+"','"+Integer.parseInt(reihe)+"','"+seat+"');");
				ps4.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Fehlgeschlagen");
			}
			//durch gr�nen screen und meldung sieht man das der Passagier hinzugef�gt wurde
			GUI.p_2.setBackground(Color.GREEN);
			JOptionPane.showMessageDialog(GUI.f_2, "Erfolgreich gebucht");
			//zur Sicherheit nochmal in die Console ausgeben
			System.out.println("Passagier: "+vname+" "+nname+", Airline: "+airline+", Flugnummer: "+flightnr+" in Reihe: "+Integer.parseInt(reihe)+" auf Sitz: "+seat+" wurde erstellt!");
			try {
				//Frames werden geschlossen
				System.exit(0);
				System.out.println("Gebucht");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
