package chess.controller;

import chess.dao.ChessGameDao;
import chess.domain.board.Square;
import chess.domain.chessgame.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.Role;
import chess.domain.side.Color;
import chess.view.OutputView;

import java.util.List;

public class MoveCommand implements Command {
    private static final int SOURCE_INDEX_OF_MOVE_COMMAND = 1;
    private static final int TARGET_INDEX_OF_MOVE_COMMAND = 2;

    @Override
    public ChessGame execute(final ChessGame chessGame, final List<String> input, final OutputView outputView) {
        validateMoveCommand(input);
        String sourceSquareInput = input.get(SOURCE_INDEX_OF_MOVE_COMMAND);
        Square sourceSquare = Square.from(sourceSquareInput);
        Piece sourcePiece = chessGame.getBoard().findPiece(sourceSquare.getFile(), sourceSquare.getRank());
        String targetSquareInput = input.get(TARGET_INDEX_OF_MOVE_COMMAND);
        Square targetSquare = Square.from(targetSquareInput);
        chessGame.move(sourceSquareInput, targetSquareInput);
        ChessGameDao dao = new ChessGameDao();
        dao.updatePiece(sourceSquare, Role.VACANT_PIECE.create(Color.NOTHING));
        dao.updatePiece(targetSquare, sourcePiece);
        dao.updateGame(chessGame);
        printMoveResult(chessGame, outputView);
        return chessGame;
    }

    private void printMoveResult(ChessGame chessGame, OutputView outputView) {
        outputView.printBoard(chessGame.findChessBoard());
        if (chessGame.isKindDied()) {
            Color winnerColor = chessGame.findWinner();
            outputView.printHigherScoreSide(winnerColor);
        }
    }

    private void validateMoveCommand(final List<String> input) {
        if (input.size() != 3) {
            throw new IllegalArgumentException("\"move 시작위치 도착위치\"의 형태로 입력해주세요.");
        }
    }
}
