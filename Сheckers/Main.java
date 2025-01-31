import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class Main extends JPanel {

    public static void main(String[] args) {
        JFrame window = new JFrame("Шашки");
        Main content = new Main();
        window.setContentPane(content);
        window.pack();
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize(); //класс, который хранит два числа, "awt.toolkit", нахождение окна
        window.setLocation( (screensize.width - window.getWidth())/2,
                (screensize.height - window.getHeight())/2 );
        window.setResizable(false);
        window.setVisible(true);
        ImageIcon icon = new ImageIcon("g0.png");
        window.setIconImage(icon.getImage());
    }

    private JButton newGameButton;
    private JButton resignButton;
    private JLabel message;

    public Main() {// контен панель
        setLayout(null);
        setPreferredSize( new Dimension(550,400) );
        setBackground(new Color(0,150,0));
        Board board = new Board();//окно с игрой и размещение всего
        add(board);
        add(newGameButton);
        add(resignButton);
        add(message);

        board.setBounds(20,20,324,324);
        newGameButton.setBounds(400, 150, 120, 30);
        resignButton.setBounds(400, 210, 120, 30);
        message.setBounds(-163, 360, 700, 30);
    }

    private static class CheckersMove {
        int fromRow, fromCol;//фигура
        int toRow, toCol;//место
        CheckersMove(int r1, int c1, int r2, int c2) {
            fromRow = r1;
            fromCol = c1;
            toRow = r2;
            toCol = c2;
        }
        boolean isJump() {
            return (fromRow - toRow == 2 || fromRow - toRow == -2);//при ходе фигура перемещается на две строки
        }
    }

    private class Board extends JPanel implements ActionListener, MouseListener {//шахматная доска
        CheckersData board;//данные о шашках
        boolean gameInProgress;
        int currentPlayer;
        int selectedRow, selectedCol;//выбранная фигра (место)
        CheckersMove[] legalMoves;//допустимые ходы для игркока
        Board() {
            setBackground(Color.BLACK);
            addMouseListener(this);
            resignButton = new JButton("Сдаться");
            resignButton.addActionListener(this);
            newGameButton = new JButton("Новая игра");
            newGameButton.addActionListener(this);
            message = new JLabel("",JLabel.CENTER);
            message.setFont(new  Font("Serif", Font.BOLD, 25));
            message.setForeground(Color.green);
            board = new CheckersData();
            doNewGame();
        }

        public void actionPerformed(ActionEvent evt) {
            Object src = evt.getSource();
            if (src == newGameButton)
                doNewGame();
            else if (src == resignButton)
                doResign();
        }

        void doNewGame() {
            if (gameInProgress == true) {
                message.setText("Сначала завершите текущую игру!");
                return;
            }
            board.setUpGame();
            currentPlayer = CheckersData.RED;
            legalMoves = board.getLegalMoves(CheckersData.RED);
            selectedRow = -1;//ещё не выбран ход
            message.setText("Красные: ваш ход");
            gameInProgress = true;
            newGameButton.setEnabled(false);
            resignButton.setEnabled(true);
            repaint();
        }

        void doResign() {
            if (gameInProgress == false) {
                message.setText("Никто ещё не сходил");
                return;
            }
            if (currentPlayer == CheckersData.RED)
                gameOver("Красные сдались, чёрные выйграли");
            else
                gameOver("Чёрные сдались, красные выйграли");
        }

        void gameOver(String str) {
            message.setText(str);
            newGameButton.setEnabled(true);
            resignButton.setEnabled(false);
            gameInProgress = false;
        }

        void doClickSquare(int row, int col) {// при нажатии на фигуру
            for (int i = 0; i < legalMoves.length; i++)
                if (legalMoves[i].fromRow == row && legalMoves[i].fromCol == col) {
                    selectedRow = row;
                    selectedCol = col;
                    if (currentPlayer == CheckersData.RED)
                        message.setText("Красные: ваш ход");
                    else
                        message.setText("Чёрные: ваш ход");
                    repaint();
                    return;
                }
            if (selectedRow < 0) {
                message.setText("Нажми на фигуру, если хочешь сходить");
                return;
            }
            for (int i = 0; i < legalMoves.length; i++)
                if (legalMoves[i].fromRow == selectedRow && legalMoves[i].fromCol == selectedCol
                        && legalMoves[i].toRow == row && legalMoves[i].toCol == col) {
                    doMakeMove(legalMoves[i]);
                    return;
                }
            message.setText("Выберете куда хотите сходить");

        }

        void doMakeMove(CheckersMove move) {

            board.makeMove(move);// съесть вторую фигуру

            if (move.isJump()) {
                legalMoves = board.getLegalJumpsFrom(currentPlayer,move.toRow,move.toCol);
                if (legalMoves != null) {
                    if (currentPlayer == CheckersData.RED)
                        message.setText("Красный: ты должны сходить");
                    else
                        message.setText("Чёрный: ты должны сходить");
                    selectedRow = move.toRow;//перемещение фигуры при поедании
                    selectedCol = move.toCol;
                    repaint();
                    return;
                }
            }

            if (currentPlayer == CheckersData.RED) {//переход на след игрока
                currentPlayer = CheckersData.BLACK;
                legalMoves = board.getLegalMoves(currentPlayer);
                if (legalMoves == null)
                    gameOver("У чёрных больше нету ходов, красные выйграли");
                else if (legalMoves[0].isJump())
                    message.setText("Чёрные: ваш ход, вы должны съесть");
                else
                    message.setText("Чёрные: ваш ход");
            }
            else {
                currentPlayer = CheckersData.RED;
                legalMoves = board.getLegalMoves(currentPlayer);
                if (legalMoves == null)
                    gameOver("У красных больше нету ходов, чёрные выйграли");
                else if (legalMoves[0].isJump())
                    message.setText("Красные: ваш ход, вы должны съесть");
                else
                    message.setText("Красные: ваш ход");
            }

            selectedRow = -1;

            if (legalMoves != null) {//из знака любезности
                boolean sameStartSquare = true;
                for (int i = 1; i < legalMoves.length; i++)
                    if (legalMoves[i].fromRow != legalMoves[0].fromRow
                            || legalMoves[i].fromCol != legalMoves[0].fromCol) {
                        sameStartSquare = false;
                        break;
                    }
                if (sameStartSquare) {
                    selectedRow = legalMoves[0].fromRow;
                    selectedCol = legalMoves[0].fromCol;
                }
            }
            repaint();
        }

        public void paintComponent(Graphics g) {

            g.setColor(Color.black);
            g.drawRect(0,0,getSize().width-1,getSize().height-1);
            g.drawRect(1,1,getSize().width-3,getSize().height-3);

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if ( row % 2 == col % 2 )
                        g.setColor(Color.LIGHT_GRAY);
                    else
                        g.setColor(Color.GRAY);
                    g.fillRect(2 + col*40, 2 + row*40, 40, 40);
                    switch (board.pieceAt(row,col)) {
                        case CheckersData.RED:
                            g.setColor(Color.RED);
                            g.fillOval(6 + col*40, 6 + row*40, 30, 30);
                            break;
                        case CheckersData.BLACK:
                            g.setColor(Color.BLACK);
                            g.fillOval(6 + col*40, 6 + row*40, 30, 30);
                            break;
                        case CheckersData.RED_KING:
                            g.setColor(Color.RED);
                            g.fillOval(6 + col*40, 6 + row*40, 30, 30);
                            g.setColor(Color.WHITE);
                            g.drawString("K", 18 + col*40, 26 + row*40);
                            break;
                        case CheckersData.BLACK_KING:
                            g.setColor(Color.BLACK);
                            g.fillOval(4 + col*40, 4 + row*40, 30, 30);
                            g.setColor(Color.WHITE);
                            g.drawString("K", 18 + col*40, 26 + row*40);
                            break;
                    }
                }
            }

            if (gameInProgress) {
                g.setColor(Color.cyan);
                for (int i = 0; i < legalMoves.length; i++) {
                    g.drawRect(2 + legalMoves[i].fromCol*40, 2 + legalMoves[i].fromRow*40, 38, 38);
                    g.drawRect(4 + legalMoves[i].fromCol*40, 4 + legalMoves[i].fromRow*40, 34, 34);
                }

                if (selectedRow >= 0) {
                    g.setColor(Color.white);
                    g.drawRect(2 + selectedCol*40, 2 + selectedRow*40, 38, 38);
                    g.drawRect(4 + selectedCol*40, 4 + selectedRow*40, 34, 34);
                    g.setColor(Color.green);
                    for (int i = 0; i < legalMoves.length; i++) {
                        if (legalMoves[i].fromCol == selectedCol && legalMoves[i].fromRow == selectedRow) {
                            g.drawRect(2 + legalMoves[i].toCol*40, 2 + legalMoves[i].toRow*40, 38, 38);
                            g.drawRect(4 + legalMoves[i].toCol*40, 4 + legalMoves[i].toRow*40, 34, 34);
                        }
                    }
                }
            }
        }

        public void mousePressed(MouseEvent evt) {
            if (gameInProgress == false)
                message.setText("Нажми \"новоя игра\" что бы начать новую игру");
            else {
                int col = (evt.getX() - 4) / 40;
                int row = (evt.getY() - 4) / 40;
                if (col >= 0 && col < 8 && row >= 0 && row < 8)
                    doClickSquare(row,col);
            }
        }
        public void mouseReleased(MouseEvent evt) { }
        public void mouseClicked(MouseEvent evt) { }
        public void mouseEntered(MouseEvent evt) { }
        public void mouseExited(MouseEvent evt) { }
    }

    private static class CheckersData { //Он знает, какая фигура находится на каждом квадрате шахматной доски.

        static final int
                EMPTY = 0,
                RED = 1,
                RED_KING = 2,
                BLACK = 3,
                BLACK_KING = 4;

        int[][] board;

        CheckersData() {// создание доски
            board = new int[8][8];
            setUpGame();
        }

        void setUpGame() {
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if ( row % 2 == col % 2 ) {
                        if (row < 3)
                            board[row][col] = BLACK;
                        else if (row > 4)
                            board[row][col] = RED;
                        else
                            board[row][col] = EMPTY;
                    }
                    else {
                        board[row][col] = EMPTY;
                    }
                }
            }
        }

        int pieceAt(int row, int col) {
            return board[row][col];
        }

        void makeMove(CheckersMove move) {
            makeMove(move.fromRow, move.fromCol, move.toRow, move.toCol);
        }

        void makeMove(int fromRow, int fromCol, int toRow, int toCol) {//переход от (fromRow,fromCol) к (toRow,toCol).
            board[toRow][toCol] = board[fromRow][fromCol];
            board[fromRow][fromCol] = EMPTY;
            if (fromRow - toRow == 2 || fromRow - toRow == -2) {
                int jumpRow = (fromRow + toRow) / 2;
                int jumpCol = (fromCol + toCol) / 2;
                board[jumpRow][jumpCol] = EMPTY;
            }
            if (toRow == 0 && board[toRow][toCol] == RED)
                board[toRow][toCol] = RED_KING;
            if (toRow == 7 && board[toRow][toCol] == BLACK)
                board[toRow][toCol] = BLACK_KING;
        }

        CheckersMove[] getLegalMoves(int player) {//Возвращает массив, содержащий все допустимые ходы шашек
            if (player != RED && player != BLACK)
                return null;
            int playerKing;
            if (player == RED)
                playerKing = RED_KING;
            else
                playerKing = BLACK_KING;

            ArrayList<CheckersMove> moves = new ArrayList<CheckersMove>();// ход будет записан сюда

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (board[row][col] == player || board[row][col] == playerKing) {
                        if (canJump(player, row, col, row+1, col+1, row+2, col+2))
                            moves.add(new CheckersMove(row, col, row+2, col+2));
                        if (canJump(player, row, col, row-1, col+1, row-2, col+2))
                            moves.add(new CheckersMove(row, col, row-2, col+2));
                        if (canJump(player, row, col, row+1, col-1, row+2, col-2))
                            moves.add(new CheckersMove(row, col, row+2, col-2));
                        if (canJump(player, row, col, row-1, col-1, row-2, col-2))
                            moves.add(new CheckersMove(row, col, row-2, col-2));
                    }
                }
            }

            if (moves.size() == 0) {
                for (int row = 0; row < 8; row++) {
                    for (int col = 0; col < 8; col++) {
                        if (board[row][col] == player || board[row][col] == playerKing) {
                            if (canMove(player,row,col,row+1,col+1))
                                moves.add(new CheckersMove(row,col,row+1,col+1));
                            if (canMove(player,row,col,row-1,col+1))
                                moves.add(new CheckersMove(row,col,row-1,col+1));
                            if (canMove(player,row,col,row+1,col-1))
                                moves.add(new CheckersMove(row,col,row+1,col-1));
                            if (canMove(player,row,col,row-1,col-1))
                                moves.add(new CheckersMove(row,col,row-1,col-1));
                        }
                    }
                }
            }

            if (moves.size() == 0)
                return null;
            else {
                CheckersMove[] moveArray = new CheckersMove[moves.size()];//создаём массив, достаточно большой, чтобы вместить все допустимые ходы
                for (int i = 0; i < moves.size(); i++)
                    moveArray[i] = moves.get(i);
                return moveArray;
            }
        }

        CheckersMove[] getLegalJumpsFrom(int player, int row, int col) {//Возвращает список разрешенных прыжков, которые указанный игрок может совершать, начиная с указанной строки и столбца.
            if (player != RED && player != BLACK)
                return null;
            int playerKing;
            if (player == RED)
                playerKing = RED_KING;
            else
                playerKing = BLACK_KING;
            ArrayList<CheckersMove> moves = new ArrayList<CheckersMove>();// Законные переходы будут сохранены в этом списке
            if (board[row][col] == player || board[row][col] == playerKing) {
                if (canJump(player, row, col, row+1, col+1, row+2, col+2))
                    moves.add(new CheckersMove(row, col, row+2, col+2));
                if (canJump(player, row, col, row-1, col+1, row-2, col+2))
                    moves.add(new CheckersMove(row, col, row-2, col+2));
                if (canJump(player, row, col, row+1, col-1, row+2, col-2))
                    moves.add(new CheckersMove(row, col, row+2, col-2));
                if (canJump(player, row, col, row-1, col-1, row-2, col-2))
                    moves.add(new CheckersMove(row, col, row-2, col-2));
            }
            if (moves.size() == 0)
                return null;
            else {
                CheckersMove[] moveArray = new CheckersMove[moves.size()];
                for (int i = 0; i < moves.size(); i++)
                    moveArray[i] = moves.get(i);
                return moveArray;
            }
        }

        private boolean canJump(int player, int r1, int c1, int r2, int c2, int r3, int c3) {//Это вызывается двумя предыдущими методами, чтобы проверить, может ли игрок законно перейти с (r1,c1) на (r3,c3).
            if (r3 < 0 || r3 >= 8 || c3 < 0 || c3 >= 8)
                return false; // (r3,c3) снят с доски.

            if (board[r3][c3] != EMPTY)
                return false; // (r3,c3) уже содержит фрагмент.

            if (player == RED) {
                if (board[r1][c1] == RED && r3 > r1)
                    return false;// Обычная красная фигура может двигаться только вверх.
                if (board[r2][c2] != BLACK && board[r2][c2] != BLACK_KING)
                    return false;// Нет черной фигуры для прыжка.
                return true;// Прыжок является законным.
            }
            else {
                if (board[r1][c1] == BLACK && r3 < r1)
                    return false;
                if (board[r2][c2] != RED && board[r2][c2] != RED_KING)
                    return false;
                return true;
            }
        }

        private boolean canMove(int player, int r1, int c1, int r2, int c2) {//Это вызывается методом getLegalMoves(), чтобы определить, является ли игрок может легально перейти из (r1,c1) в (r2,c2).

            if (r2 < 0 || r2 >= 8 || c2 < 0 || c2 >= 8)
                return false;// (r2,c2) снят с доски.

            if (board[r2][c2] != EMPTY)
                return false;// (r2,c2) уже содержит фрагмент.

            if (player == RED) {
                if (board[r1][c1] == RED && r2 > r1)
                    return false;// Обычная красная фигура может двигаться только вниз.
                return true;// Прыжок является законным.
            }
            else {
                if (board[r1][c1] == BLACK && r2 < r1)
                    return false;
                return true;
            }
        }
    }
}