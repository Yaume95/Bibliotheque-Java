import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class MenuService implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6202087863915811155L;
	private ArrayList<Adherent> adh;
	private ArrayList<Livre> livre;
	
	
	
	//------------------------- constructeur
	
	public MenuService() {
		this.adh = new ArrayList<Adherent>();
		this.livre = new ArrayList<Livre>();
	}
	
	//------------------------ getters/setters
	
		public ArrayList<Adherent> getAdh() {
			return adh;
		}
		public void setAdh(ArrayList<Adherent> adh) {
			this.adh = adh;
		}
		public ArrayList<Livre> getLivre() {
			return livre;
		}
		public void setLivre(ArrayList<Livre> livre) {
			this.livre = livre;
		}

	//------------------------------------- fonction
		
		/* Fonction pour trier les adhérents en fonctin de leur nom */
		public void AdherentTrie()
		{

			boolean tab_en_ordre = false;
			int taille = this.adh.size();
			int compare;
			Adherent tmp;
			
			
			while(!tab_en_ordre)		/* Tri a Bulle */
			    {
			        tab_en_ordre = true;
			        for(int i=0 ; i < taille-1 ; i++)
			        {
			        	compare = this.adh.get(i).getNom().compareTo(this.adh.get(i+1).getNom());
			            if(compare>0)
			            {
			                tmp = this.adh.get(i);
			                this.adh.set(i, this.adh.get(i+1));
			                this.adh.set(i+1,tmp);
			                tab_en_ordre = false;
			            }
			            else if(compare == 0)
			            {
			            	compare = this.adh.get(i).getPrenom().compareTo(this.adh.get(i+1).getPrenom());
				            if(compare>0)
				            {
				                tmp = this.adh.get(i);
				                this.adh.set(i, this.adh.get(i+1));
				                this.adh.set(i+1,tmp);
				                tab_en_ordre = false;
				            }
			            	
			            }
			        }
			        taille--;
			    }
		}
		
		/* Fonction qui permet de rechercher un livre suivant son titre ou son auteur, et qui renvoi un tableau de chaines correspondant au résultat trouvé */
		public Object[][] rechercheLivre(String s)
		{
			Object[][] l = new Object[this.getLivre().size()][5];
			int k=0;

			for(int i=0;i<this.getLivre().size();i++)
			{
				if(this.getLivre().get(i)!=null)
				{
					if(this.getLivre().get(i).getTitre().contains(s) || this.getLivre().get(i).getAuteur().contains(s)) /* Une partie seulement suffit pour trouver tout le titre/auteur */
					{
						l[k][0]= this.getLivre().get(i).getTitre();
						l[k][1]= this.getLivre().get(i).getAuteur();
						l[k][2]= this.getLivre().get(i).getCode();
						l[k][3]= this.getLivre().get(i).getNbExemplaireTotal();
						l[k][4]= this.getLivre().get(i).getNbExemplaireDispo();

						k++;
					}
				}
			}
			return l;
		}
		
		/* Fonction qui permet de rechercher un adhérent suivant son Nom, son Prénom ou son métier, et qui renvoi un tableau de chaines correspondant au résultat trouvé */
		public Object[][] rechercheAdh(String s)
		{
			Object[][] l = new Object[this.getAdh().size()][5];
			int k=0;

			for(int i=0;i<this.getAdh().size();i++)
			{
				if(this.getAdh().get(i)!=null)
				{
					if(this.getAdh().get(i).getNom().contains(s) || this.getAdh().get(i).getPrenom().contains(s)|| this.getAdh().get(i).getProfession().contains(s))
					{
						l[k][0]= this.getAdh().get(i).getNom();
						l[k][1]= this.getAdh().get(i).getPrenom();
						l[k][2]= this.getAdh().get(i).getAdresse();
						l[k][3]= this.getAdh().get(i).getMail();
						l[k][4]= this.getAdh().get(i).getProfession();
						k++;
					}
				}
			}
			return l;
		}
	
		/* Fonction qui renvoie un tableau de chaines de caractères contenant tous les adhérents et leurs données */
		public Object[][] obtenirAdherents()
		{
			int nb = this.adh.size();
			String[][] Sortie= new String[nb][5];
			
			for(int i=0 ; i<nb ; i++)
			{
				Sortie[i][0] = this.adh.get(i).getNom();
				Sortie[i][1] = this.adh.get(i).getPrenom();
				Sortie[i][2] = this.adh.get(i).getAdresse();
				Sortie[i][3] = this.adh.get(i).getMail();
				Sortie[i][4] = this.adh.get(i).getProfession();
			}
			
			return Sortie;
		}
	
		/* Fonction qui permet de trier les livres en foction de leur Titre */
		public void livreTri()
		{

			boolean tab_en_ordre = false;
			int taille = this.livre.size();
			int compare;
			Livre tmp;

			while(!tab_en_ordre) //tri a bulle
				{
			        tab_en_ordre = true;
			        for(int i=0 ; i < taille-1 ; i++)
			        {
			        	compare = this.livre.get(i).getTitre().compareTo(this.livre.get(i+1).getTitre());
			            if(compare>0)
			            {
			                tmp = this.livre.get(i);
			                this.livre.set(i, this.livre.get(i+1));
			                this.livre.set(i+1,tmp);
			                tab_en_ordre = false;
			            }
			            
			        }
			        taille--;
			    }
		}
		
		/* Fonction qui prend en argument les données du futur adhérent et les ajoute a notre base de données */
		public void addAdherent(String nom,String prenom,String adresse ,String mail,String profession)
		{	
			this.adh.add(new Adherent(nom,prenom,adresse,mail,profession));
			System.out.println("Adherent ajouté !");			
		}

		/* Fonction qui prend en argument les données du futur livre et les ajoute a notre base de données */
		public void addLivre(String titre, String auteur, String code, String nbExemT, String nbExemD)
		{		
		
			this.livre.add(new Livre(titre,auteur,code,Integer.parseInt(nbExemT),Integer.parseInt(nbExemD)));
			System.out.println("Livre ajoute !");
		}
		
		/* Fonction qui renvoie un tablea d chaines de caractères correspondant à tous les livres */
		public Object[][] obtenirLivre()
		{
			int nb = this.livre.size();
			Object[][] Sortie= new String[nb][5];
			
			for(int i=0 ; i<nb ; i++)
			{
				Sortie[i][0] = this.livre.get(i).getTitre();
				Sortie[i][1] = this.livre.get(i).getAuteur();
				Sortie[i][2] = this.livre.get(i).getCode();
				Sortie[i][3] = String.valueOf(this.livre.get(i).getNbExemplaireTotal());
				Sortie[i][4] = String.valueOf(this.livre.get(i).getNbExemplaireDispo());
			}
			
			return Sortie;
		}
		
		/* Fonction qui renvoie sous forme de tableau de caractères la liste du nom des adhérents ayant un livre en retard, et le code de ce dernier */
		public String[][] obtenirAdherentRetard()
		{
			int nbAdh = this.adh.size();
			String[][] Sortie= new String[nbAdh][4];

			Date aujourdhui = new Date();
			
			for(int i=0;i<nbAdh;i++)
			{
				for(int j=0;j<3;j++)
				{
					if(this.adh.get(i).getCodei(j) != null && this.adh.get(i).getDateRetouri(j).compareTo(aujourdhui)<0) // si la date de retour du livre est dépassé
					{
						System.out.println(this.getAdh().get(i).getNom() + " est en retard pour le livre " + this.getAdh().get(i).getCodei(j));
						Sortie[i][0]=this.getAdh().get(i).getNom();
						Sortie[i][j+1]=this.getAdh().get(i).getCodei(j);
						
					}
					
				}
				
			}
			
			return Sortie;
			
			
		}
		/* Fonction qui renvo sous forme de tablea de caractères la liste des livres détenus par des adhérents */
		public String[][] ListeAdhLivre()
		{
			int taille = this.adh.size();
		    String[][] s = new String[taille][4];
		   
		    for(int i=0;i<taille;i++)
		    {
		    	for(int k=0;k<3;k++)
		    	{
		    		if(this.getAdh().get(i).getCodei(k)!=null)
		    		{
		    			s[i][0]=this.getAdh().get(i).getNom();
		    			s[i][k+1]=this.getAdh().get(i).getCodei(k);
		    		}
		    	}
		   }
		   
		   return s;
		  }
		
		/* Foction qui permet de retirer un adhérent en fonction du nom donné */
		public int removeAdherent(String nom)
		{
			int i;
			
			for(i=0;i<this.adh.size();i++)
			{
				if(this.getAdh().get(i)!=null)
				{
					if(this.getAdh().get(i).getNom().equals(nom)) //l'adhérent existe
					{
						this.getAdh().remove(i);  //suppression
						return 0;
					}
				}
			}
			
				return -1;
			
				
		}
		
		/* Foction qui permet de retirer un Livre en fonction du Titre donné */
		public int removeLivre(String titre)
		  {

		   int i;
		   
		   boolean b = false;
		   boolean c = false;
		   
		   for(i=0; b==false &&  i<this.livre.size();i++) //on parcourt les livres jusqu'a trouver le notre
		   {
			   if(this.getLivre().get(i)!=null)
			   {
				   if(this.getLivre().get(i).getTitre().equals(titre))	
				   {
					   b=true;	//le livre existe
				   }
			   }
		   }
		   
		   for(int j=0;j<this.adh.size();j++)
		   {
			   if(this.getAdh().get(j)!=null)
			   {
				   for(int k=0;k<3;k++)
				   {
					   if(this.getAdh().get(j).getCodei(k)!=null)
					   {
						   if(this.getLivre().get(i-1).getCode().equals(this.getAdh().get(j).getCodei(k))) //si le livre est emprunté
						   {
							   c=true; //on ne supprime pas un livre emprunté
							   return -2;
						   }
					   }
				   }
			   }
		   }
		   
		   if(i==this.adh.size())
		   {
			   return -1; //le livre n'existe pas
		   }
		   	if(!c && b)
		   	{
		   		this.getLivre().remove(i-1); //suppression du livre
		   		return 0;
		   	}
		   	return -1;
		   	
		}
		
		
		/* Fonction qui permet d'enregistrer un emprunt en fonction du titre du livre et du nom de l'adhérent */
		@SuppressWarnings("deprecation")
		public int ajoutEmprunt(String titre, String nom)
		{
			int i;
			int j;
			int k;
			boolean b=false;
			
			
			
			
			for(i=0; b==false &&  i<this.livre.size();i++)
			{
				if(this.getLivre().get(i)!=null)
				{
					if(this.getLivre().get(i).getTitre().equals(titre))
					{
						b=true;  //le livre existe
					}
				}
			}
			if(b==false)
			{
				System.out.println("ce livre n'existe pas");
				return -1;
			}
			else if(this.getLivre().get(i-1).getNbExemplaireDispo()==0)
			{
				System.out.println("ce livre n'est pas disponible actuellement");
				return -2; //aucun exemplaire dispo
			}
			else
			{
				b=false;
				System.out.println("Livre trouvé");
				
				for(j=0; b==false &&  j<this.adh.size();j++)
				{
					if(this.getAdh().get(j)!=null)
					{
						if(this.getAdh().get(j).getNom().equals(nom))  //l'adherent existe
						{
							b=true;
						}
					}
				}
				
				if(b==false)
				{
					System.out.println("Cet adherent n'existe pas");
					return 1;
				}
				else
				{
					b=false;
					for(k=0;b==false && k<this.getAdh().get(j-1).getCode().length;k++)
					{
						if(this.getAdh().get(j-1).getCodei(k)==null) //si il n'a pas emprunté 3 livres
						{
							b=true;
						}
					}
					if(b==true)  // on ajoute l'emprunt, on reduit le nombre d'exemplaire dispo, et on ajoute la date de retour à aujourd'hui +15
					{
						Date d = new Date();
						d.setDate(d.getDate()+15);
						this.getAdh().get(j-1).setCodei(k-1, this.getLivre().get(i-1).getCode());
						this.getAdh().get(j-1).setDateRetouri(k-1, d);
						this.getLivre().get(i-1).setNbExemplaireDispo(this.getLivre().get(i-1).getNbExemplaireDispo()-1);
					}
					else
					{
						System.out.println("Cet adherent à déjà empreinté 3 livres");
						return 2;
					}
				}
			}
			
			return 0;
		}
		
		/* Fonction qui permet de rendre un livre en donnant son titre ainsi que le nom de l'adhérent qui l'a emprunté */
		public int rendreLivre(String titre,String nom)
		{
			int i;
			int j;
			int k;
			boolean b=false;
			
			
			for(i=0; b==false &&  i<this.livre.size();i++)
			{
				if(this.getLivre().get(i)!=null)
				{
					if(this.getLivre().get(i).getTitre().equals(titre))
					{
						b=true;  //Le livre existe
					}
				}
			}
			if(b==false)
			{
				return -1;
	
			}
			else
			{
				b=false;
				System.out.println("Livre trouvé");

				for(j=0; b==false &&  j<this.adh.size();j++)
				{
					if(this.getAdh().get(j)!=null)
					{
						if(this.getAdh().get(j).getNom().equals(nom)) //adhérent existe
						{
							b=true;
						}
					}
				}
				
				if(b==false)
				{		
					return 1;
				}
				else
				{
					b=false;
					for(k=0;b==false && k<this.getAdh().get(j-1).getCode().length;k++)
					{
						if(this.getAdh().get(j-1).getCodei(k)==this.getLivre().get(i-1).getCode())
						{
							b=true; //le livre à bien été emprunté par cet adhérent
						}
					}
					if(b==true) //on rend le livre, on augmente le nombre d'exemplaire dispo
					{
						this.getAdh().get(j-1).setCodei(k-1, null);
						this.getAdh().get(j-1).setDateRetouri(k-1, null);
						this.getLivre().get(i-1).setNbExemplaireDispo(this.getLivre().get(i-1).getNbExemplaireDispo()+1);
						return 0;
					}
					else
					{
						return -2; // le livre n'a pas été emprunté par cet adhérent
					}
	
				}
			}
			
		}
			
		/* Permet de sauvegarder nos adhérnets et livres enregistrés dans un fichier */
		public int sauvegarde() 
		{
			ObjectOutputStream oos;
			try
			{ 	
				     oos = new ObjectOutputStream(
				             new BufferedOutputStream(
				               new FileOutputStream(
				                 new File("biblio.txt"))));	
				      oos.writeObject(this.adh);
				      oos.writeObject(this.livre);
				      
				      oos.close();
				      
				      return 0;
			}
			catch(FileNotFoundException e) 
			{
			      e.printStackTrace();
			      return 1;
			}
			catch (IOException e) 
			{
			      e.printStackTrace();
			      return 1;
			} 
				 
		}
		
		/* Permet de charger une liste d'ahdhérents et de livres depuis un fichier */
		@SuppressWarnings({ "unchecked", "resource" })
		public int charge()
		{
			ObjectInputStream ois;

			try
			{
				ois = new ObjectInputStream(
						new BufferedInputStream(
							new FileInputStream(
								new File("biblio.txt"))));
				try
				{
					this.setAdh( (ArrayList <Adherent>) ois.readObject() );
					this.setLivre( (ArrayList <Livre>) ois.readObject() );
					ois.close();
					return 0;
				}
				catch (ClassNotFoundException e) 
				{
				     e.printStackTrace();
				     return 1;
				}
			}
			catch(FileNotFoundException e) 
			{
			      e.printStackTrace();
			      return 1;
			}
			catch (IOException e) 
			{
			      e.printStackTrace();
			      return 1;
			} 
		}
}