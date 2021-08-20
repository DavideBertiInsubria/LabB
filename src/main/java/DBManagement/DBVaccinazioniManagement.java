package DBManagement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.CentroVaccinale;
import common.TipologiaCentro;
import common.Vaccinato;
import common.Vaccino;
import common.Cittadino;
import common.ReportEventoAvverso;
import common.Segnalazione;

public class DBVaccinazioniManagement extends DBManager{

	public DBVaccinazioniManagement() throws SQLException {
		super("jdbc:postgresql://localhost/dblabb","postgres","test");
	}
	
	
	public void registraCentroVaccinale(CentroVaccinale centro) throws SQLException {
		String nome = centro.getNome();
		String indirizzo = centro.getIndirizzo();
		String tipologia = centro.getTipologia();
		query("INSERT INTO CentriVaccinali(Nome,Indirizzo,Tipologia) "
				+ "VALUES('"+nome+"','"+indirizzo+"','"+tipologia+"')");
	}
	

	public ArrayList<String> registraCittadino(Cittadino cittadino) throws SQLException {
		ArrayList<String > ritorno=new ArrayList<> ();
		String nome = cittadino.getNome();
		if(!checkCampi ("Nome",nome)) ritorno.add("Il nome inserito non coincide con quello inserito in fase di vaccinazione.");

		String cognome = cittadino.getCognome();
		if(!checkCampi ("Cognome",cognome)) ritorno.add("Il cognome inserito non coincide con quello inserito in fase di vaccinazione.");

		String email = cittadino.getEmail();
		if(checkCampiCit ("Email",email)) ritorno.add("L'email inserita e' gia' stata registrata.");

		String pwd = cittadino.getPassword();

		String cf = cittadino.getCF();
		if(!checkCampi ("CF",cf)) ritorno.add("Il codice fiscale inserito non coincide con quello inserito in fase di vaccinazione.");
		else if(checkCampiCit ("CF",cf)) ritorno.add("Il codice fiscale inserito e' gia' stato registrato.");

		String userId = cittadino.getUserID();
		if(checkCampiCit ("Nick",userId)) ritorno.add("Il nick inserito e' gia' stato registrato.");

		int idvacc= cittadino.getIDVaccino ();
		if(!checkCampi ("IDVaccinazione",String.valueOf (idvacc))) ritorno.add("L'id vaccinazione inserito non coincide con quello inserito in fase di vaccinazione.");
		else if(checkCampiCit ("IDVaccinazione",String.valueOf (idvacc))) ritorno.add("L'id vaccinazione inserito e' gia' stato registrato.");
		int idcentro;

		if(ritorno.size ()>0) return ritorno;
		ResultSet ids = query("SELECT IDCentro FROM Vaccinati WHERE CF='"+cf+"'");

		if(DBManager.ResultSetSize(ids) == 1) {
			idcentro = ids.getInt(1);
			
			cittadino.setIDCentro(idcentro);
			
			query("INSERT INTO CittadiniRegistrati(Nome,Cognome,Email,Password,IDVaccinazione,CF,IDCentro,Nick) "
					+ "VALUES('"+nome+"','"+cognome+"','"+email+"','"+pwd+"','"+idvacc+"','"+cf+"','"+idcentro+"','"+userId+"')");
		
		}
		return null;
	}

	public boolean checkCampi(String campo, String value) throws SQLException {
		if(campo.equals ("IDVaccinazione"))
		{
			ResultSet r = query("SELECT Nome "+
					"FROM Vaccinati WHERE "+campo+"='"+Integer.parseInt (value)+"'");
			return r.next ();
		}
		else {
			ResultSet r = query ("SELECT Nome " +
					"FROM Vaccinati WHERE " + campo + "='" + value + "'");
			return r.next ();
		}
	}

	public boolean checkCampiCit(String campo,String value) throws SQLException {
		if(campo.equals ("IDVaccinazione"))
		{
			ResultSet r = query("SELECT Nick "+
					"FROM Vaccinati WHERE "+campo+"='"+Integer.parseInt (value)+"'");
			return r.next();
		}
		else {
			ResultSet r = query ("SELECT Nick " +
					"FROM CittadiniRegistrati WHERE " + campo + "='" + value + "'");
			return r.next();
		}

	}

