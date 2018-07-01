package me.frank.demo.converter;

/**
 * @author 王明哲
 */
public interface Convertible {
    /**
     * 转换成dto
     *
     * @param targetClass Dto.class对象
     * @param <T>         Dto类型
     * @return dto对象
     */
    <T> T to(Class<T> targetClass);
}
