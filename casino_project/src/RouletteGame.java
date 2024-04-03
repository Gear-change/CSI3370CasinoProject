import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RouletteGame extends Application {
    private RouletteWheel wheel;
    private Player player;
    private Label resultLabel;
    private TextField betAmountField;
    private TextField betNumberField;

    @Override
    public void start(Stage primaryStage) {
        wheel = new RouletteWheel();
        player = new Player();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Button spinButton = new Button("Spin");
        spinButton.setOnAction(event -> {
            String result = wheel.spin();
            resultLabel.setText("Result: " + result);
            player.checkBets(result);
            updateBalanceLabel();
        });

        resultLabel = new Label("Result:");
        Label betAmountLabel = new Label("Bet Amount:");
        betAmountField = new TextField();
        Label betNumberLabel = new Label("Bet Number:");
        betNumberField = new TextField();
        Button placeBetButton = new Button("Place Bet");
        placeBetButton.setOnAction(event -> {
            try {
                int amount = Integer.parseInt(betAmountField.getText());
                int number = Integer.parseInt(betNumberField.getText());
                player.placeBet(number, amount);
                updateBalanceLabel();
            } catch (NumberFormatException e) {
                // Handle invalid input
            }
        });

        gridPane.add(spinButton, 0, 0);
        gridPane.add(resultLabel, 0, 1);
        gridPane.add(betAmountLabel, 0, 2);
        gridPane.add(betAmountField, 1, 2);
        gridPane.add(betNumberLabel, 0, 3);
        gridPane.add(betNumberField, 1, 3);
        gridPane.add(placeBetButton, 0, 4);

        updateBalanceLabel();

        Scene scene = new Scene(gridPane, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Roulette Game");
        primaryStage.show();
    }

    private void updateBalanceLabel() {
        resultLabel.setText("Result: Balance: $" + player.getBalance());
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class RouletteWheel {
    private static final String[] numbers = {
            "0", "32", "15", "19", "4", "21", "2", "25", "17", "34",
            "6", "27", "13", "36", "11", "30", "8", "23", "10", "5",
            "24", "16", "33", "1", "20", "14", "31", "9", "22", "18",
            "29", "7", "28", "12", "35", "3", "26"
    };

    // Method to simulate spinning the wheel and returning a random number
    public String spin() {
        int randomIndex = new Random().nextInt(numbers.length);
        return numbers[randomIndex];
    }
}

class Player {
    private int balance;
    private Map<Integer, Integer> bets; // Map<Number, Amount>

    public Player() {
        balance = 1000; // Initial balance
        bets = new HashMap<>();
    }

    public int getBalance() {
        return balance;
    }

    public void placeBet(int number, int amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            bets.put(number, bets.getOrDefault(number, 0) + amount);
        }
    }

    public void checkBets(String result) {
        int resultNumber = Integer.parseInt(result);
        for (Map.Entry<Integer, Integer> entry : bets.entrySet()) {
            int betNumber = entry.getKey();
            int betAmount = entry.getValue();
            if (betNumber == resultNumber) {
                balance += betAmount * 36; // Payout for winning bet
            }
        }
        bets.clear(); // Clear all bets after checking
    }
}
