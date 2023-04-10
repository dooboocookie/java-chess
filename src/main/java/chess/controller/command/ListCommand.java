package chess.controller.command;

import chess.dao.ChessGameDao;
import chess.domain.chessgame.ChessGame;
import chess.dto.ChessGameDto;
import chess.service.ChessGameService;
import chess.view.OutputView;

import java.util.List;

public class ListCommand implements Command {

    @Override
    public ChessGame execute(final ChessGameService service, final ChessGame chessGame, final List<String> input) {
        List<ChessGameDto> allChessGame = service.findAllChessGame();
        OutputView.printAllList(allChessGame);
        return chessGame;
    }
}
