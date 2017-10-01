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
	static JTextField sqlpwd=new JTextField();
	static JTextField sqldatabase=new JTextField();
	ActionListener a;
	static JComboBox abflughafenbox = new JComboBox();
	static JComboBox zielflughafenbox = new JComboBox();
	
	public void guimodell(){
		JFrame f=new JFrame();
		f.setTitle("Flugticket");
		f.setBackground(Color.GRAY);
		f.setPreferredSize(new Dimension (1200,600));
		JPanel p =new JPanel();
		p.setLayout(new BorderLayout());
		JPanel p1 = new JPanel();
		p1.setLayout(new GridBagLayout());
		//Überschrift
		JLabel ueberschrift=new JLabel("Flugticket");
		ueberschrift.setFont(new Font("Flugticket",Font.BOLD,20));
		ueberschrift.setHorizontalAlignment(JLabel.CENTER);
		//Beschreibung
		JLabel server=new JLabel("Server: ");
		JLabel port=new JLabel("Port: ");
		JLabel user=new JLabel("User: ");
		JLabel pwd=new JLabel("Password: ");
		JLabel database=new JLabel("Database: ");
		//Textfelder
		sqlserver.setColumns(20);
		sqlport.setColumns(8);
		sqluser.setColumns(20);
		sqlpwd.setColumns(20);
		sqldatabase.setColumns(20);
		//Adden
		p.add(ueberschrift,BorderLayout.NORTH);
		p.add(p1, BorderLayout.CENTER);
		//GridBagLayout
		GridBagConstraints g=new GridBagConstraints();
		g.gridx=0;
		g.gridy=0;
		g.anchor=GridBagConstraints.LINE_END;
		p1.add(server,g);
		
		g.gridx++;
		g.gridy=0;
		g.anchor=GridBagConstraints.LINE_START;
		p1.add(sqlserver, g);
		
		g.gridx++;
		g.gridy=0;
		g.anchor=GridBagConstraints.LINE_END;
		p1.add(database, g);
		
		g.gridx++;
		g.gridy=0;
		g.anchor=GridBagConstraints.LINE_START;
		p1.add(sqldatabase, g);
		
		g.gridx++;
		g.gridy=0;
		g.anchor=GridBagConstraints.LINE_END;
		p1.add(port, g);
		
		g.gridx++;
		g.gridy=0;
		g.anchor=GridBagConstraints.LINE_START;
		p1.add(sqlport, g);
		
		g.gridx=0;
		g.gridy++;
		g.anchor=GridBagConstraints.LINE_END;
		p1.add(user, g);
		
		g.gridx++;
		g.gridy=1;
		g.anchor=GridBagConstraints.LINE_START;
		p1.add(sqluser, g);
		
		g.gridx++;
		g.gridy=1;
		g.anchor=GridBagConstraints.LINE_END;
		p1.add(pwd, g);
		
		g.gridx++;
		g.gridy=1;
		g.anchor=GridBagConstraints.LINE_START;
		p1.add(sqlpwd, g);
		
		g.gridx=0;
		g.gridy++;
		g.anchor=GridBagConstraints.LINE_END;
		p1.add(abflughafenbox, g);
		
		JPanel p3=new JPanel();
		p3.setBorder(new EmptyBorder(20,20,20,20));
		Border buttonBorder = new EmptyBorder(5,25,5,25);
		p3.setLayout(new BoxLayout(p3,BoxLayout.X_AXIS));
		p.add(p3,BorderLayout.SOUTH);
		p3.add(Box.createHorizontalGlue());
		JButton connection = new JButton("Connection");
		connection.setBorder(buttonBorder);
		p3.add(connection);
		connection.addActionListener(a);
		
		sqlserver.setText("localhost");
		sqlport.setText("3306");
		sqldatabase.setText("flightdata");
		
		
		
		f.setContentPane(p);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				
				Driver d = new Driver();
				
				
				
				switch (e.getActionCommand()) {
					
				// Je nach Button werden andere Querys eingeleitet
				
				case "connection":
									d.getFlughafen();
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
		g.guimodell();
		
	}

}
