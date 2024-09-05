package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.alertas.AlertaAdapterFactory;
import domain.alertas.IAlertaAdapter;
import domain.alertas.IEventoAlerta;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Classe abstrata que representa um Utilizador do sistema.
 */
public abstract class Utilizador implements PropertyChangeListener {
    
    private String nome, pwd, email;
    private Map<String, Contexto> contextosAssociado;

    /**
     * Construtor da classe Utilizador.
     * @param nome O nome do utilizador.
     */
    public Utilizador(String nome) {
        contextosAssociado = new HashMap<>();
        this.nome = nome;
    }

    /**
     * Define a password do utilizador.
     * @param pwd2 A nova password.
     */
    public void definirPassword(String pwd2) {
        pwd = pwd2;
    }

    /**
     * Obtem o nome do utilizador.
     * @return O nome do utilizador.
     */
    public String obtemNome() {
        return nome;
    }

    /**
     * Obtem a password do utilizador.
     * @return A password do utilizador.
     */
    public String obtemPassword() {
        return pwd;
    }

    /**
     * Obtem o email do utilizador.
     * @return O email do utilizador.
     */
    public String obtemEmail() {
        return email;
    }

    /**
     * Verifica se a password fornecida estah correta.
     * @param pwd2 A password a verificar.
     * @return true se a password estah correta, false caso contrario.
     */
    public boolean pwdCorreta(String pwd2) {
        return pwd2.equals(pwd);
    }

    /**
     * Associa o utilizador a um contexto.
     * @param contCorr O contexto a associar.
     */
    public void ficasAssociado(Contexto contCorr) {
        contextosAssociado.put(contCorr.obtemDesignacao(), contCorr);
        contCorr.addObserver(this);
    }

    /**
     * Obtem os nomes dos contextos acessiveis pelo utilizador.
     * @return Uma lista com os nomes dos contextos.
     */
    public List<String> nomesContextoAcessiveis() {
        List<String> contList = new ArrayList<>();
        for (String nome : this.contextosAssociado.keySet()) {
            contList.add(nome);
        }
        return contList;
    }

    /**
     * Verifica se o utilizador estah associado a um contexto especifico.
     * @param ctx O contexto a verificar.
     * @return true se o utilizador estah associado ao contexto, false caso contrario.
     */
    public boolean estaAssociado(Contexto ctx) {
        boolean res = false;
        for (Contexto contexto : this.contextosAssociado.values()) {
            if (ctx.equals(contexto)) {
                res = true;
                break;
            }
        }
        return res;
    }

    /**
     * Metodo chamado quando uma propriedade eh alterada.
     * @param ev O evento de mudanca de propriedade.
     */
    public void propertyChange(PropertyChangeEvent ev) {
        if (ev.getNewValue() instanceof IEventoAlerta) {
            IAlertaAdapter adapter = AlertaAdapterFactory.getInstance().getAlertaAdapter();
            adapter.enviaAlerta(this, (IEventoAlerta) ev);
        }
    }
}