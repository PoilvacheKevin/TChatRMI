package Serveur;

public class ClientObject {
	private String IP = "";
	private String pseudo = "";
	private String message = "";
	
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		pseudo = pseudo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		message = message;
	}
	
	public ClientObject(String IP, String pseudo)
	{
		this.IP = IP;
		this.pseudo = pseudo;
	}
}
