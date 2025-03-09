import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class HangmanHandler extends ClientHandler implements Runnable, HasTitleContract {
    private final List<Character> playerGuesses = new ArrayList<>();
    private String word;
    private int wrongCount = 0;

    public HangmanHandler(Socket socket, int clientId) throws IOException {
        super(socket, clientId);
    }

    @Override
    public void run() {
        handleClient();
    }

    @Override
    public void handleClient() {
        try {
            while (true) {
                startGame();

                output.println("Press 'y' if you want to play again, any other key to exit.");
                char response = input.readLine().trim().toLowerCase().charAt(0);
                if (response != 'y') {
                    output.println("Thank you for playing! Goodbye.");
                    System.out.println("Client " + clientId + " dont want to play again.");
                    break;
                }

                System.out.println("Client " + clientId + " wants to play again.");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startGame() throws IOException {
        playerGuesses.clear();
        wrongCount = 0;

        output.println("1 or 2 Players?");
        String player = input.readLine();

        if ("1".equals(player)) {
            output.println("Single Player Game\n");
            word = getRandomWord();
        } else {
            output.println("Two Player Game\n");
            output.println("Player 1, enter your word: ");
            word = input.readLine().replaceAll("\\s", "").toLowerCase();
            output.println("Player 2, good luck!");
        }

        if (containsDigit(word)) {
            output.println("The word contains digits, which is not allowed.");
            return;
        }

        while (true) {
            displayHangman();
            displayWordState();

            output.println("Wrong guesses: " + wrongCount + "/6\n");

            if (wrongCount >= 6) {
                output.println("HANGMAN! YOU LOST!\n");
                output.println("The word was: \"" + word + "\"");
                System.out.println("Client " + clientId + " lost the game.");
                break;
            }

            if (!processPlayerGuess()) wrongCount++;

            if (isWordGuessed()) {
                output.println("\nCONGRATULATIONS, YOU WON!\n");
                System.out.println("Client " + clientId + " won the game.");
                break;
            }
        }
    }

    private String getRandomWord() throws IOException {
        Scanner scanner = new Scanner(new File("hangman-word.txt"));
        List<String> words = new ArrayList<>();
        while (scanner.hasNext()) {
            words.add(scanner.nextLine());
        }
        return words.get(new Random().nextInt(words.size()));
    }

    private void displayHangman() {
        output.println(" -------");
        output.println(" |     |");
        if (wrongCount >= 1) output.println(" O");
        if (wrongCount >= 2) output.print("\\ ");
        if (wrongCount >= 3) output.println("/");
        if (wrongCount >= 4) output.println(" |");
        if (wrongCount >= 5) output.print("/ ");
        if (wrongCount >= 6) output.println("\\");
        output.println("\n\n");
    }

    private void displayWordState() {
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                output.print(word.charAt(i));
            } else {
                output.print("-");
            }
        }
        output.println();
    }

    private boolean processPlayerGuess() throws IOException {
        output.print("Enter a letter: ");
        String letterGuess = input.readLine().toLowerCase();
        playerGuesses.add(letterGuess.charAt(0));
        return word.contains(letterGuess);
    }

    private boolean isWordGuessed() {
        for (int i = 0; i < word.length(); i++) {
            if (!playerGuesses.contains(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean containsDigit(String word) {
        for (char c : word.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getTitle() {
        return "Hangman Game";
    }

    @Override
    public String getDescription() {
        return "A simple hangman game. Test Deployee";
    }
}
