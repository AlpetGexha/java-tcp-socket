import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TCPServer {
//    one at a time on multi threaded server
    private static AtomicInteger clientCounter = new AtomicInteger(0);

    public static void main(String[] args) {
        int port = 5060;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                int clientId = clientCounter.incrementAndGet();
                System.out.println("New client connected: Client " + clientId);

                new ClientHandler(socket, clientId).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}