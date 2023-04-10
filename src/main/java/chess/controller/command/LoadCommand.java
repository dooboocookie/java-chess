package chess.controller.command;

import chess.domain.chessgame.ChessGame;
import chess.service.ChessGameService;
import chess.view.OutputView;

import java.util.List;

public class LoadCommand implements Command {
    public static final int COMMAND_GAME_ID_INDEX = 1;

    @Override
    public ChessGame execute(final ChessGameService service, final ChessGame chessGame, final List<String> input) {
        Long id = Long.parseLong(input.get(COMMAND_GAME_ID_INDEX));
        ChessGame loadGame = service.loadChessGame(id);
        OutputView.printBoard(loadGame.findChessBoard());
        return loadGame;
    }
}
