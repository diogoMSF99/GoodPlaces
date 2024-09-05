package domain;

/**
 * Representa uma leitura de dados associada a uma determinada unidade de medida.
 */
public class Leitura {
    private int ano, mes, dia;
    private double valor;
    private String unidade;
    
    /**
     * Constroi um objeto Leitura com os valores fornecidos.
     * @param ano O ano da leitura.
     * @param mes O mes da leitura.
     * @param dia O dia da leitura.
     * @param valor O valor da leitura.
     * @param unidade A unidade de medida da leitura.
     */
    public Leitura(int ano, int mes, int dia, double valor, String unidade) {
        this.ano = ano;
        this.dia = dia;
        this.mes = mes;
        this.valor = valor;
        this.unidade = unidade;
    }
    
    /**
     * Obtem o ano da leitura.
     * @return O ano da leitura.
     */
    public int obtemAno() {
        return this.ano;
    }
    
    /**
     * Obtem o mes da leitura.
     * @return O mes da leitura.
     */
    public int obtemMes() {
        return this.mes;
    }
    
    /**
     * Obtem o dia da leitura.
     * @return O dia da leitura.
     */
    public int obtemDia() {
        return this.dia;
    }
    
    /**
     * Obtem o valor da leitura.
     * @return O valor da leitura.
     */
    public double obtemValor() {
        return this.valor;
    }
    
    /**
     * Obtem a unidade de medida da leitura.
     * @return A unidade de medida da leitura.
     */
    public String obtemUnidade() {
        return this.unidade;
    }
}
