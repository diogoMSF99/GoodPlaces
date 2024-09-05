package domain;

import java.beans.PropertyChangeEvent;

import domain.alertas.AlertaAdapterFactory;
import domain.alertas.IAlertaAdapter;
import domain.alertas.IEventoAlerta;

/**
 * Representa um gestor no sistema.
 */
public class Gestor extends Utilizador {

    /**
     * Constroi um novo gestor com o nome especificado.
     * @param nome O nome do gestor.
     */
    public Gestor(String nome) {
        super(nome);
    }

    /**
     * Notifica uma mudanca de propriedade e envia um alerta se o novo valor for uma instancia de IEventoAlerta.
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

