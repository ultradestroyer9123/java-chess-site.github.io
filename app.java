
public class app {
  public static void main(String[] args) {
    String username = "Benjamin";

    ChessEngine engine = new ChessEngine("white");
    UserInterface ui = new UserInterface(engine);
    UserToUser server = new UserToUser(username);
    String previous_board = engine.getBoard();
    server.createGame();

    while (engine.isCheckMateOrStalemate().equals("false") && server.isActive()) {
      if (engine.getWhosMove().equals(engine.getColor())) {
        try {
          System.out.println("Your move!");
          Thread.sleep(1000);
          if (!engine.getBoard().equals(previous_board)) {
            server.updateBoard(engine.getBoard());
            engine.add1ToTurn();
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      } else {
        System.out.println("Opponent's move!");
        // wait 3 seconds
        try {
          Thread.sleep(4000);
          String updated_board = server.getUpdatedBoard();
          if (updated_board != engine.getBoard()) {
            engine.setBoard(updated_board);
            engine.add1ToTurn();
            previous_board = updated_board;
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
    // game ended
    String end_result = engine.getFinalGameState();
    // engine.move("E2", "E4", engine.getLegalMoves("E2"));
    // engine.move("D7", "D5", engine.getLegalMoves("D7"));
    // engine.move("E4", "E5", engine.getLegalMoves("E4"));
    // engine.move("D5", "D4", engine.getLegalMoves("D5"));
    // engine.move("E5", "E6", engine.getLegalMoves("E5"));

    // engine.print2dBoardAsString("white");
    // System.out.println(engine.convertIntArrayToPosition(new int[] { 4, 5 }));
    // UserInterface ui = new UserInterface(engine);

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