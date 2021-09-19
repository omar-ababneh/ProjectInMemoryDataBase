package ServerSide;

import Connection.Connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnection extends Connection {
    ServerSocket serverSocket=null;
    private  Socket socket = null;

    public  void OpenConnection(){
        try {
            serverSocket=new ServerSocket(8000);
        }catch (IOException e){
            System.out.println(e);
        }
    }
    public void Accept(){
        try {
            socket=serverSocket.accept();
            super.setSocket(socket);
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void CloseConnection(){
        try {

           socket.close();
           serverSocket.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

}



