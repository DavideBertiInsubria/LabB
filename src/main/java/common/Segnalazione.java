package common;

public class Segnalazione {
	
	private int IDVaccinazione;
	private String Evento;
	private int Severita;
	private String Nota;
	
	public Segnalazione(int IDVaccinazione,String Evento,int Severita,String Nota) {
		this.IDVaccinazione = IDVaccinazione;
		this.Evento = Evento;
		this.Severita = Severita;
		this.Nota = Nota;
	}
	
	public String getEvento() {
		return Evento;
	}
	
	public int getIDVaccinazione() {
		return IDVaccinazione;
	}

	public int getSeverita() {
		return Severita;
	}

	public String getNota() {
		return Nota;
	}

}
