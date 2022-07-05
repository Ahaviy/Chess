public class Rook extends ChessPiece {

    public Rook(String color) {
        super(color);
    }

    public Rook(ColorPiece colorPiece) {
        super(colorPiece);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getSymbol() {
        return "R";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn) {
        if (isCoordinatesNotValid(line, toLine, colum, toColumn)) return false;
        if (chessBoard.board[toLine][toColumn] != null && isSameColor(chessBoard.board[toLine][toColumn])) return false;
        if (colum == toColumn) { //Проверка вертикали
            if (line > toLine) { //нижняя часть
                for (int checkingLine = toLine + 1; checkingLine < line; checkingLine++) {
                    if (chessBoard.board[checkingLine][colum] != null)
                        return false; //если на пути есть фигура
                }
            } else { //верхняя чать
                for (int checkingLine = toLine - 1; checkingLine > line; checkingLine--) {
                    if (chessBoard.board[checkingLine][colum] != null) return false; //если на пути есть фигура
                }
            }
            return true;
        }
        if (line == toLine) { //Проверка горизантали
            if (colum > toColumn) { //правая чать
                for (int checkingColum = toColumn + 1; checkingColum < colum; checkingColum++) {
                    if (chessBoard.board[line][checkingColum] != null) return false; //если на пути есть фигура
                }
            } else { //левая часть
                for (int checkingColum = toColumn - 1; checkingColum > colum; checkingColum--) {
                    if (chessBoard.board[line][checkingColum] != null) return false; //если на пути есть фигура
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean canAttackToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn) {
        return canMoveToPosition(chessBoard, line, colum, toLine, toColumn);
    }

}
