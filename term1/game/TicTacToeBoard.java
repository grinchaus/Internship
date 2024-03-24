package game;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class TicTacToeBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );
    private final Cell[][] cells;
    private Cell turn;
    private final int row, column, k;
    private int empty;
    public TicTacToeBoard(int row, int column, int k) {
        this.cells = new Cell[row][column];
        this.row = row;
        this.column = column;
        this.k = k + 1;
        for (Cell[] dot : cells) {
            Arrays.fill(dot, Cell.E);
        }
        turn = Cell.X;
        Random random = new Random();
        if(random.nextBoolean()){
            turn = Cell.O;
        }
        empty = row * column;
    }
    @Override
    public Position getPosition() {
        return this;
    }
    @Override
    public Cell getCell() {
        return turn;
    }
    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        int getRow = move.getRow();
        int getColumn = move.getColumn();
        Cell getValue = move.getValue();
        cells[getRow][getColumn] = getValue;
        if(isWin(getRow,getColumn,getValue,-1,0) ||
                isWin(getRow,getColumn,getValue,-1,1) ||
                isWin(getRow,getColumn,getValue,0,1) ||
                isWin(getRow,getColumn,getValue,1,1)) {
            return Result.WIN;
        }
        empty--;
        if (empty == 0){
            return Result.DRAW;
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }
    private boolean isWin(int getRow, int getColumn, Cell getValue, int r, int c){
        int inDiag1 = 0, inDiag2 = 0, inRow = 0, inColumn = 0, u;
        for(int i = 1; i >= -1; i -= 2) {
            r = r * i;
            c = c * i;
            u = 0;
            while (getRow + u * r >= 0 && getRow + u * r < row && getColumn + u * c >= 0 &&
                    getColumn + u * c < column && cells[getRow + u * r][getColumn + u * c] == getValue) {
                if (c == 0) {
                    inRow++;
                }
                if (r == 0) {
                    inColumn++;
                }
                if (r + c == 0) {
                    inDiag1++;
                }
                if (r - c == 0) {
                    inDiag2++;
                }
                u++;
            }
            if(inRow >= k || inColumn >= k || inDiag1 >= k || inDiag2 >= k){
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < row
                && 0 <= move.getColumn() && move.getColumn() < column
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }
    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ".repeat(Integer.toString(row).length()+1));
        for(int i = 1; i <= column; i++){
            sb.append(i).append(" ".repeat(Integer.toString(column).length() - Integer.toString(i + 1).length() + 1));
        }
        for (int r = 0; r < row; r++) {
            sb.append("\n");
            sb.append((r + 1)).append(" ".repeat(Integer.toString(row).length() - Integer.toString(r + 1).length() + 1));
            for (int c = 0; c < column; c++) {
                sb.append(SYMBOLS.get(cells[r][c])).append(" ".repeat(Integer.toString(column).length()));
            }
        }
        return sb.toString();
    }
}