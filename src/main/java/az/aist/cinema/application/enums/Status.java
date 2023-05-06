package az.aist.cinema.application.enums;

public enum Status {
    ACTIVE(1),
    DEACTIVE(0);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    public static Status fromValue(Integer value) {
        for (Status status : Status.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }

    public int getValue() {
        return value;
    }
}
