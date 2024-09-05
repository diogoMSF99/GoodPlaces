package domain.handlers;

import java.util.List;

import domain.CatalogoContextos;
import domain.Contexto;
import domain.Utilizador;
import domain.interfaces.IRecolherDadosHandler;

/**
 * Classe responsavel por lidar com a recolha de dados de contexto.
 */
public class RecolherDadosHandler implements IRecolherDadosHandler {
    private Utilizador utilizador;
    private CatalogoContextos catCont;
    private Contexto context;
    
    /**
     * Construtor que inicializa o handler com o utilizador e o catalogo de contextos fornecidos.
     * 
     * @param utilizador o utilizador para o qual os dados sao recolhidos
     * @param catCont o catalogo de contextos disponiveis
     */
    public RecolherDadosHandler(Utilizador utilizador, CatalogoContextos catCont) {
        this.utilizador = utilizador;
        this.catCont = catCont;
    }

    /**
     * Inicia o processo de recolha de dados, retornando os nomes dos contextos acessiveis pelo utilizador.
     * 
     * @return os nomes dos contextos acessiveis pelo utilizador
     */
    @Override
    public Iterable<String> iniciarRecolha() {
        List<String> contList = utilizador.nomesContextoAcessiveis();
        return contList;
    }

    /**
     * Indica o contexto para o qual os dados serao recolhidos.
     * 
     * @param nome o nome do contexto a ser indicado
     * @return true se o contexto foi indicado com sucesso, false caso contrario
     */
    @Override
    public boolean indicarContexto(String nome) {
        Contexto ctx = catCont.obtemContexto(nome);
        boolean b = false;
        if (ctx != null) {
            b = utilizador.estaAssociado(ctx);
            if (b) {
                this.context = ctx;
                System.out.println("RecolherDados: Nome do contexto: " + this.context.obtemDesignacao());
            }
        }
        return ctx != null && b;
    }

    /**
     * Obtem as caracteristicas e unidades associadas ao contexto indicado.
     * 
     * @return as caracteristicas e unidades associadas ao contexto indicado
     */
    @Override
    public Iterable<String> obtemCaracteristicasUnidades() {
        List<String> carUniList = this.context.obtemCaracteristicaUnidades();
        return carUniList;
    }

    /**
     * Indica a caracteristica e unidade a serem registadas no contexto atual.
     * 
     * @param carac a caracteristica a ser indicada
     * @param unidade a unidade a ser indicada
     * @return true se a caracteristica e unidade foram indicadas com sucesso, false caso contrario
     */
    @Override
    public boolean indicarCaracteristica(String carac, String unidade) {
        boolean b = this.context.definirCaractUnidCorrente(carac, unidade);
        return b;
    }

    /**
     * Registra uma leitura no contexto atual com a data e valor fornecidos.
     * 
     * @param ano o ano da leitura
     * @param mes o mes da leitura
     * @param dia o dia da leitura
     * @param valor o valor da leitura
     */
    @Override
    public void indicarLeitura(int ano, int mes, int dia, double valor) {
        this.context.registarLeitura(ano, mes, dia, valor);
    }

    /**
     * Cancela o processo atual de recolha de dados.
     */
    @Override
    public void cancelar() {
        // Nenhuma implementacao necessaria para este metodo
    }
}
