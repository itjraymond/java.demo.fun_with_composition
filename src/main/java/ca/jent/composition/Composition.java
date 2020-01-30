package ca.jent.composition;

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
    }
}
