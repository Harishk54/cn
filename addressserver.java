import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class addressserver {
    public static void main(String args[]) {
        try {
            ServerSocket obj = new ServerSocket(5604);

            while (true) {
                Socket obj1 = obj.accept();
                DataInputStream din = new DataInputStream(obj1.getInputStream());
                DataOutputStream dout = new DataOutputStream(obj1.getOutputStream());

                String str = (new BufferedReader(new InputStreamReader(din))).readLine();
                String ip[] = { "165.165.80.80", "165.165.79.1" };
                String mac[] = { "6A:08:AA:C2", "8A:BC:E3:FA" };

                boolean resolved = false;
                for (int i = 0; i < ip.length; i++) {
                    if (str.equals(ip[i])) {
                        dout.writeBytes(mac[i] + '\n');
                        resolved = true;
                        break;
                    }
                }

                if (!resolved) {
                    dout.writeBytes("Unknown IP\n");
                }

                obj1.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
