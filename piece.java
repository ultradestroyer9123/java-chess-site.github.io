public class piece {
  private int posX;
  private int posY;
  private String color;
  private String type;

  public piece(int posX, int posY, String color, String type) {
    this.posX = posX;
    this.posY = posY;
    this.color = color;
    this.type = type;
  }

  public static String convertPosXToString(int posX) {
    switch (posX) {
      case 0:
        return "A";
      case 1:
        return "B";
      case 2:
        return "C";
      case 3:
        return "D";
      case 4:
        return "E";
      case 5:
        return "F";
      case 6:
        return "G";
      case 7:
        return "H";
      default:
        return null;
    }
  }

  public String getPosition() {
    return convertPosXToString(posX) + (posY + 1);
  }

  public int getPosX() {
    return posX;
  }

  public int getPosY() {
    return posY;
  }

  public String getLetterPosX() {
    return convertPosXToString(posX);
  }

  public String getColor() {
    return color;
  }

  public String getType() {
    return type;
  }

  public String getPathOfImage() {
    return System.getProperty("user.dir") + "\\pieces\\" + color + "\\" + Character.toLowerCase(type.charAt(0)) + ".png";
  }

  public void promote(String promoteTo) {
    type = promoteTo;
  }

  public void setPosition(int posX, int posY) {
    this.posX = posX;
    this.posY = posY;
  }

  public String toString() {
    return "Piece{" +
            "posX=" + posX +
            ", posY=" + posY +
            ", color='" + color + '\'' +
            ", type='" + type + '\'' +
            '}';
  }
}
