package chess.dto;

public class ChessGameDto {
    private final Long id;
    private final String turn;

    public ChessGameDto(Long id, String turn) {
        this.id = id;
        this.turn = turn;
    }

    public Long getId() {
        return id;
    }

    public String getTurn() {
        return turn;
    }
}
