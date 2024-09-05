package domain.alertas;

/**
 * Classe que representa um evento de alerta.
 */
public class EventoAlerta implements IEventoAlerta{

	private int ano, mes, dia;
	private double valor;
	private String unidade, caracteristica, contexto, mensagem;
	
	/**
     * Construtor que inicializa um evento de alerta com os parametros fornecidos.
     * 
     * @param ano o ano do evento
     * @param mes o mes do evento
     * @param dia o dia do evento
     * @param valor o valor do evento
     * @param unidade a unidade do evento
     * @param caracteristica a caracteristica do evento
     */
	public EventoAlerta(int ano, int mes, int dia, double valor, String unidade, String caracteristica, String mensagem){
		this.ano = ano;
		this.mes = mes;
		this.dia = dia;
		this.valor = valor;
		this.unidade = unidade;
		this.mensagem = mensagem;
		this.caracteristica = caracteristica;
	}
	
	/**
     * Retorna o ano do evento.
     * 
     * @return o ano do evento
     */
    @Override
    public int ano() {
        return this.ano;
    }

    /**
     * Retorna o mes do evento.
     * 
     * @return o mes do evento
     */
    @Override
    public int mes() {
        return this.mes;
    }

    /**
     * Retorna o dia do evento.
     * 
     * @return o dia do evento
     */
    @Override
    public int dia() {
        return this.dia;
    }
	
    /**
     * Retorna a data do evento.
     * 
     * @return a data do evento
     */
	@Override
	public String data() {
		return this.ano + "/" + this.mes + "/" + this.dia;
	}

	/**
     * Retorna o valor do evento.
     * 
     * @return o valor do evento
     */
    @Override
    public double valor() {
        return this.valor;
    }

    /**
     * Retorna a unidade do evento.
     * 
     * @return a unidade do evento
     */
    @Override
    public String unidade() {
        return this.unidade;
    }

    /**
     * Retorna a caracteristica do evento.
     * 
     * @return a caracteristica do evento
     */
    @Override
    public String caracteristica() {
        return this.caracteristica;
    }

    /**
     * Retorna o contexto do evento.
     * 
     * @return o contexto do evento
     */
    @Override
    public String contexto() {
        return this.contexto;
    }

    /**
     * Retorna a mensagem de alerta padrao.
     * 
     * @return a mensagem de alerta padrao
     */
	@Override
	public String mensagem() {
		return this.mensagem;
	}

	/**
     * Adiciona um contexto ao evento.
     * 
     * @param contexto o contexto a ser adicionado
     */
	@Override
	public void adicionaContexto(String contexto) {
		// TODO Auto-generated method stub
		this.contexto = contexto;
	}
}
