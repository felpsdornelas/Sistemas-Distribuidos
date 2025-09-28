public class App {
    public static void main(String[] args) throws Exception {

        Corrida Felipe = new Corrida("Felipe");
        Corrida Pedro = new Corrida("Pedro");
        Corrida Arthur = new Corrida("Arthur");

        Thread thread = new Thread(Felipe);
        Thread thread2 = new Thread(Pedro);
        Thread thread3 = new Thread(Arthur);

        thread.start();
        thread2.start();    
        thread3.start();

        thread.join();
        thread2.join(); 
        thread3.join();
        
        System.out.println("Corrida finalizada!");
    }
}
