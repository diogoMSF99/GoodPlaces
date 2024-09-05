package domain.alertas;

import domain.Utilizador;

public interface IAlertaAdapter {

	public void enviaAlerta(Utilizador u, IEventoAlerta ev);
}
