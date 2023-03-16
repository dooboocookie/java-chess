package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Role;
import chess.domain.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Direction.NORTH;
import static chess.domain.piece.Direction.SOUTH;
import static org.assertj.core.api.Assertions.assertThat;

class InitialPawnTest {
    @Test
    @DisplayName("이동할 수 있는지 확인한다.")
    void isMovable() {
        // when
        InitialPawn blackInitialPawn = new InitialPawn(Side.from(Color.BLACK), Role.INITIAL_PAWN);
        InitialPawn whiteInitialPawn = new InitialPawn(Side.from(Color.WHITE), Role.INITIAL_PAWN);

        // expected
        assertThat(blackInitialPawn.canMove(SOUTH, 2)).isTrue();
        assertThat(whiteInitialPawn.canMove(NORTH, 2)).isTrue();
    }

    @Test
    @DisplayName("이동할 수 없는지 확인한다.")
    void canNotMove() {
        // when
        InitialPawn blackInitialPawn = new InitialPawn(Side.from(Color.BLACK), Role.INITIAL_PAWN);
        InitialPawn whiteInitialPawn = new InitialPawn(Side.from(Color.WHITE), Role.INITIAL_PAWN);

        // expected
        assertThat(blackInitialPawn.canMove(NORTH, 2)).isFalse();
        assertThat(whiteInitialPawn.canMove(SOUTH, 2)).isFalse();
    }
}