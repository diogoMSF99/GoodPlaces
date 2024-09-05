package domain;

import java.beans.PropertyChangeEvent;

import domain.alertas.AlertaAdapterFactory;
import domain.alertas.IAlertaAdapter;
import domain.alertas.IEventoAlerta;

/**
 * Representa um tecnico no sistema.
 */
public class Tecnico extends Utilizador {

    /**
     * Constroi um objeto Tecnico com o nome especificado.
     * @param nome O nome do tecnico.
     */
    public Tecnico(String nome) {
        super(nome);
    }

    /**
     * Metodo chamado quando ocorre uma mudanca de propriedade.
     * @param evt O evento de mudanca de propriedade.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof IEventoAlerta) {
            IAlertaAdapter adapter = AlertaAdapterFactory.getInstance().getAlertaAdapter();
            adapter.enviaAlerta(this, (IEventoAlerta) evt.getNewValue());
        }
    }
}
