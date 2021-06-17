import java.sql.SQLException;

public class DBVaccinazioniManagement extends DBManager{

	public DBVaccinazioniManagement() throws SQLException {
		super("jdbc:postgresql://localhost/test","postgres","test");
	}
	
	
	public void registraCentroVaccinale(CentroVaccinale centro) throws SQLException {
		String nome = centro.getNome();
		String indirizzo = centro.getIndirizzo();
		String tipologia = centro.getTipologia();
		query("INSERT INTO CentriVaccinali(Nome,Indirizzo,Tipologia)"
				+ "VALUES("+nome+","+indirizzo+","+tipologia+")");
	}
	

	public void registraCittadino(Cittadino cittadino) throws SQLException {
		
		String nome = cittadino.getNome();
		String cognome = cittadino.getCognome();
		String email = cittadino.getEmail();
		String pwd = cittadino.getPassword();
		String cf = cittadino.getCF();
		String idcentro = cittadino.getIDCentro();
		

		query("INSERT INTO CittadiniRegistrati(Nome,Cognome,Email,Password,CF,IDCentro)"
				+ "VALUES("+nome+","+cognome+","+email+","+pwd+","+cf+","+idcentro+")");
		
	}
	
	/*public void cercaCentroVaccinale(String nome,String comune,String tipologia) throws SQLException {
		
		query("SELECT Nome,Indirizzo,Tipologia FROM CentriVaccinali"+
		"WHERE Nome='"+nome+"' "+
				"OR Cognome LIKE '%"+comune+"%' "+
				"OR tipologia='"+tipologia+"'");
	}
	*/
	
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
