import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SrvTCP {
    /* Servidor TCP que genera un número perquè ClientTcpAdivina.java jugui a encertar-lo
     * i on la comunicació dels diferents jugador passa per el Thread : ThreadServidorAdivina.java
     * */

    int port;
    public SrvTCP(int port ) {
        this.port = port;
    }

    public void listen() {
        ServerSocket serverSocket = null;
        Socket clientSocket=null;
        Socket clientSocket1 = null;
        try {
            serverSocket = new ServerSocket(port);
            while(true) { //esperar connexió del client i llançar thread
                clientSocket = serverSocket.accept();
                clientSocket1 = serverSocket.accept();
                //Llançar Thread per establir la comunicació
                ThreadSrvTCP FilServidor = new ThreadSrvTCP(clientSocket, clientSocket1);
                Thread client = new Thread(FilServidor);
                client.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(SrvTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
		/*if (args.length != 1) {
            System.err.println("Usage: java SrvTcpAdivina <port number>");
            System.exit(1);
        }*/


        //int port = Integer.parseInt(args[0]);
        SrvTCP srv = new SrvTCP(5558);
        srv.listen();

    }

}

