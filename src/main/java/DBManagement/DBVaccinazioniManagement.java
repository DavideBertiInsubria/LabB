package DBManagement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.CentroVaccinale;
import common.TipologiaCentro;
import common.Vaccino;
import common.Cittadino;
import common.Segnalazione;

public class DBVaccinazioniManagement extends DBManager{

	public DBVaccinazioniManagement() throws SQLException {
		super("jdbc:postgresql://87.8.75.106/dblabb","postgres","test");
	}
	
	
	public void registraCentroVaccinale(CentroVaccinale centro) throws SQLException {
		String nome = centro.getNome();
		String indirizzo = centro.getIndirizzo();
		String tipologia = centro.getTipologia();
		query("INSERT INTO CentriVaccinali(Nome,Indirizzo,Tipologia) "
				+ "VALUES('"+nome+"','"+indirizzo+"','"+tipologia+"')");
	}
	

	public void registraCittadino(Cittadino cittadino) throws SQLException {
		
		String nome = cittadino.getNome();
		String cognome = cittadino.getCognome();
		String email = cittadino.getEmail();
		String pwd = cittadino.getPassword();
		String cf = cittadino.getCF();
		String userId = cittadino.getUserID();
		int idcentro;
		int idvacc;
		
		ResultSet ids = query("SELECT IDCentro,IDVaccinazione FROM Vaccinati WHERE CF='"+cf+"'");

		if(DBManager.ResultSetSize(ids) == 1) {
			idcentro = ids.getInt(1);
			idvacc = ids.getInt(2);
			
			cittadino.setIDCentro(idcentro);
			cittadino.setIDVaccino(idvacc);
			
			query("INSERT INTO CittadiniRegistrati(Nome,Cognome,Email,Password,IDVaccinazione,CF,IDCentro,Nick) "
					+ "VALUES('"+nome+"','"+cognome+"','"+email+"','"+pwd+"','"+idvacc+"','"+cf+"','"+idcentro+"','"+userId+"')");
		
		}
	}
	
	public void registraVaccinato(Cittadino cittadino,String datasomm,Vaccino vaccino) throws SQLException {
		
		String nome = cittadino.getNome ();
		String cognome = cittadino.getCognome();
		String cf = cittadino.getCF();
		String nomecentro = cittadino.getNomeCentro();
		int idcentro;
		
		ResultSet id = query("SELECT IDCentro FROM CentriVaccinali WHERE Nome='"+nomecentro+"'");
		
		if(DBManager.ResultSetSize(id) == 1) {
			idcentro = id.getInt(1);
			cittadino.setIDCentro(idcentro);
			
			query("INSERT INTO "+
					"Vaccinati(IDCentro,NomeCentro,Nome,Cognome,CF,DataSomministrazione,VaccinoSomministrato) "+
					"VALUES("+idcentro+",'"+nomecentro+"','"+nome+"','"+cognome+"','"+cf+"','"+datasomm+"','"+vaccino+"')");
		
		}
	}
	
	public void registraSegnalazione(Segnalazione segnalazione) throws SQLException {
		
		int idvaccinazione = segnalazione.getIDVaccinazione();
		String evento = segnalazione.getEvento();
		int severita = segnalazione.getSeverita();
		String nota = segnalazione.getNota();
		
		ResultSet id = query("SELECT IDEvento FROM EventoAvverso WHERE Evento='"+evento+"'");
		int idevento;
		
		if(DBManager.ResultSetSize(id) == 1) {
			idevento = id.getInt(1);
			query("INSERT INTO Segnalazione(IDVaccinazione,IDEvento,Severita,Nota)"+
			"VALUES("+idvaccinazione+","+idevento+","+severita+",'"+nota+"')");
		}
		
	}
	
	public ResultSet loginCittadino(String nick,String pwd) throws SQLException {

		ResultSet l = query("SELECT * FROM CittadiniRegistrati WHERE nick='"+nick+"' AND Password='"+pwd+"'");
		
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
			comune="%;%;%"+comune+"%;%;%";
		
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
