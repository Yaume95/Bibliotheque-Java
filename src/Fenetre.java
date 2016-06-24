import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;


 
public class Fenetre extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Panneau pan = new Panneau();
	private JPanel container = new JPanel();

	private MenuService Donnee= new MenuService();


	private JLabel Label = new JLabel("Choisissez votre menu :");
	Font Police = new Font("Arial", Font.BOLD, 40);
	BorderLayout General = new BorderLayout();
	GridLayout BoiteBoutons = new GridLayout(1,2);
	Bouton RetourMenuDepart = new Bouton("Retour");

    JPanel BoiteRetour= new JPanel();
	  
    /* Cr�ation d'une fen�tre avec notre iamge de fond */
	public Fenetre()
	{
	    this.setTitle("Biblioth�que 2.0");
	    this.setSize(1280, 900);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);
	    
	    
	    initChoix();
	    this.setVisible(true);  
	}
	
	/* Modifie les �l�ments de la fen�tre pour demander quel type de menu veut consulter  l'utilisateur */
	public void initChoix()
	{ 
		Bouton MenuServ = new Bouton("Menu de Service");
		Bouton MenuInt = new Bouton("Menu d'interface");
		Bouton Charger = new Bouton("Charger");
		Bouton Sauvegarder = new Bouton("Sauvegarder");
		
		
		effacertout();
		BoiteRetour.removeAll();
		BoiteRetour.setOpaque(false);
		

		
	    InitialiserLayoutGeneral(370,300);
	    this.pan.setLayout(General);

	    this.BoiteBoutons.setHgap(200);
	    this.container.setLayout(BoiteBoutons);
	    
	    // On effectue un traitement de texte sur notre label
	    Label = InitialiserLabel("Choisissez ce que vous voulez :", Police, Color.WHITE);
	
	    
	    // Rendre le JPanel contenant les JButtons transparent
	    container.setOpaque(false);
	    
	    // Modififier affcihage des JButtons
	    MenuServ.setFocusPainted(false);
	    MenuServ.setFont(new Font("Arial", Font.BOLD,20));
	    MenuInt.setFocusPainted(false);
	    MenuInt.setFont(new Font("Arial", Font.BOLD,20));
	    Charger.setFocusPainted(false);
	    Charger.setFont(new Font("Arial", Font.BOLD,15));
	    Sauvegarder.setFocusPainted(false);
	    Sauvegarder.setFont(new Font("Arial", Font.BOLD,15));
	    
	    //Ajout des boutons à un conteneur pour ces derniers
	    container.add(MenuServ);
	    container.add(MenuInt);
	   
	    container.setLayout(BoiteBoutons);
	    
	    //Notre classe s'�coute elle m�me, sur les boutons afin d'effectuer  une action
	    MenuServ.addActionListener(new Menu1Listener());
	    MenuInt.addActionListener(new Menu2Listener());
	    Sauvegarder.addActionListener(new BoutonSaveListener());
	    Charger.addActionListener(new BoutonChargerListener());
	    // Ajout d'un label et de la boite � boutons
	    pan.add(Label, BorderLayout.NORTH);
	    
	    BoiteRetour.add(Sauvegarder, BorderLayout.EAST);
	    BoiteRetour.add(Charger,BorderLayout.WEST);
	    
	    pan.add(BoiteRetour, BorderLayout.SOUTH);
	    pan.add(new JLabel(), BorderLayout.EAST);
	    pan.add(new JLabel(), BorderLayout.WEST);
	    pan.add(container);
	    
	    
	    this.setContentPane(pan);
	    this.setVisible(true);  
    
  }
	
	/* Permet l'imbrication d'un tableau dans un panneau pour pouvoir ensuite l'afficher */
	public void afficherListe(JTable Tableau, JPanel Container)
	{
		/* Traitements sur le fond du tableau */
		Tableau.setOpaque(false);
    	((DefaultTableCellRenderer)Tableau.getDefaultRenderer(Object.class)).setOpaque(false);
    	Tableau.setFont(new Font("Arial", Font.BOLD, 19));
    	Tableau.setForeground(Color.WHITE);
    	Tableau.setShowGrid(false);
    	Tableau.setRowHeight(20);
    	
    	/*Ajout du tableau dans le panneau */
    	Container.add(Tableau);
    	
    	container=Container;
    	
    	setContentPane(pan);
    	repaint();
	}
	
	/* Efface les 3 panneaux de base */
	public void effacertout()
	{
		pan.removeAll();
    	container.removeAll();
    	BoiteRetour.removeAll();
	}
	/* Efface le panneau central */
	public void effacerConteneur()
	{
    	container.removeAll();
	}
	
	/* Permet de mettre en place l'espace entre les diff�rentes parties du layout de la fenetre elle m�me */
	public void InitialiserLayoutGeneral(int VGap, int HGap)
	{
		General.setVgap(VGap);
		General.setHgap(HGap);
	}
	
	/* permet de traiter un label en fonction du font et de la couleur souhait�e */
	public JLabel InitialiserLabel(String strg, Font font, Color color)
	{
		JLabel Label=new JLabel(strg);
		Label.setFont(font);
		Label.setForeground(Color.WHITE);
 	    Label.setHorizontalAlignment(JLabel.CENTER);
 	    
 	    return Label;
	}
	
	/* Fonction qui modifie l'apparence d'un bouton puis le lie au listener pass� en argument */
	public void InitialiserBouton(Bouton B, ActionListener AL)
	{
		B.setFocusPainted(false);
		B.setFont(new Font("Arial", Font.BOLD,18));
		B.addActionListener(AL);
	}
	
	/* Fonction qui permet d'initialiser un champs et de le lier a un listener ass� en argument */
	public void InitialiserChamps(JTextField JTF, FocusAdapter FA)
	{

		JTF.setFont(new Font("Arial", Font.ITALIC,18));
		JTF.addFocusListener(FA);
	}
	
	/* Permet d'mbriquer un layout au nord du panneau g�n�ral, tout en initialisant les autres parties du layout par un vide */
	public void imbriquerLabelNord(JLabel Label)
	{
		pan.add(Label, BorderLayout.NORTH);
		pan.add(new JLabel(""), BorderLayout.EAST);
		pan.add(new JLabel(""), BorderLayout.WEST);
		pan.add(new JLabel(""), BorderLayout.SOUTH);
		
	}
	
	/* Permet de cr�er un masque de fitrage pour les champs formatt�s */
	protected MaskFormatter createFormatter(String s) {
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	        System.exit(-1);
	    }
	    return formatter;
	}
	
	/* Classe permettant de cr�er un tableau dont les cellules ne sont pas �ditables et empechant de s�lectionner une ligne enti�re */
	class UneditableTableModel extends DefaultTableModel {
		 
	    /**
		 * 
		 */
		private static final long serialVersionUID = -9178317622792195593L;
		
		public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	    public boolean setRowSelectionAllowed()
	    {
	    	return false;
	    }
	    public UneditableTableModel(Object[][] data, Object[] columnNames) 
	    {
	        super(data, columnNames);
	    }
	
	  
	}
	
	
	/*--------------------------------------------*  INTERFACE DU 1er MENU	*---------------------------------------------*/
	@SuppressWarnings("deprecation")
	public void InterfaceMenuServ()
	{
		
		Bouton ListeAdh = new Bouton("Obtenir la liste des Adh�rents");
		Bouton ListeLivres = new Bouton("Obtenir la liste des Livres");
		Bouton ListeRetards = new Bouton("Liste des adh�rents en retard");
		Bouton LivresAdh = new Bouton("Liste des emprunts");
		Bouton Rechercher = new Bouton("Recherche");
		
		 // On efface tout  
    	effacertout();
  
    	
    	// On met en place le Layout de la box qui va remplacer l'actuelle
    	General =new BorderLayout();
    	InitialiserLayoutGeneral(125,475);
    	pan.setLayout(General);
    	BoiteRetour.setOpaque(false);
    	
    	// On modifie le label
    	Label = InitialiserLabel((String.valueOf(new Date().getDay()+12) + "/" + String.valueOf(1+new Date().getMonth()) +"/"+ (new Date().getYear()+1900)),Police,Color.WHITE);
 	    
 	    // Ajout du tout dans le panneau g�n�ral
    	imbriquerLabelNord(Label);
    	
    	// Layout du conteneur
    	GridLayout NewMenu = new GridLayout(5,1);
		NewMenu.setVgap(80);

		container.setLayout(NewMenu);
		
		/* On lie les boutons au listener correpondant */
		InitialiserBouton(ListeAdh, new BoutonListeAdhListener());
		InitialiserBouton(ListeLivres, new BoutonListeLivreListener());
		InitialiserBouton(ListeRetards, new BoutonListeRetardsListener());
		InitialiserBouton(LivresAdh, new BoutonLivresAdhListener());
		InitialiserBouton(Rechercher, new BoutonRechercherListener());
		
		RetourMenuDepart.addActionListener(new ActionListener(){
	    	  public void actionPerformed(ActionEvent event){
	    		  initChoix();
	    	  }
	    	});
		
		
		
		/* On imbrique */
		container.add(ListeAdh);
		container.add(ListeLivres);
		container.add(LivresAdh);
		container.add(ListeRetards);
		container.add(Rechercher);
		BoiteRetour.add(RetourMenuDepart,BorderLayout.SOUTH);	
		pan.add(container);
    	pan.add(BoiteRetour, BorderLayout.SOUTH);
    	
    	/* On affiche */
		setContentPane(pan);
    	repaint();
	}
	

					//* LISTENERS POUR MENU DE SERVICE *//
	/*Classe du Listener pour le bouton qui permet de charger une base de donn�e depuis un fichier texte */
	class BoutonChargerListener implements ActionListener
	{
	    Bouton Retour = new Bouton("Retour");
	    //Red�finition de la m�thode actionPerformed()
	    public void actionPerformed(ActionEvent arg0) 
	    {
	    	BoiteRetour.removeAll();
	    	BoiteRetour.setOpaque(false);
	    	switch(Donnee.charge())
	    	{
		    	case 0:
		    	{
		    		effacerConteneur();
		    		BoiteRetour.removeAll();
					Label.setText("Charge r�ussie");
					Label.setFont(Police);
					pan.add(Label);
					
					InitialiserBouton(Retour,new ActionListener(){
				    	  public void actionPerformed(ActionEvent event){
				    		  initChoix();
				    	  }
				    	});
					BoiteRetour.removeAll();
					BoiteRetour.add(Retour);
					pan.add(BoiteRetour, BorderLayout.SOUTH);
					
					break;
		    	}
		    	
		    	case -1:
		    	{
		    		effacerConteneur();
		    		BoiteRetour.removeAll();

    				Label.setText("�chec...");
    				Label.setFont(Police);
    				pan.add(Label);
    				
    				InitialiserBouton(Retour,new ActionListener(){
				    	  public void actionPerformed(ActionEvent event){
				    		  initChoix();
				    	  }
				    	});
    				BoiteRetour.removeAll();
    				BoiteRetour.add(Retour);
    				pan.add(BoiteRetour, BorderLayout.SOUTH);
    				
    				break;
		    	}
	    	}
	    }
	}
	
	/*Classe du Listener pour le bouton qui permet de sauvegarder une base de donn�e dans un fichier texte */
	class BoutonSaveListener implements ActionListener
	{
	    Bouton Retour = new Bouton("Retour");
	    //Red�finition de la m�thode actionPerformed()
	    public void actionPerformed(ActionEvent arg0) 
	    {
	    	BoiteRetour.removeAll();
	    	BoiteRetour.setOpaque(false);
	    	switch(Donnee.sauvegarde())
	    	{
		    	case 0:
		    	{
		    		effacerConteneur();
		    		BoiteRetour.removeAll();
					Label.setText("Sauvegarde r�ussie");
					Label.setFont(Police);
					pan.add(Label);
					
					InitialiserBouton(Retour, new ActionListener(){
				    	  public void actionPerformed(ActionEvent event){
				    		  initChoix();
				    	  }
				    	});
					BoiteRetour.removeAll();
					BoiteRetour.add(Retour);
					pan.add(BoiteRetour, BorderLayout.SOUTH);
					
					break;
		    	}
		    	
		    	case -1:
		    	{
		    		effacerConteneur();
    				Label.setText("�chec...");
    				Label.setFont(Police);
    				pan.add(Label);
    				
    				InitialiserBouton(Retour,new ActionListener(){
				    	  public void actionPerformed(ActionEvent event){
				    		  initChoix();
				    	  }
				    	});
    				BoiteRetour.removeAll();
    				BoiteRetour.add(Retour);
    				pan.add(BoiteRetour, BorderLayout.SOUTH);
    				
    				break;
		    	} 	
	    	
	    	}
	    }
	}
	
	/*Classe du Listener pour le bouton qui permet l'affichage de la liste tri�e des adh�rents */
	class BoutonListeRetardsListener implements ActionListener
	{
		
		String[][] data ;
	    String  Titre[] = {"Nom", "Code"};
	    
	    JTable ListeAdhRetards = new JTable();
	    GridLayout NewLayout = new GridLayout(2,1);
	    
	    
	    
	    Bouton Retour = new Bouton("Retour");
	    
	    public void actionPerformed(ActionEvent arg0) 
	    {
	    	/* On efface */
	    	effacerConteneur();
	    	BoiteRetour.removeAll();
	    	
	    	/* On met en place les Layouts */
	    	InitialiserLayoutGeneral(40,350);
	    	pan.setLayout(General);
	    	BoiteRetour.setOpaque(false);
	    	
	    	NewLayout.setVgap(50);;
	    	container.setLayout(NewLayout);
	    	
	    	/* On r�cup�re la liste des adh�rents en retards */
	    	data = Donnee.obtenirAdherentRetard();
	    	
	    	/* On cr�er le tableau d�di� � l'affichage */
	    	UneditableTableModel DTM = new UneditableTableModel(data,Titre);
	    	ListeAdhRetards.setModel(DTM);
	    	ListeAdhRetards.setRowSelectionAllowed(false);
	    	
	    	
	    	/* Initialisation du bouton de retour */
	    	Retour.setFocusPainted(false);
	    	Retour.setFont(new Font("Arial", Font.BOLD, 20));
	    	
	    	Retour.addActionListener(new ActionListener(){
		    	  public void actionPerformed(ActionEvent event){
		    		  InterfaceMenuServ();
		    	  }
		    	});
	    	
	    	/* Et on affiche */
	    	afficherListe(ListeAdhRetards,container);
	    	
	    	
	    	BoiteRetour.add(Retour);
	    	pan.add(BoiteRetour,  BorderLayout.SOUTH);

	    }
	    
	}
	
	
	/*Classe du Listener pour le bouton qui permet l'affichage de la liste tri�e des adh�rents */
	class BoutonListeAdhListener implements ActionListener
	{
		
		Object[][] data ;
	    String  Titre[] = {"Nom", "Pr�nom", "Mail","Adresse", "Profession"};
	    
	    JTable ListeAdh = new JTable();
	    GridLayout NewLayout = new GridLayout(2,1);
	    
	    Bouton Retour = new Bouton("Retour");
	    
	    //Red�finition de la m�thode actionPerformed()
	    public void actionPerformed(ActionEvent arg0) 
	    {
	    	effacerConteneur();
	    	BoiteRetour.removeAll();
	    	
	    	
	    	
	    	InitialiserLayoutGeneral(40,100);
	    	pan.setLayout(General);
	    	BoiteRetour.setOpaque(false);
	    	
	    	NewLayout.setVgap(50);;
	    	container.setLayout(NewLayout);
	    	
	    	Donnee.AdherentTrie();
	    	data= Donnee.obtenirAdherents();
	    	
	    	UneditableTableModel DTM = new UneditableTableModel(data,Titre);
	    	ListeAdh.setModel(DTM);
	    	ListeAdh.setRowSelectionAllowed(false);
	    	
	    	ListeAdh.getColumnModel().getColumn(2).setPreferredWidth(200);
	    	ListeAdh.getColumnModel().getColumn(3).setPreferredWidth(200);
	    	
	    		
	    	Retour.setFocusPainted(false);
	    	Retour.setFont(new Font("Arial", Font.BOLD, 20));
	    	
	    	Retour.addActionListener(new ActionListener(){
		    	  public void actionPerformed(ActionEvent event){
		    		  InterfaceMenuServ();
		    	  }
		    	});
	    	
	    	
	    	afficherListe(ListeAdh,container);
	    	
	    	
	    	BoiteRetour.add(Retour);
	    	pan.add(BoiteRetour,  BorderLayout.SOUTH);

	    }
	    
	}

	/*Classe du Listener pour le bouton qui permet l'affichage de la liste tri�e des livres */
	class BoutonListeLivreListener implements ActionListener
	{

		Object[][] data ;
	    String  Titre[] = {"Titre", "Auteur", "Code","Nombre d'exempalires", "Disponibles"};
	    JTable ListeLivre = new JTable();
	    GridLayout NewLayout = new GridLayout(2,1);
	    
	    Bouton Retour = new Bouton("Retour");
	    
	    
	    public void actionPerformed(ActionEvent arg0) 
	    {
	    	/* On efface */
	    	effacerConteneur();
	    	BoiteRetour.removeAll();
	    	
	    	/* Initialisation des Layouts */
	    	InitialiserLayoutGeneral(40,100);
	    	pan.setLayout(General);
	    	
	    	NewLayout.setVgap(50);;
	    	container.setLayout(NewLayout);
	    	
	    	BoiteRetour.removeAll();
	    	
	    	Donnee.livreTri();
	    	data= Donnee.obtenirLivre();
	    	
	    	/* Cr�aton d'un model non �ditable */
	    	UneditableTableModel DTM = new UneditableTableModel(data,Titre);
	    	ListeLivre.setModel(DTM);
	    	ListeLivre.setRowSelectionAllowed(false);
	    	/* Redimension de la largeur de certaines colonnes */
	    	ListeLivre.getColumnModel().getColumn(0).setPreferredWidth(150);
	    	ListeLivre.getColumnModel().getColumn(3).setPreferredWidth(100);
	    	ListeLivre.getColumnModel().getColumn(4).setPreferredWidth(100);
	    		
	    	/* initialisation du bouton retour */
	    	Retour.setFocusPainted(false);
	    	Retour.setFont(new Font("Arial", Font.BOLD, 20));
	    	
	    	Retour.addActionListener(new ActionListener(){
		    	  public void actionPerformed(ActionEvent event){
		    		  InterfaceMenuServ();
		    	  }
		    	});
	    	
	    	/* Affichage de la listetri�e */
	    	afficherListe(ListeLivre,container);
	    	
	    	/* Imbrication finale */
	    	BoiteRetour.add(Retour);
	    	pan.add(BoiteRetour,  BorderLayout.SOUTH);

	    }
	    
	}
	
	/*Classe du Listener pour le bouton qui permet l'affichage de la liste tri�e des adh�rents */
	class BoutonLivresAdhListener implements ActionListener
	{
		
		String[][] data ;
	    String  Titre[] = {"Nom", "Code"};
	    
	    JTable ListeLivresAdh= new JTable();
	    GridLayout NewLayout = new GridLayout(2,1);
	    
	    Bouton Retour = new Bouton("Retour");
	    
	    //Red�finition de la m�thode actionPerformed()
	    public void actionPerformed(ActionEvent arg0) 
	    {
	    	effacerConteneur();
	    	BoiteRetour.removeAll();
	    	

	    	InitialiserLayoutGeneral(40,350);
	    	pan.setLayout(General);
	    	BoiteRetour.setOpaque(false);
	    	
	    	NewLayout.setVgap(50);;
	    	container.setLayout(NewLayout);
	    	
	    	
	    	data = Donnee.ListeAdhLivre();
	    	
	    	UneditableTableModel DTM = new UneditableTableModel(data,Titre);
	    	ListeLivresAdh.setModel(DTM);
	    	ListeLivresAdh.setRowSelectionAllowed(false);
	    	
	    		
	    	Retour.setFocusPainted(false);
	    	Retour.setFont(new Font("Arial", Font.BOLD, 20));
	    	
	    	Retour.addActionListener(new ActionListener(){
		    	  public void actionPerformed(ActionEvent event){
		    		  InterfaceMenuServ();
		    	  }
		    	});
	    	
	    	
	    	afficherListe(ListeLivresAdh,container);
	    	
	    	
	    	BoiteRetour.add(Retour);
	    	pan.add(BoiteRetour,  BorderLayout.SOUTH);

	    }
	    
	}
	
	/* Classe du Listener permettant d'enregistrer l'emprunt d'un livre par un lecteur */
	class BoutonRechercherListener implements ActionListener
	{
		private Bouton TerminerAdh = new Bouton("Terminer");
		private Bouton TerminerLivre = new Bouton("Terminer");
		private Bouton Retour = new Bouton("Retour");
		private JTextField TexteLivre = new JTextField("Rechercher");
		private JTextField TexteAdh= new JTextField("Rechercher");

		
		private Object[][] data;
		private String[] TitreAdh = {"Nom","Pr�nom","Adresse","Mail","Profession"};
		private String[] TitreLivre = {"Titre","Auteur","Code","Exemplaires","Disponibles"};
		private JTable ListeFinale= new JTable();
		
		GridLayout LayoutChamps = new GridLayout(3,1);
		JPanel BoiteAdh = new JPanel();
		JPanel BoiteLivre = new JPanel();
		
	    public void actionPerformed(ActionEvent arg0) 
	    {
	    	/* On efface */
	    	effacerConteneur();
	    	BoiteRetour.removeAll();
	    	
	    	/* Initialisation des layouts */
	    	InitialiserLayoutGeneral(200,200);

	    	
	    	GridLayout AffichageChamps= new GridLayout(1,2);
	    	AffichageChamps.setHgap(120);
	    	container.setLayout(AffichageChamps);
	    	
	    	LayoutChamps.setVgap(150);
	    	
	    	BoiteAdh.setLayout(LayoutChamps);
	    	BoiteAdh.setOpaque(false);
	    	
	    	BoiteLivre.setLayout(LayoutChamps);
	    	BoiteLivre.setOpaque(false);
	    	
	    	
	    	
	    	
	    	/* Traitement de texte */
	    	Label.setText("Entrez le Nom du lecteur OU le Titre du livre :");
	    	Label.setFont(new Font("Arial", Font.BOLD, 25));
	    	 	
	    	/*Personalisation des champs avec une classe anonyme */
	    	InitialiserChamps(TexteLivre,new FocusAdapter() {
	    	    public void focusGained(FocusEvent e) 
	    	    {
	    	    	TexteLivre.setText("");
	    	    }
	    	 });
	    	
	    	InitialiserChamps(TexteAdh,new FocusAdapter() {
	    	    public void focusGained(FocusEvent e) 
	    	    {
	    	    	TexteAdh.setText("");
	    	    }
	    	 });
	    	
	    	TerminerAdh.setFont(new Font("Arial", Font.BOLD,18));
	    	TerminerLivre.setFont(new Font("Arial", Font.BOLD,18));
	    	
	    	InitialiserBouton(TerminerAdh, new ActionListener(){
	    		public void actionPerformed(ActionEvent event){
	    			 General.setVgap(50);
		    		 data =Donnee.rechercheAdh(TexteAdh.getText());
		    		 UneditableTableModel DTM = new UneditableTableModel(data,TitreAdh);
		    		 ListeFinale.setModel(DTM);
		    		 ListeFinale.setRowSelectionAllowed(false);
		    		   
		    		  
		    		effacerConteneur();
	  				Label.setText("Voici les r�sultats obtenus :");
	  				Label.setFont(new Font("Arial", Font.BOLD, 30));
	  				pan.add(Label, BorderLayout.NORTH);
	  				
	  				afficherListe(ListeFinale,container);
	  				
	  				InitialiserBouton(Retour, new Menu1Listener());
	  				BoiteRetour.removeAll();
	  				BoiteRetour.add(Retour);
	  				pan.add(BoiteRetour, BorderLayout.SOUTH);
	  				

		    	  }
	    	});
	    	
	    	InitialiserBouton(TerminerLivre, new ActionListener(){
	    		public void actionPerformed(ActionEvent event){
	    			 General.setVgap(50);
		    		 data =Donnee.rechercheLivre(TexteLivre.getText());
		    		 UneditableTableModel DTM = new UneditableTableModel(data,TitreLivre);
		    		 ListeFinale.setModel(DTM);
		    		 ListeFinale.setRowSelectionAllowed(false);
		    		 ListeFinale.getColumnModel().getColumn(0).setPreferredWidth(150);
		    		   
		    		  
		    		effacerConteneur();
	  				Label.setText("Voici les r�sultats obtenus :");
	  				Label.setFont(new Font("Arial", Font.BOLD, 30));
	  				pan.add(Label, BorderLayout.NORTH);
	  				
	  				afficherListe(ListeFinale,container);
	  				
	  				InitialiserBouton(Retour, new Menu1Listener());
	  				BoiteRetour.removeAll();
	  				BoiteRetour.add(Retour);
	  				pan.add(BoiteRetour, BorderLayout.SOUTH);
	  				

		    	  }
	    	});
	    	
	    	
	    	BoiteAdh.add(InitialiserLabel("Adh�rent :",new Font("Arial",Font.BOLD,25), Color.WHITE) );
	    	BoiteAdh.add(TexteAdh);
	    	BoiteAdh.add(TerminerAdh);
	    	
	    	BoiteLivre.add(InitialiserLabel("Livre :",new Font("Arial",Font.BOLD,25), Color.WHITE) );
	    	BoiteLivre.add(TexteLivre);
	    	BoiteLivre.add(TerminerLivre);
	    	
	    	
	    	/* On imbrique le tout */
	    	container.add(BoiteAdh);
	    	container.add(BoiteLivre);

	    
	    	
	    	pan.add(Label, BorderLayout.NORTH);

	      	setContentPane(pan);
	    	repaint();
	   	}
	   }
	
	/* Listener correspondant au bouton du second menu */
	class Menu2Listener implements ActionListener
	{
	    //Red�finition de la m�thode actionPerformed()
	    public void actionPerformed(ActionEvent arg0) 
	    {	
	    	// Initialisation du choix pour le menu d'interface
	    	InterfaceMenuInt();    	
	    }
	 }

	
	
	/*-----------------------------------------* INTERFACE DU 2nd MENU	*-----------------------------------------*/
	
	@SuppressWarnings("deprecation")
	public void InterfaceMenuInt()
	{
		 Bouton AjouterAdh = new Bouton("Ajouter un adh�rent");
		 Bouton AjouterLivre = new Bouton("Ajouter un livre");
		 Bouton SupprimerAdh = new Bouton("Supprimer un adh�rent");
		 Bouton SupprimerLivre = new Bouton("Supprimer un livre");
		 Bouton EnregistrerEmp = new Bouton("Enregistrer un emprunt");
		 Bouton EnregistrerRetour = new Bouton("Enregistrer un retour");
		 
				
		// On commence par effacer les 2 conteneurs 
    	effacertout();
    	BoiteRetour.removeAll();
    	
    	// Puis on met en place les nouveaux Layouts qui nous int�ressent
    	GridLayout NewMenu = new GridLayout(6,1);
		NewMenu.setVgap(60);
		
		container.setLayout(NewMenu);
		BoiteRetour.setOpaque(false);
		
		General = new BorderLayout();
    	InitialiserLayoutGeneral(90,475);
    	pan.setLayout(General);
    	
    	// On modifie le label qui sert d'Indication a l'utilisateur
    	Label = InitialiserLabel((String.valueOf(new Date().getDay()+12) + "/" + String.valueOf(1+new Date().getMonth()) +"/"+ (new Date().getYear()+1900)),Police,Color.WHITE);
 	  

		
		//Modification du design des boutons puis ndication des Listener qui les observent
    	InitialiserBouton(AjouterAdh,new BoutonAjoutAdhListener());
    	InitialiserBouton(AjouterLivre,new BoutonAjoutLivreListener());
    	InitialiserBouton(SupprimerAdh,new BoutonSupprimerAdhListener());
    	InitialiserBouton(EnregistrerEmp, new BoutonEnregistrerEmpruntListener());
    	InitialiserBouton(SupprimerLivre, new BoutonSupprimerLivreListener());
    	InitialiserBouton(EnregistrerRetour, new BoutonEnregistrerRetourListener());
    	
    	RetourMenuDepart.addActionListener(new ActionListener(){
	    	  public void actionPerformed(ActionEvent event){
	    		  initChoix();
	    	  }
	    	});

		// On imbrique les conteneurs � leur place
		
		imbriquerLabelNord(Label);
		
		container.add(AjouterAdh);
		container.add(AjouterLivre);
		container.add(SupprimerAdh);
		container.add(SupprimerLivre);
		container.add(EnregistrerEmp);
		container.add(EnregistrerRetour);
		
		BoiteRetour.add(RetourMenuDepart);
		
		pan.add(BoiteRetour,BorderLayout.SOUTH);
		pan.add(container);
		
		// ON met � jour l'affichage
    	setContentPane(pan);
    	repaint();
	}
	
	
					//* LISTENERS POUR MENU D'INTERFACE *//
	
	/*Classe du Listener pour le bouton qui permet l'ajout d'un adh�rent */
	class BoutonAjoutAdhListener implements ActionListener
	{
		
		private Bouton Terminer = new Bouton("Terminer");
		private JTextField Nom = new JTextField("Nom");
		private JTextField Prenom = new JTextField("Pr�nom");
		private JTextField Adresse = new JTextField("Adresse");
		private JTextField Mail = new JTextField("Mail");
		private JTextField Profession = new JTextField("Profession");
		private Bouton Retour = new Bouton("Retour");
		
		private GridLayout LayoutChamp = new GridLayout(1,2);
		
		private JPanel BoiteNom = new JPanel();
		private JPanel BoitePrenom = new JPanel();
		private JPanel BoiteAdresse = new JPanel();
		private JPanel BoiteMail = new JPanel();
		private JPanel BoiteProfession = new JPanel();
		private JPanel BoiteTerminer = new JPanel();
		

		
	    //Red�finition de la m�thode actionPerformed()
	    public void actionPerformed(ActionEvent arg0) 
	    {
	    	/* On efface */
	    	effacerConteneur();
	    	BoiteRetour.removeAll();
	    	pan.remove(BoiteRetour);
	    	
	    	 /* initialisation du layout g�n�ral */   	
	    	InitialiserLayoutGeneral(225,220);
	    	
	    	pan.add(new JLabel(), BorderLayout.NORTH);
	    	
	    	/* Mise en place du layout du panneau conenant les champs */
	    	GridLayout AffichageChamps= new GridLayout(3,2);
	    	AffichageChamps.setVgap(125);
	    	
	   
	    	
	    	BoiteNom.setLayout(LayoutChamp);
	    	BoiteNom.setOpaque(false);
	    	
	    	BoitePrenom.setLayout(LayoutChamp);
	    	BoitePrenom.setOpaque(false);
	    	
	    	BoiteProfession.setLayout(LayoutChamp);
	    	BoiteProfession.setOpaque(false);
	    	
	    	BoiteAdresse.setLayout(LayoutChamp);
	    	BoiteAdresse.setOpaque(false);
	    	
	    	BoiteMail.setLayout(LayoutChamp);
	    	BoiteMail.setOpaque(false);
	    	
	    	BoiteTerminer.setLayout(LayoutChamp);
	    	BoiteTerminer.setOpaque(false);
	    	
	    	container.setLayout(AffichageChamps);
	    	
	    	/* traitement du label d'ent�te*/
	    	Label.setText("Ajouter un Adh�rent :");
	    	Label.setFont(new Font("Arial", Font.BOLD, 25));
	    	
	    	
	    	/* Personalisation des champs � travers des classes anonymes */
	    	InitialiserChamps(Nom,new FocusAdapter() {public void focusGained(FocusEvent e) 
	    		{
	    	    	Nom.setText("");
	    	    }
	    	});
	    	
	    	InitialiserChamps(Prenom,new FocusAdapter() {public void focusGained(FocusEvent e) 
	    		{
	    	    	Prenom.setText("");
	    	    }
	    	});
	    	
	    	InitialiserChamps(Mail,new FocusAdapter() {public void focusGained(FocusEvent e) 
	    		{
	    	    	Mail.setText("");
	    	    }
	    	});
	    	
	    	InitialiserChamps(Adresse,new FocusAdapter() {public void focusGained(FocusEvent e) 
	    		{
	    	    	Adresse.setText("");
	    	    }
	    	});
		    	
	    	InitialiserChamps(Profession,new FocusAdapter() {public void focusGained(FocusEvent e) 
	    		{
	    	    	Profession.setText("");
	    	    }
	    	});
		    	
	    	
	    	Terminer.setFont(new Font("Arial", Font.BOLD,18));
	    	Terminer.addActionListener(new ActionListener(){
	    	  public void actionPerformed(ActionEvent event){
	    		  Donnee.addAdherent(Nom.getText(), Prenom.getText(), Adresse.getText(), Mail.getText(),Profession.getText() );
	    		  
	    		effacerConteneur();
  				Label.setText("Ajout r�ussi");
  				Label.setFont(Police);
  				pan.add(Label);
  				
  				InitialiserBouton(Retour, new Menu2Listener());
  				BoiteRetour.removeAll();
  				BoiteRetour.add(Retour);
  				pan.add(BoiteRetour, BorderLayout.SOUTH);
  				

	    	  }
	    	});
	    	
	    	/* On imbrique tout ensemble */
	    	JLabel LabelNom= InitialiserLabel("Nom : ", new Font("Arial",Font.BOLD,18),Color.WHITE);
	    	LabelNom.setHorizontalAlignment(JLabel.RIGHT);
	    	BoiteNom.add(LabelNom);
	    	BoiteNom.add(Nom);
	    	container.add(BoiteNom);
	    	
	    	JLabel LabelPrenom= InitialiserLabel("Pr�nom : ", new Font("Arial",Font.BOLD,18),Color.WHITE);
	    	LabelPrenom.setHorizontalAlignment(JLabel.RIGHT);
	    	BoitePrenom.add(LabelPrenom);
	    	BoitePrenom.add(Prenom);
	    	container.add(BoitePrenom);
	    	
	    	JLabel LabelAdresse= InitialiserLabel("Adresse : ", new Font("Arial",Font.BOLD,18),Color.WHITE);
	    	LabelAdresse.setHorizontalAlignment(JLabel.RIGHT);
	    	BoiteAdresse.add(LabelAdresse);
	    	BoiteAdresse.add(Adresse);
	    	container.add(BoiteAdresse);

	    	JLabel LabelMail= InitialiserLabel("Mail : ", new Font("Arial",Font.BOLD,18),Color.WHITE);
	    	LabelMail.setHorizontalAlignment(JLabel.RIGHT);
	    	BoiteMail.add(LabelMail);
	    	BoiteMail.add(Mail);
	    	container.add(BoiteMail);
	    	
	    	JLabel LabelProfession= InitialiserLabel("Profession : ", new Font("Arial",Font.BOLD,18),Color.WHITE);
	    	LabelProfession.setHorizontalAlignment(JLabel.RIGHT);
	    	BoiteProfession.add(LabelProfession);
	    	BoiteProfession.add(Profession);
	    	container.add(BoiteProfession);
	    	
	    	BoiteTerminer.add(new JLabel());
	    	BoiteTerminer.add(Terminer);
	    	container.add(BoiteTerminer);
	    	
	    	pan.add(Label, BorderLayout.NORTH);
	    	pan.add(new JLabel(), BorderLayout.SOUTH);

	    	repaint();		
	   	}
	   }
	
	/* Classe Qui correspond au listener du bouton permettant l'ajout d'un Livre */
	class BoutonAjoutLivreListener implements ActionListener
	{
		private Bouton Terminer = new Bouton("Terminer");
		private JTextField Titre = new JTextField("Titre");
		private JTextField Auteur = new JTextField("Auteur");
		private JFormattedTextField Code = new JFormattedTextField(createFormatter("UUU-###"));
		private JFormattedTextField NbExempl = new JFormattedTextField();
		private Bouton Retour = new Bouton("Retour");
		
		private GridLayout LayoutChamp = new GridLayout(1,2);
		
		private JPanel BoiteTitre = new JPanel();
		private JPanel BoiteAuteur = new JPanel();
		private JPanel BoiteCode= new JPanel();
		private JPanel BoiteNb = new JPanel();
		private JPanel BoiteTerminer = new JPanel();
		

	    public void actionPerformed(ActionEvent arg0) 
	    {
	    	/* on efface */
	    	effacerConteneur();
	    	BoiteRetour.remove(RetourMenuDepart);
	    	
	    	/* On initialise les Layouts  */
	    	InitialiserLayoutGeneral(200,250);
	    	pan.add(new JLabel(), BorderLayout.NORTH);
	    	
	    	GridLayout AffichageChamps= new GridLayout(3,2);
	    	AffichageChamps.setVgap(150);
	    	AffichageChamps.setHgap(75);
	    	container.setLayout(AffichageChamps);
	    	
	    	BoiteTitre.setLayout(LayoutChamp);
	    	BoiteTitre.setOpaque(false);
	    	
	    	BoiteAuteur.setLayout(LayoutChamp);
	    	BoiteAuteur.setOpaque(false);
	    	
	    	BoiteCode.setLayout(LayoutChamp);
	    	BoiteCode.setOpaque(false);
	    	
	    	BoiteNb.setLayout(LayoutChamp);
	    	BoiteNb.setOpaque(false);
	    	
	    	BoiteTerminer.setLayout(LayoutChamp);
	    	BoiteTerminer.setOpaque(false);
	    	
	    	/* Traitement sur el label d'ent�te */
	    	Label.setText("Ajouter un Adh�rent :");
	    	Label.setFont(new Font("Arial", Font.BOLD, 25));
	    	 	
	    	//Personalisation des champs
	    	InitialiserChamps(Titre,new FocusAdapter() {
	    	    public void focusGained(FocusEvent e) 
	    	    {
	    	    	Titre.setText("");
	    	    }
	    	 });
	    	
	    	InitialiserChamps(Auteur,new FocusAdapter() {
	    	    public void focusGained(FocusEvent e) 
	    	    {
	    	    	Auteur.setText("");
	    	    }
	    	 });
	    	
	    	InitialiserChamps(Code,new FocusAdapter() {
	    	    public void focusGained(FocusEvent e) 
	    	    {
	    	    	Code.setText("");
	    	    }
	    	 });
	    	
	    	InitialiserChamps(NbExempl,new FocusAdapter() {
	    	    public void focusGained(FocusEvent e) 
	    	    {
	    	    	NbExempl.setText("");
	    	    }
	    	 });
	
	    	Terminer.setFont(new Font("Arial", Font.BOLD,18));
	    	// Classe anonyme locale pour notre bouton retour
	    	Terminer.addActionListener(new ActionListener(){
		    	  public void actionPerformed(ActionEvent event){
		    		  Donnee.addLivre(Titre.getText(), Auteur.getText(), Code.getText(), NbExempl.getText(),NbExempl.getText() );
		    		  
		    		effacerConteneur();
	  				Label.setText("Ajout r�ussi");
	  				Label.setFont(Police);
	  				pan.add(Label);
	  				
	  				InitialiserBouton(Retour, new Menu2Listener());
	  				BoiteRetour.removeAll();
	  				BoiteRetour.add(Retour);
	  				pan.add(BoiteRetour, BorderLayout.SOUTH);
	  				

		    	  }
		    	});
	    	
	    	/* Ajout des Labels devant les champs et imbrication g�n�rale */ 
	    	JLabel LabelTitre= InitialiserLabel("Titre : ", new Font("Arial",Font.BOLD,18),Color.WHITE);
	    	LabelTitre.setHorizontalAlignment(JLabel.RIGHT);
	    	BoiteTitre.add(LabelTitre);
	    	BoiteTitre.add(Titre);
	    	container.add(BoiteTitre);
	    	
	    	JLabel LabelAuteur= InitialiserLabel("Auteur : ", new Font("Arial",Font.BOLD,18),Color.WHITE);
	    	LabelAuteur.setHorizontalAlignment(JLabel.RIGHT);
	    	BoiteAuteur.add(LabelAuteur);
	    	BoiteAuteur.add(Auteur);
	    	container.add(BoiteAuteur);

	    	JLabel LabelCode= InitialiserLabel("Code : ", new Font("Arial",Font.BOLD,18),Color.WHITE);
	    	LabelCode.setHorizontalAlignment(JLabel.RIGHT);
	    	BoiteCode.add(LabelCode);
	    	BoiteCode.add(Code);
	    	container.add(BoiteCode);
	    	
	    	JLabel LabelExemplaires= InitialiserLabel("Exemplaires : ", new Font("Arial",Font.BOLD,18),Color.WHITE);
	    	LabelExemplaires.setHorizontalAlignment(JLabel.RIGHT);
	    	BoiteNb.add(LabelExemplaires);
	    	BoiteNb.add(NbExempl);
	    	container.add(BoiteNb);
	    	
	    	BoiteTerminer.add(new JLabel());
	    	BoiteTerminer.add(Terminer);
	    	
	    	container.add(BoiteTitre);
	    	container.add(BoiteAuteur);
	    	container.add(BoiteCode);
	    	container.add(BoiteNb);
	    	container.add(BoiteTerminer);
	    	
	    	
	    	pan.add(Label, BorderLayout.NORTH);

	      	setContentPane(pan);
	    	repaint();
	   	}
	   }
	
	/* Classe qui correspond au listener du bouton permettant de supprimer un adh�rent en fonction de son nom */
	class BoutonSupprimerAdhListener implements ActionListener
	{
		private Bouton Terminer = new Bouton("Terminer");
		private JTextField Nom = new JTextField("Nom");
		Bouton RetourFail = new Bouton("Retour");
		
	    public void actionPerformed(ActionEvent arg0) 
	    {
	    	/* On efface */
	    	effacerConteneur();
	    	BoiteRetour.removeAll();
	    	
	    	/* On initialise les layouts de nos panneaux */
	    	InitialiserLayoutGeneral(200,475);
	    	pan.add(new JLabel(), BorderLayout.NORTH);
	    	
	    	GridLayout AffichageChamps= new GridLayout(3,2);
	    	AffichageChamps.setVgap(150);
	    	AffichageChamps.setHgap(75);
	    	container.setLayout(AffichageChamps);
	    	
	    	/* Traitement de texte */
	    	Label.setText("Supprimer un Adh�rent :");
	    	Label.setFont(new Font("Arial", Font.BOLD, 25));
	    	 	
	    	/* Personalisation des champs avec des classes anonymes */
	    	InitialiserChamps(Nom,new FocusAdapter() {
	    	    public void focusGained(FocusEvent e) 
	    	    {
	    	    	Nom.setText("");
	    	    }
	    	 });
	
	    	Terminer.setFont(new Font("Arial", Font.BOLD,18));
	    	Terminer.addActionListener(new ActionListener(){
	    	  public void actionPerformed(ActionEvent event){
	    		switch( Donnee.removeAdherent(Nom.getText()) )	/* On regarde le r�sultat du retrait*/
	    		{
	    			case 0:		/* Si  est renvoy�, la suppression s'est bien effectu�e */
	    			{
	    				
	    				effacerConteneur();
	    				General.setHgap(100);
	    				Label.setFont(new Font("Arial",Font.BOLD,30));
	    				Label.setText("Suppression r�ussie");
	    				pan.add(Label);
	    				
	    				InitialiserBouton(RetourFail, new Menu2Listener());
	    				BoiteRetour.removeAll();
	    				BoiteRetour.add(RetourFail);
	    				pan.add(BoiteRetour, BorderLayout.SOUTH);
	    				
	    
	    				break;
	    			}
	    			
	    			case -1:		/* Si -1 est renvoy�, l'adh�rent n'est pas reconnu */
	    			{
	    				
	    				/* On va donc tout effacer, expliquer que cet adh�rent n'est pas r�pertori� */
	    				effacerConteneur();
	    				
	    				Label.setText("Cet adh�rent n'existe pas");
	    				General.setHgap(100);
	    				Label.setFont(new Font("Arial",Font.BOLD,30));
	    				pan.add(Label);
	    				
	    				/* Et on donne l'acc�s � l'utilisateur � la fen�tre pr�c�dente pour qu'il rentre de nouveau le nom"*/
	    				InitialiserBouton(RetourFail, new Menu2Listener());
	    				BoiteRetour.removeAll();
	    				BoiteRetour.add(RetourFail);
	    				pan.add(BoiteRetour, BorderLayout.SOUTH);
	    				
	    				break;
	    			}
	    		}
	    		  
	    	  }
	    	});
	    	
	    	/* On imbrique */
	    	
	    	
	    	container.add(Nom);
	    	container.add(Terminer);
	    	
	    	pan.add(Label, BorderLayout.NORTH);

	      	setContentPane(pan);
	    	repaint();
	   	}
}
	/* Classe qui correspond au listener du bouton permettant de supprimer un un livre en fonction de son titre */
	class BoutonSupprimerLivreListener implements ActionListener
	{
		private Bouton Terminer = new Bouton("Terminer");
		private JTextField Titre = new JTextField("Titre");
		private Bouton RetourFail = new Bouton("Retour");

	    public void actionPerformed(ActionEvent arg0) 
	    {
	    	/* On efface */
	    	effacerConteneur();
	    	BoiteRetour.removeAll();
	    	
	    	/*Initialisation des layouts */
	    	InitialiserLayoutGeneral(200,475);
	    	pan.add(new JLabel(), BorderLayout.NORTH);
	    	
	    	GridLayout AffichageChamps= new GridLayout(3,2);
	    	AffichageChamps.setVgap(150);
	    	AffichageChamps.setHgap(75);
	    	container.setLayout(AffichageChamps);
	    	
	    	/* Traitement de texte */
	    	Label.setText("Supprimer un Livre :");
	    	Label.setFont(new Font("Arial", Font.BOLD, 25));
	    	 	
	    	/* Personalisation des champs avec une classe anonyme */
	    	InitialiserChamps(Titre,new FocusAdapter() {
	    	    public void focusGained(FocusEvent e) 
	    	    {
	    	    	Titre.setText("");
	    	    }
	    	 });

	    	Terminer.setFont(new Font("Arial", Font.BOLD,18));
	    	// Classe anonyme locale pour notre bouton retour
	    	Terminer.addActionListener(new ActionListener(){
	    	  public void actionPerformed(ActionEvent event){
	    		  General.setHgap(100);
	    		  Label.setFont(new Font("Arial",Font.BOLD,30));
	    		  switch( Donnee.removeLivre(Titre.getText()) ) /* On regarde le r�sultat du retrait du livre */
	    		  {
	    			case 0:	/* Si 0 est renvoy�, le retrait a �t� effectu� avec succ�s ==> renvoi sur le menu */
	    			{
	    				effacerConteneur();
	    				Label.setText("Suppression r�ussie");
	    				pan.add(Label);
	    				
	    				InitialiserBouton(RetourFail, new Menu2Listener());
	    				BoiteRetour.removeAll();
	    				BoiteRetour.add(RetourFail);
	    				pan.add(BoiteRetour, BorderLayout.SOUTH);
	    				
	    				break;
	    			}
	    			
	    			case -1:	/* Si -1 est renvoy�, echec et on affiche la raison, puis on demande � l'utilisateur de recommencer la saisie */
	    			{
	    				effacerConteneur();
	    				Label.setText("Ce  livre n'est pas r�pertori�");
	    				pan.add(Label);
	    				
	    				InitialiserBouton(RetourFail, new Menu2Listener());
	    				BoiteRetour.removeAll();
	    				BoiteRetour.add(RetourFail);
	    				
	    				break;
	    			}
	    			
	    			case -2:	/* Si -1 est renvoy�, echec et on affiche la raison, puis on demande � l'utilisateur de recommencer la saisie */
	    			{
	    				effacerConteneur();
	    				Label.setText("Des livres sont emprunt�s");
	    				pan.add(Label);
	    				
	    				InitialiserBouton(RetourFail, new Menu2Listener());
	    				BoiteRetour.removeAll();
	    				BoiteRetour.add(RetourFail);
	    				
	    				break;
	    			}
	    		  }
	    		  
	    	  }
	    	});
	    	
	    	/* Imbrication g�n�rale */
	    	container.add(Titre);
	    	container.add(Terminer);
	    	
	    	pan.add(Label, BorderLayout.NORTH);

	      	setContentPane(pan);
	    	repaint();
	   	}
}
	/* Classe du Listener permettant d'enregistrer l'emprunt d'un livre par un lecteur */
	class BoutonEnregistrerEmpruntListener implements ActionListener
	{
		private Bouton Terminer = new Bouton("Terminer");
		private JTextField Titre = new JTextField("Titre");
		private JTextField Nom = new JTextField("Nom");
		private Bouton RetourFail = new Bouton("Retour");
		
	    public void actionPerformed(ActionEvent arg0) 
	    {
	    	/* On efface */
	    	effacerConteneur();
	    	BoiteRetour.removeAll();
	    	
	    	/* Initialisation des layouts */
	    	InitialiserLayoutGeneral(200,475);
	    	pan.add(new JLabel(), BorderLayout.NORTH);
	    	
	    	GridLayout AffichageChamps= new GridLayout(3,2);
	    	AffichageChamps.setVgap(150);
	    	AffichageChamps.setHgap(75);
	    	container.setLayout(AffichageChamps);
	    	
	    	/* Traitement de texte */
	    	Label.setText("Entrez le Titre du livre et le nom du lecteur :");
	    	Label.setFont(new Font("Arial", Font.BOLD, 25));
	    	 	
	    	/*Personalisation des champs avec une classe anonyme */
	    	InitialiserChamps(Titre,new FocusAdapter() {
	    	    public void focusGained(FocusEvent e) 
	    	    {
	    	    	Titre.setText("");
	    	    }
	    	 });
	    	
	    	InitialiserChamps(Nom,new FocusAdapter() {
	    	    public void focusGained(FocusEvent e) 
	    	    {
	    	    	Nom.setText("");
	    	    }
	    	 });
	    	
	    	Terminer.setFont(new Font("Arial", Font.BOLD,18));
	    	// Classe anonyme locale pour notre bouton retour
	    	Terminer.addActionListener(new ActionListener(){
	    	  public void actionPerformed(ActionEvent event){
	    		  	effacerConteneur();
	    		  	General.setHgap(100);
	    		  	Label.setFont(new Font("Arial",Font.BOLD,30));
	    		  	switch(Donnee.ajoutEmprunt(Titre.getText(), Nom.getText())) /* On regarde le r�sultat de la fonction d'emprunt dans notre base */
	    		  	{
		    		  	case 0:			/* Si  0 est retour�, tout s'estbien d�roul� ==> renvoi au menu */
		    		  	{
		    		  		Label.setText("Emprunt effectu�");
		    		  		pan.add(Label);
		    		  		
		    		  		InitialiserBouton(RetourFail, new Menu2Listener());
		    				BoiteRetour.removeAll();
		    				BoiteRetour.add(RetourFail);
		    		  		break;
		    		  	}
		    		  	
		    		  	case -1:	/* Si  -1 est retour�, probl�me : livre inexistant ==> utilisateur doit r�-entrer les donn�es du livre*/
		    		  	{
		    		  		
		    		  		Label.setText("Ce livre n'existe pas");
		    		  		pan.add(Label);
		    		  		
		    		  		InitialiserBouton(RetourFail, new Menu2Listener());
		    				BoiteRetour.removeAll();
		    				BoiteRetour.add(RetourFail);
		    		  	
		    		  		
		    		  		break;
		    		  		
		    		  	}
		    		  	
		    		  	case -2:	/* Si  -2 est retour�, probl�me : livre inedisponible ==> renvoi au menu */
		    		  	{
		    		  		
		    		  		Label.setText("Ce livre n'est actuellement indisponible");
		    		  		Label.setFont(Police);
		    		  		pan.add(Label);
		    		  		
		    		  		InitialiserBouton(RetourFail, new Menu2Listener());
		    				BoiteRetour.removeAll();
		    				BoiteRetour.add(RetourFail);
		    		  		break;
		    		  	}
		    		  	
		    		  	case 1:		/* Si 1 est retour�, probl�me : adh�rent non existant ==> renvoi � la demande de noms */
		    		  	{
		    		  		
		    		  		Label.setText("Cet Adh�rent n'existe pas");
		    		  		Label.setFont(Police);
		    		  		pan.add(Label);
		    		  		
		    		  		InitialiserBouton(RetourFail, new Menu2Listener());
		    				BoiteRetour.removeAll();
		    				BoiteRetour.add(RetourFail);
		    		  		break;
		    		  	}
		    		  	
		    		  	case 2:	/* Si 2 est retour�, probl�me : adh�rent a atteint la limite de livres ==> renvoi au menu */
		    		  	{
		    		  		Label.setText("Cet Adh�rent a atteint sa limite de livres");
		    		  		Label.setFont(Police);
		    		  		pan.add(Label);
		    		  		
		    		  		InitialiserBouton(RetourFail, new Menu2Listener());
		    				BoiteRetour.removeAll();
		    				BoiteRetour.add(RetourFail);
		    		  		break;
		    		  	}
	    		  	}
	    		  
	    		 
	    	  }
	    	});
	    	
	    	/* On imbrique le tout */
	    	container.add(Titre);
	    	container.add(Nom);

	    	container.add(Terminer);
	    	
	    	pan.add(Label, BorderLayout.NORTH);

	      	setContentPane(pan);
	    	repaint();
	   	}
	   }
	
	/* Classe du Listener permettant d'enregistrer l'emprunt d'un livre par un lecteur */
	class BoutonEnregistrerRetourListener implements ActionListener
	{
		private Bouton Terminer = new Bouton("Terminer");
		private JTextField Titre = new JTextField("Titre");
		private JTextField Nom = new JTextField("Nom");
		private Bouton RetourFail = new Bouton("Retour");
		
	    public void actionPerformed(ActionEvent arg0) 
	    {
	    	/* On efface */
	    	effacerConteneur();
	    	BoiteRetour.removeAll();
	    	
	    	/* Initialisation des layouts */
	    	InitialiserLayoutGeneral(200,475);
	    	pan.add(new JLabel(), BorderLayout.NORTH);
	    	
	    	GridLayout AffichageChamps= new GridLayout(3,2);
	    	AffichageChamps.setVgap(150);
	    	AffichageChamps.setHgap(75);
	    	container.setLayout(AffichageChamps);
	    	
	    	/* Traitement de texte */
	    	Label.setText("Entrez le Titre du livre et le nom du lecteur :");
	    	Label.setFont(new Font("Arial", Font.BOLD, 25));
	    	 	
	    	/*Personalisation des champs avec une classe anonyme */
	    	InitialiserChamps(Titre,new FocusAdapter() {
	    	    public void focusGained(FocusEvent e) 
	    	    {
	    	    	Titre.setText("");
	    	    }
	    	 });
	    	
	    	InitialiserChamps(Nom,new FocusAdapter() {
	    	    public void focusGained(FocusEvent e) 
	    	    {
	    	    	Nom.setText("");
	    	    }
	    	 });
	    	
	    	Terminer.setFont(new Font("Arial", Font.BOLD,18));
	    	// Classe anonyme locale pour notre bouton retour
	    	Terminer.addActionListener(new ActionListener(){
	    	  public void actionPerformed(ActionEvent event){
	    		  	effacerConteneur();
	    		  	General.setHgap(100);
	    		  	Label.setFont(new Font("Arial",Font.BOLD,30));
	    		  	switch(Donnee.rendreLivre(Titre.getText(), Nom.getText())) /* On regarde le r�sultat de la fonction d'emprunt dans notre base */
	    		  	{
		    		  	case 0:			/* Si  0 est retour�, tout s'estbien d�roul� ==> renvoi au menu */
		    		  	{
		    		  		Label.setText("Retour effectu�");
		    		  		pan.add(Label);
		    		  		
		    		  		InitialiserBouton(RetourFail, new Menu1Listener());
		    				BoiteRetour.removeAll();
		    				BoiteRetour.add(RetourFail);
		    		  	
		    		  		break;
		    		  	}
		    		  	
		    		  	case -1:	/* Si  -1 est retour�, probl�me : livre inexistant ==> utilisateur doit r�-entrer les donn�es du livre*/
		    		  	{
		    		  		
		    		  		Label.setText("Ce livre n'existe pas");
		    		  		pan.add(Label);
		    		  		
		    		  		InitialiserBouton(RetourFail, new Menu2Listener());
		    				BoiteRetour.removeAll();
		    				BoiteRetour.add(RetourFail);
		    		  	
		    		  		
		    		  		break;
		    		  		
		    		  	}
		    		  	
		    		  	case -2:	/* Si  -1 est retour�, probl�me : livre non ermprunt� ==> utilisateur doit r�-entrer les donn�es du livre*/
		    		  	{
		    		  		
		    		  		Label.setText("Ce livre n'est pas emprunt�");
		    		  		pan.add(Label);
		    		  		
		    		  		InitialiserBouton(RetourFail, new Menu2Listener());
		    				BoiteRetour.removeAll();
		    				BoiteRetour.add(RetourFail);
		    		  	
		    		  		
		    		  		break;
		    		  		
		    		  	}

		    		  	
		    		  	case 1:		/* Si 1 est retour�, probl�me : adh�rent non existant ==> renvoi � la demande de noms */
		    		  	{  		
		    		  		Label.setText("Cet Adh�rent n'existe pas");
		    		  		Label.setFont(Police);
		    		  		pan.add(Label);
		    		  		
		    		  		InitialiserBouton(RetourFail, new Menu2Listener());
		    				BoiteRetour.removeAll();
		    				BoiteRetour.add(RetourFail);
		    		  		break;
		    		  	}
	    		  	}
	    		  
	    		 
	    	  }
	    	});
	    	
	    	/* On imbrique le tout */
	    	container.add(Titre);
	    	container.add(Nom);

	    	container.add(Terminer);
	    	
	    	pan.add(Label, BorderLayout.NORTH);

	      	setContentPane(pan);
	    	repaint();
	   	}
	   }
	
	/* Classe correspondant au listener du bouton du premier bouton */
	class Menu1Listener implements ActionListener
	{
	    //Red�finition de la m�thode actionPerformed()
	    public void actionPerformed(ActionEvent arg0) 
	    {
	       	// Initialisation du choix pour le menu de service
	    	InterfaceMenuServ();

	    }
	 }
	
	
	
}


	