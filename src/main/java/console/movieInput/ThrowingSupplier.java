package console.movieInput;

@FunctionalInterface
public interface ThrowingSupplier<T> {
    T get() throws Exception;
}
