package Client;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.util.*;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.JList;
import Serveur.ServeurInterface;

public class JFrameClient extends JFrame {
	
	private ServeurInterface myServer;
	private ClientClass myClient;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					JFrameClient frame = new JFrameClient();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void Connection()
	{
		try {
			boolean checkNameUser = false;
			myClient = new ClientClass(name.getText(), InetAddress.getLocalHost().getHostAddress());
			myClient.Graphics(this);
			myServer = (ServeurInterface) Naming.lookup("rmi://" + ip.getText() + ":1099/Object");
			checkNameUser = myServer.Connection(myClient);
			if (!checkNameUser)
			{
				JOptionPane.showMessageDialog(frame, "Pseudo déjà utilisé, choisis en un autre !");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void AjouteUtilisateur(List<ClientInterface> myListOfClients)
	{
		DefaultListModel listeModel = new DefaultListModel();
		listeModel.clear();
		String newClient;
		if(myListOfClients != null)
		{
			for(int i = 0; i < myListOfClients.size(); i++)
			{
				try {
					newClient = ((ClientInterface) myListOfClients.get(i)).SendPseudo();
					listeModel.addElement(newClient);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		lst.setModel(listeModel);
	}
	
	public void EcritMsg(String msg)
	{
		tx.setText(tx.getText() + "\n" + msg);
	}
	
	public void EnvoieMsg()
	{
		try {
			String value = (String) lst.getSelectedValue();
			Date myDate = new Date();
			DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
			String message = "["+ shortDateFormat.format(myDate)+ "]" + name.getText() + ":-->" + tf.getText();
			EcritMsg(message);
			myServer.MessageServer(value, message);
			tf.setText("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JFrameClient() {
		frame=new JFrame("Mon Chat RMI");
	    JPanel main =new JPanel();
	    JPanel top =new JPanel();
	    JPanel cn =new JPanel();
	    JPanel bottom =new JPanel();
	    ip=new JTextField();
	    tf=new JTextField();
	    name=new JTextField();
	    tx=new JTextArea();
	    connect=new JButton("Connection");
	    JButton bt=new JButton("Envoyer");
	    lst=new JList();        
	    main.setLayout(new BorderLayout(5,5));         
	    top.setLayout(new GridLayout(1,0,5,5));   
	    cn.setLayout(new BorderLayout(5,5));
	    bottom.setLayout(new BorderLayout(5,5));
	    top.add(new JLabel("Ton nom: "));top.add(name);    
	    top.add(new JLabel("Serveur Adresse: "));top.add(ip);
	    top.add(connect);
	    cn.add(new JScrollPane(tx), BorderLayout.CENTER);        
	    cn.add(lst, BorderLayout.EAST);    
	    bottom.add(tf, BorderLayout.CENTER);    
	    bottom.add(bt, BorderLayout.EAST);
	    main.add(top, BorderLayout.NORTH);
	    main.add(cn, BorderLayout.CENTER);
	    main.add(bottom, BorderLayout.SOUTH);
	    main.setBorder(new EmptyBorder(10, 10, 10, 10) );
	    //Events
	    connect.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){ Connection();   }  });
	    bt.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){ EnvoieMsg();   }  });
	    tf.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){ EnvoieMsg();   }  });
	    frame.addWindowListener(new WindowAdapter() {
	    	public void windowClosing(WindowEvent we) {
	    		try {
					myServer.Deconnection(myClient);
	            	System.exit(0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
	    });
	    tx.setEditable(false);
	    frame.setContentPane(main);
	    frame.setSize(600,600);
	    frame.setVisible(true);  
	  }
	  JTextArea tx;
	  JTextField tf,ip, name;
	  JButton connect;
	  JList lst;
	  JFrame frame;
}

