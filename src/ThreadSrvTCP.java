import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import static java.util.Collections.sort;

public class ThreadSrvTCP implements Runnable {
        /* Thread que gestiona la comunicació de SrvTcPAdivina.java i un cllient ClientTcpAdivina.java */
        Tablero tablero = new Tablero();
        Socket clientSocket = null;
        Socket clientSocket1 = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        ObjectInputStream in1 = null;
        ObjectOutputStream out1 = null;
        int msgEntrant;
    String msgSortint;
        int msgEntrant1;
    String msgSortint1;

        boolean acabat;

        public ThreadSrvTCP(Socket socket, Socket clientSocket) throws IOException {
            this.clientSocket = socket;
            this.clientSocket1 = clientSocket;
            acabat = false;
            out= new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            out1= new ObjectOutputStream(clientSocket.getOutputStream());
            in1 = new ObjectInputStream(clientSocket.getInputStream());

            out.writeObject(tablero.matriz);
        }

        @Override
        public void run() {
            try {
                while(!acabat) {
                    msgEntrant = (int) in.readObject();
                    msgEntrant1 = (int) in1.readObject();
                    String mensaje = generaResposta(msgEntrant,msgEntrant1);

                    out.writeObject(mensaje);
                    out.reset();
                    out1.writeObject(mensaje);
                    out1.reset();
                    out.flush();
                    out1.flush();
                }
            }catch(IOException | ClassNotFoundException e){
                System.out.println(e.getLocalizedMessage());
            }
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String generaResposta(int en, int en1) {
            return tablero.comprobarTablero(en,en1);
        }


}
