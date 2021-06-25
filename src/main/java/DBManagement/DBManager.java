package DBManagement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBManager {

	private Connection conn=null;
	private String url,user,pwd;
	
	public DBManager(String url,String user, String pwd) throws SQLException{
        this.url = url;
        this.user = user;
        this.pwd = pwd;
        connect();
	}
	
	public void connect() throws SQLException{
		conn = DriverManager.getConnection(url, user, pwd);
	}
	
	public static int ResultSetSize(ResultSet r) throws SQLException {
		int size=0;
		r.last();
		size = r.getRow();
		r.first();
		return size;
	}
	
	public ResultSet query(String q)  throws SQLException{
		if(conn != null) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(q);
			return rs;
		}
		return null;
	}
	
	public void close()  throws SQLException {
		if(conn != null)
			conn.close();
	}
	
	
}
