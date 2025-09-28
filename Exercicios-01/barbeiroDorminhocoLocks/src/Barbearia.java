import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Barbearia {

     private final int cadeiras;
     private int clientesEspera;

     private final Lock lock = new ReentrantLock(true);
     private final Condition clienteDisponivel = lock.newCondition();
     private final Condition barbeiroDisponivel = lock.newCondition();

     public Barbearia(int cadeiras) {
          this.cadeiras = cadeiras;
     }

     public void cortarCabelo() throws InterruptedException {
          lock.lock();
          try {
               while (clientesEspera == 0) {
                    System.out.println();
                    System.out.println("Barbeiro dormindo...");
                    clienteDisponivel.await();
               }
               clientesEspera--;
               // System.out.println("Cliente sendo atendido. clientes esperando: " + clientesEspera);
               barbeiroDisponivel.signal();
          } finally {
               lock.unlock();
          }
          Thread.sleep(3000);
          System.out.println("Barbeiro terminou o corte.");
     }

     public void entrarNaBarbearia(int idCliente) throws InterruptedException {
          lock.lock();
          try {
               if (clientesEspera < cadeiras) {
                    clientesEspera++;
                    System.out.println("Cliente " + idCliente + " entrou e está esperando. \nHá " + clientesEspera + " cliente(s) esperando");
                    clienteDisponivel.signal(); // Acorda barbeiro
                    barbeiroDisponivel.await(); // Espera ser atendido
                    System.out.println("Cliente " + idCliente + " está sendo atendido.");
               } else {
                    System.out.println("Cliente " + idCliente + " foi embora (barbearia cheia).");
               }
          } finally {
               lock.unlock();
          }
     }
}
