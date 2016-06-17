package Serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import Client.ClientInterface;

public interface ServeurInterface extends Remote {
	public void MessageServer(String IP, String msg) throws RemoteException;
	public boolean Connection(ClientInterface myClient) throws RemoteException;
	public void Deconnection(ClientInterface myClient) throws RemoteException;
	public List<ClientInterface> ReturnList() throws RemoteException;
}
