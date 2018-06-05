package quantity;
/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

// Understands a specific metric
public class Unit {
    public static final Unit TEASPOON = new Unit();
    public static final Unit TABLESPOON = new Unit(3, TEASPOON);
    public static final Unit OUNCE = new Unit();
    public static final Unit CUP = new Unit();
    public static final Unit PINT = new Unit();
    public static final Unit QUART = new Unit();
    public static final Unit GALLON = new Unit();

    private final double baseUnitRatio;

    private Unit() {
        baseUnitRatio = 1.0;
    }

    private Unit(double relativeRatio, Unit relativeUnit) {
        baseUnitRatio = relativeRatio * relativeUnit.baseUnitRatio;
    }
}
