import java.util.ArrayList;

public class ChessEngine {
  private String board;
  private int turn = 0;
  private String color = "white";

  public ArrayList<String> getLegalMoves(String pieceAt) {
    ArrayList<String> legalMoves = new ArrayList<>();
    String[][] board2d = convertBoardTo2d(board);
    int[] pos = convertPositionToIntArray(pieceAt);
    String piece = board2d[pos[0]][pos[1]];
    String color = Character.isUpperCase(piece.charAt(0)) ? "white" : "black";
    String enemy = color.equals("white") ? "black" : "white";
    String type = piece.toLowerCase();
    String rank = getRank(pieceAt);
    System.out.println(piece + color + pieceAt);
    if (type.equals("p")) {
      // diagnal moves
      if (isValidPosition(getPositionOffsetOf(pieceAt, -1, 1))
          && color.equals("white") && isPieceAt(getPositionOffsetOf(pieceAt, -1, 1)).equals("black")
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, -1, 1))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, -1, 1));
      }
      if (isValidPosition(getPositionOffsetOf(pieceAt, 1, 1))
          && color.equals("white") && isPieceAt(getPositionOffsetOf(pieceAt, 1, 1)).equals("black")
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, 1, 1))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, 1, 1));
      }
      if (isValidPosition(getPositionOffsetOf(pieceAt, -1, -1))
          && color.equals("black") && isPieceAt(getPositionOffsetOf(pieceAt, -1, -1)).equals("white")
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, -1, 1))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, -1, 1));
      }
      if (isValidPosition(getPositionOffsetOf(pieceAt, 1, -1))
          && color.equals("black") && isPieceAt(getPositionOffsetOf(pieceAt, 1, -1)).equals("white")
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, 1, 1))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, 1, 1));
      }
      // forward moves
      if (isValidPosition(getPositionOffsetOf(pieceAt, 0, 1))
          && color.equals("white") && isPieceAt(getPositionOffsetOf(pieceAt, 0, 1)).equals(" ")
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, 0, 1))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, 0, 1));
      }
      if (isValidPosition(getPositionOffsetOf(pieceAt, 0, 2))
          && color.equals("white") && isPieceAt(getPositionOffsetOf(pieceAt, 0, 2)).equals(" ") && rank.equals("2")
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, 0, 2))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, 0, 2));
      }
      if (isValidPosition(getPositionOffsetOf(pieceAt, 0, -1))
          && color.equals("black") && isPieceAt(getPositionOffsetOf(pieceAt, 0, -1)).equals(" ")
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, 0, -1))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, 0, -1));
      }
      if (isValidPosition(getPositionOffsetOf(pieceAt, 0, -2))
          && color.equals("black") && isPieceAt(getPositionOffsetOf(pieceAt, 0, -2)).equals(" ") && rank.equals("7")
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, 0, -2))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, 0, -2));
      }
    } else if (type.equals("n")) {
      if (isValidPosition(getPositionOffsetOf(pieceAt, -2, 1))
          && isPieceAt(getPositionOffsetOf(pieceAt, -2, 1)) != color
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, -2, 1))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, -2, 1));
      }
      if (isValidPosition(getPositionOffsetOf(pieceAt, -2, -1))
          && isPieceAt(getPositionOffsetOf(pieceAt, -2, -1)) != color
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, -2, -1))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, -2, -1));
      }
      if (isValidPosition(getPositionOffsetOf(pieceAt, 2, 1))
          && isPieceAt(getPositionOffsetOf(pieceAt, 2, 1)) != color
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, 2, 1))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, 2, 1));
      }
      if (isValidPosition(getPositionOffsetOf(pieceAt, 2, -1))
          && isPieceAt(getPositionOffsetOf(pieceAt, 2, -1)) != color
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, 2, -1))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, 2, -1));
      }
      if (isValidPosition(getPositionOffsetOf(pieceAt, 1, 2))
          && isPieceAt(getPositionOffsetOf(pieceAt, 1, 2)) != color
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, 1, 2))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, 1, 2));
      }
      if (isValidPosition(getPositionOffsetOf(pieceAt, 1, -2))
          && isPieceAt(getPositionOffsetOf(pieceAt, 1, -2)) != color
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, 1, -2))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, 1, -2));
      }
      if (isValidPosition(getPositionOffsetOf(pieceAt, -1, 2))
          && isPieceAt(getPositionOffsetOf(pieceAt, -1, 2)) != color
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, -1, 2))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, -1, 2));
      }
      if (isValidPosition(getPositionOffsetOf(pieceAt, -1, -2))
          && isPieceAt(getPositionOffsetOf(pieceAt, -1, -2)) != color
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, -1, -2))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, -1, -2));
      }
    } else if (type.equals("b")) {
      // top left
      int offset = 0;
      while (true) {
        offset++;
        String posOffsetOf = getPositionOffsetOf(pieceAt, -offset, offset);
        if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf) == enemy
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
          break;
        } else if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf).equals(" ")
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
        } else {
          break;
        }
      }
      // top right
      offset = 0;
      while (true) {
        offset++;
        String posOffsetOf = getPositionOffsetOf(pieceAt, offset, offset);
        if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf) == enemy
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
          break;
        } else if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf).equals(" ")
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
        } else {
          break;
        }
      }
      // bottom left
      offset = 0;
      while (true) {
        offset++;
        String posOffsetOf = getPositionOffsetOf(pieceAt, -offset, -offset);
        if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf) == enemy
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
          break;
        } else if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf).equals(" ")
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
        } else {
          break;
        }
      }
      // bottom right
      offset = 0;
      while (true) {
        offset++;
        String posOffsetOf = getPositionOffsetOf(pieceAt, offset, -offset);
        if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf) == enemy
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
          break;
        } else if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf).equals(" ")
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
        } else {
          break;
        }
      }
    } else if (type.equals("r")) {
      // top
      int offset = 0;
      while (true) {
        offset++;
        String posOffsetOf = getPositionOffsetOf(pieceAt, 0, offset);
        if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf) == enemy
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
          break;
        } else if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf).equals(" ")
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
        } else {
          break;
        }
      }
      // right
      offset = 0;
      while (true) {
        offset++;
        String posOffsetOf = getPositionOffsetOf(pieceAt, offset, 0);
        if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf) == enemy
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
          break;
        } else if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf).equals(" ")
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
        } else {
          break;
        }
      }
      // down
      offset = 0;
      while (true) {
        offset++;
        String posOffsetOf = getPositionOffsetOf(pieceAt, 0, -offset);
        if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf) == enemy
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
          break;
        } else if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf).equals(" ")
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
        } else {
          break;
        }
      }
      // left
      offset = 0;
      while (true) {
        offset++;
        String posOffsetOf = getPositionOffsetOf(pieceAt, -offset, 0);
        if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf) == enemy
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
          break;
        } else if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf).equals(" ")
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
        } else {
          break;
        }
      }
    } else if (type.equals("k")) {
      if (isValidPosition(getPositionOffsetOf(pieceAt, -1, 0))
          && isPieceAt(getPositionOffsetOf(pieceAt, -1, 0)) != color
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, -1, 0))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, -1, 0));
      }
      if (isValidPosition(getPositionOffsetOf(pieceAt, 1, 0)) && isPieceAt(getPositionOffsetOf(pieceAt, 1, 0)) != color
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, 1, 0))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, 1, 0));
      }
      if (isValidPosition(getPositionOffsetOf(pieceAt, 0, 1)) && isPieceAt(getPositionOffsetOf(pieceAt, 0, 1)) != color
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, 0, 1))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, 0, 1));
      }
      if (isValidPosition(getPositionOffsetOf(pieceAt, 0, -1))
          && isPieceAt(getPositionOffsetOf(pieceAt, 0, -1)) != color
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, 0, -1))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, 0, -1));
      }
      if (isValidPosition(getPositionOffsetOf(pieceAt, -1, 1))
          && isPieceAt(getPositionOffsetOf(pieceAt, -1, 1)) != color
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, -1, 1))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, -1, 1));
      }
      if (isValidPosition(getPositionOffsetOf(pieceAt, 1, 1)) && isPieceAt(getPositionOffsetOf(pieceAt, 1, 1)) != color
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, 1, 1))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, 1, 1));
      }
      if (isValidPosition(getPositionOffsetOf(pieceAt, -1, -1))
          && isPieceAt(getPositionOffsetOf(pieceAt, -1, -1)) != color
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, -1, -1))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, -1, -1));
      }
      if (isValidPosition(getPositionOffsetOf(pieceAt, 1, -1))
          && isPieceAt(getPositionOffsetOf(pieceAt, 1, -1)) != color
          && !isIllegalMove(turn, color, pieceAt, getPositionOffsetOf(pieceAt, 1, -1))) {
        legalMoves.add(getPositionOffsetOf(pieceAt, 1, -1));
      }
    } else if (type.equals("q")) {
      // top left
      int offset = 0;
      while (true) {
        offset++;
        String posOffsetOf = getPositionOffsetOf(pieceAt, -offset, offset);
        if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf) == enemy
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
          break;
        } else if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf).equals(" ")
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
        } else {
          break;
        }
      }
      // top right
      offset = 0;
      while (true) {
        offset++;
        String posOffsetOf = getPositionOffsetOf(pieceAt, offset, offset);
        if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf) == enemy
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
          break;
        } else if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf).equals(" ")
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
        } else {
          break;
        }
      }
      // bottom left
      offset = 0;
      while (true) {
        offset++;
        String posOffsetOf = getPositionOffsetOf(pieceAt, -offset, -offset);
        if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf) == enemy
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
          break;
        } else if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf).equals(" ")
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
        } else {
          break;
        }
      }
      // bottom right
      offset = 0;
      while (true) {
        offset++;
        String posOffsetOf = getPositionOffsetOf(pieceAt, offset, -offset);
        if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf) == enemy
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
          break;
        } else if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf).equals(" ")
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
        } else {
          break;
        }
      }
      // top
      offset = 0;
      while (true) {
        offset++;
        String posOffsetOf = getPositionOffsetOf(pieceAt, 0, offset);
        if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf) == enemy
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
          break;
        } else if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf).equals(" ")
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
        } else {
          break;
        }
      }
      // right
      offset = 0;
      while (true) {
        offset++;
        String posOffsetOf = getPositionOffsetOf(pieceAt, offset, 0);
        if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf) == enemy
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
          break;
        } else if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf).equals(" ")
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
        } else {
          break;
        }
      }
      // down
      offset = 0;
      while (true) {
        offset++;
        String posOffsetOf = getPositionOffsetOf(pieceAt, 0, -offset);
        if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf) == enemy
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
          break;
        } else if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf).equals(" ")
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
        } else {
          break;
        }
      }
      // left
      offset = 0;
      while (true) {
        offset++;
        String posOffsetOf = getPositionOffsetOf(pieceAt, -offset, 0);
        if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf) == enemy
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
          break;
        } else if (isValidPosition(posOffsetOf) && isPieceAt(posOffsetOf).equals(" ")
            && !isIllegalMove(turn, color, pieceAt, posOffsetOf)) {
          legalMoves.add(posOffsetOf);
        } else {
          break;
        }
      }
    }
    return legalMoves;
  }

  public ChessEngine() {
    board = "rhbqkbhrpppppppp                                PPPPPPPPRHBQKBHR";
  }

  public String getBoard() {
    return board;
  }

  public int getTurn() {
    return turn;
  }

  public boolean isIllegalMove(int turn, String color, String position1, String position2) {
    if (turn % 2 == 0 && color.equals("black")) {
      return true;
    } else if (turn % 2 == 1 && color.equals("white")) {
      return true;
    }
    return inCheck(simulateMove(position1, position2), color);
  }

  public boolean inCheck(String boardP, String color) {
    // Determine the king's symbol based on color
    String kingSymbol = color.equals("white") ? "K" : "k";
    
    // Find the king's position
    int[] pos = findPiece(boardP, kingSymbol);
    if (pos == null) {
      // King not found on the board
      return false;
    }

    // Convert the position to chess notation
    String chessPos = convertIntArrayToPosition(pos);

    // Determine the opposite color
    String oppositeColor = color.equals("white") ? "black" : "white";

    // Check if any piece of the opposite color can capture the king
    return pieceOfOppositeColorCanCapturePosition(boardP, chessPos, oppositeColor);
  }

  public boolean pieceOfOppositeColorCanCapturePosition(String boardP, String positionOfCapture, String color) {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        String piece = boardP.charAt(i * 8 + j) + "";
        if (piece.equals(" ") || !isOpponentPiece(color, piece)) {
          continue;
        }
        String pieceLowered = piece.toLowerCase();
        String piecePos = convertIntArrayToPosition(new int[] { i, j });
        if (pieceLowered.equals("p")) {
          if (getOppositeColor(color).equals("black")) {
            if (isValidAndEquals(piecePos, positionOfCapture, -1, -1) || isValidAndEquals(piecePos, positionOfCapture, 1, -1)) {
              return true;
            }
          } else if (getOppositeColor(color).equals("white")) {
            if (isValidAndEquals(piecePos, positionOfCapture, -1, 1) || isValidAndEquals(piecePos, positionOfCapture, 1, 1)) {
                return true;
            }
          }
        } else if (pieceLowered.equals("n")) {
          if (isValidAndEquals(piecePos, positionOfCapture, -2, 1) || isValidAndEquals(piecePos, positionOfCapture, -2, -1)
              || isValidAndEquals(piecePos, positionOfCapture, 2, 1) || isValidAndEquals(piecePos, positionOfCapture, 2, -1)
              || isValidAndEquals(piecePos, positionOfCapture, 1, 2) || isValidAndEquals(piecePos, positionOfCapture, 1, -2)
              || isValidAndEquals(piecePos, positionOfCapture, -1, 2) || isValidAndEquals(piecePos, positionOfCapture, -1, -2)) {
            return true;
          }
        } else if (pieceLowered.equals("k")) {
          if (isValidAndEquals(piecePos, positionOfCapture, -1, 0) || isValidAndEquals(piecePos, positionOfCapture, 1, 0)
              || isValidAndEquals(piecePos, positionOfCapture, 0, 1) || isValidAndEquals(piecePos, positionOfCapture, 0, -1)
              || isValidAndEquals(piecePos, positionOfCapture, -1, 1) || isValidAndEquals(piecePos, positionOfCapture, 1, 1)
              || isValidAndEquals(piecePos, positionOfCapture, -1, -1) || isValidAndEquals(piecePos, positionOfCapture, 1, -1)) {
            return true;
          }
        } else if (pieceLowered.equals("b")) {
          // top left
          int offset = 0;
          while (true) {
            offset++;
            String posOffsetOf = getPositionOffsetOf(piecePos, -offset, offset);
            if (isValidPosition(posOffsetOf)) {
              if (isPieceAt(posOffsetOf).equals(" ")) {
                continue;
              } else if (posOffsetOf.equals(positionOfCapture)) {
                return true;
              } else {
                break;
              }
            } else {
              break;
            }
          }
          // top right
          offset = 0;
          while (true) {
            offset++;
            String posOffsetOf = getPositionOffsetOf(piecePos, offset, offset);
            if (isValidPosition(posOffsetOf)) {
              if (isPieceAt(posOffsetOf).equals(" ")) {
                continue;
              } else if (posOffsetOf.equals(positionOfCapture)) {
                return true;
              } else {
                break;
              }
            } else {
              break;
            }
          }
          // bottom left
          offset = 0;
          while (true) {
            offset++;
            String posOffsetOf = getPositionOffsetOf(piecePos, -offset, -offset);
            if (isValidPosition(posOffsetOf)) {
              if (isPieceAt(posOffsetOf).equals(" ")) {
                continue;
              } else if (posOffsetOf.equals(positionOfCapture)) {
                return true;
              } else {
                break;
              }
            } else {
              break;
            }
          }
          // bottom right
          offset = 0;
          while (true) {
            offset++;
            String posOffsetOf = getPositionOffsetOf(piecePos, offset, -offset);
            if (isValidPosition(posOffsetOf)) {
              if (isPieceAt(posOffsetOf).equals(" ")) {
                continue;
              } else if (posOffsetOf.equals(positionOfCapture)) {
                return true;
              } else {
                break;
              }
            } else {
              break;
            }
          }
        } else if (pieceLowered.equals("r")) {
          // up
          int offset = 0;
          while (true) {
            offset++;
            String posOffsetOf = getPositionOffsetOf(piecePos, 0, offset);
            if (isValidPosition(posOffsetOf)) {
              if (isPieceAt(posOffsetOf).equals(" ")) {
                continue;
              } else if (posOffsetOf.equals(positionOfCapture)) {
                return true;
              } else {
                break;
              }
            } else {
              break;
            }
          }
          // down
          offset = 0;
          while (true) {
            offset++;
            String posOffsetOf = getPositionOffsetOf(piecePos, 0, -offset);
            if (isValidPosition(posOffsetOf)) {
              if (isPieceAt(posOffsetOf).equals(" ")) {
                continue;
              } else if (posOffsetOf.equals(positionOfCapture)) {
                return true;
              } else {
                break;
              }
            } else {
              break;
            }
          }
          // left
          offset = 0;
          while (true) {
            offset++;
            String posOffsetOf = getPositionOffsetOf(piecePos, -offset, 0);
            if (isValidPosition(posOffsetOf)) {
              if (isPieceAt(posOffsetOf).equals(" ")) {
                continue;
              } else if (posOffsetOf.equals(positionOfCapture)) {
                return true;
              } else {
                break;
              }
            } else {
              break;
            }
          }
          // right
          offset = 0;
          while (true) {
            offset++;
            String posOffsetOf = getPositionOffsetOf(piecePos, offset, 0);
            if (isValidPosition(posOffsetOf)) {
              if (isPieceAt(posOffsetOf).equals(" ")) {
                continue;
              } else if (posOffsetOf.equals(positionOfCapture)) {
                return true;
              } else {
                break;
              }
            } else {
              break;
            }
          }
        } else if (pieceLowered.equals("q")) {
          // top left
          int offset = 0;
          while (true) {
            offset++;
            String posOffsetOf = getPositionOffsetOf(piecePos, -offset, offset);
            if (isValidPosition(posOffsetOf)) {
              if (isPieceAt(posOffsetOf).equals(" ")) {
                continue;
              } else if (posOffsetOf.equals(positionOfCapture)) {
                return true;
              } else {
                break;
              }
            } else {
              break;
            }
          }
          // top right
          offset = 0;
          while (true) {
            offset++;
            String posOffsetOf = getPositionOffsetOf(piecePos, offset, offset);
            if (isValidPosition(posOffsetOf)) {
              if (isPieceAt(posOffsetOf).equals(" ")) {
                continue;
              } else if (posOffsetOf.equals(positionOfCapture)) {
                return true;
              } else {
                break;
              }
            } else {
              break;
            }
          }
          // bottom left
          offset = 0;
          while (true) {
            offset++;
            String posOffsetOf = getPositionOffsetOf(piecePos, -offset, -offset);
            if (isValidPosition(posOffsetOf)) {
              if (isPieceAt(posOffsetOf).equals(" ")) {
                continue;
              } else if (posOffsetOf.equals(positionOfCapture)) {
                return true;
              } else {
                break;
              }
            } else {
              break;
            }
          }
          // bottom right
          offset = 0;
          while (true) {
            offset++;
            String posOffsetOf = getPositionOffsetOf(piecePos, offset, -offset);
            if (isValidPosition(posOffsetOf)) {
              if (isPieceAt(posOffsetOf).equals(" ")) {
                continue;
              } else if (posOffsetOf.equals(positionOfCapture)) {
                return true;
              } else {
                break;
              }
            } else {
              break;
            }
          }
          // up
          offset = 0;
          while (true) {
            offset++;
            String posOffsetOf = getPositionOffsetOf(piecePos, 0, offset);
            if (isValidPosition(posOffsetOf)) {
              if (isPieceAt(posOffsetOf).equals(" ")) {
                continue;
              } else if (posOffsetOf.equals(positionOfCapture)) {
                return true;
              } else {
                break;
              }
            } else {
              break;
            }
          }
          // down
          offset = 0;
          while (true) {
            offset++;
            String posOffsetOf = getPositionOffsetOf(piecePos, 0, -offset);
            if (isValidPosition(posOffsetOf)) {
              if (isPieceAt(posOffsetOf).equals(" ")) {
                continue;
              } else if (posOffsetOf.equals(positionOfCapture)) {
                return true;
              } else {
                break;
              }
            } else {
              break;
            }
          }
          // left
          offset = 0;
          while (true) {
            offset++;
            String posOffsetOf = getPositionOffsetOf(piecePos, -offset, 0);
            if (isValidPosition(posOffsetOf)) {
              if (isPieceAt(posOffsetOf).equals(" ")) {
                continue;
              } else if (posOffsetOf.equals(positionOfCapture)) {
                return true;
              } else {
                break;
              }
            } else {
              break;
            }
          }
          // right
          offset = 0;
          while (true) {
            offset++;
            String posOffsetOf = getPositionOffsetOf(piecePos, offset, 0);
            if (isValidPosition(posOffsetOf)) {
              if (isPieceAt(posOffsetOf).equals(" ")) {
                continue;
              } else if (posOffsetOf.equals(positionOfCapture)) {
                return true;
              } else {
                break;
              }
            } else {
              break;
            }
          }
        }
      }
    }
    return false;
  }

  public String getColor() {
    return color;
  }

  public boolean isValidAndEquals(String piecePos, String positionOfCapture, int offsetX, int offsetY) {
    return isValidPosition(getPositionOffsetOf(piecePos, offsetX, offsetY)) && getPositionOffsetOf(piecePos, offsetX, offsetY).equals(positionOfCapture);
  }

  public boolean isOpponentPiece(String color, String piece) {
    return ((piece.equals(piece.toUpperCase()) ? "white" : "black") != color);
  }

  public String getOppositeColor(String color) {
    return color.equals("white") ? "black" : "white";
  }

  public int[] findPiece(String boardP, String piece) {
    int[] pos = new int[2];
    String[][] board2d = convertBoardTo2d(boardP);
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (board2d[i][j].equals(piece)) {
          pos[0] = i;
          pos[1] = j;
          return pos;
        }
      }
    }
    return null; // Return null if the piece is not found
  }

  public String getWhosMove() {
    return turn % 2 == 0 ? "white" : "black";
  }



  public String getPositionOffsetOf(String position, int xOffset, int yOffset) {
    int[] pos = convertPositionToIntArray(position);
    int newRank = pos[0] + yOffset; // Apply vertical offset
    int newFile = pos[1] + xOffset; // Apply horizontal offset

    // Validate the new position
    if (newRank < 0 || newRank >= 8 || newFile < 0 || newFile >= 8) {
      return null; // Return null for invalid positions
    }

    return convertIntArrayToPosition(new int[] { newRank, newFile });
  }

  public String compressString(String boardP) {
    int letterRepeat = 1;
    char letterRepeating = boardP.charAt(0);
    StringBuilder finalString = new StringBuilder();

    for (int i = 1; i <= boardP.length(); i++) {
      if (i < boardP.length() && boardP.charAt(i) == letterRepeating) {
        letterRepeat++;
      } else {
        if (letterRepeat > 2) {
          finalString.append(letterRepeating).append(letterRepeat);
        } else {
          finalString.append(boardP, i - letterRepeat, i);
        }
        if (i < boardP.length()) {
          letterRepeating = boardP.charAt(i);
          letterRepeat = 1;
        }
      }
    }
    return finalString.toString();
  }

  public String decompressString(String boardP) {
    StringBuilder finalString = new StringBuilder();
    String digits = "0123456789";

    for (int i = 0; i < boardP.length(); i++) {
      char currentChar = boardP.charAt(i);

      if (digits.contains(currentChar + "")) {
        int repeatAmount = currentChar - '0';
        int j = i + 1;

        while (j < boardP.length() && digits.contains(boardP.charAt(j) + "")) {
          repeatAmount = repeatAmount * 10 + (boardP.charAt(j) - '0');
          j++;
        }

        StringBuilder repeatString = new StringBuilder();
        for (int k = 0; k < repeatAmount - 1; k++) {
          repeatString.append(boardP.charAt(i - 1));
        }
        finalString.append(repeatString);
        i = j - 1;
      } else {
        finalString.append(currentChar);
      }
    }

    return finalString.toString();
  }

  public String[][] convertBoardTo2d(String board) {
    String[][] board2d = new String[8][8];
    int index = 0;

    for (int i = 7; i >= 0; i--) { // Start from the bottom row (rank 8)
      for (int j = 0; j < 8; j++) {
        board2d[i][j] = String.valueOf(board.charAt(index++));
      }
    }
    return board2d;
  }

  public String isPieceAt(String position) {
    String[][] board2d = convertBoardTo2d(board);
    int[] pos = convertPositionToIntArray(position);
    String piece = board2d[pos[0]][pos[1]];
    return piece.equals(" ") ? " " : piece.equals(piece.toUpperCase()) ? "white" : "black";
  }

  public String convert2dBoardToString(String[][] board2d) {
    StringBuilder boardString = new StringBuilder();
    for (int i = 7; i >= 0; i--) { // Start from the bottom row (rank 8)
      for (int j = 0; j < 8; j++) {
        boardString.append(board2d[i][j]);
      }
    }
    return boardString.toString();
  }

  public void get2dBoardAsString() {
    String[][] board2d = convertBoardTo2d(board);
    for (int x = 0; x < 8; x++) {
      for (int y = 0; y < 8; y++) {
        System.out.print(board2d[x][y].equals(" ") ? "*" : board2d[x][y]);
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  public boolean isValidPosition(String position) {
    if (position == null) {
      return false;
    }
    int[] pos = convertPositionToIntArray(position);
    return pos[0] >= 0 && pos[0] < 8 && pos[1] >= 0 && pos[1] < 8;
  }

  public int[] convertPositionToIntArray(String position) {
    String letters = "HGFEDCBA";
    position = position.toUpperCase();
    String file = position.substring(0, 1); // File (A-H)
    int rank = Integer.parseInt(position.substring(1, 2)) - 1; // Rank (1-8)
    int fileInt = letters.indexOf(file); // Convert file to 0-7
    return new int[] { rank, fileInt }; // Rank first, file second
  }

  public String convertIntArrayToPosition(int[] position) {
    String letters = "HGFEDCBA";
    return letters.charAt(position[1]) + "" + (position[0] + 1); // File first, rank second
  }

  public String getRank(String position) {
    return position.substring(1, 2);
  }

  public String getFile(String position) {
    return position.substring(0, 1).toUpperCase();
  }

  public void move(String position1, String position2, ArrayList<String> legalMoves) {
    if (containsString(legalMoves, position2)) {
      String[][] board2d = convertBoardTo2d(board);
      int[] pos1 = convertPositionToIntArray(position1);
      int[] pos2 = convertPositionToIntArray(position2);

      String pieceToMove = board2d[pos1[0]][pos1[1]];
      board2d[pos2[0]][pos2[1]] = pieceToMove;
      board2d[pos1[0]][pos1[1]] = " ";
      board = convert2dBoardToString(board2d);

      turn++;
    }
  }

  public String simulateMove(String position1, String position2) {
    String[][] board2d = convertBoardTo2d(board);
    int[] pos1 = convertPositionToIntArray(position1);
    int[] pos2 = convertPositionToIntArray(position2);

    String pieceToMove = board2d[pos1[0]][pos1[1]];
    board2d[pos2[0]][pos2[1]] = pieceToMove;
    board2d[pos1[0]][pos1[1]] = " ";
    return convert2dBoardToString(board2d);
  }

  // Reintroduced containsString method
  public boolean containsString(ArrayList<String> list, String target) {
    return list.contains(target);
  }
}
