package models;

import java.util.HashMap;
import java.util.Arrays;

public enum SpecialCause {
    None("Ninguno"),
    TechnicalIssues("Fallos Técnicos"),
    LackOfFuel("Escasez de Combustible"),
    Other("Otro");

    private static final HashMap<String, SpecialCause> map;
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

    public static SpecialCause from(String label) {
        return map.get(label);
    }

    static {
        map = new HashMap<>();
        for (var value : values())
            map.put(value.label(), value);
        //values().forEach(value -> map.put(value.label(), value));
    }
}
