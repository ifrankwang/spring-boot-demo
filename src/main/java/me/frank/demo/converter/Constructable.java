package me.frank.demo.converter;

/**
 * @author 王明哲
 */
public interface Constructable<T> {
    T apply(long id);
}
