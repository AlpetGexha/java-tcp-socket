import java.io.IOException;
import java.net.Socket;

public class Server1 extends TCPServer {
    public Server1() {
        super(5060);
    }

    public static void main(String[] args) {
        new Server1().start();
    }

    @Override
    protected ClientHandler createHandler(Socket socket, int clientId) throws IOException {
        return new PrimeHandler(socket, clientId);
    }
}