public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    public King(ColorPiece colorPiece) {
        super(colorPiece);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn) {
        if (isCoordinatesNotValid(line, toLine, colum, toColumn)) return false;
        if (chessBoard.board[toLine][toColumn] != null && isSameColor(chessBoard.board[toLine][toColumn])) return false;
        if (isUnderAttack(chessBoard, toLine, toColumn)) return false;
        if ((line - toLine >= -1 && line - toLine <= 1) && (colum - toColumn >= -1 && colum - toColumn <= 1)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canAttackToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn) {
        if (isCoordinatesNotValid(line, toLine, colum, toColumn)) return false;
        if ((line - toLine >= -1 && line - toLine <= 1) && (colum - toColumn >= -1 && colum - toColumn <= 1)) {
            return true;
        }
        return false;
    }

    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        for (int checkL = 0; checkL < 8; checkL++) {
            for (int checkC = 0; checkC < 8; checkC++) {
                if (chessBoard.board[checkL][checkC] != null) {
                    if (!isSameColor(chessBoard.board[checkL][checkC]))  {
                        if (chessBoard.board[checkL][checkC].canAttackToPosition(chessBoard, checkL, checkC, line, column))
                            return true;
                    }
                }
            }
        }
        return false;
    }

}
