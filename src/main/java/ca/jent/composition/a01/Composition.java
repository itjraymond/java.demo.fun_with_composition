package ca.jent.composition.a01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * Taken from "Functional Programming in Java" by Pierre-Yves Saumont.
 * Purpose is solely for learning advance capabilities of Java.
 *
 * Note: In my opinion, Java is not great at Functional Programming, but the capabilities (limited?) are there.
 *       For FP, prefer Kotlin over Java (or even Groovy).
 */

public class Composition {

    // Polymorphic function composer
    static <T,U,V> Function<Function<U,V>, Function<Function<T,U>, Function<T,V>>> higherCompose() {
        return (Function<U,V> f) -> (Function<T,U> g) -> (T x) -> f.apply(g.apply(x));
    }

    // For my personal taste, I prefer to order the function parameter as T -> U and then U -> V
    // which return the final function type as: T -> V
    static <T,U,V> Function<Function<T,U>, Function<Function<U,V>, Function<T,V>>> higherComposeOrd() {
        return (Function<T,U> f) -> (Function<U,V> g) -> (T x) -> g.apply(f.apply(x));
    }

    // Here is a nice example of composition found in the "Functional Programming in Java" book.
    // Having a list of prices, we want to apply a 9% tax and a fix shipping cost. Of course, we
    // could use Stream API without function composition to get the same result but that wouldn't be fun.
    // First, the function to apply the tax on each element.
    static Function<Double,Double> applyTax = price -> price * 1.09;
    // The function to apply fix shipping cost
    static Function<Double,Double> applyShipping = price -> price + 7.99;
    // Compose these two functions such that when iterating over the price list, only one operation is applied.
    static Function<Double,Double> applyTotalCharge = applyTax.andThen(applyShipping);

    public static void main(String[] args) {
        System.out.println(
            Composition.<Integer, Double, String>higherCompose()
                    .apply( (Double x) -> "'" + x.toString() + "' is the string value" )
                    .apply( (Integer y) -> y * 10.0 )
                    .apply(30)
        );
        // '300.0' is the string value

        System.out.println(
            "My double is: " +
            Composition.<String,Integer,Double>higherComposeOrd()
                .apply( s -> Integer.valueOf(s) )
                .apply( i -> i + 2.5 )
                .apply("10")
        );
        // My double is: 12.5

        // Usage for applyTotalCharge
        List<Double> prices = Arrays.asList(8.79, 15.49, 12.50, 21.69);
        prices.stream().map(applyTotalCharge).forEach(System.out::println);
        // [17.5711, 24.8741, 21.61500000002, 31.6321]
    }
}
