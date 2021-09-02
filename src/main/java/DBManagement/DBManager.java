package DBManagement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * La classe DBManager offre servizi di base per la connessione ed
 * esecuzione di query su un determinato database
 *
 * @author Berti Davide - 740665 VA
 * @author Ivanov Aleksandar Evgeniev - 742789 VA
 * @author Mazza Serghej - 740687 VA
 * @author Rizzi Silvio - 719638 VA
 */

public class DBManager {

	private Connection conn=null;
	private String url,user,pwd;
	
	/**
	 * @param url URL del database a cui collegarsi
	 * @param user nome utente
	 * @param pwd password
	 * @throws SQLException
	 */
	public DBManager(String url,String user, String pwd) throws SQLException{
        this.url = url;
        this.user = user;
        this.pwd = pwd;
        connect();
	}
	
	/**
	 * Metodo per la connessione al database con i parameteri specificati
	 * nel costruttore
	 * @throws SQLException
	 */
	public void connect() throws SQLException{
    	System.out.println("Classpath: "+System.getProperty("java.class.path").replace(':','\n'));
		conn = DriverManager.getConnection(url, user, pwd);
	}
	
	/**
	 * Metodo statico utile a determinare la lunghezza di un resultset
	 * @param r ResultSet di cui si vuole sapere la lunghezza
	 * @return
	 * @throws SQLException
	 */
	public static int ResultSetSize(ResultSet r) throws SQLException {
		int size=0;
		r.last();
		size = r.getRow();
		r.first();
		return size;
	}
	
	/**
	 * Metodo per eseguire una query sul database
	 * @param q Query da eseguire
	 * @return Ritorna un resultset oppure null se non c'è connessione al database
	 * @throws SQLException
	 */
	public ResultSet query(String q)  throws SQLException{
		if(conn != null) {
			System.out.println(q);
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(q);
			return rs;
		}
		return null;
	}
	
	/**
	 * Chiude la connessione al database, per riaprirla istanziare un nuovo oggetto
	 * della classe DBManager
	 * @throws SQLException
	 */
	public void close()  throws SQLException {
		if(conn != null)
			conn.close();
	}
	
	
}
