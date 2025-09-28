public class Filosofo implements Runnable {

     private int id;

     private Garfo garfo;

     public Filosofo(int id, Garfo garfo) {
         this.id = id;
         this.garfo = garfo;
     }

     @Override
     public void run() {
        try {
          System.out.println("O filosofo " +id+ "quer pegar um garfo?");
          garfo.pegar(id);
          System.out.println("O filosofo " +id+  "pegou o garfo");

        } catch {
          
        }
} 
