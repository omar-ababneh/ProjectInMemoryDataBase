package Connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {
    private Socket socket = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;

    public void OpenConnection(){
        try {
            socket = new Socket("localhost", 8000);
            System.out.println("Connected");
        }catch (IOException e){
            System.out.println(e);
        }
    }
    public void OpenResource(){
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void CloseResource(){
        try {
            System.out.println("Close Resource...");
            in.close();
            out.close();
        }catch (IOException e){
            System.out.println(e);
        }

    }
    public void CloseConnection(){
        try {
            System.out.println("Close Connection...");
            socket.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public  Socket getSocket() {
        return socket;
    }

    public  void setSocket(Socket socket) {
        this.socket = socket;
    }

    public  ObjectOutputStream getOut() {
        return out;
    }

    public  void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public  ObjectInputStream getIn() {
        return in;
    }

    public  void setIn(ObjectInputStream in) {
        this.in = in;
    }

}
