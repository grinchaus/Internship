package game;
public interface Player {
    Move move(Position position, Cell cell) throws NullPointerException, IndexOutOfBoundsException, NumberFormatException;
}