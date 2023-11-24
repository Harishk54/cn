import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

class addressclient {
    public static void main(String args[]) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            Socket clsct = new Socket("127.0.0.1", 5604);
            DataInputStream din = new DataInputStream(clsct.getInputStream());
            DataOutputStream dout = new DataOutputStream(clsct.getOutputStream());
            
            System.out.println("Enter the Logical address(IP):");
            String str1 = in.readLine();
            dout.writeBytes(str1 + '\n');
            
            String str = (new BufferedReader(new InputStreamReader(din))).readLine();
            System.out.println("The Physical Address is: " + str);
            
            clsct.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
