package mediatheque;

public class Utilisateur {
	
	private String login;
	private String mdp;
	
	public Utilisateur(String login,String mdp)
	{
		this.login = login;
		this.mdp = mdp;
	}
	
	public String getLogin()
	{
		return this.login;
	}
	
	public String getMdp()
	{
		return this.mdp;
	}
}
