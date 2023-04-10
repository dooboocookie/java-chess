package chess.controller.command;

import chess.domain.chessgame.ChessGame;
import chess.service.ChessGameService;
import chess.view.OutputView;

import java.util.List;

public class StartCommand implements Command {

    @Override
    public ChessGame execute(final ChessGameService service, final ChessGame chessGame, final List<String> input) {
        final ChessGame startGame = service.startNewGame();
        OutputView.printBoard(startGame.findChessBoard());
        return startGame;
    }
}
