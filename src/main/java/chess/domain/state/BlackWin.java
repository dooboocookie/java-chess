package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Result;

public class BlackWin extends Finished {

    public BlackWin(ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    public Result winner() {
        return Result.BLACK;
    }
}