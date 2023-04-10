package chess.controller.command;

import chess.domain.chessgame.ChessGame;
import chess.service.ChessGameService;

import java.util.List;

public interface Command {
    ChessGame execute(final ChessGameService service, final ChessGame chessGame, final List<String> input);
}
