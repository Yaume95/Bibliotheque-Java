import java.io.Serializable;
import java.util.Date;

public class Adherent implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3037715235945291651L;
	private String nom;
	private String prenom;
	private String adresse;
	private String mail;
	private String profession;
	private String[] code;
	private Date[] dateRetour;

	//-------------------------------------- getters/setters
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	
	public String[] getCode() {
		return code;
	}
	public String getCodei(int i)
	{
		return this.code[i];
	}
	public void setCodei(int i,String code)
	{
		 this.code[i]=code;
	}
	public void setCode(String[] code) {
		System.arraycopy(code, 0, this.code, 0, 3);
	}
	
	public int getNbEmprunts()
	{
		int nb=0;
		
		for (int i=0; i < 3; i++)
		{
			if(this.code[i]!= null) nb++;
		}
		return nb;
	}
	public Date[] getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(String[] dateRetour) {
		System.arraycopy(dateRetour, 0, this.dateRetour, 0, 3);
	}
	public void setDateRetouri(int i,Date dateRetour) {
		this.dateRetour[i]=dateRetour;
	}
	public Date getDateRetouri(int i) {
		return this.dateRetour[i];
	}
	
	
	
	
	//-------------------------------------- CONSTRUCTEURS
	public Adherent()
	{
		this.nom=null;
		this.prenom=null;
		this.adresse=null;
		this.mail=null;
		this.code=null;
		this.dateRetour=null;
	}
	
	public Adherent(String nom, String prenom, String adresse, String mail, String profession, String[] code,
			Date[] dateRetour) 
	{
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.mail = mail;
		this.profession = profession;
		this.code = new String[3];
		System.arraycopy(code,0,this.code,0,3);
		this.dateRetour = new Date[3];
		System.arraycopy(dateRetour, 0, this.dateRetour, 0, 3);
	}
	
	public Adherent(String nom, String prenom, String adresse, String mail, String profession) 
	{
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.mail = mail;
		this.profession = profession;
		this.code = new String[3];
		this.dateRetour = new Date[3];
		
	}
	
}
