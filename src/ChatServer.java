import java.net.*;
import java.io.*;
import java.util.*;

public class ChatServer {
    ArrayList<Client> clients = new ArrayList<>();
    ServerSocket serverSocket;

    ChatServer() throws IOException {
        // создаем серверный сокет на порту 1234
       serverSocket = new ServerSocket(1234);
    }
    void sendAll(String message) {
        for (Client client: clients) {
            client.receive(message);
        }
    }
    public void run(){
        while(true) {
            System.out.println("Waiting...");
            // ждем клиента из сети
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                // создаем клиента на своей стороне
                //Client client = new Client(socket);
                clients.add(new Client(socket, this));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new ChatServer().run();

    }


}


