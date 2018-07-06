package me.frank.demo.util;

/**
 * 随机数值生成器
 *
 * @author 王明哲
 */
public class RandomNumberGenerator {

    public static String getRandomNumber() {
        return getRandomNumber(6);
    }

    public static String getRandomNumber(int length) {
        final long power = Math.round(Math.pow(10, length - 1));
        return Math.round((Math.random() * power + power)) + "";
    }
}
