package domain.alertas;

public interface IEventoAlerta {
	
	public int ano();

	public int mes();
	
	public int dia();
	
	public String data();
	
	public double valor();
	
	public String unidade();
	
	public String caracteristica();
	
	public String contexto();
	
	public String mensagem();
	
	public void adicionaContexto(String contexto);
}
