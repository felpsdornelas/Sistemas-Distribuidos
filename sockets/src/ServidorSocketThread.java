import java.net.Socket;

public class ServidorSocketThread implements Runnable {
     
     private final Socket socketClient;

     public ServidorSocketThread(Socket socketClient) {
          this.socketClient = socketClient;
     }

     @Override
     public void run() {
          try (DataInputStream entrada = new DataInputStream (socketClient.getInputStream()); 
              (DataOutputStream saida = new DataOutputStream (socketClient.getInputStream())) {

              } 

     }
     
}
