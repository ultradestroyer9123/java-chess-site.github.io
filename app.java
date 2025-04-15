import java.util.ArrayList;

public class app {
  public static void main(String[] args) {
    ChessEngine engine = new ChessEngine();
    String board = engine.getBoard();
    engine.move("E2", "E4", engine.getLegalMoves("E2"));
    engine.move("D7", "D5", engine.getLegalMoves("D7"));
    engine.move("E4", "D5", engine.getLegalMoves("E4"));
    engine.move("E8", "D7", engine.getLegalMoves("E8"));
    engine.move("A2", "A4", engine.getLegalMoves("A2"));
    engine.move("A7","A5", engine.getLegalMoves("A7"));
    engine.move("D1", "H5", engine.getLegalMoves("D1"));
    engine.move("G7", "G6", engine.getLegalMoves("G7"));
    engine.move("H5", "E5", engine.getLegalMoves("H5"));

    System.out.println(engine.getLegalMoves("E8"));
    engine.get2dBoardAsString();
    // UserInterface ui = new UserInterface(engine.getBoard());
  }
}