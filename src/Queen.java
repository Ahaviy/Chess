public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    public Queen(Color color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }

    @Override
    public boolean is(Piece piece) {
        if (piece == null) return false;
        return piece == Piece.QUEEN;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn) {
        if (isCoordinatesNotValid(line, toLine, colum, toColumn)) return false;
        if (chessBoard.board[toLine][toColumn] != null && isSameColor(chessBoard.board[toLine][toColumn])) return false;
        return canAttackToPosition(chessBoard, line, colum, toLine, toColumn);
    }

    @Override
    public boolean canAttackToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn) {
        if (isCoordinatesNotValid(line, toLine, colum, toColumn)) return false;
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
        if (line - toLine == colum - toColumn) { //Проверка возрастающей диагонали
            if (line > toLine) {//левая часть
                for (int checkingLine = toLine + 1; checkingLine < line; checkingLine++) {
                    int checkingColum = checkingLine - line + colum;
                    if (chessBoard.board[checkingLine][checkingColum] != null) return false; //если на пути есть фигура
                }
            } else { //правая часть
                for (int checkingLine = toLine - 1; checkingLine > line; checkingLine--) {
                    int checkingColum = checkingLine - line + colum;
                    if (chessBoard.board[checkingLine][checkingColum] != null) return false; //если на пути есть фигура
                }
            }
            return true;
        }
        if (line - toLine == toColumn - colum) { //Поверка убывающей диагонали
            if (line > toLine) { //левая часть
                for (int checkingLine = toLine + 1; checkingLine < line; checkingLine++) {
                    int checkingColum = line - checkingLine + colum;
                    if (chessBoard.board[checkingLine][checkingColum] != null)
                        return false; //если на пути есть фигура
                }
            } else { //правая часть
                for (int checkingLine = toLine - 1; checkingLine > line; checkingLine--) {
                    int checkingColum = line - checkingLine + colum;
                    if (chessBoard.board[checkingLine][checkingColum] != null)
                        return false; //если на пути есть фигура
                }
            }
            return true;
        }
        return false;
    }

}
