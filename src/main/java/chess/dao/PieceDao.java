package chess.dao;

import chess.dto.PieceDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {

    public List<PieceDto> findPieces(final Long boardId) {
        String query = "SELECT file, ranks, role, color " +
                "FROM board_piece " +
                "WHERE board_id = ? ";
        try (
                final var connection = ConnectionProvider.getConnection();
                final var preparedStatementPiece = connection.prepareStatement(query)
        ) {
            preparedStatementPiece.setLong(1, boardId);
            ResultSet resultSet = preparedStatementPiece.executeQuery();
            List<PieceDto> pieces = new ArrayList<>();
            while (resultSet.next()) {
                String file = resultSet.getString(1);
                int rank = resultSet.getInt(2);
                String roleName = resultSet.getString(3);
                String colorName = resultSet.getString(4);
                pieces.add(new PieceDto(file, rank, roleName, colorName));
            }
            return pieces;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerPiece(final Long boardId, final PieceDto piece) {
        String query = "INSERT INTO board_piece(board_id, file, ranks, role, color) " +
                "VALUES (?, ?, ?, ?, ?); ";
        try (
                final var connection = ConnectionProvider.getConnection();
                final var preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setLong(1, boardId);
            preparedStatement.setString(2, piece.getFile());
            preparedStatement.setInt(3, piece.getRank());
            preparedStatement.setString(4, piece.getRole());
            preparedStatement.setString(5, piece.getColor());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePiece(final Long boardId, final PieceDto piece) {
        String query = "UPDATE board_piece " +
                "SET role = ?, color = ? " +
                "WHERE board_id = ? AND file = ? AND ranks = ? ";
        try (
                final var connection = ConnectionProvider.getConnection();
                final var preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, piece.getRole());
            preparedStatement.setString(2, piece.getColor());
            preparedStatement.setLong(3, boardId);
            preparedStatement.setString(4, piece.getFile());
            preparedStatement.setInt(5, piece.getRank());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
