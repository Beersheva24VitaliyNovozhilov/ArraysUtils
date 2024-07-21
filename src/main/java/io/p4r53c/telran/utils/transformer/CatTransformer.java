package io.p4r53c.telran.utils.transformer;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * A functional interface that defines a transformation operation on a single
 * input object and returns a result object.
 * 
 * @author p4r53c
 * @since Self work 1
 *
 * @param <T> the type of the input object to be transformed
 * @param <R> the type of the result object after the transformation
 */
@FunctionalInterface
public interface CatTransformer<T, R> {

    Logger logger = Logger.getLogger(CatTransformer.class.getName());

    /**
     * Transforms the input object and returns the result object.
     *
     * @param t the input object to be transformed
     * @return the result object after the transformation
     */
    R transform(T t);

    /**
     * Transforms the input object in a verbose mode and returns the result object.
     *
     * @param t the input object to be transformed
     * @return the result object after the transformation
     */
    default R verboseTransform(T t) {
        R result = transform(t);

        logger.info("Transforming " + t.toString() + " to " + result.toString());

        return result;
    }

    /**
     * Transforms a list of input objects and returns a list of result objects.
     *
     * @param list the list of input objects to be transformed
     * @return the list of result objects after the transformation
     */
    default List<R> transformAll(List<T> list) {
        List<R> result = new ArrayList<>();

        for (T t : list) {
            result.add(verboseTransform(t));
        }

        return result;
    }

    /**
     * Combines two {@code CatTransformer} objects into a single
     * {@code CatTransformer} that applies the first transformation and then
     * applies the second transformation.
     *
     * @param <V>    the type of the result object after the second transformation
     * @param first  the first {@code CatTransformer} to be applied
     * @param second the second {@code CatTransformer} to be applied
     * @return the combined {@code CatTransformer} that applies the first
     *         transformation and then applies the second transformation
     */
    static <T, R, V> CatTransformer<T, V> transformChain(CatTransformer<T, R> first, CatTransformer<R, V> second) {
        return t -> second.verboseTransform(first.verboseTransform(t));
    }

}
