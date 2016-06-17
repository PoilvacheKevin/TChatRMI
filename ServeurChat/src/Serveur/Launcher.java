package Serveur;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Launcher {

	public static void main(String[] args) {
		try {
			java.rmi.registry.LocateRegistry.createRegistry(1099);
			ServeurClass myServer = new ServeurClass();
			Naming.rebind("rmi://" +  InetAddress.getLocalHost().getHostAddress() + ":1099/Object",myServer);
			System.out.println("Serveur Prêt : " + InetAddress.getLocalHost().getHostAddress().toString());			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
