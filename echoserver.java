import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class echoserver {
    public static void main(String[] args) throws IOException {
        ServerSocket sock = null;
        BufferedReader fromClient = null;
        PrintWriter toClient = null;
        Socket client = null;
        
        try {
            sock = new ServerSocket(4000);
            System.out.println("Server Ready");
            
            client = sock.accept();
            System.out.println("Client Connected");
            
            fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            toClient = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
            
            String line;
            
            while (true) {
                line = fromClient.readLine();
                if (line == null || line.equals("bye")) break;
                
                System.out.println("Client says: " + line);
                toClient.println("Server says: " + line);
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        } finally {
            if (fromClient != null) fromClient.close();
            if (toClient != null) toClient.close();
            if (client != null) client.close();
            if (sock != null) sock.close();
            System.out.println("Client Disconnected");
        }
    }
}
