package pl.gielerak.domain.date.model;

public enum DateFormat {
    ISO8601("yyyy-MM-dd'T'HH:mm:ss'Z'"),
    JSON("yyyy-MM-dd'T'HH:mm:ss");

    private final String format;

    DateFormat(String format) {
        this.format = format;
    }

    public String format() {
        return format;
    }
}
