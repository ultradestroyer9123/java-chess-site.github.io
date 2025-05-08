import java.awt.*;
import java.awt.event.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.*;

public class UserInterface {
  private final Color whiteSquare = Color.decode("#f0d9b5");
  private final Color blackSquare = Color.decode("#b58863");
  private final int squareSize = 100;
  private final int boardSize = 8;

  private final ChessEngine engine;
  private final String color;

  private final JFrame frame;
  private final JPanel boardPanel = new JPanel(new GridLayout(boardSize, boardSize));
  private final JPanel[][] squares = new JPanel[boardSize][boardSize];
  private final JLabel[][] pieceLabels = new JLabel[boardSize][boardSize];
  private final ArrayList<JButton> moveButtons = new ArrayList<>();

  private String[][] board;

  public UserInterface(ChessEngine engine) {
    this.engine = engine;
    this.color = engine.getColor();
    this.board = engine.get2dBoardAsColor(color);

    frame = new JFrame("Chess Game");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(squareSize * boardSize, squareSize * boardSize);
    frame.setLayout(new BorderLayout());

    setupBoard();
    drawBoard();

    frame.add(boardPanel, BorderLayout.CENTER);
    frame.setVisible(true);
  }

  private void setupBoard() {
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        JPanel square = new JPanel(new BorderLayout());
        square.setPreferredSize(new Dimension(squareSize, squareSize));
        boolean isWhite = (row + col) % 2 == 0;
        square.setBackground(isWhite ? whiteSquare : blackSquare);
        int finalRow = row;
        int finalCol = col;
        square.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent e) {
            handleSquareClick(finalRow, finalCol);
          }
        });
        squares[row][col] = square;
        boardPanel.add(square);
      }
    }
  }

  private void drawBoard() {
    board = engine.get2dBoardAsColor(color);
    for (int row = 0; row < boardSize; row++) {
        for (int col = 0; col < boardSize; col++) {
            JPanel square = squares[row][col];
            square.removeAll();

            String piece = board[row][col];
            if (!piece.equals(" ")) {
                String imagePath = getPathOfImage(piece);
                ImageIcon icon = new ImageIcon(imagePath);
                if (icon.getIconWidth() == -1) {
                    System.err.println("Missing image: " + imagePath);
                } else {
                    JLabel label = new JLabel(icon);
                    pieceLabels[row][col] = label;
                    square.add(label);
                }
            }

            // Flip the board if the color is black
            boolean isWhite = (row + col) % 2 == 0;
            square.setBackground(isWhite ? whiteSquare : blackSquare);
            
            // Only flip for black player's view when drawing (not when calculating moves)
            if (color.equals("black")) {
                row = 7 - row;  // Flip the row index
            }

            square.revalidate();
            square.repaint();
        }
    }
  }

  private void handleSquareClick(int row, int col) {
    clearMoveButtons();
    drawBoard(); // Ensure board is reset

    String piece = board[row][col];
    System.out.println("Clicked square (" + row + ", " + col + ") with piece: '" + piece + "'");

    if (piece.equals(" ")) {
        System.out.println("Empty square, nothing to do.");
        return;
    }

    boolean isPlayerPiece = (Character.isUpperCase(piece.charAt(0)) && color.equals("white"))
            || (Character.isLowerCase(piece.charAt(0)) && color.equals("black"));

    if (!isPlayerPiece) {
        System.out.println("Not your piece.");
        return;
    }

    String from = engine.convert2NumsToPosition(row, col);
    ArrayList<String> legalMoves = engine.getLegalMoves(from);

    System.out.println("Legal moves for " + from + ": " + legalMoves);

    for (String move : legalMoves) {
        int[] targetPosition = engine.convertPositionToIntArray(move);
        int targetRow = targetPosition[0];
        int targetCol = targetPosition[1];

        // Do not flip targetRow or targetCol for move calculation
        // Flip for black perspective only when rendering (in drawBoard), not here
        if (color.equals("black")) {
            // Target coordinates should remain as they are for move calculation
            // Any flip or transformation should happen when drawing the board
        }

        System.out.println("  -> Target square for move " + move + ": (" + targetRow + ", " + targetCol + ")");

        // Ensure target indices are valid
        if (targetRow < 0 || targetRow >= boardSize || targetCol < 0 || targetCol >= boardSize) {
            System.out.println("Invalid move: Out of bounds.");
            continue;  // Skip if the move is out of bounds
        }

        JButton moveButton = new JButton();
        moveButton.setOpaque(false);
        moveButton.setContentAreaFilled(false);
        moveButton.setBorderPainted(true);
        moveButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

        moveButton.addActionListener(e -> {
            System.out.println("Executing move from " + from + " to " + move);
            // Call the engine's move method to actually make the move
            engine.move(from, move, legalMoves);
            engine.print2dBoardAsString("white");
            clearMoveButtons();
            drawBoard(); // Update the board after the move
        });

        moveButtons.add(moveButton);

        // Ensure button is added within bounds
        if (targetRow >= 0 && targetRow < boardSize && targetCol >= 0 && targetCol < boardSize) {
            squares[targetRow][targetCol].add(moveButton);
            squares[targetRow][targetCol].revalidate();
            squares[targetRow][targetCol].repaint();
            System.out.println("  -> Button added to square (" + targetRow + ", " + targetCol + ")");
        }
    }

    boardPanel.revalidate();
    boardPanel.repaint();
  }

  private void clearMoveButtons() {
    for (JButton btn : moveButtons) {
      Container parent = btn.getParent();
      if (parent != null) parent.remove(btn);
    }
    moveButtons.clear();
    boardPanel.revalidate();
    boardPanel.repaint();
  }

  private static String getPathOfImage(String piece) {
    String pieceColor = Character.isLowerCase(piece.charAt(0)) ? "black" : "white";
    String fileName = piece.toLowerCase() + ".png";
    return Paths.get(System.getProperty("user.dir"), "pieces", pieceColor, fileName).toString();
  }
}
