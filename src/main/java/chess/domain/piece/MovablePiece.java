package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Side;

public class MovablePiece extends Piece {
    private final Side side;

    public MovablePiece(final Side side) {
        this.side = side;
    }

    public boolean isSameSide(final MovablePiece targetPiece) {
        return side.equals(targetPiece.side);
    }

    public Color getColor() {
        return side.getColor();
    }
}
