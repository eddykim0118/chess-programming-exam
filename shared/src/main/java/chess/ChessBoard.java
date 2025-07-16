package chess;

import java.util.Arrays;
import java.util.Objects;
/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    private ChessPiece [][] board;

    public ChessBoard() {
        board = new ChessPiece [8][8];
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        int row = position.getRow() - 1;
        int col = position.getColumn() - 1;

        board [row][col] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        int row = position.getRow() - 1;
        int col = position.getColumn() - 1;

        if (row < 0 || row > 7 || col < 0 || col > 7) {
            return null;
        }

        return board [row][col];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        board = new ChessPiece [8][8];

        setupInitialPosition();
    }

    private void setupInitialPosition() {
        ChessPiece.PieceType [] backRow = {
                ChessPiece.PieceType.ROOK,
                ChessPiece.PieceType.KNIGHT,
                ChessPiece.PieceType.BISHOP,
                ChessPiece.PieceType.QUEEN,
                ChessPiece.PieceType.KING,
                ChessPiece.PieceType.BISHOP,
                ChessPiece.PieceType.KNIGHT,
                ChessPiece.PieceType.ROOK,
        };

        for (int col = 1; col <= 8; col++) {
            addPiece(new ChessPosition(1, col), new ChessPiece(ChessGame.TeamColor.WHITE, backRow[col - 1]));
            addPiece(new ChessPosition(2, col), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));

            addPiece(new ChessPosition(8, col), new ChessPiece(ChessGame.TeamColor.BLACK, backRow[col - 1]));
            addPiece(new ChessPosition(7, col), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
        }
    }

    @Override
    public boolean equals(Object ob) {
        if (this == ob) return true;
        if (ob == null || getClass() != ob.getClass()) return false;

        ChessBoard that = (ChessBoard) ob;
        return Arrays.deepEquals(board, that.board);
    }

    @Override
    public int hashCode() { return Arrays.deepHashCode(board); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int row = 7; row >= 0; row--) {
            sb.append(row + 1).append(" |");

            for (int col = 0; col < 8; col++) {
                ChessPiece piece = board [row][col];
                if (piece == null) {
                    sb.append(" . ");
                } else {
                    char colorChar = piece.getTeamColor() == ChessGame.TeamColor.WHITE ? 'W' : 'B';
                    char pieceChar = switch (piece.getPieceType()) {
                        case KING -> 'K';
                        case QUEEN -> 'Q';
                        case ROOK -> 'R';
                        case BISHOP -> 'B';
                        case KNIGHT -> 'N';
                        case PAWN -> 'P';
                    };
                    sb.append(" ").append(colorChar).append(pieceChar);
                }
                sb.append(" |");
            }
            sb.append("\n");
        }
        sb.append("   ");
        for (char col = 'a'; col <= 'h'; col++) {
            sb.append("  ").append(col).append(" ");
        }
        sb.append("\n");

        return sb.toString();
    }
}
