import java.util.Scanner;
import java.util.Random;

public class Mastermind {
    public static final int KEY_LENGTH = 16;
    public static final char[] POSSIBLE_LETTERS = {'R', 'M', 'I', 'T'};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String secretKey = generateSecretKey(random);
        int count = 0;
        try {
            while (true) {
                System.out.print("Enter your guess: ");
                String guess = scanner.nextLine();
                count++;
                if (guess.length() != KEY_LENGTH) {
                    System.out.println("Invalid guess. The key must have " + KEY_LENGTH + " characters.");
                    continue;
                }
                int correctPositions = getCorrectPositions(secretKey, guess);
                if (correctPositions == KEY_LENGTH) {
                    System.out.println("Congratulations! You found the secret key in " + count + " guesses.");
                    break;
                } else {
                    System.out.println("Your guess has " + correctPositions + " correct positions.");
                }
            }
        } finally {
            scanner.close();
        }
    }
    
    private static String generateSecretKey(Random random) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < KEY_LENGTH; i++) {
            int index = random.nextInt(POSSIBLE_LETTERS.length);
            sb.append(POSSIBLE_LETTERS[index]);
        }
        return sb.toString();
    }

    private static int getCorrectPositions(String secretKey, String guess) {
        int count = 0;
        for (int i = 0; i < KEY_LENGTH; i++) {
            if (secretKey.charAt(i) == guess.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}