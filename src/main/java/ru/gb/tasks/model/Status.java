package ru.gb.tasks.model;

public enum Status {
    NOT_STARTED("Не начата"),
    IN_PROCESS("В процессе"),
    DONE("Завершена");

    private String descr;

    Status(String descr) {
        this.descr = descr;
    }

    public String getDescription() {
        return descr;
    }
}
