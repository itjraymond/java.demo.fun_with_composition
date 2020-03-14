package ca.jent.func;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * fun with recursion on the heap and using class within a lambda.
 */
public class Add {

    public static Function<Integer, Function<Integer, Integer>> add = x -> y -> {
        class AddHelper {
            Function<Integer, Function<Integer, TailCall<Integer>>> addHelper = a -> b ->
                    b == 0
                    ? TailCall.ret(a)
                    : TailCall.sus( () -> this.addHelper.apply(a+1).apply(b-1) );
        }
        return new AddHelper().addHelper.apply(x).apply(y).eval();
    };

    public static void main(String[] args) {
        System.out.println(add.apply(298788878).apply(139867565));
    }
}

abstract class TailCall<T> {
    public abstract TailCall<T> resume();
    public abstract T eval();
    public abstract boolean isSuspend();

    private TailCall(){}

    private static class Return<T> extends TailCall<T> {
        private final T t;
        private Return(T t) {
            this.t = t;
        }
        @Override
        public TailCall<T> resume() {
            throw new IllegalStateException("Return has no resume");
        }

        @Override
        public T eval() {
            return t;
        }

        @Override
        public boolean isSuspend() {
            return false;
        }
    }

    private static class Suspend<T> extends TailCall<T> {

        private final Supplier<TailCall<T>> resume;
        private Suspend(Supplier<TailCall<T>> resume) {
            this.resume = resume;
        }

        @Override
        public TailCall<T> resume() {
            return resume.get();
        }

        @Override
        public T eval() {
            TailCall<T> tc = this;
            while (tc.isSuspend()) {
                tc = tc.resume();
            }
            return tc.eval();
        }

        @Override
        public boolean isSuspend() {
            return true;
        }
    }

    public static <T> Return<T> ret(T t) {
        return new Return<>(t);
    }

    public static <T> Suspend<T> sus(Supplier<TailCall<T>> supplier) {
        return new Suspend<>(supplier);
    }
}


