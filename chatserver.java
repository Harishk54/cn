import java.io.*;
import java.net.*;

public class chatserver {
        public static void main(String args[]) throws Exception {
        PrintWriter toClient;
        BufferedReader fromUser, fromClient;
        try {
            ServerSocket srv = new ServerSocket(5555);
            System.out.println("Server started");
            Socket clt = srv.accept();
            System.out.println("Client connected");

            // Create output stream to client
            toClient = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clt.getOutputStream())), true);

            // Create input stream from client
            fromClient = new BufferedReader(new InputStreamReader(clt.getInputStream()));
            fromUser = new BufferedReader(new InputStreamReader(System.in));

            String cltMsg, srvMsg;
            while (true) {
                cltMsg = fromClient.readLine();
                if (cltMsg.equals("end")) {
                    break;
                } else {
                    System.out.println("\nServer <<< " + cltMsg);
                    System.out.print("Message to Client : ");
                    srvMsg = fromUser.readLine();
                    toClient.println(srvMsg);
                }
            }

            System.out.println("\nClient Disconnected");
            fromClient.close();
            toClient.close();
            fromUser.close();
            clt.close();
            srv.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
