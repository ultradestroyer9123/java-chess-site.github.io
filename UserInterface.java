import javax.swing.*;
import java.util.ArrayList;
import java.awt.Image;

public class UserInterface {
  static piece selectedPiece = null;
  static java.awt.Color white_square = java.awt.Color.decode("#f0d9b5");
  static java.awt.Color black_square = java.awt.Color.decode("#b58863");

  static ChessEngine engine;
  static String color;

  static JPanel[][] squares = new JPanel[8][8];
  static ArrayList<JButton> avaliableMoves = new ArrayList<JButton>();
  static ArrayList<String> legalMoves = new ArrayList<String>();

  public UserInterface(ChessEngine engine) {
    this.engine = engine;
  }

  public static void main(String[] args) {
    engine = new ChessEngine();
    JFrame frame = new JFrame("Java Chess");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 800);
    frame.setLayout(new java.awt.GridLayout(8, 8));

    ArrayList<piece> pieces = new ArrayList<>();

    // Add pawns
    for (int col = 0; col < 8; col++) {
      pieces.add(new piece(col, 1, "white", "p"));
      pieces.add(new piece(col, 6, "black", "p"));
    }

    // Add rooks
    pieces.add(new piece(0, 0, "white", "r"));
    pieces.add(new piece(7, 0, "white", "r"));
    pieces.add(new piece(0, 7, "black", "r"));
    pieces.add(new piece(7, 7, "black", "r"));

    // Add knights
    pieces.add(new piece(1, 0, "white", "h"));
    pieces.add(new piece(6, 0, "white", "h"));
    pieces.add(new piece(1, 7, "black", "h"));
    pieces.add(new piece(6, 7, "black", "h"));

    // Add bishops
    pieces.add(new piece(2, 0, "white", "b"));
    pieces.add(new piece(5, 0, "white", "b"));
    pieces.add(new piece(2, 7, "black", "b"));
    pieces.add(new piece(5, 7, "black", "b"));

    // Add queens
    pieces.add(new piece(3, 0, "white", "q"));
    pieces.add(new piece(3, 7, "black", "q"));

    // Add kings
    pieces.add(new piece(4, 0, "white", "k"));
    pieces.add(new piece(4, 7, "black", "k"));

    // Create all squares first
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        JPanel square = new JPanel();
        if ((row + col) % 2 == 0) {
          square.setBackground(white_square);
        } else {
          square.setBackground(black_square);
        }
        squares[col][row] = square;
        frame.add(square);
      }
    }

    // Add pieces to their respective squares
    for (piece p : pieces) {
      int col = p.getPosX();
      int row = p.getPosY();
      JLabel pieceLabel = new JLabel(new ImageIcon(new ImageIcon(p.getPathOfImage()).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
      pieceLabel.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
          selectedPiece = p;
          for (JButton button : avaliableMoves) {
            button.getParent().remove(button);
          }
          avaliableMoves.clear();
          System.out.println("Selected piece: " + p);
          legalMoves = engine.getLegalMoves(p.getPosition());
          System.out.println("Legal moves: " + legalMoves);
          for (String legalMove : legalMoves) {
            int[] buttonLocation = engine.convertPositionToIntArray(legalMove);
            JButton moveButton = new JButton();
            moveButton.setOpaque(false);
            moveButton.setContentAreaFilled(false);
            moveButton.setBorderPainted(false);
            moveButton.setBounds(0, 0, 100, 100); // Fixed size for the button
            moveButton.addActionListener(b -> {
              engine.move(p.getPosition(), legalMove, legalMoves);
              selectedPiece = null;
              for (JButton button : avaliableMoves) {
                button.getParent().remove(button);
              }
              p.setPosition(buttonLocation[0], buttonLocation[1]);
              pieceLabel.getParent().remove(pieceLabel);
              squares[buttonLocation[1]][buttonLocation[0]].add(pieceLabel);
              avaliableMoves.clear();
              frame.revalidate();
              frame.repaint();
            });
          
            squares[buttonLocation[1]][buttonLocation[0]].setLayout(null); // Use null layout for manual positioning
            squares[buttonLocation[1]][buttonLocation[0]].add(moveButton, 0); // Add button below
            squares[buttonLocation[1]][buttonLocation[0]].add(pieceLabel, 1); // Add piece above
            avaliableMoves.add(moveButton);
          }
        }
      });
      squares[col][row].add(pieceLabel);
    }

    frame.setVisible(true);
  }
}