package chess.service;

import chess.dao.ChessGameDao;
import chess.dao.PieceDao;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Square;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.GameState;
import chess.domain.piece.Piece;
import chess.domain.piece.Role;
import chess.domain.side.Color;
import chess.dto.ChessGameDto;
import chess.dto.PieceDto;

import java.util.*;

public class ChessGameService {
    private final ChessGameDao chessGameDao;
    private final PieceDao pieceDao;

    public ChessGameService(final ChessGameDao chessGameDao, final PieceDao pieceDao) {
        this.chessGameDao = chessGameDao;
        this.pieceDao = pieceDao;
    }

    public List<ChessGameDto> findAllChessGame() {
        return chessGameDao.selectAll();
    }

    public ChessGame loadChessGame(final Long id) {
        Optional<ChessGameDto> select = chessGameDao.select(id);
        ChessGameDto chessGameDto = select.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게임입니다."));
        List<PieceDto> pieces = pieceDao.findPieces(id);
        Map<Square, Piece> pieceBySquare = convertPieceDtoToPieceBySquare(pieces);
        Board board = BoardFactory.create(pieceBySquare, Color.findByName(chessGameDto.getTurn()));
        return new ChessGame(id, GameState.RUNNING, board);
    }

    public ChessGame startNewGame() {
        Long id = chessGameDao.insertGame();
        ChessGame startGame = ChessGame.createNew(id);
        Board board = startGame.getBoard();
        chessGameDao.updateTurn(id, board.getTurn());
        List<PieceDto> pieces = convertBoardToPiecesDto(board);
        for (PieceDto piece : pieces) {
            pieceDao.registerPiece(id, piece);
        }
        return ChessGame.createNew(id);
    }

    public void makePauseGame(final ChessGame chessGame) {
        chessGame.pause();
    }

    public void movePiece(final ChessGame chessGame, final Square sourceSquare, final Square targetSquare) {
        Long id = chessGame.getId();
        chessGame.move(sourceSquare, targetSquare);
        Board board = chessGame.getBoard();
        Piece vacantPiece = board.findPiece(sourceSquare);
        Piece movingPiece = board.findPiece(targetSquare);
        pieceDao.updatePiece(id, convertPieceToPieceDto(vacantPiece, sourceSquare));
        pieceDao.updatePiece(id, convertPieceToPieceDto(movingPiece, targetSquare));
        chessGameDao.updateTurn(id, board.getTurn());
    }

    public Map<Color, Double> calculateStatus(final ChessGame chessGame) {
        return chessGame.status();
    }

    private PieceDto convertPieceToPieceDto(final Piece piece, final Square square) {
        String file = square.getFile().name();
        int rank = square.getRank().getPosition();
        String role = piece.getRole().name();
        String color = piece.getColor().name();

        return new PieceDto(file, rank, role, color);
    }

    private Map<Square, Piece> convertPieceDtoToPieceBySquare(final List<PieceDto> pieces) {
        Map<Square, Piece> pieceBySquare = new HashMap<>();
        for (PieceDto pieceDto : pieces) {
            Square square = Square.from(pieceDto.getFile() + pieceDto.getRank());
            Role role = Role.findRoleByName(pieceDto.getRole());
            Color color = Color.findByName(pieceDto.getColor());
            Piece piece = role.create(color);
            pieceBySquare.put(square, piece);
        }
        return pieceBySquare;
    }

    private List<PieceDto> convertBoardToPiecesDto(final Board board) {
        List<PieceDto> pieces = new ArrayList<>();
        List<Square> squares = Square.getAllSquares();
        for (Square square : squares) {
            Piece piece = board.findPiece(square);
            PieceDto pieceDto = new PieceDto(
                    square.getFile().name(),
                    square.getRank().getPosition(),
                    piece.getRole().name(),
                    piece.getColor().name());
            pieces.add(pieceDto);
        }
        return pieces;
    }
}
