import java.util.ArrayList;

public class app {
  public static void main(String[] args) {
    String username = "Benjamin";

    ChessEngine engine = new ChessEngine();
    UserInterface ui = new UserInterface(engine);

    // while ("board changes") {
    //   ui.updateBoard(engine.convertBoardTo2d(engine.getBoard()));
    // }
    
    // UserToUser server = new UserToUser(username);
    //activeGame.createGame();
    //activeGame.playUser();
    //activeGame.close();


    //String board = engine.getBoard();
    // engine.move("E2", "E4", engine.getLegalMoves("E2"));
    // engine.move("E7", "E5", engine.getLegalMoves("E7"));

    // System.out.println(engine.getLegalMoves("F1"));
    // System.out.println(engine.getWhosMove() + " to move");
    // engine.get2dBoardAsString();
    // UserInterface ui = new UserInterface(engine.getBoard());
  }
}