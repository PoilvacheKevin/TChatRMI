package Client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import Serveur.ServeurInterface;

public class ClientClass extends UnicastRemoteObject implements ClientInterface {

	JFrameClient myFrame;
	private String pseudo = "";
	private String IP = "";
	
	protected ClientClass(String pseudo , String IP) throws RemoteException {
		this.pseudo = pseudo;
		this.IP = IP;
	}
	
	public void Graphics(JFrameClient frame){ 
		myFrame = frame ; 
	} 

	@Override
	public void SendMessage(String msg) throws RemoteException {
		myFrame.EcritMsg(msg);
	}

	@Override
	public String SendPseudo() throws RemoteException {
		return this.pseudo;
	}

	public void SetPseudo(String pseudo)
	{
		this.pseudo = pseudo;
	}
	@Override
	public String SendIP() throws RemoteException {
		return this.IP;
	}

	@Override
	public void UpdateListOfClients(String nameClient, boolean truefalse, List myListOfClients) throws RemoteException {
		if(truefalse == true)
		{
			myFrame.EcritMsg(nameClient + ": s'est connecté à la conversation !");
		}
		else
		{
			myFrame.EcritMsg(nameClient + " : s'est déconnecté de la conversation !");
		}
		myFrame.AjouteUtilisateur(myListOfClients);
		
	}
	

}
