package chess;

//import java.util.Collection;
import java.util.*;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private final ChessGame.TeamColor pieceColor;
    private final ChessPiece.PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        ArrayList<ChessMove> moves = new ArrayList<>();

        switch (type) {
            case KING:
                addKingMoves(moves, board, myPosition);
                break;
            case QUEEN:
                addQueenMoves(moves, board, myPosition);
                break;
            case BISHOP:
                addBishopMoves(moves, board, myPosition);
                break;
            case KNIGHT:
                addKnightMoves(moves, board, myPosition);
                break;
            case ROOK:
                addRookMoves(moves, board, myPosition);
                break;
            case PAWN:
                addPawnMoves(moves, board, myPosition);
                break;
        }
        return moves;
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 1 && row <= 8 && col >= 1 && col <= 8;
    }

    private boolean isEmpty(ChessBoard board, ChessPosition position) {
        return board.getPiece(position) == null;
    }

    private void addMoveWithPromotion(ArrayList<ChessMove> moves, ChessPosition start, ChessPosition end, int newRow, int promotionRow) {
        if (newRow == promotionRow) {
            moves.add(new ChessMove(start, end, PieceType.BISHOP));
            moves.add(new ChessMove(start, end, PieceType.KNIGHT));
            moves.add(new ChessMove(start, end, PieceType.QUEEN));
            moves.add(new ChessMove(start, end, PieceType.ROOK));
        } else {
            moves.add(new ChessMove(start, end));
        }
    }

    private void addSlideMoves(ArrayList<ChessMove> moves, ChessBoard board, ChessPosition myPosition, int rowDir, int colDir) {
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        for (int i = 1; i <= 8; i++) {
            int newRow = row + (i * rowDir);
            int newCol = col + (i * colDir);

            if (!isValidPosition(newRow, newCol)) {
                break;
            }

            ChessPosition newPos = new ChessPosition(newRow, newCol);
            ChessPiece pieceAtNewPos = board.getPiece(newPos);

            if (pieceAtNewPos == null) {
                moves.add(new ChessMove(myPosition, newPos));
            } else if (pieceAtNewPos.getTeamColor() != this.pieceColor) {
                moves.add(new ChessMove(myPosition, newPos));
                break;
            } else {
                break;
            }
        }
    }

    private void addKingMoves(ArrayList<ChessMove> moves, ChessBoard board, ChessPosition myPosition) {}

    private void addQueenMoves(ArrayList<ChessMove> moves, ChessBoard board, ChessPosition myPosition) {}

    private void addBishopMoves(ArrayList<ChessMove> moves, ChessBoard board, ChessPosition myPosition) {}

    private void addKnightMoves(ArrayList<ChessMove> moves, ChessBoard board, ChessPosition myPosition) {}

    private void addRookMoves(ArrayList<ChessMove> moves, ChessBoard board, ChessPosition myPosition) {
        int [][] directions = {
                {1, 0}, {-1, 0},
                {0, 1}, {0, -1}
        };

        for (int [] dir : directions) {
            addSlideMoves(moves, board, myPosition, dir[0], dir[1]);
        }
    }

    private void addPawnMoves(ArrayList<ChessMove> moves, ChessBoard board, ChessPosition myPosition) {
        int row = myPosition.getRow();
        int col = myPosition.getColumn();

        int direction = (pieceColor == ChessGame.TeamColor.WHITE) ? 1 : -1;
        int startingRow = (pieceColor == ChessGame.TeamColor.WHITE) ? 2 : 7;
        int promotionRow = (pieceColor == ChessGame.TeamColor.WHITE) ? 8 : 1;

        int newRow = row + direction;
        if (isValidPosition(newRow, col)) {
            ChessPosition newPos = new ChessPosition(newRow, col);
            if (isEmpty(board, newPos)) {
                addMoveWithPromotion(moves, myPosition, newPos, newRow, promotionRow);

                if (row == startingRow) {
                    newRow = row + (2 * direction);
                    if (isValidPosition(newRow, col)) {
                        ChessPosition doubPos = new ChessPosition(newRow, col);
                        if (isEmpty(board, doubPos)) {
                            moves.add(new ChessMove(myPosition, doubPos));
                        }
                    }
                }
            }
        }

        for (int colOffset : new int[] {-1, 1}) {
            int newCol = col + colOffset;
            newRow = row + direction;

            if (isValidPosition(newRow, newCol)) {
                ChessPosition newPos = new ChessPosition(newRow, newCol);
                ChessPiece targPiece = board.getPiece(newPos);

                if (targPiece != null && targPiece.getTeamColor() != this.pieceColor) {
                    addMoveWithPromotion(moves, myPosition, newPos, newRow, promotionRow);
                }
            }
        }
    }
    @Override
    public boolean equals(Object ob) {
        if (this == ob) return true;
        if (ob == null || getClass() != ob.getClass()) return false;

        ChessPiece that = (ChessPiece) ob;
        return pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }

    @Override
    public String toString() {
        return pieceColor + " " + type;
    }
}
