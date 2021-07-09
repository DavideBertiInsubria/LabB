package DBManagement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.CentroVaccinale;
import common.TipologiaCentro;
import common.Vaccino;
import common.Cittadino;

public class DBVaccinazioniManagement extends DBManager{

	public DBVaccinazioniManagement() throws SQLException {
		super("jdbc:postgresql://localhost/dblabb","postgres","test");
	}
	
	
	public void registraCentroVaccinale(CentroVaccinale centro) throws SQLException {
		String nome = centro.getNome();
		String indirizzo = centro.getIndirizzo();
		String tipologia = centro.getTipologia();
		query("INSERT INTO CentriVaccinali(Nome,Indirizzo,Tipologia)"
				+ "VALUES('"+nome+"','"+indirizzo+"','"+tipologia+"')");
	}
	

	public void registraCittadino(Cittadino cittadino) throws SQLException {
		
		String nome = cittadino.getNome ();
		String cognome = cittadino.getCognome();
		String email = cittadino.getEmail();
		String pwd = cittadino.getPassword();
		String cf = cittadino.getCF();
		String idcentro = cittadino.getIDCentro();
		

		query("INSERT INTO CittadiniRegistrati(Nome,Cognome,Email,Password,CF,IDCentro)"
				+ "VALUES('"+nome+"','"+cognome+"','"+email+"','"+pwd+"','"+cf+"',"+idcentro+")");
		
	}
	
	public void registraVaccinato(Cittadino cittadino,String datasomm,Vaccino vaccino, int idvaccinazione) throws SQLException {
		
		String nome = cittadino.getNome ();
		String cognome = cittadino.getCognome();
		String email = cittadino.getEmail();
		String pwd = cittadino.getPassword();
		String cf = cittadino.getCF();
		String idcentro = cittadino.getIDCentro();
		
		ResultSet nomi = query("SELECT Nome FROM CentriVaccinale WHERE IDCentro="+idcentro);
		String nomecentro = nomi.getString(1);
		
		query("INSERT INTO"+
				"Vaccinati(IDCentro,NomeCentro,Nome,Cognome,CF,DataSomministrazione,VaccinoSomministrato,IDVaccinazione)"+
				"VALUES("+idcentro+",'"+nomecentro+"','"+nome+"','"+cognome+"','"+cf+"',"+datasomm+","+vaccino+","+idvaccinazione+")");
	}
	
	public ResultSet loginCittadino(Cittadino cittadino) throws SQLException {
		String email = cittadino.getEmail();
		String pwd = cittadino.getPassword();
		
		ResultSet l = query("SELECT * FROM CittadiniRegistrati WHERE Email='"+email+"' AND Password='"+pwd+"'");
		
		if(DBManager.ResultSetSize(l) == 1)
			return l;
		return null;
	}
	
	public ResultSet cercaCentroVaccinale(String nome,String comune,String tipologia) throws SQLException {
		
		if(nome.equals(""))
			nome="%";
		else
			nome="%"+nome+"%";
		
		if(comune.equals(""))
			comune="%";
		else
			comune="%"+comune+"%";
		
		if(tipologia.equals(""))
			tipologia=";";
		else
			tipologia=" AND Tipologia ='"+tipologia+"'";
			
		ResultSet centri = null;
		
		String s = "SELECT Nome,Indirizzo,Tipologia FROM CentriVaccinali WHERE Nome LIKE '"+nome+"' AND Indirizzo LIKE '"+comune+"'"+tipologia;
		centri = query(s);
		return centri;
		
	}
	
}
