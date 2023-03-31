package chess.controller;

import java.util.Arrays;

public enum CommandMapper {
    START("start", new StartCommand()),
    LIST("list", new ListCommand()),
    LOAD("load", new LoadCommand()),
    MOVE("move", new MoveCommand()),
    STATUS("status", new StatusCommand()),
    END("end", new EndCommand());

    private final String input;
    private final Command command;

    CommandMapper(final String input, final Command command) {
        this.input = input;
        this.command = command;
    }

    public static Command findCommand(String input) {
        return Arrays.stream(values())
                .filter(commandMapper -> commandMapper.input.equals(input))
                .map(commandMapper -> commandMapper.command)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력한 명령어가 없습니다."));
    }

    public Command getCommand() {
        return command;
    }
}
