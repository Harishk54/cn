;l[import java.net.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;

public class HTTPServer2 {
    public static void main(String args[]) throws Exception {
        ServerSocket server = null;
        Socket socket;

        server = new ServerSocket(4000);
        System.out.println("Server Waiting for image");

        socket = server.accept();
        System.out.println("Client connected.");

        InputStream in = socket.getInputStream();
        DataInputStream dis = new DataInputStream(in);
        int len = dis.readInt();
        System.out.println("Image Size: " + len / 1024 + " KB");
        byte[] data = new byte[len];
        dis.readFully(data);
        dis.close();
        in.close();

        // Display the received image in a JFrame
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        BufferedImage bImage = ImageIO.read(byteArrayInputStream);

        JFrame frame = new JFrame("Server");
        ImageIcon icon = new ImageIcon(bImage);
        JLabel label = new JLabel();
        label.setIcon(icon);
        frame.add(label);
        frame.pack();
        frame.setVisible(true);

        // Close the server socket
        socket.close();
        server.close();
    }
}
