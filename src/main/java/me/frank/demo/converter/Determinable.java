package me.frank.demo.converter;

/**
 * @author 王明哲
 */
public interface Determinable<T> {
    /**
     * 返回判断结果
     *
     * @param source 判断对象
     * @return 判断结果
     */
    boolean apply(T source);
}
