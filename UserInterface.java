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
  String color;

  JFrame frame;

  JPanel[][] squares = new JPanel[8][8];
  ArrayList<String> legalMoves = new ArrayList<>();
  ArrayList<JPanel> moveToButtons = new ArrayList<>();

  public UserInterface(ChessEngine engine) {
    this.engine = engine;
    this.color = engine.getColor();

    frame = new JFrame("Java Chess");
  }

  public void initialize() {
    frame.setSize(square_size * square_side_amount, square_size * square_side_amount);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(null);

    for (int row = 0; row < square_side_amount; row++) {
      for (int col = 0; col < square_side_amount; col++) {
        
      }
    }
  }

  public void update() {
    String[][] new_board = engine.get2dBoardAsColor(color);


  }
}
