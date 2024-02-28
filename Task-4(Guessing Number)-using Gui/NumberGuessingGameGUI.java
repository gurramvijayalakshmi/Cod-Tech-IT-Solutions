import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class NumberGuessingGameGUI extends JFrame {
    private int lowerBound = 1;
    private int upperBound = 100;
    private int numberToGuess;
    private int maxAttempts = 10;
    private int attempts = 0;

    private JTextField guessField;
    private JTextArea outputArea;

    public NumberGuessingGameGUI() {
        super("Number Guessing Game");

        // Initialize components
        guessField = new JTextField(10);
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        JButton guessButton = new JButton("Guess");
        JButton newGameButton = new JButton("New Game");
        JButton saveButton = new JButton("Save Game");
        JButton loadButton = new JButton("Load Game");

        // Add action listeners
        guessButton.addActionListener(new GuessButtonListener());
        newGameButton.addActionListener(new NewGameButtonListener());
        saveButton.addActionListener(new SaveButtonListener());
        loadButton.addActionListener(new LoadButtonListener());

        // Set layout
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Enter your guess: "));
        topPanel.add(guessField);
        topPanel.add(guessButton);
        topPanel.add(newGameButton);
        topPanel.add(saveButton);
        topPanel.add(loadButton);
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Initialize a new game
        startNewGame();

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startNewGame() {
        numberToGuess = (int) (Math.random() * (upperBound - lowerBound + 1)) + lowerBound;
        attempts = 0;
        outputArea.setText("Welcome to the Number Guessing Game!\nI have selected a number between "
                + lowerBound + " and " + upperBound + ". Try to guess it.\n");
    }

    private void handleGuess(int guess) {
        attempts++;
        if (guess < numberToGuess) {
            outputArea.append("Too low! Try again.\n");
        } else if (guess > numberToGuess) {
            outputArea.append("Too high! Try again.\n");
        } else {
            outputArea.append("Congratulations! You guessed the correct number " + numberToGuess +
                    " in " + attempts + " attempts.\n");
            startNewGame();
        }

        if (attempts == maxAttempts) {
            outputArea.append("Sorry, you've reached the maximum number of attempts. The correct number was " +
                    numberToGuess + ".\n");
            startNewGame();
        }
    }

    private void saveGameState() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("game_state.dat"))) {
            oos.writeInt(numberToGuess);
            oos.writeInt(attempts);
            oos.flush();
            outputArea.append("Game saved successfully.\n");
        } catch (IOException e) {
            outputArea.append("Error saving game state.\n");
            e.printStackTrace();
        }
    }

    private void loadGameState() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("game_state.dat"))) {
            numberToGuess = ois.readInt();
            attempts = ois.readInt();
            outputArea.append("Game loaded successfully.\n");
        } catch (IOException | ClassNotFoundException e) {
            outputArea.append("Error loading game state. Starting a new game.\n");
            startNewGame();
            e.printStackTrace();
        }
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                handleGuess(guess);
            } catch (NumberFormatException ex) {
                outputArea.append("Invalid input. Please enter a valid number.\n");
            }
            guessField.setText("");
            guessField.requestFocus();
        }
    }

    private class NewGameButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            startNewGame();
            guessField.setText("");
            guessField.requestFocus();
        }
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveGameState();
            guessField.requestFocus();
        }
    }

    private class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadGameState();
            guessField.setText("");
            guessField.requestFocus();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberGuessingGameGUI());
    }
}
