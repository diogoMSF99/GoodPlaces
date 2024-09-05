package domain.alertas;

import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Fabrica para criar adaptadores de alerta com base nas configuracoes.
 */
public class AlertaAdapterFactory {
    private static AlertaAdapterFactory instance;
    private IAlertaAdapter alertaAdapter;
    
    /**
     * Construtor privado para inicializar a fabrica de alertas.
     */
    private AlertaAdapterFactory() {
        Properties props = new Properties();
        File configFile = new File("configuracao.properties");
        try (FileInputStream input = new FileInputStream(configFile)) {
            props.load(input);
            String adapterClassName = props.getProperty("alertaAdapter");
            if (adapterClassName == null || adapterClassName.isEmpty()) {
                System.err.println("Error: alertaAdapter property not found in configuracao.properties.");
                throw new IllegalArgumentException("alertaAdapter property not found in configuracao.properties.");
            }
            Class<?> clazz = Class.forName("domain.alertas." + adapterClassName);
            alertaAdapter = (IAlertaAdapter) clazz.getDeclaredConstructor().newInstance();
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException
                | NoSuchMethodException | InvocationTargetException e) {
            System.err.println("Error while initializing AlertaAdapterFactory: " + e.getMessage());
            e.printStackTrace();
            alertaAdapter = new SysOutAlertaAdapter();
        }
    }
    
    /**
     * Obtem o adaptador de alerta atualmente configurado.
     * @return O adaptador de alerta atual.
     */
    public IAlertaAdapter getAlertaAdapter() {
        return alertaAdapter;
    }
    
    /**
     * Obtem uma instância da fabrica de alertas.
     * @return Uma instância da fabrica de alertas.
     */
    public static AlertaAdapterFactory getInstance() {
        if(instance == null) {
            instance = new AlertaAdapterFactory();
        }
        return instance;
    }
}
