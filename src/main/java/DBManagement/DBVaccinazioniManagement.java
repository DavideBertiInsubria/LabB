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

/**
 * La classe DBVaccinazioniManagement mette a disposizione servizi specifici per il database dei centri vaccinali
 *
 * @author Berti Davide - 740665 VA
 * @author Ivanov Aleksandar Evgeniev - 742789 VA
 * @author Mazza Serghej - 740687 VA
 * @author Rizzi Silvio - 719638 VA
 * @see #registraCentroVaccinale(CentroVaccinale)
 * @see #registraCittadino(Cittadino)
 * @see #checkCampi(String, String)
 * @see #checkCampiCit(String, String)
 * @see #registraVaccinato(Vaccinato)
 * @see #registraVaccinato(Vaccinato, String, Vaccino)
 * @see #getReportSegnalazioni(int)
 * @see #checkSegnalazione(int, String)
 * @see #registraSegnalazione(Segnalazione)
 * @see #loginCittadino(String, String)
 * @see #cercaCentroVaccinale(String, String, String)
 */
public class DBVaccinazioniManagement extends DBManager{

	/**
	 * Costruttore per la connessione al database specificato
	 * @throws SQLException
	 */
	public DBVaccinazioniManagement() throws SQLException {
		super("jdbc:postgresql://localhost/dblabb","admin","admin");
	}
	
	
	/**
	 * Aggiunge un centro vaccinale nel database
	 * @param centro Centro vaccinali da registrare
	 * @throws SQLException
	 */
	public void registraCentroVaccinale(CentroVaccinale centro) throws SQLException {
		String nome = centro.getNome();
		String indirizzo = centro.getIndirizzo();
		String tipologia = centro.getTipologia();
		queryUpdate("INSERT INTO CentriVaccinali(Nome,Indirizzo,Tipologia) "
				+ "VALUES('"+nome+"','"+indirizzo+"','"+tipologia+"')");
	}
	
	
	/**
	 * Aggiunge un cittadino già vaccinato nel database
	 * @param cittadino {@link common.Cittadino Cittadino} da registrare
	 * @return
	 * @throws SQLException
	 */

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

		if ( !checkIDVaccCF(cf, idvacc) ) ritorno.add("L'id vaccinazione inserito non coincide con quello inserito in fase di vaccinazione.");
		else if(checkCampiCit ("IDVaccinazione",String.valueOf (idvacc))) ritorno.add("L'id vaccinazione inserito e' gia' stato registrato.");

		int idcentro;

		if(ritorno.size ()>0) return ritorno;
		ResultSet ids = query("SELECT IDCentro FROM Vaccinati WHERE CF='"+cf+"'");

