package chess.controller.command;

import chess.domain.chessgame.ChessGame;
import chess.domain.side.Color;
import chess.service.ChessGameService;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;

public class StatusCommand implements Command {

    @Override
    public ChessGame execute(final ChessGameService service, final ChessGame chessGame, final List<String> input) {
        Map<Color, Double> status = service.calculateStatus(chessGame);
        OutputView.printStatus(status);
        return chessGame;
    }

}
