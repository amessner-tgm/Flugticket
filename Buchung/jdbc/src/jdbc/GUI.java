package jdbc;

import java.awt.*;
import javax.swing.*;

public class GUI extends JPanel{
	JTextField sqlserver=new JTextField();
	JTextField sqlport=new JTextField();
	JTextField sqluser=new JTextField();
	JTextField sqlpwd=new JTextField();
	public GUI(){
		JFrame f=new JFrame();
		f.setTitle("Flugticket");
		f.setBackground(Color.GRAY);
		f.setPreferredSize(new Dimension (600,300));
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
		//Textfelder
		sqlserver.setColumns(20);
		sqlport.setColumns(8);
		sqluser.setColumns(20);
		sqlpwd.setColumns(20);
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
		
		
		
		
		f.setContentPane(p);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	public static void main(String[] arqs){
		GUI g= new GUI();
	}

}
