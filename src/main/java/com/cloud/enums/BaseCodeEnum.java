package com.cloud.enums;

/**
 * @ClassName BaseCodeEnums
 * @Description TODO
 * @Author kongxiangjin
 * @Date 2020/1/8 19:15
 * @Version 1.0
 **/
public interface BaseCodeEnum {

    /**
     * 获取Code值
     *
     * @return Code值
     */
    short getCode();

    String getStatus();

    /**
     * 将数值转换为枚举类
     *
     * @param clazz 枚举类型
     * @param code  值
     * @param <E>   枚举
     * @return 枚举
     */
    public static <E extends Enum<?> & BaseCodeEnum> E codeOf(Class<E> clazz, short code) {
        final E[] enumConstants = clazz.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getCode() == code) {
                return e;
            }
        }

        final String msg = String.format("Cannot convert %d to %s by code value", code, clazz.getSimpleName());
        throw new IllegalArgumentException(msg);
    }

    /**
     * 将状态量转换为枚举类
     *
     * @param clazz  枚举类型
     * @param status 值
     * @param <E>    枚举
     * @return 枚举
     */
    public static <E extends Enum<?> & BaseCodeEnum> E codeOf(Class<E> clazz, String status) {
        final E[] enumConstants = clazz.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getStatus().equals(status)) {
                return e;
            }
        }

        final String msg = String.format("Cannot convert %s to %s by code status", status, clazz.getSimpleName());
        throw new IllegalArgumentException(msg);
    }
}

