import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class ClientHandler extends Thread {
    private final Socket socket;
    private final int clientId;
    private boolean isLiveChat;

    public ClientHandler(Socket socket, int clientId) {
        this.socket = socket;
        this.clientId = clientId;
        this.isLiveChat = true;
    }

    public void run() {
        try (
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)
        ) {
            String clientMessage;

            if (isLiveChat) {
                Thread sendThread = new Thread(() -> {
                    while (true) {
                        System.out.print("Server: ");
                        String serverMessage = scanner.nextLine();
                        output.println(serverMessage);
                    }
                });

                sendThread.start();

                while ((clientMessage = input.readLine()) != null) {
                    System.out.println("Client " + clientId + ": " + clientMessage);
                }
            }

            if (!this.isLiveChat) {
                int value = Integer.parseInt(input.readLine());

                while ((clientMessage = input.readLine()) != null) {
                    System.out.println("Client " + clientId + ": " + clientMessage);
                    System.out.println("Server: " + (isPrime(value) ? "Prime" : "Not Prime"));
                    System.out.println("Server: " + (isEven(value) ? "Even" : "Odd"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isEven(int n) {
        return n % 2 == 0;
    }
}