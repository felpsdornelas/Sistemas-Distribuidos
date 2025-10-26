package proto;
import java.nio.charset.StandardCharsets;
import com.google.protobuf.InvalidProtocolBufferException;
import proto.ClienteOuterClass.Cliente;

public class JsonVsProtobuf {
     public static void main(String[] args) throws InvalidProtocolBufferException {
          String json = "{ \"id\": 1, \"nome\": \"Carlos\" }";
          byte[] jsonBytes = json.getBytes(StandardCharsets.UTF_8);
          Cliente cliente = Cliente.newBuilder()
                    .setId(1)
                    .setNome("Carlos")
                    .build();
          byte[] protoBytes = cliente.toByteArray();
          System.out.println("--- JSON ---");
          System.out.println(json);
          System.out.println("Tamanho JSON (bytes): " + jsonBytes.length);
          System.out.println("\n--- Protobuf ---");
          System.out.println(cliente);
          System.out.println("Tamanho Protobuf (bytes): " + protoBytes.length);
     }
}