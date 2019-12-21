package model;

public enum FeatureType {
    SIMPLE("+"),
    OR("|"),
    XOR("^");

    private final String delimiter;

    FeatureType(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiterAsRegex() {
        return "\\" + this.delimiter;
    }

    public static FeatureType typeOf(String delimiter) {
        for (FeatureType featureType : FeatureType.values())
            if (delimiter.equals(featureType.delimiter))
                return featureType;
        return SIMPLE; // default, to get rid of NPE warnings
    }
}
