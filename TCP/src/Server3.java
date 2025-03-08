import java.io.IOException;
import java.net.Socket;

public class Server3 extends TCPServer {
    public Server3() {
        super(5062);
    }

    public static void main(String[] args) {
        new Server3().start();
    }

    @Override
    protected ClientHandler createHandler(Socket socket, int clientId) throws IOException {
        return new LiveChatHandler(socket, clientId);
    }
}