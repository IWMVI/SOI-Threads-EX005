package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ThreadPing extends Thread {

    private String servidorURL;
    private String servidorNome;

    public ThreadPing(String servidorURL) {
        this.servidorURL = servidorURL;
    }

    @Override
    public void run() {
        chamadaIP();
    }

    private void chamadaIP() {
        StringBuilder cmd = new StringBuilder("ping -4 -c 10 ");
        cmd.append(servidorURL);
        try {
            Process p = Runtime.getRuntime().exec(cmd.toString());
            InputStream dados = p.getInputStream();
            InputStreamReader leitor = new InputStreamReader(dados);
            BufferedReader buffer = new BufferedReader(leitor);
            buffer.readLine();
            String linha, ultima = "";
            int cont = 0, QUANTIDADE_PING = 10;
            while ((linha = buffer.readLine()) != null) {
                if (cont++ < QUANTIDADE_PING) {
                    exibirNomePing(linha);
                }
                ultima = linha;
            }
            buffer.close();
            leitor.close();
            dados.close();

            exibirMedia(ultima);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void exibirNomePing(String linha) {
        String[] linhaDividida = linha.split(" ");
        String tempo = linhaDividida[linhaDividida.length - 2];

        System.out.println("Ping [" + servidorNome + "] " + tempo);
    }

    private void exibirMedia(String linha) {
        String[] linhaDividida = linha.split("/");
        String media = linhaDividida[linhaDividida.length - 3];
        System.out.println("Média [" + servidorNome + "] = " + media);
    }
}
