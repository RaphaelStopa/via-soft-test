package com.example.demo.domain.enumeration;

public enum Status {

    AVAILABLE("Available", "bola_verde_P.png"),
    UNSTABLE("Unstable", "bola_amarela_P.png"),
    UNAVAILABLE("Unavailable", "bola_vermelha_P.png"),
    UNKNOWN("Unknown", "");

    private final String description;
    private final String imageUrl;

    Status(String description, String imageUrl) {
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public static Status fromImageUrl(String imageUrl) {
        for (Status status : values()) {
            if (status.imageUrl.equals(imageUrl)) {
                return status;
            }
        }
        return UNKNOWN;
    }
}
