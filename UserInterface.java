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

  }

  public static void main(String[] args) {

  }
}
