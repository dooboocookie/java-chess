package chess.dto;

public class PieceDto {
    private final String file;
    private final int rank;
    private final String role;
    private final String color;

    public PieceDto(final String file, final int rank, final String role, final String color) {
        this.file = file;
        this.rank = rank;
        this.role = role;
        this.color = color;
    }

    public String getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    public String getRole() {
        return role;
    }

    public String getColor() {
        return color;
    }
}
