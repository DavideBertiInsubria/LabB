package common;

public class ReportEventoAvverso {
		
	private String Evento;
	private float severitaMedia;
	private int NSegnalazioni;
	
	public ReportEventoAvverso(String Evento,float severitaMedia, int NSegnalazioni){
		this.Evento = Evento;
		this.severitaMedia = severitaMedia;
		this.NSegnalazioni = NSegnalazioni;
	}

	public String getEvento() {
		return Evento;
	}

	public float getSeveritaMedia() {
		return severitaMedia;
	}

	public int getNSegnalazioni() {
		return NSegnalazioni;
	}
	

}
