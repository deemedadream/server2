package server2;

/**
 * Created by Tahir on 4/28/2017.
 */

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameState implements Serializable {
    String dstAddress = "35.185.103.105";
    int dstPort = 5554;
    boolean connected = false;
    int playerWins = 0;
    int playerLosses = 0;
    int playerDrosses = 0;
    String status = "Not connected";

    public void saveToFile(Context context) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("state1.ser", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Creates an object by reading it from a file
    public static GameState readFromFile(Context context) {
        GameState state2 = null;
        try {
            FileInputStream fileInputStream = context.openFileInput("state1.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            state2 = (GameState) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return state2;
    }

    public void sendState() {
        Socket socket = null;
        ObjectOutputStream oos = null;
        try {
            socket = new Socket(dstAddress, dstPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream fos = new FileOutputStream("state1.ser");
            oos = new ObjectOutputStream(fos);
            oos.flush();
            oos.writeObject(this);
            oos.flush();
            oos.close();
        }catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
