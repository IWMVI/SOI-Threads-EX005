package view;

import controller.ThreadPing;

public class Principal {
    public static void main(String[] args) {
        
        Thread[] ping = new ThreadPing[3];
        ping[0] = new ThreadPing("www.google.com.br");
        ping[1] = new ThreadPing("www.uol.com.br");
        ping[2] = new ThreadPing("www.terra.com.br");

        for(Thread atual : ping){
            atual.start();
        }
    }
}
