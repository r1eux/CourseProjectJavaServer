package ServerLogic;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {


    public void startServer(){
        try(ServerSocket serverSocket = new ServerSocket(5556)){
            System.out.println("Server's port: " + 5556);
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("Client's connected: " + socket.getInetAddress());

                Thread thread = new Thread(new Handler(socket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
