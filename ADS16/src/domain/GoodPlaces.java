package domain;

import domain.handlers.CriarCaracteristicaHandler;
import domain.handlers.LoginHandler;
import domain.handlers.LogoutHandler;
import domain.handlers.RecolherDadosHandler;
import domain.interfaces.ICriarCaracteristicaHandler;
import domain.interfaces.IGoodPlaces;
import domain.interfaces.ILoginHandler;
import domain.interfaces.ILogoutHandler;
import domain.interfaces.IRecolherDadosHandler;
import services.SessionManager;

/**
 * Classe que define o objeto inicial da aplicacao
 * 
 * @author ADS
 */
public class GoodPlaces implements IGoodPlaces {
	private CatalogoDescricaoCaracteristicas catDescrCaract;
	private CatalogoContextos catContexts;
	private CatalogoUtilizadores catUser;
	private CatalogoTiposSensor catTiposSensor;
	private CatalogoUnidades catUnidades;

    /**
     * Construtor
     */
	public GoodPlaces() {
    	catUser = new CatalogoUtilizadores();       // Ja' sao criados alguns utilizadore
    	catUnidades = new CatalogoUnidades();       // Ja' sao criadas algumas unidades
    	catTiposSensor = new CatalogoTiposSensor();
    	catDescrCaract = new CatalogoDescricaoCaracteristicas();
    	catContexts = new CatalogoContextos();
    	// Criar alguns objetos necessarios para testar os casos de uso UC6
    	// e UC11 porque os outros casos de uso não estao ainda implementados:
    	catTiposSensor.loadSomeTipoSensor(catUnidades); // Cria 4 Tipos de sensor e sensores associados
    	catDescrCaract.loadSomeDescCar(catTiposSensor, catUnidades);  // Cria 2 caracteristicas
    	catContexts.loadSomeContexts(catDescrCaract, catUser);   // Cria dois contextos
    }
    
    /**
     * Obtem o tipo de usuario autenticado na sessao.
     * @return O tipo de usuario autenticado.
     */
    @Override
    public String obtemTipoUserAutenticado() {
        return getAuthenticatedUser().getClass().getName().substring(7);
    }

    /**
     * Obtem o usuario autenticado na sessao.
     * @return O usuario autenticado.
     */
    private Object getAuthenticatedUser() {
        String nome = SessionManager.getInstance().getAuthenticatedUser();
        Utilizador uAut = catUser.obtemUtilizador(nome);
        return uAut;
    }

    /**
     * Obtem o handler de login.
     * @return O handler de login.
     */
    @Override
    public ILoginHandler obtemLoginHandler() {
        return new LoginHandler();
    }

    /**
     * Obtem o handler para criar características se o usuario autenticado for um tecnico.
     * @return O handler para criar caracteristicas, ou null se o usuario nao for um tecnico.
     */
    @Override
    public ICriarCaracteristicaHandler obtemCriarCaracteristicaHandler() {
        Object uAut = getAuthenticatedUser();
        if (uAut instanceof Tecnico) {
            CriarCaracteristicaHandler h = new CriarCaracteristicaHandler(catUnidades, catDescrCaract, catTiposSensor);
            return h;
        }
        return null;
    }

    /**
     * Obtem o handler para recolher dados se o usuario autenticado for um tecnico.
     * @return O handler para recolher dados, ou null se o usuario nao for um tecnico.
     */
    @Override
    public IRecolherDadosHandler obtemRecolherDadosHandler() {
        Utilizador uAut = (Utilizador) getAuthenticatedUser();
        if (uAut instanceof Tecnico) {
            RecolherDadosHandler h = new RecolherDadosHandler(uAut, catContexts);
            return h;
        }
        return null;
    }

    /**
     * Obtem o handler de logout.
     * @return O handler de logout.
     */
    @Override
    public ILogoutHandler obtemLogoutHandler() {
        return new LogoutHandler();
    }

    /**
     * Obtem o catalogo de unidades.
     * @return O catalogo de unidades.
     */
    public CatalogoUnidades obtemCatalogoUnidades() {
        return catUnidades;
    }
}