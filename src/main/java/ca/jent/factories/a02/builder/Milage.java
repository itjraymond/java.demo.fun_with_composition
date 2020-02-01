package ca.jent.factories.a02.builder;

public class Milage {
    private Double odometerValue;
    private UNIT unit;

    Milage() {
        this.odometerValue = 0D;
        this.unit = UNIT.UNKNOWN;
    }

    Milage(Double odometerValue, UNIT unit) {
        this.odometerValue = odometerValue;
        this.unit = unit;
    }

    public Double getOdometerValue() {
        return odometerValue;
    }

    void setOdometerValue(Double odometerValue) {
        this.odometerValue = odometerValue;
    }

    public UNIT getUnit() {
        return unit;
    }

    void setUnit(UNIT unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Milage{" + "odometerValue=" + odometerValue + ", unit=" + unit.name() + '}';
    }

    public enum UNIT {
        MILE("Mile"),
        KILOMETER("Kilometer"),
        UNKNOWN("Unknown unit");

        private String value;

        UNIT(String value) {
            this.value = value;
        }
    }
}
