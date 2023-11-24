import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class echoclient {
    public static void main(String[] args) throws IOException {
        BufferedReader fromServer = null, fromUser = null;
        PrintWriter toServer = null;
        Socket sock = null;

        try {
            if (args.length == 0)
                sock = new Socket(InetAddress.getLocalHost(), 4000);
            else
                sock = new Socket(InetAddress.getByName(args[0]), 4000);

            fromServer = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            fromUser = new BufferedReader(new InputStreamReader(System.in));
            toServer = new PrintWriter(sock.getOutputStream(), true);

            String usrMsg, srvMsg;
            System.out.println("Type \"bye\" to quit");

            while (true) {
                System.out.print("Enter message to server: ");
                usrMsg = fromUser.readLine();

                if (usrMsg == null || usrMsg.equals("bye")) {
                    toServer.println("bye");
                    break;
                } else {
                    toServer.println(usrMsg);
                }

                srvMsg = fromServer.readLine();
                System.out.println("Server says: " + srvMsg);
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        } finally {
            if (fromUser != null) fromUser.close();
            if (fromServer != null) fromServer.close();
            if (toServer != null) toServer.close();
            if (sock != null) sock.close();
        }
    }
}
