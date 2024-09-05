package domain.handlers;

import domain.interfaces.ILogoutHandler;
import services.SessionManager;

/**
 * Classe responsavel por lidar com o processo de logout dos utilizadores.
 */
public class LogoutHandler implements ILogoutHandler {
    
    /**
     * Realiza o logout, encerrando a sessao do utilizador.
     */
    @Override
    public void logout() {
        SessionManager.getInstance().deleteSession();
    }
}
