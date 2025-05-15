package core;

public enum ApplicationStatus {
    RUNNING(0),
    EXIT(1),
    ERROR(2);

    private final int code;

    ApplicationStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
