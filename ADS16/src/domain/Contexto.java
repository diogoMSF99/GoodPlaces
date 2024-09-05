package domain;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.alertas.IEventoAlerta;

/* Classe referente ao contexto*/
public class Contexto implements PropertyChangeListener{
	
	private String designacao;
	private Map<String,CaracteristicaContexto> carcons;
	private List<Par<String,String>> carUniList;
	private PropertyChangeSupport support = new PropertyChangeSupport(this);
	private CaracteristicaContexto caracContx;

	/**
	 * Construtor
	 * @param desig A designacao do contexto
	 */
	public Contexto(String desig) {
		carcons = new HashMap<>();
		designacao = desig;
		this.carUniList = new ArrayList<>();
	}

	/**
	 * Adiciona uma CaracteristicaContexto ao contexto
	 * @param carCont A CaracteristicaContexto a adicionar
	 */
	public void adicionaCaracteristica(CaracteristicaContexto carCont) {
		carcons.put(carCont.nomeCaracteristica(), carCont);
		carCont.addObserver(this);
	}

	/**
	 * Obtem a designacao do contexto
	 * @return Da designacao
	 */
	public String obtemDesignacao() {
		return designacao;
	}
	
	/**
	 * Obtem a lista dos pares designacao unidade no formato "designacao - unidade"
	 * @return Lista dos pares designacao unidade
	 */
	public List<String> obtemCaracteristicaUnidades() {
		List<String> res = new ArrayList<String>();
		for(CaracteristicaContexto carcon : this.carcons.values()) {
			String desig = carcon.nomeCaracteristica();
			String uni = carcon.abreviaturaUnidade();
			this.carUniList.add(new Par<String,String>(desig,uni));
		}
		if (this.carUniList != null) {
			res = this.obtemCaracteristicaUnidadesAux(this.carUniList);
		}
		return res;
	}
	
	/**
	 * Formata a lista de pares recebida
	 * @param list Lista a formatar
	 * @return Lista formatada
	 */
	private List<String> obtemCaracteristicaUnidadesAux(List<Par<String,String>> list) {
		List<String> res = new ArrayList<String>();
		for(Par<String,String> par : list) {
			res.add(par.primeiro() + " - " + par.segundo());
		}
		return res;
	}
	
	/**
	 * Obtem a CaracteristicaContexto
	 * @return A CaracteristicaContexto
	 */
	public CaracteristicaContexto obtemCaracContx() {
		return this.caracContx;
	}

	/**
	 * Adiciona um utilizador como observador do contexto
	 * @param utilizador O utilizador
	 */
	public void addObserver(Utilizador utilizador) {
		support.addPropertyChangeListener(utilizador);
		
	}
	
	
	/**
	 * Notifica a mudanca de propriedade e dispara um evento de propriedade se o novo valor for uma instancia de IEventoAlerta.
	 * @param evt O evento de mudanca de propriedade.
	 */
	public void propertyChange(PropertyChangeEvent evt) {
	    if (evt.getNewValue() instanceof IEventoAlerta) {
	        IEventoAlerta alerta = (IEventoAlerta) evt.getNewValue();
	        alerta.adicionaContexto(this.designacao);
	        support.firePropertyChange(evt.getPropertyName(), null, alerta);
	    }
	}

	/**
	 * Define se a caracteristica e a unidade de medida fornecidas correspondem ah caracteristica e unidade de medida atuais.
	 * @param carac O nome da caracteristica.
	 * @param unid A abreviatura da unidade de medida.
	 * @return Verdadeiro se a caracteristica e unidade de medida correspondem as atuais, falso caso contrario.
	 */
	public boolean definirCaractUnidCorrente(String carac, String unid) {
	    CaracteristicaContexto cc = null;
	    boolean b1 = false;
	    for (CaracteristicaContexto caracCont : this.carcons.values()) {
	        if (carac.equals(caracCont.nomeCaracteristica())) {
	            cc = caracCont;
	            if (cc != null) {
	                this.caracContx = cc;
	                b1 = cc.definirUnidadeCorrenteLeitura(unid);
	                break;
	            }
	        }
	    }
	    return cc != null && b1;
	}

	/**
	 * Registra uma leitura para o contexto atual.
	 * @param ano O ano da leitura.
	 * @param mes O mes da leitura.
	 * @param dia O dia da leitura.
	 * @param valor O valor da leitura.
	 */
	public void registarLeitura(int ano, int mes, int dia, double valor) {
	    this.caracContx.registarLeitura(ano, mes, dia, valor);
	}

	/**
	 * Verifica se o objeto fornecido eh igual ao contexto atual com base na designacao.
	 * @param obj O objeto a ser comparado.
	 * @return Verdadeiro se os objetos forem iguais, falso caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (obj == null || getClass() != obj.getClass()) {
	        return false;
	    }
	    Contexto contexto = (Contexto) obj;
	    return designacao.equals(contexto.designacao);
	}

	
}
