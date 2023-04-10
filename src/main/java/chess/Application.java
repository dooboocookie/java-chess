package chess;

import chess.controller.ChessController;
import chess.dao.ChessGameDao;
import chess.dao.PieceDao;
import chess.service.ChessGameService;

public class Application {
    public static void main(String[] args) {
        final ChessGameDao chessGameDao = new ChessGameDao();
        final PieceDao pieceDao = new PieceDao();
        final ChessGameService chessGameService = new ChessGameService(chessGameDao, pieceDao);
        final ChessController controller = new ChessController(chessGameService);
        controller.run();
    }
}
