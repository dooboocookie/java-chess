package chess.dao;

import chess.domain.side.Color;
import chess.dto.ChessGameDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChessGameDao {

    public List<ChessGameDto> selectAll() {
        String query = "SELECT id, turn " +
                "FROM board ";
        try (
                final var connection = ConnectionProvider.getConnection();
                final var preparedStatement = connection.prepareStatement(query);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ChessGameDto> results = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String turnName = resultSet.getString("turn");
                results.add(new ChessGameDto(id, turnName));
            }
            return results;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<ChessGameDto> select(final Long chessGameId) {
        String query = "SELECT id, turn " +
                "FROM board b " +
                "WHERE id = ? ";
        try (
                final var connection = ConnectionProvider.getConnection();
                final var preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setLong(1, chessGameId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                long id = resultSet.getLong("id");
                String turnName = resultSet.getString("turn");
                return Optional.of(new ChessGameDto(id, turnName));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long insertGame() {
        String query = "INSERT INTO board(turn) " +
                "VALUES(\"WHITE\"); ";
        try (
                final var connection = ConnectionProvider.getConnection();
                final var preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            return generatedKeys.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTurn(final Long id, final Color turn) {
        String query = "UPDATE board " +
                "SET turn = ? " +
                "WHERE id = ? ";
        try (
                final var connection = ConnectionProvider.getConnection();
                final var preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, turn.name());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
