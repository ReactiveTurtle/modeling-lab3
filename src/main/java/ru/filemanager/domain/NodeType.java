package ru.filemanager.domain;

public enum NodeType {
    DIR(1),
    FILE(2);

    private final int value;

    NodeType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
