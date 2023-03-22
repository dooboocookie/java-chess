package chess.domain.piece;

import chess.domain.side.Side;

public final class King extends Piece {
    private static final int MAX_MOVE_DISTANCE = 1;

    public King(final Side side, final Role role) {
        super(side, role);
    }

    @Override
    public boolean canMove(final Direction direction, final int distance) {
        return direction.isStraightOrDiagonal() && distance == MAX_MOVE_DISTANCE;
    }

    @Override
    public boolean canAttack(final Direction direction, final int distance, final Piece target) {
        return canMove(direction, distance) && isOpponentSide(target);
    }

    @Override
    public Piece update() {
        return this;
    }
}