		if(DBManager.ResultSetSize(ids) == 1) {
			idcentro = ids.getInt(1);
			
			cittadino.setIDCentro(idcentro);

			queryUpdate("INSERT INTO CittadiniRegistrati(Nome,Cognome,Email,Password,IDVaccinazione,CF,IDCentro,Nick) "
					+ "VALUES('"+nome+"','"+cognome+"','"+email+"','"+pwd+"','"+idvacc+"','"+cf+"','"+idcentro+"','"+userId+"')");
		
		}
		return null;
	}


	public boolean checkIDVaccCF(String CF, int IDVacc) throws SQLException {
		ResultSet r = query("SELECT Nome "+
				"FROM Vaccinati WHERE IDVaccinazione = '"+IDVacc+"' AND CF = '" + CF + "'");
		return r.next ();
	}




	/**
	 * Ricerca di vaccinati tramite chiave e valore dinamici
	 * @param campo Il nome del campo su cui si vuole basare la ricerca
	 * @param value Il valore che deve assumere campo per ricercare i record
	 * @return ritorna un valore booleano che sta a indicare se &egrave; stato trovato almeno un record di un vaccinato che ha come campo=valore
	 * @throws SQLException
	 */
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

	/**
	 * Ricerca di vaccinati o cittadini tramite chiave e valore dinamici
	 * @param campo Il nome del campo su cui si vuole basare la ricerca.
	 * Se &egrave; uguale a "IDVaccinazione" si cercherà nella tabella dei vaccinati, altrimenti in quella dei cittadini
	 * @param value Il valore che deve assumere campo per ricercare i record
	 * @return ritorna un valore booleano che sta a indicare se &egrave; stato trovato almeno
	 * un record di un vaccinato o di un cittadino che ha come campo=valore
	 * @throws SQLException
	 */
	public boolean checkCampiCit(String campo,String value) throws SQLException {
		if(campo.equals ("IDVaccinazione"))
		{
			ResultSet r = query("SELECT nick "+
					"FROM CittadiniRegistrati WHERE "+campo+"='"+Integer.parseInt (value)+"'");
			return r.next();
		}
		else {
			ResultSet r = query ("SELECT Nick " +
					"FROM CittadiniRegistrati WHERE " + campo + "='" + value + "'");
			return r.next();
		}

	}

	/**
	 * Registra un vaccinato nel database
	 * @param vaccinato {@link common.Vaccinato Vaccinato} che si vuole registrare
	 * @param datasomm La data in cui &egrave; stato somministrato il vaccino
	 * @param vaccino Vaccino che &egrave; stato somministrato
	 * @throws SQLException
	 */
	public void registraVaccinato(Vaccinato vaccinato,String datasomm,Vaccino vaccino) throws SQLException {

		String nome = vaccinato.getNome ();
		String cognome = vaccinato.getCognome();
		String cf = vaccinato.getCF();
		String nomecentro = vaccinato.getCentro().getNome();
		int idvaccinazione = vaccinato.getIDVaccino();
		int idcentro;
		
		ResultSet id = query("SELECT IDCentro FROM CentriVaccinali WHERE Nome='"+nomecentro+"'");
		
		if(DBManager.ResultSetSize(id) == 1) {
			idcentro = id.getInt(1);
			vaccinato.setIDCentro(idcentro);

			queryUpdate("INSERT INTO "+
					"Vaccinati(IDCentro,NomeCentro,IDVaccinazione,Nome,Cognome,CF,DataSomministrazione,VaccinoSomministrato) "+
					"VALUES("+idcentro+",'"+nomecentro+"',"+idvaccinazione+",'"+nome+"','"+cognome+"','"+cf+"','"+datasomm+"','"+vaccino+"')");
		
		}
	}
	
	/**
	 * Registra un vaccinato nel database
	 * @param vaccinato {@link common.Vaccinato Vaccinato} che si vuole registrare
	 * @throws SQLException
	 */
	public void registraVaccinato(Vaccinato vaccinato) throws SQLException {

		String nome = vaccinato.getNome ();
		String cognome = vaccinato.getCognome();
		String cf = vaccinato.getCF();
		String nomecentro = vaccinato.getCentro().getNome();
		int idvaccinazione = vaccinato.getIDVaccino();
		int idcentro;

		ResultSet id = query("SELECT IDCentro FROM CentriVaccinali WHERE Nome='"+nomecentro+"'");

		if(DBManager.ResultSetSize(id) == 1) {
			idcentro = id.getInt(1);
			vaccinato.setIDCentro(idcentro);

			queryUpdate("INSERT INTO "+
					"Vaccinati(IDCentro,NomeCentro,IDVaccinazione,Nome,Cognome,CF,DataSomministrazione,VaccinoSomministrato) "+
					"VALUES("+idcentro+",'"+nomecentro+"',"+idvaccinazione+",'"+nome+"','"+cognome+"','"+cf+"','"+vaccinato.getDatasomm()+"','"+vaccinato.getVaccino()+"')");

		}
	}
	
	/**
	 * Report per evento avverso del numero di segnalazioni e media della severità segnalati ad un centro vaccinale
	 * @param IDCentro Identificativo numero del centro vaccinale
	 * @return ArrayList di oggetti di tipo {@link common.ReportEventoAvverso ReportEventoAvverso}
	 * @throws SQLException
	 */
	
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
	
	/**
	 * Controlla se un vaccinato ha già segnalato un determinato evento avverso
	 * @param IDVaccinazione Identificativo numero della vaccinazione
	 * @param evento Nome dell' evento avverso
	 * @return Ritorna un valore booleano che assume vero se il vaccinato ha già segnalato un
	 * evento avverso di nome evento
	 * @throws SQLException
	 */
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

	/**
	 * Registra un segnalazione di un evento avverso sul database
	 * @param segnalazione {@link common.Segnalazione Segnalazione} da registrare
	 * @throws SQLException
	 */
	public void registraSegnalazione(Segnalazione segnalazione) throws SQLException {
		
		int idvaccinazione = segnalazione.getIDVaccinazione();
		String evento = segnalazione.getEvento();
		int severita = segnalazione.getSeverita();
		String nota = segnalazione.getNota();
		
		ResultSet id = query("SELECT IDEvento FROM EventoAvverso WHERE Evento='"+evento+"'");
		int idevento;
		
		if(DBManager.ResultSetSize(id) == 1) {
			idevento = id.getInt(1);
			queryUpdate("INSERT INTO Segnalazione(IDVaccinazione,IDEvento,Severita,Nota)"+
			"VALUES("+idvaccinazione+","+idevento+","+severita+",'"+nota+"')");
		}
		
	}
	
	/**
	 * Effettua il controllo se esiste un {@link common.Cittadino Cittadino} registrato con nickname e password specificati
	 * @param nick Nickname del cittadino registrato
	 * @param pwd Password del cittadino registrato
	 * @return Ritorna un resultset con i dati del cittadino che ha come nickname e password quelli specificati come parametri, altrimenti null
	 * @throws SQLException
	 */
	public ResultSet loginCittadino(String nick,String pwd) throws SQLException {

		ResultSet l = query("SELECT * FROM CittadiniRegistrati WHERE nick='"+nick+"' AND Password='"+pwd+"'");
		
		if(DBManager.ResultSetSize(l) == 1)
			return l;
		return null;
	}
	
	/**
	 * Cerca un centro vaccinale nel database con filtri di ricerca per nome, comune o tipologia. Lasciare un parametro vuoto
	 * se non lo si vuole come filtro di ricerca
	 * @param nome Nome del centro vaccinale che si vuole cercare
	 * @param comune Comune del centro vaccinale che si vuole cercare
	 * @param tipologia Tipologia del centro vaccinale che si vuole cercare
	 * @return ResultSet con i dati dei centri vaccinali trovati
	 * @throws SQLException
	 */
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

	public void CheckStrutturaEventi() throws SQLException {

		queryEvento(1,"Mal di testa");
		queryEvento(2,"Febbre");
		queryEvento(3,"Dolori muscolari e articolari");
		queryEvento(4,"Linfoadenopatia");
		queryEvento(5,"Tachicardia");
		queryEvento(6,"Crisi ipertensiva");

	}

	private void queryEvento(int id, String evento) throws SQLException {
		queryUpdate(" INSERT INTO EventoAvverso (idEvento,Evento)" +
				" VALUES(" + id + ",'" + evento + "')" );
	}
}
