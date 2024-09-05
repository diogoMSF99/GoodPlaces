package domain.handlers;

import domain.CatalogoDescricaoCaracteristicas;
import domain.CatalogoTiposSensor;
import domain.CatalogoUnidades;
import domain.DescricaoCaracteristica;
import domain.TipoSensor;
import domain.UnidadeMedida;
import domain.interfaces.ICriarCaracteristicaHandler;

/**
 * Classe responsavel por lidar com a criacao de caracteristicas.
 */
public class CriarCaracteristicaHandler implements ICriarCaracteristicaHandler {
    private CatalogoDescricaoCaracteristicas catalogoDescricaoCarac;
    private CatalogoTiposSensor tplist;
    private CatalogoUnidades catalogoUnidades;
    private DescricaoCaracteristica dc;
    private TipoSensor ts;
    
    /**
     * Construtor que inicializa o handler com os catalogos necessarios.
     * 
     * @param catalogoUnidades o catalogo de unidades de medida
     * @param catalogoDescricaoCarac o catalogo de descricoes de caracteristicas
     * @param tplist o catalogo de tipos de sensor
     */
    public CriarCaracteristicaHandler(CatalogoUnidades catalogoUnidades, CatalogoDescricaoCaracteristicas catalogoDescricaoCarac, CatalogoTiposSensor tplist) {
        this.catalogoUnidades = catalogoUnidades;
        this.catalogoDescricaoCarac = catalogoDescricaoCarac;
        this.tplist = tplist;
    }

    /**
     * Cria uma nova caracteristica com a designacao fornecida.
     * 
     * @param desig a designacao da caracteristica a ser criada
     * @return true se a caracteristica foi criada com sucesso, false caso contrario
     */
    @Override
    public boolean criarCaracteristica(String desig) {
        boolean b = catalogoDescricaoCarac.existeCaracteristica(desig);
        if(!b) {
            dc = new DescricaoCaracteristica(desig);
            return true;
        }
        return false;
    }

    /**
     * Obtem os nomes dos tipos de sensor disponiveis.
     * 
     * @return os nomes dos tipos de sensor disponiveis
     */
    @Override
    public Iterable<String> obterTiposSensor() {
        return tplist.nomesTiposSensor();
    }

    /**
     * Indica o tipo de sensor associado ah caracteristica.
     * 
     * @param tipo, o nome do tipo de sensor a ser associado
     * @return true se o tipo de sensor foi associado com sucesso, false caso contrario
     */
    @Override
    public boolean indicarTipoSensor(String tipo) {
        ts = tplist.obtemTipoSensor(tipo);
        if(ts != null) {
            dc.associaTipoSensor(ts);
            return true;
        }
        return false;
    }

    /**
     * Obtem os nomes das unidades de medida disponiveis.
     * 
     * @return os nomes das unidades de medida disponiveis
     */
    @Override
    public Iterable<String> obterUnidadesMedida() {
        return ts.obtemNomesUnidades();
    }

    /**
     * Indica a unidade de medida associada a caracteristica.
     * 
     * @param abrev a abreviatura da unidade de medida a ser associada
     * @return true se a unidade de medida foi associada com sucesso, false caso contrario
     */
    @Override
    public boolean indicarUnidade(String abrev) {
        UnidadeMedida uni = catalogoUnidades.obtemUnidade(abrev);
        if(uni != null) {
            dc.defineUnidadeMedida(uni);
            return true;
        }
        return false;
    }

    /**
     * Confirma a criacao da caracteristica.
     */
    @Override
    public void confirmar() {
        catalogoDescricaoCarac.adicionaCaracteristica(dc);
    }

    /**
     * Cancela a operacao atual de criacao de caracteristica.
     */
    @Override
    public void cancelar() {
    	
    }

}
