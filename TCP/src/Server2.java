import java.io.IOException;
import java.net.Socket;

public class Server2 extends TCPServer {
    public Server2() {
        super(5061);
    }

    @Override
    protected ClientHandler createHandler(Socket socket, int clientId) throws IOException {
        return new HangmanHandler(socket, clientId);
    }

    public static void main(String[] args) {
        new Server2().start();
    }
}