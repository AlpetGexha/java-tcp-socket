import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

abstract class ClientHandler extends Thread {
    protected final Socket socket;
    protected final int clientId;
    protected final BufferedReader input;
    protected final PrintWriter output;

    public ClientHandler(Socket socket, int clientId) throws IOException {
        this.socket = socket;
        this.clientId = clientId;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public abstract void run();
}