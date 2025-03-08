import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class TCPServer {
    private final int port;
    private static final AtomicInteger clientCounter = new AtomicInteger(0);

    public TCPServer(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                int clientId = clientCounter.incrementAndGet();
                System.out.println("New client connected: Client " + clientId);

                ClientHandler handler = createHandler(socket, clientId);
                new Thread(() -> {
                    try {
                        handler.run();
                    } catch (Exception e) {
                        handleError(e, clientId);
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract ClientHandler createHandler(Socket socket, int clientId) throws IOException;

    protected void handleError(Exception e, int clientId) {
        System.err.println("Client " + clientId + " error: " + e.getMessage());
        e.printStackTrace();
    }
}