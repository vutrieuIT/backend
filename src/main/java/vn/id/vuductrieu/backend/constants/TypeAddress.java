package vn.id.vuductrieu.backend.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TypeAddress {
    HOME("home"),
    WORK("work");

    private final String type;
}
