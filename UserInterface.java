import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class UserInterface {
  piece selectedPiece = null;
  Color white_square = Color.decode("#f0d9b5");
  Color black_square = Color.decode("#b58863");
  int square_size = 100;
  int square_side_amount = 8;
  ChessEngine engine;
  String[][] board;
  String color;

  JFrame frame;

  JPanel[][] squares = new JPanel[8][8];
  ArrayList<String> legalMoves = new ArrayList<>();
  ArrayList<JPanel> moveToButtons = new ArrayList<>();

  public UserInterface(ChessEngine engine) {
    this.engine = engine;
    this.color = engine.getColor();
    this.board = engine.get2dBoardAsColor(color);

    frame = new JFrame("Java Chess");
    frame.setSize(square_size * square_side_amount, square_size * square_side_amount);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(null);

    Color first_color = engine.getColor().equals("white") ? white_square : black_square;
    Color second_color = engine.getColor().equals("white") ? black_square : white_square;

    for (int row = 0; row < square_side_amount; row++) {
      for (int col = 0; col < square_side_amount; col++) {
        JPanel square = new JPanel();
        square.setLayout(null);
        square.setBounds(square_size * col, square_size * row, square_size, square_size);
        square.setBackground((col + row) % 2 == 0 ? first_color : second_color);
        frame.add(square);
      }
    }
  }

  public void changePieceAt(String piece, int x, int y) {
    
  }

  public void update() {
    String[][] old_board = board;
    board = engine.get2dBoardAsColor(color);

    for (int row = 0; row < square_side_amount; row++) {
      for (int col = 0; col < square_side_amount; col++) {
        if (!old_board[col][row].equals(board[col][row]) || board[col][row].equals("")) {
          changePieceAt(board[col][row], col, row);
        }
      }
    }
  }

  public static void main(String[] args) {
    ChessEngine engine = new ChessEngine("white");
    UserInterface ui = new UserInterface(engine);
  }
}
