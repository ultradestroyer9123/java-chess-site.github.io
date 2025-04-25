import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserInterface {
  static piece selectedPiece = null;
  static Color white_square = Color.decode("#f0d9b5");
  static Color black_square = Color.decode("#b58863");

  static ChessEngine engine;
  static String color;

  static JPanel[][] squares = new JPanel[8][8];
  static ArrayList<String> legalMoves = new ArrayList<>();
  static ArrayList<JPanel> moveToButtons = new ArrayList<>();

  public UserInterface(ChessEngine engine) {
    this.engine = engine;
  }

  public static void main(String[] args) {
    int square_size = 100;
    engine = new ChessEngine();
    JFrame frame = new JFrame("Java Chess");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);
    frame.getContentPane().setBackground(Color.BLACK);
    frame.setVisible(true); // Make frame visible first to calculate insets

    // Compute insets and apply proper frame size
    Insets insets = frame.getInsets();
    int boardSize = square_size * 8;
    int totalWidth = boardSize + insets.left + insets.right;
    int totalHeight = boardSize + insets.top + insets.bottom;
    frame.setSize(totalWidth, totalHeight);

    ArrayList<piece> pieces = new ArrayList<>();

    int x = 0;
    int y = 0;
    String board = engine.getBoard();
    for (int i = 0; i < board.length(); i++) {
      x = i % 8; // File (column)
      y = i / 8; // Rank (row)
      String pieceIs = board.charAt(i) + "";
      String pieceColor = pieceIs.toUpperCase().equals(pieceIs) ? "white" : "black";
      String pieceType = pieceIs.toLowerCase();

      JPanel square = new JPanel();
      square.setLayout(null);
      square.setBounds(square_size * x, square_size * y, square_size, square_size);
      square.setBackground((x + y) % 2 == 0 ? white_square : black_square);
      squares[x][y] = square;

      if (!pieceIs.equals(" ")) {
        System.out.println("Piece: " + pieceIs + " at " + pieceIs + " at " + x + ", " + y);
        piece p = new piece(x, y, pieceColor, pieceType);
        System.out.println(p.toString());
        JLabel pieceLabel = new JLabel(new ImageIcon(new ImageIcon(p.getPathOfImage()).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        pieceLabel.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mouseClicked(java.awt.event.MouseEvent e) {
            for (JPanel movePanel : moveToButtons) {
              movePanel.getParent().remove(movePanel);
            }
            moveToButtons.clear();
            frame.revalidate();
            frame.repaint();
            System.out.println("Placing piece: " + pieceIs + " at ");
            System.out.println("Legal moves: " + legalMoves);
            ArrayList<String> legalMoves = engine.getLegalMoves(p.getPosition());
            for (String legalMove : legalMoves) {
              int[] legalMovePosition = engine.convertPositionToIntArray(legalMove);
            
              // Create a transparent JPanel
              JPanel transparentPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                  super.paintComponent(g);
                  Graphics2D g2d = (Graphics2D) g.create();
                  g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); // 50% transparency
                  g2d.setColor(Color.decode("#990000")); // Set the color of the panel
                  g2d.fillRect(0, 0, getWidth(), getHeight()); // Fill the panel with the color
                  g2d.dispose();
                }
              };
            
              transparentPanel.setBounds(0, 0, 100, 100); // Match the square size
              transparentPanel.setOpaque(false); // Ensure the panel is transparent
              transparentPanel.setLayout(null); // No layout manager
            
              // Add a mouse listener for the click event
              transparentPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                  System.out.println("Clicked on move: " + legalMove);
                  // Handle the move logic here
                  engine.move(p.getPosition(), legalMove, legalMoves);
                  engine.get2dBoardAsString("white");
                  selectedPiece = null;
            
                  // Remove all move panels after the move
                  for (JPanel movePanel : moveToButtons) {
                    movePanel.getParent().remove(movePanel);
                  }
                  moveToButtons.clear();
            
                  // Update the piece's position
                  int[] newPosition = engine.convertPositionToIntArray(legalMove);
                  newPosition[0] = newPosition[0]; // Adjust for the GUI coordinate system
                  newPosition[1] = newPosition[1]; // Adjust for the GUI coordinate system
                  System.out.println(legalMove);
                  System.out.println("New position: " + newPosition[0] + ", " + newPosition[1]);
                  p.setPosition(newPosition[0], newPosition[1]);
                  pieceLabel.getParent().remove(pieceLabel);
                  squares[newPosition[0]][newPosition[1]].add(pieceLabel);
            
                  frame.revalidate();
                  frame.repaint();
                }
              });
            
              // Add the transparent panel to the correct square
              JPanel local_square = squares[7 - legalMovePosition[1]][7 - legalMovePosition[0]];
              local_square.setLayout(null); // Ensure manual positioning
              local_square.add(transparentPanel);
              local_square.revalidate();
              local_square.repaint();
            
              moveToButtons.add(transparentPanel); // Keep track of the panels for cleanup
            }
            // legalMoves = engine.getLegalMoves(p.getPosition());
            // System.out.println("Legal moves: " + legalMoves);
            // for (String legalMove : legalMoves) {
            //   int[] legalMovePosition = engine.convertPositionToIntArray(legalMove);
            //   JButton button = moveToButtons[7-legalMovePosition[1]][legalMovePosition[0]];
            //   button.setVisible(true);
            //   button.repaint();
            //   button.revalidate();
            // }
            // frame.revalidate();
            // frame.repaint();
          }
        });
        pieceLabel.setBounds(0, 0, 100, 100); // Position within the square
        square.add(pieceLabel);
      }


      frame.getContentPane().add(square);
    }

    frame.revalidate();
    frame.repaint();
  }
}
