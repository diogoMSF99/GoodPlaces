package domain.handlers;

import domain.CatalogoUtilizadores;
import domain.Utilizador;
import domain.interfaces.ILoginHandler;
import services.SessionManager;

/**
 * Classe responsavel por lidar com o processo de login dos utilizadores.
 */
public class LoginHandler implements ILoginHandler {
    private CatalogoUtilizadores catalogoU = new CatalogoUtilizadores();
    
    /**
     * Verifica as credenciais de login e cria uma sessao para o utilizador se forem validas.
     * 
     * @param nome o nome do utilizador
     * @param pwd a senha do utilizador
     * @return true se o login foi bem-sucedido e uma sessao foi criada, false caso contrario
     */
    @Override
    public boolean login(String nome, String pwd) {
        Utilizador user = catalogoU.obtemUtilizador(nome);
        if(user != null) {
            boolean b = user.pwdCorreta(pwd);
            if(b) {
                SessionManager.getInstance().createSession(nome);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
