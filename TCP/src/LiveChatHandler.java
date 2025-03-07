import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class LiveChatHandler extends ClientHandler {
    public LiveChatHandler(Socket socket, int clientId) throws IOException {
        super(socket, clientId);
    }

    @Override
    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            Thread sendThread = new Thread(() -> {
                while (true) {
                    System.out.print("Server: ");
                    String serverMessage = scanner.nextLine();
                    output.println(serverMessage);
                }
            });

            sendThread.start();

            String clientMessage;
            while ((clientMessage = input.readLine()) != null) {
                System.out.println("Client " + clientId + ": " + clientMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}