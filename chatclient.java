import java.io.*;
import java.net.*;

public class chatclient {
    public static void main(String args[]) throws Exception {
        Socket clt;
        PrintWriter toServer;
        BufferedReader fromUser, fromServer;
        try {
            if (args.length > 1) {
                System.out.println("Usage: java TCPSimpleChatClient hostipaddr");
                System.exit(-1);
            }

            if (args.length == 0)
                clt = new Socket(InetAddress.getLocalHost(), 5555);
            else
                clt = new Socket(InetAddress.getByName(args[0]), 5555);

            toServer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clt.getOutputStream())), true);
            fromServer = new BufferedReader(new InputStreamReader(clt.getInputStream()));
            fromUser = new BufferedReader(new InputStreamReader(System.in));
            
            String cltMsg, srvMsg;
            System.out.println("Type \"end\" to Quit");
            
            while (true) {
                System.out.print("\nMessage to Server: ");
                cltMsg = fromUser.readLine();
                toServer.println(cltMsg);
                
                if (cltMsg.equals("end")) 
                    break;
                
                srvMsg = fromServer.readLine();
                System.out.println("Client <<< " + srvMsg);
            }
            
            clt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
