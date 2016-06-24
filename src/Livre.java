import java.io.*;

public class Livre implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8962750523048383779L;
	private String titre;
	private String auteur;
	private String code;
	private int nbExemplaireTotal;
	private int nbExemplaireDispo;
	
	
	//--------------------------------- getters/setters
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getNbExemplaireTotal() {
		return nbExemplaireTotal;
	}
	public void setNbExemplaireTotal(int nbExemplaireTotal) {
		this.nbExemplaireTotal = nbExemplaireTotal;
	}
	public int getNbExemplaireDispo() {
		return nbExemplaireDispo;
	}
	public void setNbExemplaireDispo(int nbExemplaireDispo) {
		this.nbExemplaireDispo = nbExemplaireDispo;
	}
	
	
	//-------------------------------------- constructeur
	
	public Livre()
	{
		this.titre = null;
		this.auteur=null;
		this.code=null;
		this.nbExemplaireDispo=0;
		this.nbExemplaireTotal=0;
	}
	
	
	public Livre(String titre, String auteur, String code, int nbExemplaireTotal, int nbExemplaireDispo) 
	{
		this.titre = titre;
		this.auteur = auteur;
		this.code = code;
		this.nbExemplaireTotal = nbExemplaireTotal;
		this.nbExemplaireDispo = nbExemplaireDispo;
	}
	
	
	
	
	
}
