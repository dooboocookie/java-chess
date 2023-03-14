package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SideTest {
    @Test
    @DisplayName("진영 색을 입력 받아 생성한다.")
    void create() {
        // given
        Color color = Color.BLACK;

        // when
        Side side = new Side(color);

        // expected
        assertThat(side).isNotNull();
    }
}