package by.Ahmed.entity;

import java.util.Arrays;
import java.util.Optional;

public enum CheckStatus {
    CHECKED,
    UNCHECKED;

    public static Optional<CheckStatus> find(String checkStatus) {
        return Arrays.stream(values())
                .filter(it -> it.name().equals(checkStatus))
                .findFirst();
    }
}
