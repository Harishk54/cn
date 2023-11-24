import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class reverseclient {
    public static void main(String args[]) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress addr = InetAddress.getByName("127.0.0.1");

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the Physical address (MAC):");
            String str = in.readLine();

            byte[] sendbyte = str.getBytes();
            DatagramPacket sender = new DatagramPacket(sendbyte, sendbyte.length, addr, 1309);
            client.send(sender);

            byte[] receivebyte = new byte[1024];
            DatagramPacket receiver = new DatagramPacket(receivebyte, receivebyte.length);
            client.receive(receiver);

            String receivedData = new String(receiver.getData()).trim();
            System.out.println("The Logical Address is (IP): " + receivedData);

            client.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
