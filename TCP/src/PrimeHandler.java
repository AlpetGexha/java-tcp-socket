import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class PrimeHandler extends ClientHandler implements HasTitleContract {
    public PrimeHandler(Socket socket, int clientId) throws IOException {
        super(socket, clientId);
    }

    @Override
    public void handleClient() {
        try {
            String clientMessage;

            while ((clientMessage = input.readLine()) != null) {
                System.out.println("Client " + clientId + ": " + clientMessage);

                // Process the input from client as a number
                int value;
                try {
                    value = Integer.parseInt(clientMessage);
                } catch (NumberFormatException e) {
                    output.println("Please enter a valid number");
                    continue;
                }

                String primeMessage = (isPrime(value) ? "Prime" : "Not Prime");
                String evenMessage = (isEven(value) ? "Even" : "Odd");

                output.println(primeMessage);
                output.println(evenMessage);
                logToServer("Client " + clientId + " Process success");
            }
        } catch (IOException e) {
            System.err.println("Error handling client " + clientId + ": " + e.getMessage());
        }
    }


    private static boolean isPrime(int n) {
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

    private static boolean isEven(int n) {
        return n % 2 == 0;
    }


    @Override
    public String getTitle() {
        return "Prime Handler";
    }

    @Override
    public String getDescription() {
        return "Handles prime number and even/odd checks for the client.";
    }
}