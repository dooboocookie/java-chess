package chess.controller.command;

import chess.domain.board.Square;
import chess.domain.chessgame.ChessGame;
import chess.domain.side.Color;
import chess.service.ChessGameService;
import chess.view.OutputView;

import java.util.List;

public class MoveCommand implements Command {
    private static final int SOURCE_INDEX_OF_MOVE_COMMAND = 1;
    private static final int TARGET_INDEX_OF_MOVE_COMMAND = 2;

    @Override
    public ChessGame execute(final ChessGameService service, final ChessGame chessGame, final List<String> input) {
        validateMoveCommand(input);
        Square sourceSquare = Square.from(input.get(SOURCE_INDEX_OF_MOVE_COMMAND));
        Square targetSquare = Square.from(input.get(TARGET_INDEX_OF_MOVE_COMMAND));
        service.movePiece(chessGame, sourceSquare, targetSquare);
        printMoveResult(chessGame);
        return chessGame;
    }

    private void printMoveResult(final ChessGame chessGame) {
        OutputView.printBoard(chessGame.findChessBoard());
        if (chessGame.isKindDied()) {
            Color winnerColor = chessGame.findWinner();
            OutputView.printHigherScoreSide(winnerColor);
        }
    }

    private void validateMoveCommand(final List<String> input) {
        if (input.size() != 3) {
            throw new IllegalArgumentException("\"move 시작위치 도착위치\"의 형태로 입력해주세요.");
        }
    }
}
