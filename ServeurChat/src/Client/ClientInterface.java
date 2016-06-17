package Client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientInterface extends Remote {
	public void SendMessage(String Msg) throws RemoteException;
	public String SendPseudo() throws RemoteException;
	public String SendIP() throws RemoteException;
	public void UpdateListOfClients(String nameClient, boolean truefalse, List myListOfClients) throws RemoteException;
}
