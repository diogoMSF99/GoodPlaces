package domain;

/**
 * Representa uma unidade de medida.
 */
public class UnidadeMedida {

    private String designacao;
    private String abreviatura;
    
    /**
     * Constroi um objeto UnidadeMedida com a designacao e a abreviatura especificadas.
     * @param nome A designacao da unidade de medida.
     * @param abrev A abreviatura da unidade de medida.
     */
    public UnidadeMedida(String nome, String abrev) {
        designacao = nome;
        abreviatura = abrev;
    }

    /**
     * Retorna a abreviatura da unidade de medida.
     * @return A abreviatura da unidade de medida.
     */
    public String obtemAbreviatura() {
        return abreviatura;
    }
    
    /**
     * Retorna uma representacao em string da unidade de medida.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(designacao + "     " + abreviatura + "\n");
        return sb.toString();
    }
}
