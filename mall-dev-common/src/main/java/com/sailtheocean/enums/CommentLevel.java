package com.sailtheocean.enums;

/**
 * @Desc: Product comment level enum
 */
public enum CommentLevel {
    GOOD(1, "Good"),
    NORMAL(2, "Normal"),
    BAD(3, "Bad");

    public final Integer type;
    public final String value;

    CommentLevel(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
