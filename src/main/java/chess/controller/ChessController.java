package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandMapper;
import chess.domain.chessgame.ChessGame;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {
    private static final int ORDER_COMMAND = 0;

    private final ChessGameService chessGameService;

    public ChessController(final ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }

    public void run() {
        OutputView.printStartMessage();
        ChessGame chessGame = ChessGame.createEmptyGame();
        while (chessGame.isContinue()) {
            chessGame = repeat(chessGame);
        }
    }

    private ChessGame repeat(final ChessGame chessGame) {
        try {
            List<String> inputCommands = InputView.readCommand();
            String inputCommand = inputCommands.get(ORDER_COMMAND);
            Command command = CommandMapper.findCommand(inputCommand);
            return command.execute(chessGameService, chessGame, inputCommands);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return repeat(chessGame);
        }
    }
}