	public void registraVaccinato(Vaccinato vaccinato,String datasomm,Vaccino vaccino) throws SQLException {

		String nome = vaccinato.getNome ();
		String cognome = vaccinato.getCognome();
		String cf = vaccinato.getCF();
		String nomecentro = vaccinato.getCentro().getNome();
		int idcentro;
		
		ResultSet id = query("SELECT IDCentro FROM CentriVaccinali WHERE Nome='"+nomecentro+"'");
		
		if(DBManager.ResultSetSize(id) == 1) {
			idcentro = id.getInt(1);
			vaccinato.setIDCentro(idcentro);
			
			query("INSERT INTO "+
					"Vaccinati(IDCentro,NomeCentro,Nome,Cognome,CF,DataSomministrazione,VaccinoSomministrato) "+
					"VALUES("+idcentro+",'"+nomecentro+"','"+nome+"','"+cognome+"','"+cf+"','"+datasomm+"','"+vaccino+"')");
		
		}
	}
	public void registraVaccinato(Vaccinato vaccinato) throws SQLException {

		String nome = vaccinato.getNome ();
		String cognome = vaccinato.getCognome();
		String cf = vaccinato.getCF();
		String nomecentro = vaccinato.getCentro().getNome();
		int idcentro;

		ResultSet id = query("SELECT IDCentro FROM CentriVaccinali WHERE Nome='"+nomecentro+"'");

		if(DBManager.ResultSetSize(id) == 1) {
			idcentro = id.getInt(1);
			vaccinato.setIDCentro(idcentro);

			query("INSERT INTO "+
					"Vaccinati(IDCentro,NomeCentro,Nome,Cognome,CF,DataSomministrazione,VaccinoSomministrato) "+
					"VALUES("+idcentro+",'"+nomecentro+"','"+nome+"','"+cognome+"','"+cf+"','"+vaccinato.getDatasomm()+"','"+vaccinato.getVaccino()+"')");

		}
	}
	
	public ArrayList<ReportEventoAvverso> getReportSegnalazioni(int IDCentro) throws SQLException {
		ResultSet eventi = query("SELECT IDEvento,Evento FROM EventoAvverso");
		ArrayList<ReportEventoAvverso> report = new ArrayList<ReportEventoAvverso>(); 
		while(eventi.next()) {
			int idevento = eventi.getInt(1);
			String evento = eventi.getString(2);
			ResultSet nsegnalazioni = query("SELECT COUNT(*) FROM Segnalazione INNER JOIN Vaccinati USING(IDVaccinazione) "+
					"WHERE IDCentro="+IDCentro+" AND IDEvento="+idevento);
			nsegnalazioni.next();
			ResultSet media = query("SELECT AVG(Severita) FROM Segnalazione INNER JOIN Vaccinati USING(IDVaccinazione) "+
					"WHERE IDCentro="+IDCentro+" AND IDEvento="+idevento);
			media.next();

			report.add(new ReportEventoAvverso(evento,media.getFloat(1),nsegnalazioni.getInt(1)));
		}
		return report;
	}
	
	public boolean checkSegnalazione(int IDVaccinazione, String evento) throws SQLException{
		ResultSet r = query("SELECT IDEvento FROM EventoAvverso WHERE Evento='"+evento+"'");
		if(r.next()) {
			ResultSet r2 = query("SELECT IDSegnalazione FROM Segnalazione "+
								"WHERE IDVaccinazione="+IDVaccinazione+" AND IDEvento="+r.getInt(1));
			return r2.next();
		}
		return false;
	}

	/*public boolean checkUserId(String nick) throws SQLException {
		ResultSet r = query("SELECT nick "+
							"FROM CittadiniRegistrati WHERE nick='"+nick+"'");
		return r.next();
	}*/

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
		
		String s = "SELECT IDCentro,Nome,Indirizzo,Tipologia FROM CentriVaccinali WHERE Nome LIKE '"+nome+"' AND Indirizzo LIKE '"+comune+"'"+tipologia;
		centri = query(s);
		return centri;
		
	}

	
}
