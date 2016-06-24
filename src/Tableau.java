import javax.swing.table.AbstractTableModel;

class Tableau extends AbstractTableModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5854649164907681564L;
	private Object[][] data;
    private String[] title;

    //Constructeur
    public Tableau(Object[][] data, String[] title){
      this.data = data;
      this.title = title;
    }

    //Retourne le nombre de colonnes
    public int getColumnCount() {
      return this.title.length;
    }

    //Retourne le nombre de lignes
    public int getRowCount() {
      return this.data.length;
    }

    //Retourne la valeur à l'emplacement spécifié
    public Object getValueAt(int row, int col) {
      return this.data[row][col];
    }            
  }