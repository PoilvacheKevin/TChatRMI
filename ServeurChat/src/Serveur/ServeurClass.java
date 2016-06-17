package Serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import Client.ClientInterface;

public class ServeurClass extends UnicastRemoteObject implements ServeurInterface {
	private List<ClientInterface> myListOfClients = new ArrayList<ClientInterface>();
	
	protected ServeurClass() throws RemoteException {
	}

	@Override
	public void MessageServer(String IP, String msg) throws RemoteException {
		for(int i = 0; i < myListOfClients.size(); i++)
		{
			if(IP.equals(myListOfClients.get(i).SendPseudo()))
			{
				ClientInterface myClient = (ClientInterface)myListOfClients.get(i);
				myClient.SendMessage(msg);
			}
		}
	}

	@Override
	public boolean Connection(ClientInterface myClient) throws RemoteException {	
		if(myListOfClients.size() == 0)
		{
			myListOfClients.add(myClient);
			myClient.UpdateListOfClients(myClient.SendPseudo(), true, myListOfClients);
			return true;
		}
		
		for(int i = 0; i < myListOfClients.size(); i++)
		{		
			if(myClient.SendPseudo().equals(myListOfClients.get(i).SendPseudo()))
			{
				return false;
			}
		}
		
		myListOfClients.add(myClient);
		for(int i = 0; i < myListOfClients.size(); i++)
		{
			ClientInterface myTmpClient = (ClientInterface)myListOfClients.get(i);
			myTmpClient.UpdateListOfClients(myClient.SendPseudo(), true, myListOfClients);
		}
		return true;
	}
	
	@Override
	public void Deconnection(ClientInterface myClient) throws RemoteException {
		String nameClient = myClient.SendPseudo();
		myListOfClients.remove(myClient);
		for(int i = 0; i < myListOfClients.size(); i++)
		{
			ClientInterface myTmpClient = (ClientInterface)myListOfClients.get(i);
			myTmpClient.UpdateListOfClients(nameClient, false, myListOfClients);
		}		
	}

	@Override
	public List<ClientInterface> ReturnList() throws RemoteException {
		return myListOfClients;
	}
}
