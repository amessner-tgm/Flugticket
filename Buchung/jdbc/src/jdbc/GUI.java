package jdbc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class GUI extends JPanel implements ActionListener{
	static JTextField sqlserver=new JTextField();
	static JTextField sqlport=new JTextField();
	static JTextField sqluser=new JTextField();
	static JPasswordField sqlpwd=new JPasswordField();
	static JTextField sqldatabase=new JTextField();
	static JComboBox abflughafenbox = new JComboBox();
	static JComboBox zielflughafenbox = new JComboBox();
	static JComboBox flightsbox=new JComboBox();
	static JLabel flugbescheid = new JLabel("Kein Flug gefunden");
	JFrame f;
	JPanel p;
	JPanel p1;
	JPanel p2;
	JPanel p3;
	
	JFrame f_1;
	JPanel p_1;
	JPanel p1_1;
	static JPanel p2_1;
	JPanel p3_1;
	
	public void guimodell_connect(){
		f=new JFrame();
		f.setTitle("Flugticket");
		f.setBackground(Color.GRAY);
		f.setPreferredSize(new Dimension (400,250));
		//ContentPane
		p =new JPanel();
		p.setLayout(new BorderLayout());
		//UeberschriftPanel
		p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		//MainPanel
		p2 = new JPanel();
		p2.setLayout(new GridBagLayout());
		//ButtonPanel
		p3 = new JPanel();
		p3.setLayout(new BorderLayout());
		
		//�berschrift
		JLabel ueberschrift=new JLabel("Flugticket");
		ueberschrift.setFont(new Font("Flugticket",Font.BOLD,20));
		ueberschrift.setHorizontalAlignment(JLabel.CENTER);
		p1.add(ueberschrift, BorderLayout.CENTER);
		
		//Beschreibung
		JLabel server=new JLabel("Server: ");
		JLabel port=new JLabel("Port: ");
		JLabel user=new JLabel("User: ");
		JLabel pwd=new JLabel("Password: ");
		JLabel database=new JLabel("Database: ");
		JLabel abflughafen=new JLabel("Abflughafen: ");
		JLabel zielflughafen=new JLabel("Zielflughafen: ");
		
		//Textfelder
		sqlserver.setColumns(20);
		sqlport.setColumns(8);
		sqluser.setColumns(20);
		sqlpwd.setColumns(20);
		sqldatabase.setColumns(20);
		
		//Adden
		p.add(p1, BorderLayout.NORTH);
		p.add(p3,BorderLayout.SOUTH);
		p.add(p2,BorderLayout.CENTER);
		//GridBagLayout
		GridBagConstraints g = new GridBagConstraints();
		
		g.gridx=0;
		g.gridy=0;
		g.anchor=GridBagConstraints.LINE_END;
		p2.add(server, g);
		
		g.gridx=1;
		g.gridy=0;
		g.anchor=GridBagConstraints.LINE_START;
		p2.add(sqlserver,g);
		
		g.gridx=0;
		g.gridy=1;
		g.anchor=GridBagConstraints.LINE_END;
		p2.add(port, g);
		
		g.gridx=1;
		g.gridy=1;
		g.anchor=GridBagConstraints.LINE_START;
		p2.add(sqlport,g);
		
		g.gridx=0;
		g.gridy=2;
		g.anchor=GridBagConstraints.LINE_END;
		p2.add(database, g);
		
		g.gridx=1;
		g.gridy=2;
		g.anchor=GridBagConstraints.LINE_START;
		p2.add(sqldatabase, g);
		
		g.gridx=0;
		g.gridy=3;
		g.anchor=GridBagConstraints.LINE_END;
		p2.add(user, g);
		
		g.gridx=1;
		g.gridy=3;
		g.anchor=GridBagConstraints.LINE_START;
		p2.add(sqluser, g);
		
		g.gridx=0;
		g.gridy=4;
		g.anchor=GridBagConstraints.LINE_END;
		p2.add(pwd,g);
		
		g.gridx=1;
		g.gridy=4;
		g.anchor=GridBagConstraints.LINE_START;
		p2.add(sqlpwd, g);
		
		
		p3.setBorder(new EmptyBorder(20,20,20,20));
		Border buttonBorder = new EmptyBorder(5,25,5,25);
		p3.setLayout(new BoxLayout(p3,BoxLayout.X_AXIS));
		p3.add(Box.createHorizontalGlue());
		JButton Connect = new JButton("Connect");
		Connect.setBorder(buttonBorder);
		p3.add(Connect,BorderLayout.CENTER);
		Connect.addActionListener(this);
		
		
		sqlserver.setText("localhost");
		sqlport.setText("3306");
		sqldatabase.setText("flightdata");
		sqluser.setText("root");

		
		f.setContentPane(p);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	public void guimodell_flug(){
		Driver d=new Driver();
		f_1=new JFrame();
		f_1.setTitle("Flugticket");
		f_1.setBackground(Color.GRAY);
		f_1.setPreferredSize(new Dimension (1200,600));
		//ContentPane
		p_1 =new JPanel();
		p_1.setLayout(new BorderLayout());
		//UeberschriftPanel
		p1_1 = new JPanel();
		p1_1.setLayout(new BorderLayout());
		//MainPanel
		p2_1 = new JPanel();
		p2_1.setLayout(new GridBagLayout());
		//ButtonPanel
		p3_1 = new JPanel();
		p3_1.setLayout(new BorderLayout());
		
		//�berschrift
		JLabel ueberschrift=new JLabel("Flugticket");
		ueberschrift.setFont(new Font("Flugticket",Font.BOLD,20));
		ueberschrift.setHorizontalAlignment(JLabel.CENTER);
		p1_1.add(ueberschrift, BorderLayout.CENTER);
		
		d.getFlughafen();
		
		
		
		//Adden
		p_1.add(p1_1, BorderLayout.NORTH);
		p_1.add(p3_1,BorderLayout.SOUTH);
		p_1.add(p2_1,BorderLayout.CENTER);
		
		
		//abflughafenbox.addActionListener(this);
		//zielflughafenbox.addActionListener(this);
		
		//JLABEL
		JLabel abflughafen=new JLabel("Abflughafen: ");
		JLabel zielflughafen=new JLabel("Zielflughafen: ");
		JLabel flights=new JLabel("Fl�ge: ");
		//GridBagLayout
		GridBagConstraints g = new GridBagConstraints();
		
		g.gridx=0;
		g.gridy=0;
		g.anchor=GridBagConstraints.LINE_END;
		p2_1.add(abflughafen, g);
		
		g.gridx=1;
		g.gridy=0;
		g.anchor=GridBagConstraints.LINE_START;
		p2_1.add(abflughafenbox, g);
		
		g.gridx=0;
		g.gridy=1;
		g.anchor=GridBagConstraints.LINE_END;
		p2_1.add(zielflughafen, g);
		
		g.gridx=1;
		g.gridy=1;
		g.anchor=GridBagConstraints.LINE_START;
		p2_1.add(zielflughafenbox,g);
		
		g.gridx=0;
		g.gridy=2;
		g.anchor=GridBagConstraints.LINE_END;
		p2_1.add(flights, g);
		
		g.gridx=1;
		g.gridy=2;
		g.anchor=GridBagConstraints.LINE_END;
		p2_1.add(flightsbox, g);
		
		flugbescheid.setVisible(false);
		g.gridx=2;
		g.gridy=3;
		g.anchor=GridBagConstraints.LINE_START;
		p2_1.add(flugbescheid, g);
		
		JButton Buchen =new JButton("Buchen");
		p3_1.add(Buchen, BorderLayout.CENTER);
		Buchen.addActionListener(this);
		
		f_1.setContentPane(p_1);
		f_1.pack();
		f_1.setLocationRelativeTo(null);
		f_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f_1.setVisible(true);
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				Driver d=new Driver();
				switch (e.getActionCommand()) {
					
				// Je nach Button werden andere Querys eingeleitet
				
				case "Connect":
									guimodell_flug();
									break;
				
				case "Buchen":
									System.out.println(abflughafenbox.getSelectedItem());
									String s=String.valueOf(abflughafenbox.getSelectedItem());
									String[] sarr= s.split(",");
									System.out.println(sarr[1]);
									System.out.println(zielflughafenbox.getSelectedItem());
									String s1=String.valueOf(zielflughafenbox.getSelectedItem());
									String[] s1arr= s1.split(",");
									System.out.println(s1arr[1]);
									d.getFlights();
									break;
				default:			
									System.out.println("Something went wrong!");
									break;
								}				
				
			}catch(NullPointerException e2) {
				e2.printStackTrace();
			}
	}
	public static void main(String[] arqs){
		GUI g = new GUI();
		g.guimodell_connect();
		
	}

}
