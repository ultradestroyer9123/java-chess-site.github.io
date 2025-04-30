import java.util.ArrayList;

public class app {
  public static void main(String[] args) {
    String username = "Benjamin";

    ChessEngine engine = new ChessEngine("white");
    UserInterface ui = new UserInterface(engine);
    UserToUser server = new UserToUser(username);
    String previous_board = engine.getBoard();
    server.createGame();

    while (!engine.isCheckMate() && server.isActive()) {
      if (engine.getWhosMove().equals(engine.getColor())) {
        try {
          System.out.println("Your move!");
          Thread.sleep(1000);
          if (!engine.getBoard().equals(previous_board)) {
            server.updateBoard(engine.getBoard());
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


    String[][] bT = engine.get2dBoardAsString("white");
    System.out.println(bT[0][0] + " " + bT[0][1] + " " + bT[0][2] + " " + bT[0][3] + " " + bT[0][4] + " " + bT[0][5] + " " + bT[0][6] + " " + bT[0][7]);
    System.out.println(bT[1][0] + " " + bT[1][1] + " " + bT[1][2] + " " + bT[1][3] + " " + bT[1][4] + " " + bT[1][5] + " " + bT[1][6] + " " + bT[1][7]);
    System.out.println(bT[2][0] + " " + bT[2][1] + " " + bT[2][2] + " " + bT[2][3] + " " + bT[2][4] + " " + bT[2][5] + " " + bT[2][6] + " " + bT[2][7]);
    System.out.println(bT[3][0] + " " + bT[3][1] + " " + bT[3][2] + " " + bT[3][3] + " " + bT[3][4] + " " + bT[3][5] + " " + bT[3][6] + " " + bT[3][7]);
    System.out.println(bT[4][0] + " " + bT[4][1] + " " + bT[4][2] + " " + bT[4][3] + " " + bT[4][4] + " " + bT[4][5] + " " + bT[4][6] + " " + bT[4][7]);
    System.out.println(bT[5][0] + " " + bT[5][1] + " " + bT[5][2] + " " + bT[5][3] + " " + bT[5][4] + " " + bT[5][5] + " " + bT[5][6] + " " + bT[5][7]);
    System.out.println(bT[6][0] + " " + bT[6][1] + " " + bT[6][2] + " " + bT[6][3] + " " + bT[6][4] + " " + bT[6][5] + " " + bT[6][6] + " " + bT[6][7]);
    System.out.println(bT[7][0] + " " + bT[7][1] + " " + bT[7][2] + " " + bT[7][3] + " " + bT[7][4] + " " + bT[7][5] + " " + bT[7][6] + " " + bT[7][7]);
    System.out.println("Whos move: " + engine.getWhosMove());
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