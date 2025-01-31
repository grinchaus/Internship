package game;
import java.util.Random;
public class Game {
    private static int random1 = 1;
    private static int random2 = 2;
    private final Player player1;
    private final Player player2;
    private final Random random = new Random();
    public Game(final Player player1, final Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
    public int tournament(int r, int c, int k, int kol){
        int[] pl = new int[2*kol-1];
        for(int i = 0; i < kol; i++){
            pl[i] = i+1;
        }
        int counter = kol, j = 0, result;
        while (counter < kol * 2 - 1) {
            random1 = pl[j];
            random2 = pl[j+1];
            result = play(r, c, k);
            while(result == 0) {
                System.out.println("Draw, time to replay");
                result = play(r, c, k);
            }
            if(result == -2){
                return -2;
            }
            pl[counter] = result;
            counter++;
            j+=2;
        }
        return pl[counter-1];
    }
    public int play(int r, int c, int k) {
        Board board = new TicTacToeBoard(r,c,k);
        if(random.nextBoolean()){
            int ran = random1;
            random1 = random2;
            random2 = ran;
        }
        while (true) {
            int result1 = move(board, player1, random1);
            if (result1 == -3){
                System.out.println("Your player is not working correctly");
                return isWin(1,random2,random1);
            }
            int win1 = isWin(result1,random1,random2);
            if(win1 != -1){
                return win1;
            }
            int result2 = move(board, player2, random2);
            if (result2 == -3){
                System.out.println("Your player is not working correctly");
                return isWin(1,random1,random2);
            }
            int win2 = isWin(result2,random2,random1);
            if(win2 != -1){
                return win2;
            }
        }
    }
    private int isWin(int result, int rand1, int rand2){
        if (result > 0) {
            System.out.println("Player " + rand1 + " won");
            System.out.println("Player " + rand2 + " lose");
            return rand1;
        }
        if (result == 0 || result == -2){
            return result;
        }
        else {
            return -1;
        }
    }
    private int move(final Board board, final Player player, final int no) {
        try {
            System.out.println("Position:\n" + board);
            System.out.println(board.getCell() + "'s move, player "+no);
            final Move move = player.move(board.getPosition(), board.getCell());
            if(move == null){
                return -2;
            }
            final Result result = board.makeMove(move);
            System.out.println("Player " + no + " move: " + move);
            if (result == Result.WIN) {
                System.out.println("Position:\n" + board);
                return no;
            } else if (result == Result.LOSE) {
                System.out.println("Position:\n" + board);
                return -3;
            } else if (result == Result.DRAW) {
                System.out.println("Position:\n" + board);
                return 0;
            } else {
                return -1;
            }
        }
        catch (NullPointerException | IndexOutOfBoundsException | NumberFormatException e){
            return -3;
        }
    }
}