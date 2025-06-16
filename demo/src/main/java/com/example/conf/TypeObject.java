package com.example.conf;

public enum TypeObject {
    ROCK("🗿"),
    GRASS("💵"),
    TREE("\uD83C\uDF33"),
    HERBIVORE("🏃"),
    PREDATOR("👮"),
    EMPTY(" ");

    private final String symbol;

    TypeObject (String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol () {
        return symbol;
    }

    public static TypeObject fromSymbol(String symbol) {
        for (TypeObject type: TypeObject.values()) {
            if (type.getSymbol().equals(symbol)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Uncnow map symbol: " + symbol);
    }
}
