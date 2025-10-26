import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {
     public static void main(String[] args) throws Exception {
          Socket conexao = new Socket("10.108.166.162", 3030);

          try (DataInputStream entrada = new DataInputStream(conexao.getInputStream());
               DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
               BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

               while (true) {
                    System.out.print("\n > ");
                    String linha = teclado.readLine();
                    saida.writeUTF(linha);

                    linha = entrada.readUTF();
                    if (linha.isEmpty()) {
                         System.out.println("Conexão encerrada");
                         break;
                    }
                    System.out.println(linha);
               }
          }
     }
}
