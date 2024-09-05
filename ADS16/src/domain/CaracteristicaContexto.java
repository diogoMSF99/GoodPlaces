package domain;

import java.util.ArrayList;
import java.util.List;
import domain.alertas.EventoAlerta;
import java.beans.PropertyChangeSupport;

/**
 * Representa uma caracteristica de um contexto associada a um sensor.
 */
public class CaracteristicaContexto {
    private DescricaoCaracteristica carac;
    private Sensor sensor;
    private List<Leitura> leituras;
    private int inf, sup;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    
    /**
     * Constroi um novo objeto CaracteristicaContexto com a descricao da caracteristica especificada.
     * @param carac A descrição da caracteristica.
     */
    public CaracteristicaContexto(DescricaoCaracteristica carac) {
        this.carac = carac;
        this.leituras = new ArrayList<>();
    }

    /**
     * Obtem o nome da caracteristica.
     * @return O nome da caracteristica.
     */
    public String nomeCaracteristica() {
        return this.carac.obtemDesignacao();
    }
    
    /**
     * Obtem a abreviatura da unidade associada ah caracteristica.
     * @return A abreviatura da unidade.
     */
    public String abreviaturaUnidade() {
        return this.carac.obtemAbreviaturaUnidade();
    }

    /**
     * Adiciona um contexto como observador.
     * @param contexto O contexto a ser adicionado como observador.
     */
    public void addObserver(Contexto contexto) {
        support.addPropertyChangeListener(contexto);
    }
    
    /**
     * Remove um contexto como observador.
     * @param contexto O contexto a ser removido como observador.
     */
    public void removeObserver(Contexto contexto) {
        support.removePropertyChangeListener(contexto);
    }

    /**
     * Associa um sensor ah caracteristica.
     * @param sens O sensor a ser associado.
     */
    public void associaSensor(Sensor sens) {
        this.sensor = sens;
    }
    
    /**
     * Obtem o sensor associado ah caracteristica.
     * @return O sensor associado.
     */
    public Sensor obtemSensor() {
        return sensor;
    }

    /**
     * Regista os valores de referencia para a caracteristica.
     * @param i O valor de referencia inferior.
     * @param j O valor de referencia superior.
     */
    public void registaValoresRef(int i, int j) {
        this.inf = i;
        this.sup = j;
    }
    
    /**
     * Regista uma leitura para a caracteristica e dispara um evento se estiver fora do intervalo de referencia.
     * @param ano O ano da leitura.
     * @param mes O mes da leitura.
     * @param dia O dia da leitura.
     * @param valor O valor da leitura.
     */
    public void registarLeitura(int ano, int mes, int dia, double valor) {
        String unidade = this.abreviaturaUnidade();    
        Leitura l = new Leitura(ano, mes, dia, valor, unidade);
        this.adicionaLeitura(l);
        int k = this.estaNoIntervalo(valor);
        if (k != 0) {
            firePropertyChange(k, l);
        }
    }
    
    /**
     * Emite um alerta se o valor nao estiver no intervalo
     * @param k -1 se for inferior, 1 se for superior.
     * @param l leitura realizada.
     */
    private void firePropertyChange(int k, Leitura l) {
        if (k == 1) {
            EventoAlerta alerta = new EventoAlerta(l.obtemAno(), l.obtemMes(), l.obtemDia(), l.obtemValor(), l.obtemUnidade(), this.nomeCaracteristica(), "Acima do normal");
            support.firePropertyChange("alerta", null, alerta);
        } else if (k == -1) {
            EventoAlerta alerta = new EventoAlerta(l.obtemAno(), l.obtemMes(), l.obtemDia(), l.obtemValor(), l.obtemUnidade(), this.nomeCaracteristica(), "Abaixo do normal");
            support.firePropertyChange("alerta", null, alerta);
        }
    }

    /**
     * Define se a unidade de medida atual corresponde ah abreviatura de unidade fornecida.
     * @param unid A abreviatura de unidade para comparar.
     * @return Verdadeiro se a unidade atual corresponder ah abreviatura fornecida, falso caso contrario.
     */
    public boolean definirUnidadeCorrenteLeitura(String unid) {
        String abrev = this.carac.obtemAbreviaturaUnidade();
        return abrev.equals(unid);
    }
    
    /**
     * Adiciona uma leitura a this.leituras
     * @param l
     */
    private void adicionaLeitura(Leitura l) {
        this.leituras.add(l);
    }
    
    /**
     * Verifica se o valor estah no intervalo dos valores definidos previamente
     * @param valor
     * @return True se estah nao intervalo, False caso contrario
     */
    private int estaNoIntervalo(double valor) {
        int res = 0;
        
        if (valor < this.inf) {
            res = -1;
        }
        
        if (valor > this.sup) {
            res = 1;
        } 
        
        return res;
    }
}
