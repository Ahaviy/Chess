public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard() {
        this.nowPlayer = "White";
    }

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int line, int column, int toLine, int toColumn) {
        //TODO Проверки перед ходом
        if (checkPos(line) && checkPos(column)) {

            if (!nowPlayer.equals(board[line][column].getColor())) return false;
            if (!board[line][column].canMoveToPosition(this, line, column, toLine, toColumn)) return false;


            makeMove(line, column, toLine, toColumn, false);
            return true;

        } else return false;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol()
                            + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }


    public boolean castling0() {
        if (nowPlayer.equals("White")) {
            if (board[0][0] == null || board[0][4] == null) return false;
            if (board[0][0].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && // check that King and Rook
                    board[0][1] == null && board[0][2] == null && board[0][3] == null) {              // never moved
                if (board[0][0].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][0].check && board[0][4].check &&
                        !new King("White").isUnderAttack(this, 0, 2)) { // check that position not in under attack
                    makeMove(0, 4, 0, 0, true);
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][0] == null || board[7][4] == null) return false;
            if (board[7][0].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") && // check that King and Rook
                    board[7][1] == null && board[7][2] == null && board[7][3] == null) {              // never moved
                if (board[7][0].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][0].check && board[7][4].check &&
                        !new King("Black").isUnderAttack(this, 7, 2)) { // check that position not in under attack
                    makeMove(7, 4, 7, 0, true);
                    return true;
                } else return false;
            } else return false;
        }
    }

    public boolean castling7() {
        if (nowPlayer.equals("White")) {
            if (board[0][7] == null || board[0][4] == null) return false;
            if (board[0][7].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && // check that King and Rook
                    board[0][5] == null && board[0][6] == null) {              // never moved
                if (board[0][7].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][7].check && board[0][4].check &&
                        !new King("White").isUnderAttack(this, 0, 6)) { // check that position not in under attack
                    makeMove(0, 4, 0, 7, true);
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][7] == null || board[7][4] == null) return false;
            if (board[7][7].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") && // check that King and Rook
                    board[7][6] == null && board[7][5] == null) {              // never moved
                if (board[7][7].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][7].check && board[7][4].check &&
                        !new King("Black").isUnderAttack(this, 7, 6)) { // check that position not in under attack
                    makeMove(7, 4, 7, 7, true);
                    return true;
                } else return false;
            } else return false;
        }
    }


    public void makeMove(int line, int column, int toline, int toColumn, boolean isCastling) {
        //Проверяем все клетки, ищем все пешки, обнуляем проверку на возможность взятия на проходе
        for (int Line = 0; Line < 7; Line++) {
            for (int Colum = 0; Colum < 7; Colum++) {
                if (board[Line][Colum] != null && board[Line][Colum].getSymbol().equals("P")) {
                    ((Pawn) board[Line][Colum]).canBeEatenOnTheAisle = false;
                }
            }
        }
        if (isCastling) { //Если рокировка
            if (toColumn == 0) { //длинная рокировка
                board[line][2] = board[line][4];
                board[line][2].check = false;
                board[line][4] = null;
                board[line][3] = board[line][0];
                board[line][3].check = false;
                board[line][0] = null;
            } else { //короткая рокировка
                board[line][6] = board[line][4];
                board[line][6].check = false;
                board[line][4] = null;
                board[line][5] = board[line][7];
                board[line][5].check = false;
                board[line][7] = null;
            }
        } else {
            if (board[line][column].getSymbol().equals("P")) { //Проверки для пешек перед заменой на доске
                //если пешка достигает последней линии предворительно заменяется на выбранную фигуру
                if (board[line][column].isWhite() && line == 6) {
                    board[line][column] = getPieceFromPawn(ColorPiece.WHITE);
                }
                if (!board[line][column].isWhite() && line == 1) {
                    board[line][column] = getPieceFromPawn(ColorPiece.BLACK);
                }
                //если пешка может съесть на проходе, то съедаеммую пешку переносим на клетку назад
                if (((Pawn) board[line][column]).canTakeOnTheAisle(this, line, column, toline, toColumn)) {
                    if (board[line][column].isWhite()) {
                        board[5][toColumn] = board[4][toColumn];
                        board[4][toColumn] = null;
                    } else {
                        board[2][toColumn] = board[3][toColumn];
                        board[3][toColumn] = null;
                    }
                }
            }
            //Перемещение фигуры
            board[toline][toColumn] = board[line][column];
            if (board[toline][toColumn].getSymbol().equals("K") || board[toline][toColumn].getSymbol().equals("R")) {
                board[toline][toColumn].check = false;
            }
            board[line][column] = null;
        }
        nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White"; //смена игрока
    }

    private ChessPiece getPieceFromPawn(ColorPiece color) {
        //TODO реализовать выбор фигуры
        return new Queen(color); //заглушка
    }



/*    public boolean castlingWithH() {
        if (nowPlayer.equals("White")) {
            if (board[0][7] == null || board[0][4] == null) return false;
            if (!board[0][7].getSymbol().equals("R") || !board[0][4].getSymbol().equals("K")) return false;
            if (!board[0][7].getColor().equals("White") || !board[0][4].getColor().equals("White")) return false;
            if (!board[0][7].check || !board[0][4].check) return false;
            if (board[0][5] != null || board[0][6] != null) return false;
            if (((King) board[0][4]).isUnderAttack(this, 0, 5) ||
                    ((King) board[0][4]).isUnderAttack(this, 0, 6)) return false;
            board[0][5] = board[0][7];
            board[0][7] = null;
            board[0][6] = board[0][4];
            board[0][4] = null;
            nowPlayer = "Black";
            return true;
        } else {
            if (board[7][7] == null || board[7][4] == null) return false;
            if (!board[7][7].getSymbol().equals("R") || !board[7][4].getSymbol().equals("K")) return false;
            if (!board[7][7].getColor().equals("Black") || !board[7][4].getColor().equals("Black")) return false;
            if (!board[7][7].check || !board[7][4].check) return false;
            if (board[7][5] != null || board[7][6] != null) return false;
            if (((King) board[7][4]).isUnderAttack(this, 7, 5) ||
                    ((King) board[7][4]).isUnderAttack(this, 7, 6)) return false;
            board[7][5] = board[7][7];
            board[7][7] = null;
            board[7][6] = board[7][4];
            board[7][4] = null;
            nowPlayer = "White";
            return true;
        }
    }*/
}
