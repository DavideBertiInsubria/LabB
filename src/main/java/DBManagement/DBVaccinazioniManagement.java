package DBManagement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.CentroVaccinale;
import common.TipologiaCentro;
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
				+ "VALUES("+nome+","+indirizzo+","+tipologia+")");
	}
	

	public void registraCittadino(Cittadino cittadino) throws SQLException {
		
		String nome = cittadino.getNome ();
		String cognome = cittadino.getCognome();
		String email = cittadino.getEmail();
		String pwd = cittadino.getPassword();
		String cf = cittadino.getCF();
		String idcentro = cittadino.getIDCentro();
		

		query("INSERT INTO CittadiniRegistrati(Nome,Cognome,Email,Password,CF,IDCentro)"
				+ "VALUES("+nome+","+cognome+","+email+","+pwd+","+cf+","+idcentro+")");
		
	}
	
	public ResultSet loginCittadino(Cittadino cittadino) throws SQLException {
		String email = cittadino.getEmail();
		String pwd = cittadino.getPassword();
		
		ResultSet l = query("SELECT * FROM CittadiniRegistrati WHERE Email='"+email+"' AND Password='"+pwd+"'");
		
		if(DBManager.ResultSetSize(l) > 0)
			return l;
		return null;
	}
	
	public ResultSet cercaCentroVaccinale(String nome,String comune,String tipologia) throws SQLException {
		
		if(nome.equals(""))
			nome="%";
		
		if(comune.equals(""))
			comune="%";
		else
			comune="%"+comune+"%";
		
		if(tipologia.equals(""))
			tipologia="";
		else
			tipologia="AND Tipologia ='"+tipologia+"'";
			
		ResultSet centri = null;
		
		centri = query("SELECT Nome,Indirizzo,Tipologia FROM CentriVaccinali WHERE Nome LIKE '"+nome+"' AND Indirizzo LIKE '"+comune+"' "+tipologia);
		
		return centri;
		
	}
	
	private void creaVaccinatiCentroVaccinale(String nome_centro) throws SQLException {
		
		query("CREATE TABLE "+nome_centro+
				"(IDVaccinazione serial primary key not null,"+
				"IDCentro int not null,"+
				"NomeCentro varchar(30) not null,"+
				"Nome varchar(30) not null,"+
				"Cognome varchar(30) not null,"+
				"CF char(16) not null,"+
				"DataSomministrazione date not null,"+
				"Vaccino VaccinoSomministrato not null,"+
				"foreign key (IDCentro) references CentriVaccinali(IDCentro));"
				);
	}
}
