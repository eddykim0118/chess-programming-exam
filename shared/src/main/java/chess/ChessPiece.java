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

    private void addKingMoves(ArrayList<ChessMove> moves, ChessBoard board, ChessPosition myPosition) {}

    private void addQueenMoves(ArrayList<ChessMove> moves, ChessBoard board, ChessPosition myPosition) {}

    private void addBishopMoves(ArrayList<ChessMove> moves, ChessBoard board, ChessPosition myPosition) {}

    private void addKnightMoves(ArrayList<ChessMove> moves, ChessBoard board, ChessPosition myPosition) {}

    private void addRookMoves(ArrayList<ChessMove> moves, ChessBoard board, ChessPosition myPosition) {}

    private void addPawnMoves(ArrayList<ChessMove> moves, ChessBoard board, ChessPosition myPosition) {}

}
