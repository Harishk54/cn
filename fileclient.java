import java.io.*;
import java.net.*;

public class fileclient {
    public static void main(String args[]) throws IOException {
        Socket s = null;
        BufferedReader get = null;
        PrintWriter put = null;
        
        try {
            s = new Socket("127.0.0.1", 8081);
            get = new BufferedReader(new InputStreamReader(s.getInputStream()));
            put = new PrintWriter(s.getOutputStream(), true);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        String u, f;
        System.out.print("Enter the filename to transfer from server: ");
        BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
        f = userInputReader.readLine();
        put.println(f);

        File f1 = new File("f:\\output\\" + f);
        FileOutputStream fs = new FileOutputStream(f1);
        BufferedOutputStream bos = new BufferedOutputStream(fs);

        while ((u = get.readLine()) != null) {
            byte jj[] = (u + "\n").getBytes();
            bos.write(jj);
        }

        bos.flush();  // Flush the buffered output stream
        bos.close();
        fs.close();
        System.out.println("File received");
        s.close();
    }
}
