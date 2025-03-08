import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class HangmanHandler extends ClientHandler implements HasTitleContract {
    private String word;
    private final List<Character> playerGuesses = new ArrayList<>();
    private int wrongCount = 0;

    public HangmanHandler(Socket socket, int clientId) throws IOException {
        super(socket, clientId);
    }

    @Override
    public void handleClient() {
        try {
            output.println("1 or 2 Players ?");
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

            while (true) {
                displayHangman();
                displayWordState();

                if (wrongCount >= 6) {
                    output.println("HANGMAN! YOU LOST!\n");
                    output.println("The word was: \"" + word + "\"");
                    break;
                }

                if (!processPlayerGuess()) wrongCount++;

                if (isWordGuessed()) {
                    output.println("\nCONGRATULATIONS, YOU WON!\n");
                    break;
                }

                output.println("What do you think the word is: ");
                if (input.readLine().toLowerCase().equals(word)) {
                    output.println("\nCONGRATULATIONS, YOU WON!\n");
                    break;
                } else {
                    output.println("No! Try again.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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

    @Override
    public String getTitle() {
        return "Hangman Game";
    }

    @Override
    public String getDescription() {
        return "A simple hangman game. \n1 Player - The server will choose a random word from a list of words. \n2 Players - Player 1 will enter a word for Player 2 to guess.";
    }
}