public class Garfo {

     private static final int FILOSOFOS = 5;
     private EstadoFilosofo []  estadoFilosofo = new EstadoFilosofo[5];

     public synchronized void pegar(Integer IdFilosofo) {
          int vizinhoEsquerda = (IdFilosofo + FILOSOFOS - 1) % FILOSOFOS;
          int vizinhoDireita = (IdFilosofo + 1) % FILOSOFOS;
          EstadoFilosofo estadoEsquerda = estadoFilosofo[vizinhoEsquerda];
          EstadoFilosofo estadoDireita = estadoFilosofo[vizinhoDireita];
          while (estadoEsquerda == EstadoFilosofo.COMENDO || 
                 estadoDireita == EstadoFilosofo.COMENDO) {
               try {
                    wait();
               } estadoFilosofo[IdFilosofo] = EstadoFilosofo.COMENDO;

          } catch (Exception e) {
               // TODO: handle exception
          }
     }


     
}
