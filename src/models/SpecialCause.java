package models;

import java.util.Arrays;

public enum SpecialCause {
    None("Ninguna"),
    TechnicalIssues("Fallos Técnicos"),
    LackOfFuel("Escasez de Combustible"),
    Other("Otro");

    private final String label;
    private SpecialCause(String label) {
        this.label = label;
    }

    public String label() { return label; }

    public static String[] names() {
        return Arrays.stream(SpecialCause.values())
            .map(SpecialCause::label)
            .toArray(String[]::new);
    }
}
