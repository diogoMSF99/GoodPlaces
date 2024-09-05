package domain.alertas;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import domain.Utilizador;

/**
 * Implementacao de um adaptador de alerta que envia alertas para um arquivo de texto.
 */
public class FileAlertaAdapter implements IAlertaAdapter {

    /**
     * Envia um alerta para o usuario especificado e regista o alerta em um arquivo de texto.
     * 
     * @param u O usuario para quem o alerta serah enviado.
     * @param ev O evento de alerta a ser enviado.
     */
    @Override
    public void enviaAlerta(Utilizador u, IEventoAlerta ev) {
        String mensagem = "Enviando alerta para " + u.toString() + " sobre " + ev.toString();
        System.out.println(mensagem);
        
        String nomeArquivo = u.obtemNome() + ".txt";
        try (FileWriter fw = new FileWriter(nomeArquivo, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(mensagem);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
        
    }

}
