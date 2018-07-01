package me.frank.demo.converter;

/**
 * @author 王明哲
 */
public interface Calculable<T, N extends Number> {
    /**
     * 计算
     *
     * @param source 计算对象
     * @return 计算值
     */
    N apply(T source);
}
