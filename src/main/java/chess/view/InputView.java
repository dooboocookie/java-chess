package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    public static final String DELIMITER = " ";

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> readCommand() {
        String input = scanner.nextLine();
        validateNull(input);
        return splitInput(input);
    }

    private static List<String> splitInput(String input) {
        String[] split = input.split(DELIMITER);
        return Arrays.stream(split).collect(Collectors.toList());
    }


    private static void validateNull(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("값을 입력해 주세요.");
        }
    }
}
