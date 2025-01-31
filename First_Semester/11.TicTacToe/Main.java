package game;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Scanner line = null;
        try {
            String mode;
            do {
                System.out.println("Do you want to play a tournament? Enter yes/no");
                line = new Scanner(scan.nextLine());
                mode = line.next().toLowerCase();
            } while (!mode.equals("yes") && !mode.equals("no"));
            System.out.println("Enter the value of the field (3 numbers)");
            int m = scan.nextInt();
            int n = scan.nextInt();
            int k = scan.nextInt();
            int result;
            final Game game = new Game(new RandomPlayer(m,n), new RandomPlayer(m,n));
            if(mode.equals("yes")) {
                System.out.println("Enter number of players:");
                scan.nextLine();
                line = new Scanner(scan.nextLine());
                result = game.tournament(m, n, k, line.nextInt());
                if (result == -2) {
                    System.out.println("This combination is banned, you will be disqualified");
                }else {
                    System.out.println("Player number " + result + " won the tournament!");
                }
            }
            if(mode.equals("no")){
                result = game.play(m, n, k);
                while (result == 0) {
                    System.out.println("Draw, time to replay");
                    result = game.play(m, n, k);
                }
                if (result == -2) {
                    System.out.println("This combination is banned");
                }
            }
        }
        catch (NoSuchElementException | NegativeArraySizeException e){
            System.out.println("Enter positive numbers not exceeding the value of 10^8");
        }
        finally {
            try {
                scan.close();
                line.close();
            }
            catch (NullPointerException e){}
        }
    }
}