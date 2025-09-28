import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {
     public static void main(String[] args) throws Exception {
          Socket conexao = new Socket("127.0.0.1", 50001);

          try (DataInputStream input = new DataInputStream(conexao.getInputStream());
                    DataOutputStream output = new DataOutputStream(conexao.getOutputStream());
                    BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

               while (true) {
                    System.out.println("\n > ");
                    String linha = teclado.readLine();
                    output.writeUTF(linha);

                    linha = input.readUTF();
                    if (linha.isEmpty()) {
                         System.out.println("Conexão encerrada");
                         break;
                    }
                    System.out.println(linha);
               }
          }
     }
}
