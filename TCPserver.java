package server2;
import java.net.*; 
import java.io.*; 

public class TCPserver { 
  public static void main(String arg[]) {
    ServerSocket serverSocket = null;
    Socket socket = null;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;


    try {
        serverSocket = new ServerSocket(5554);
        System.out.println("Listening :5554");
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    while (true) {
        try {
            socket = serverSocket.accept();
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();

            System.out.println("ip: " + socket.getInetAddress());
            Object state1 = in.readObject();  //Message captured from chat client.
            System.out.println(" message received from ");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } 

        finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
}