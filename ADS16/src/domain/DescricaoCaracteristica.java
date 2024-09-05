package domain;

/**
 * Representa uma descricao de caracteristica.
 */
public class DescricaoCaracteristica {

    private String desig;
    private UnidadeMedida unid;
    private TipoSensor tipoSens;
    
    /**
     * Constroi uma nova descricao de caracteristica com a designacao especificada.
     * @param desig A designacao da caracteristica.
     */
    public DescricaoCaracteristica(String desig) {
        this.desig = desig;
    }

    /**
     * Associa um tipo de sensor ah caracteristica.
     * @param ts O tipo de sensor a ser associado.
     */
    public void associaTipoSensor(TipoSensor ts) {
        this.tipoSens = ts;    
    }

    /**
     * Define a unidade de medida da caracteristica.
     * @param uni A unidade de medida a ser definida.
     */
    public void defineUnidadeMedida(UnidadeMedida uni) {
        this.unid = uni;        
    }

    /**
     * Obtem a designacao da caracteristica.
     * @return A designacao da caracteristica.
     */
    public String obtemDesignacao() {
        return desig;
    }

    /**
     * Obtem o tipo de sensor associado ah caracteristica.
     * @return O tipo de sensor associado.
     */
    public TipoSensor obtemTipoSensor() {
        return this.tipoSens;
    }
    
    /**
     * Obtem a unidade de medida da caracteristica.
     * @return A unidade de medida da caracteristica.
     */
    public UnidadeMedida obtemUnidadeMedida() {
        return this.unid;
    }
    
    /**
     * Obtem a abreviatura da unidade de medida da caracteristica.
     * @return A abreviatura da unidade de medida da caracteristica.
     */
    public String obtemAbreviaturaUnidade() {
        return this.unid.obtemAbreviatura();
    }
}
