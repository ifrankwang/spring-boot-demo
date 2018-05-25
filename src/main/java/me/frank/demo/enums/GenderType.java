package me.frank.demo.enums;

/**
 * @author 王明哲
 */
public enum GenderType {
    /**
     * 男性
     */
    MALE("01"),
    /**
     * 女性
     */
    FEMALE("02"),
    /**
     * 其他
     */
    OTHERS("03");

    private String value;

    GenderType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
