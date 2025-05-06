import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class UserInterface {
  Color white_square = Color.decode("#f0d9b5");
  Color black_square = Color.decode("#b58863");
  int square_size = 100;
  int square_side_amount = 8;
  ChessEngine engine;
  String[][] board;
  String color;

  JFrame frame;

  ArrayList<JButton> move_buttons = new ArrayList<>();
  JLabel[][] piece_labels = new JLabel[8][8];

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

  public void refresh() {
    frame.revalidate();
    frame.repaint();
  }

  public static String getPathOfImage(String piece) {
    return System.getProperty("user.dir") + "\\pieces\\" + (piece.toLowerCase().equals(piece) ? "black" : "white") + "\\" + piece.toLowerCase() + ".png";
  }

  public void changePieceAt(String piece, int x, int y) {
    int final_x = x;
    int final_y = y;
    if (color.equals("black")) {
      final_x = 7 - x;
      final_y = 7 - y;
    }

    if (piece_labels[x][y] != null) {
      frame.remove(piece_labels[x][y]);
      piece_labels[x][y] = null;
    }
    JLabel pieceLabel = new JLabel(new ImageIcon(new ImageIcon(getPathOfImage(piece)).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
    pieceLabel.setBounds(x * square_size, y * square_size, square_size, square_size);
    frame.add(pieceLabel);
    piece_labels[final_x][final_y] = pieceLabel;
    pieceLabel.addMouseListener(new java.awt.event.MouseAdapter() {
      @Override
      public void mouseClicked(java.awt.event.MouseEvent e) {
        ArrayList<String> moves = engine.getLegalMoves(engine.convert2NumsToPosition(final_x, final_y));
        for (JButton button : move_buttons) {
          frame.remove(button);
        }
        move_buttons.clear();
        for (String move : moves) {
          int[] position = engine.convertPositionToIntArray(move);
          JButton button = new JButton();
          button.setBounds((position[0] * 100) - 50, (position[1] * 100) - 50, 50, 50);
          button.setFocusPainted(false);
          button.setContentAreaFilled(true); 
          button.setBorderPainted(true);     
          button.setBackground(Color.LIGHT_GRAY);
          frame.add(button);
          move_buttons.add(button);
        }
        refresh();
      }
    });
    
  }

  public void update() {
    String[][] old_board = board;
    board = engine.get2dBoardAsColor(color);
    for (int row = 0; row < square_side_amount; row++) {
      for (int col = 0; col < square_side_amount; col++) {
        if (!old_board[col][row].equals(board[col][row]) || engine.getTurn() == 0) {
          changePieceAt(board[col][row], col, row);
        }
      }
    }
    refresh();
  }
}
