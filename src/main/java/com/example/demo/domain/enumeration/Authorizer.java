package com.example.demo.domain.enumeration;

public enum Authorizer {
    AM,
    BA,
    GO,
    MG,
    MS,
    MT,
    PE,
    PR,
    RS,
    SP,
    SVAN,
    SVRS,
    SVC_AN,
    SVC_RS;

    public static Authorizer fromString(String text) {
        if (text != null) {
            for (Authorizer authorizer : Authorizer.values()) {
                if (text.equalsIgnoreCase(authorizer.name().replace("_", "-"))) {
                    return authorizer;
                }
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found in Authorizer enum");
    }
}
