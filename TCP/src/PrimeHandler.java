import java.io.IOException;
import java.net.Socket;

class PrimeHandler extends ClientHandler {
    public PrimeHandler(Socket socket, int clientId) throws IOException {
        super(socket, clientId);
    }

    @Override
    public void run() {
        try {
            int value = Integer.parseInt(input.readLine());
            String clientMessage;

            while ((clientMessage = input.readLine()) != null) {
                System.out.println("Client " + clientId + ": " + clientMessage);
                String primeMessage = "Server: " + (isPrime(value) ? "Prime" : "Not Prime");
                String evenMessage = "Server: " + (isEven(value) ? "Even" : "Odd");
                logToServer(primeMessage); // Display on server side
                logToServer(evenMessage); // Display on server side
                output.println(primeMessage); // Send to client
                output.println(evenMessage); // Send to client
            }
        } catch (IOException e) {
            System.err.println("Error handling client " + clientId + ": " + e.getMessage());
        }
    }

    private boolean isPrime(int n) {
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

    private boolean isEven(int n) {
        return n % 2 == 0;
    }
}