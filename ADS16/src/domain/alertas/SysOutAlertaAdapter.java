package domain.alertas;

import domain.Utilizador;

/**
 * Implementa a interface IAlertaAdapter para enviar alertas atraves da saida padrao.
 */
public class SysOutAlertaAdapter implements IAlertaAdapter {

    /**
     * Envia um alerta para o usuario especificado.
     * @param u O usuario para quem o alerta sera enviado.
     * @param ev O evento de alerta a ser enviado.
     */
    @Override
    public void enviaAlerta(Utilizador u, IEventoAlerta ev) {
        StringBuilder sb = new StringBuilder();
        sb.append("------ ALERTA para " + u.obtemNome() + " - "+ ev.mensagem() +" -------");
        sb.append("\n");
        sb.append("Data: " + ev.data());
        sb.append("   Contexto: " + ev.contexto());
        sb.append("   Caracteristica: " + ev.caracteristica());
        sb.append("\n");
        sb.append("Valor lido: " + ev.valor() + ev.unidade());
        System.out.println(sb.toString());
    }
}
