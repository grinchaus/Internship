package game;
import java.util.*;
public class HumanPlayer implements Player {
    private final Scanner in;

    public HumanPlayer(final Scanner in) {
        this.in = in;
    }

    public HumanPlayer() {
        this(new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            int row;
            int column;
            Scanner scan = null;
            try {
                System.out.println("Enter row and column");
                scan = new Scanner(in.nextLine());
                row = scan.nextInt();
                column = scan.nextInt();
                final Move move = new Move(row-1, column-1, cell);
                if (position.isValid(move)) {
                    return move;
                }
                System.out.println("Move " + move + " is invalid");
                System.out.println("Position");
                System.out.println(position);
                System.out.println(cell + "'s move");
            }
            catch (NoSuchElementException e){
                System.out.println("Enter 2 numbers: position for your move");
                System.out.println("Position");
                System.out.println(position);
                System.out.println(cell + "'s move");
            }
            finally {
                try {
                    scan.close();
                }
                catch (NullPointerException e){
                    return null;
                }
            }
        }
    }
}
