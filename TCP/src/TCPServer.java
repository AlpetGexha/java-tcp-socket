import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class TCPServer {
    private static final AtomicInteger clientCounter = new AtomicInteger(0);

    public static void main(String[] args) {
        int port = 5060;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                int clientId = clientCounter.incrementAndGet();
                System.out.println("New client connected: Client " + clientId);

//                new LiveChatHandler(socket, clientId).start();
                 new PrimeHandler(socket, clientId).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}